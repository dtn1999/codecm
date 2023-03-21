# syntax=docker/dockerfile:experimental
# Inspired by https://github.com/coder/code-server
ARG BASE=debian:11
FROM $BASE

RUN apt-get update \
  && apt-get install -y \
    curl \
    dumb-init \
    zsh \
    htop \
    locales \
    man \
    nano \
    git \
    git-lfs \
    procps \
    openssh-client \
    sudo \
    vim.tiny \
    lsb-release \
  && git lfs install \
  && rm -rf /var/lib/apt/lists/*

# https://wiki.debian.org/Locale#Manually
RUN sed -i "s/# en_US.UTF-8/en_US.UTF-8/" /etc/locale.gen \
  && locale-gen
ENV LANG=en_US.UTF-8

RUN adduser --gecos '' --disabled-password coder \
  && echo "coder ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers.d/nopasswd

RUN ARCH="$(dpkg --print-architecture)" \
  && curl -fsSL "https://github.com/boxboat/fixuid/releases/download/v0.5/fixuid-0.5-linux-$ARCH.tar.gz" | tar -C /usr/local/bin -xzf - \
  && chown root:root /usr/local/bin/fixuid \
  && chmod 4755 /usr/local/bin/fixuid \
  && mkdir -p /etc/fixuid \
  && printf "user: coder\ngroup: coder\n" > /etc/fixuid/config.yml

RUN curl -fsSL https://code-server.dev/install.sh | sh

ENV USER=coder
USER coder
# install nvm
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash
ENV NVM_DIR=/home/coder/.nvm
ENV NODE_VERSION=18.14.2
RUN . "$NVM_DIR/nvm.sh" && nvm install node
# RUN . "$NVM_DIR/nvm.sh" && nvm use v${NODE_VERSION}
# RUN . "$NVM_DIR/nvm.sh" && nvm alias default v${NODE_VERSION}
# ENV PATH="/home/coder/.nvm/versions/node/v${NODE_VERSION}/bin/:${PATH}"
# install yarn
# RUN npm install --global yarn

# install oh-my-zsh
RUN sh -c "$(curl -fsSL https://raw.github.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

RUN mkdir -p /home/coder/.config/code-server
COPY ./config/config.yaml /home/coder/.config/code-server/config.yaml

EXPOSE 8080
EXPOSE 3000
USER 1000
WORKDIR /home/coder/workspace
RUN mkdir -p /home/coder/.local/share/code-server/User
COPY ./vscode/tasks.json  /home/coder/.local/share/code-server/User/tasks.json
COPY ./vscode/settings.json  /home/coder/.local/share/code-server/Machine/settings.json
ENTRYPOINT ["/usr/bin/code-server","--app-name", "codergen","--auth", "none", "--disable-workspace-trust","--disable-getting-started-override","--proxy-domain","localhost.com","-an","we.oportunity","--bind-addr", "0.0.0.0:8080", "."]