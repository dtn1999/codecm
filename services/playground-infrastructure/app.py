import time
from crypt import methods

from docker.errors import NotFound
from flask import Flask
import docker
import requests
import random
from dotenv import dotenv_values
from pymongo import MongoClient

import uuid

from config.docker import docker_client

app = Flask(__name__)

db = {}
vscode_server_ports = set()
client_app_ports = set()


def is_playground_instance_live(url):
    try:
        x = requests.get(url)
        return True
    except Exception as e:
        print(e)
        return False


@app.route('/api/v1/playground', methods=['POST'])
def start_playground_instance():
    volume_id = str(uuid.uuid4())
    try:
        volume = docker_client.volumes.get(volume_id);
    except NotFound:
        volume = docker_client.volumes.create(name=volume_id)
    except Exception as e:
        print(e)
        return {
            "error": "Internal Server Error",
            "message": "Could not create volume for the playground instance"
        }, 500

    # make sure to assign each instance a unique port
    port = random.randint(8400, 8500)
    client_port = random.randint(3000, 3100)

    while port in vscode_server_ports:
        port = random.randint(8400, 8500)

    while client_port in client_app_ports:
        client_port = random.randint(3000, 3100)

    ports = {
        "8443": port,
        "3000": client_port,
    }

    volumes = {volume.name: {
        "bind": "/config/workspace",
        "mode": "rw"
    }}
    environment = {
        "PUID": 1000,
        "PGID": 1000,
        "TZ": "Europe / London",
        "PASSWORD": "",  # optional
        "HASHED_PASSWORD": "",  # optional
        "SUDO_PASSWORD": "root",  # optional
        "SUDO_PASSWORD_HASH": ""  # optional
    }

    try:
        container = docker_client.containers.run('codercm/playground',
                                                 detach=True,
                                                 ports=ports,
                                                 volumes=volumes,
                                                 environment=environment)

        while not is_playground_instance_live(f"http://localhost:{port}"):
            time.sleep(2)
            pass

        # map volume to container
        db[container.id] = {
            "volumeId": volume.id,
            "port": port,
            "clientPort": client_port
        }

        return {
            "containerId": container.id,
            "volumeId": volume.id,
            "instanceUrl": f"http://localhost:{port}"
        }, 200

    except Exception as e:
        print(e)
        return {
            "error": "Internal Server Error",
            "message": "Could not start the playground instance"
        }, 500





if __name__ == '__main__':
    app.run()
