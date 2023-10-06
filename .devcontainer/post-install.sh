#!/bin/bash
set -e

##
## Create some aliases
##
echo 'alias ll="ls -alF"' >> $HOME/.bashrc
echo 'alias la="ls -A"' >> $HOME/.bashrc
echo 'alias l="ls -CF"' >> $HOME/.bashrc
echo 'alias k="kubectl"' >> $HOME/.bashrc

# Convenience workspace directory for later use
WORKSPACE_DIR=$(pwd)

##
## Print Versions, for references
##

java --version
python3 --version

# AWS
aws --version

# Docker
docker --version 
docker-compose --version 

# K8s 
#kubectl version
#kustomize version
#skaffold version
#helm version

echo "Done!"