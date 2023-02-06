ARG BASE=amd64/debian:bullseye
FROM $BASE
RUN rm /bin/sh && ln -s /bin/bash /bin/sh

LABEL maintainer="dtn1999 <"

# Environment variables
RUN \
  echo "**** install runtime dependencies ****" \
  && apt-get update \
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
    systemd \
    sudo \
    vim.tiny \
    lsb-release \
  && rm -rf /var/lib/apt/lists/* \
  echo "**** install code-server ****" && \
    curl -fsSL https://code-server.dev/install.sh | sh -s -- --dry-run && \
    curl -fsSL https://code-server.dev/install.sh | sh \
  && echo "*** clean up ****" &&\
    apt-get clean && \
    rm -rf \
      /tmp/* \
      /var/lib/apt/lists/* \
      /var/tmp/*

# https://wiki.debian.org/Locale#Manually
RUN sed -i "s/# en_US.UTF-8/en_US.UTF-8/" /etc/locale.gen \
  && locale-gen
ENV LANG=en_US.UTF-8

RUN adduser --gecos '' --disabled-password codercm \
  && echo "codercm ALL=(ALL) NOPASSWD:ALL" >> /etc/sudoers.d/nopasswd

WORKDIR /home/codercm
# we don't need to authenticate to the coder-server instance since the authentication is 
# handled somewhere else
COPY ./config  /home/codercm/config
# RUN sed -i.bak 's/auth: password/auth: none/' /home/codercm/config/code-server/config.yaml

# Install nodejs using nvm
RUN curl -o- https://raw.githubusercontent.com/nvm-sh/nvm/v0.38.0/install.sh | bash
RUN export NVM_DIR="$HOME/.nvm" \
    && [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"\
    && [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"\
    && nvm install node

# Install yarn 
# TODO: this is not wworking
RUN source ~/.bashrc \
    && npm install -g yarn

RUN echo "**** install oh-my-bash ****" &&\
    bash -c "$(curl -fsSL https://raw.githubusercontent.com/ohmybash/oh-my-bash/master/tools/install.sh)" 

COPY ./scripts/entrypoint.sh /usr/bin/entrypoint.sh
# Allow users to have scripts run on container startup to prepare workspace.
# https://github.com/coder/code-server/issues/5177
ENV ENTRYPOINTD=${HOME}/entrypoint.d

EXPOSE 8080
# This will be used to connect to client applications running in the container
EXPOSE 3000-3050
USER 1000
ENV USER=codercm

ENTRYPOINT ["bash", "/usr/bin/entrypoint.sh", "--bind-addr", "0.0.0.0:8080", "--auth", "none", "."]