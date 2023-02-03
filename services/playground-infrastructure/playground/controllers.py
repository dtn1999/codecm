from app import app
from config.docker import docker_client


@app.route('/api/v1/playground')
def get_all_playground_instances():
    try:
        result = []
        for container in docker_client.containers.list():
            if "codercm/playground:latest" in container.image.tags:
                pass
                # result.append({
                #     "containerId": container.id,
                #     "volumeId": db[container.id].volumeId,
                #     "instanceUrl": f"http://localhost:{db[container.id].port}",
                #     "clientPort": db[container.id].clientPort,
                #     "status": container.status
                # })
        return result, 200
    except Exception as e:
        print(e)
        return {
            "error": "Internal Server Error",
            "message": "Could not start the playground instance"
        }, 500
