#!/bin/bash
# Start the docker container for vscode-server
# And then renturn the address, port and container Id
# Usage: ./start-docker.sh
docker run \
  --name=code-server \
  -e PUID=1000 \
  -e PGID=1000 \
  -e TZ=Europe/London \
  -e PASSWORD=  \
  -e HASHED_PASSWORD= \
  -e SUDO_PASSWORD=  \
  -e SUDO_PASSWORD_HASH= \
  -e DEFAULT_WORKSPACE=/config/workspace \
  -p 8443:8443 \
  -p 3000-3003:3000-3003 \
  -p 9222:9222 \
  --restart unless-stopped \
  codercm/playground