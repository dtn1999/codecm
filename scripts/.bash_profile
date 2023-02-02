
#THIS MUST BE AT THE END OF THE FILE FOR SDKMAN TO WORK!!!
export SDKMAN_DIR="$HOME/.sdkman"
[[ -s "$HOME/.sdkman/bin/sdkman-init.sh" ]] && source "$HOME/.sdkman/bin/sdkman-init.sh"

export NVM_DIR=~/.nvm

# aws cli
export PATH=/usr/local/bin/:$PATH
complete -C '/usr/local/bin/aws_completer' aws

# add the openshift client CLI to the path
export PATH="~/Documents/dev-tools/openshift:$PATH"
source $(brew --prefix nvm)/nvm.sh
