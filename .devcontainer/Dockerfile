# See here for image contents: https://github.com/microsoft/vscode-dev-containers/blob/main/containers/java/.devcontainer/base.Dockerfile
# [Choice] Java version (use -bullseye variants on local arm64/Apple Silicon): 8, 11, 17, 8-bullseye, 11-bullseye, 17-bullseye, 8-buster, 11-buster, 17-buster
ARG VARIANT=17
FROM mcr.microsoft.com/vscode/devcontainers/java:${VARIANT}

# [Option] Install Maven
ARG INSTALL_MAVEN="false"
ARG MAVEN_VERSION=""
# [Option] Install Gradle
ARG INSTALL_GRADLE="false"
ARG GRADLE_VERSION=""
RUN if [ "${INSTALL_MAVEN}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install maven \"${MAVEN_VERSION}\""; fi \
    && if [ "${INSTALL_GRADLE}" = "true" ]; then su vscode -c "umask 0002 && . /usr/local/sdkman/bin/sdkman-init.sh && sdk install gradle \"${GRADLE_VERSION}\""; fi

# [Choice] Node.js version: none, lts/*, 16, 14, 12, 10
ARG NODE_VERSION="none"
RUN if [ "${NODE_VERSION}" != "none" ]; then su vscode -c "umask 0002 && . /usr/local/share/nvm/nvm.sh && nvm install ${NODE_VERSION} 2>&1"; fi

# AWS CLI is already installed as remote container feature - we add AWS CDK and AWS SAM to the group
ARG AWS_CDK_VERSION="none"
RUN if [ "${AWS_CDK_VERSION}" != "none" ]; then su vscode -c "umask 0002 && npm install -g aws-cdk@${AWS_CDK_VERSION}"; fi

ARG AWS_SAM_VERSION="none"
RUN if [ "${AWS_SAM_VERSION}" != "none" ]; then \
        su vscode -c "umask 0002 && curl -o /tmp/aws-sam-cli-linux-x86_64.zip -L https://github.com/aws/aws-sam-cli/releases/${AWS_SAM_VERSION}/download/aws-sam-cli-linux-x86_64.zip" \
        && su vscode -c "umask 0002 && cd /tmp && unzip aws-sam-cli-linux-x86_64.zip -d sam-installation" \
        && su vscode -c "umask 0002 && sudo /tmp/sam-installation/install" \
        && su vscode -c "rm -rf /tmp/aws-sam-cli-linux-x86_64.zip sam-installation 2>&1"; \
    fi

# We install binaries for for AMD64 architectures - if you use ARM, you have to update these according 
# to your architecture (or provide easy support inside these scripts :D)
ARG KUBECTL_VERSION="none"
RUN if [ "${KUBECTL_VERSION}" != "none" ]; then \
        su vscode -c "umask 0002 && cd /tmp && curl -LO https://dl.k8s.io/release/${KUBECTL_VERSION}/bin/linux/amd64/kubectl" \
        && su vscode -c "sudo install /tmp/kubectl /usr/local/bin/" ; \
    fi

ARG SKAFFOLD_VERSION="none"
RUN if [ "${SKAFFOLD_VERSION}" != "none" ]; then \
        su vscode -c "umask 0002 && cd /tmp && curl -Lo skaffold https://storage.googleapis.com/skaffold/releases/${SKAFFOLD_VERSION}/skaffold-linux-amd64" \
        && su vscode -c "sudo install /tmp/skaffold /usr/local/bin/" ; \
    fi

ARG KUSTOMIZE_VERSION="none"
RUN if [ "${KUSTOMIZE_VERSION}" != "none" ]; then \
        su vscode -c "umask 0002 && cd /tmp && curl -s 'https://raw.githubusercontent.com/kubernetes-sigs/kustomize/master/hack/install_kustomize.sh' | bash" \
        && su vscode -c "sudo install /tmp/kustomize /usr/local/bin/" ; \
    fi

ARG HELM_VERSION="none"
RUN if [ "${HELM_VERSION}" != "none" ]; then \
        su vscode -c "umask 0002 && cd /tmp && curl -Lo helm.tgz  https://get.helm.sh/helm-${HELM_VERSION}-linux-amd64.tar.gz && tar xzf helm.tgz" \
        && su vscode -c "sudo install /tmp/linux-amd64/helm /usr/local/bin/" ; \
    fi

# [Optional] Uncomment this section to install additional OS packages.
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
     && apt-get -y install --no-install-recommends python3-pip

# Required for access to AWS CodeCommit repositories using codecommit://<profile>@<repo-name> syntax
RUN su vscode -c "sudo pip install git-remote-codecommit"

# Graphviz is required for PlantUML extension
RUN apt-get update && export DEBIAN_FRONTEND=noninteractive \
     && apt-get -y install --no-install-recommends graphviz

# [Optional] Uncomment this line to install global node packages.
# RUN su vscode -c "source /usr/local/share/nvm/nvm.sh && npm install -g <your-package-here>" 2>&1
