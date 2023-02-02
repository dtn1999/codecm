# Set default environment variables
FROM lscr.io/linuxserver/code-server:latest

RUN curl -sS https://dl.yarnpkg.com/debian/pubkey.gpg | sudo apt-key add -
RUN echo "deb https://dl.yarnpkg.com/debian/ stable main" \
    | sudo tee /etc/apt/sources.list.d/yarn.list

RUN sudo apt-get update && sudo apt-get install -y \
    git \
    wget \
    zsh \
    vim \
    yarn \
    && sudo apt-get clean \
    && sudo rm -rf /var/lib/apt/lists/* /tmp/* /var/tmp/*

RUN sh -c "$(curl -fsSL https://raw.githubusercontent.com/ohmyzsh/ohmyzsh/master/tools/install.sh)"

RUN wget -qO- https://raw.githubusercontent.com/nvm-sh/nvm/v0.39.3/install.sh | bash
RUN export NVM_DIR="$HOME/.nvm" \
    && [ -s "$NVM_DIR/nvm.sh" ] && \. "$NVM_DIR/nvm.sh"\
    && [ -s "$NVM_DIR/bash_completion" ] && \. "$NVM_DIR/bash_completion"\
    && nvm install node 

# TODO: the following files are from my host machine, change them when dockerfile will work
COPY .bashrc .
COPY .zshrc .

# Install Yarn
COPY ./config /config


# Export some ports
EXPOSE 8443
EXPOSE 3000-3050