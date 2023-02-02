#!/bin/bash
# Start the docker container for vscode-server
# And then renturn the address, port and container Id
# Usage: ./start-docker.sh
docker run -d \
  --name=code-server \
  -e PUID=1000 \
  -e PGID=1000 \
  -e TZ=Europe/London \
  -e PASSWORD= `#optional` \
  -e HASHED_PASSWORD= `#optional` \
  -e SUDO_PASSWORD=root `#optional` \
  -e SUDO_PASSWORD_HASH= `#optional` \
  -e DEFAULT_WORKSPACE=/config/workspace `#optional` \
  -p 8443:8443 \
  -v config:/config \
  --restart unless-stopped \
  lscr.io/linuxserver/code-server:latest

echo "Container ID: $(docker ps -aqf "name=code-server")"
echo "Address: $(curl -s ifconfig.me)"
echo "Port: 8443"