from crypt import methods

from flask import Flask
import docker
from pymongo_inmemory import MongoClient

import uuid

client = docker.from_env()


db = client['codercm']
collection = db['containers']

app = Flask(__name__)


@app.route('/playground', methods=['POST'])
def start_playground_instance():
    volume_id = str(uuid.uuid4())
    volume = client.volumes.get(volume_id)
    if volume is None:
        volume = client.volumes.create(name=volume_id)

    ports = {
        "8443": "8443",
        "3000": "3000",
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

    container = client.containers.run('codercm/playground', detach=True, ports=ports, volumes=volumes)
    return {
        "containerId": container.id,
        "volumeId": volume.id,
        "instanceUrl": "http://localhost:8443"
    }


if __name__ == '__main__':
    app.run()
