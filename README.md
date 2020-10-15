# Hello World w/ Spring Boot and Skaffold

This is a sample project for demo purposes, providing a simple RESTful API for saluting.

It shows how to 
1. package a Spring Boot app into a Docker image, using [Skaffold](https://skaffold.dev/) and [Google Jib](https://github.com/GoogleContainerTools/jib);
2. Deploy it into your Kubernetes cluster

# Requirements

You will need the following in your system:
* [Kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/)
* a Kubernetes cluster like [Docker for Desktop CE](https://hub.docker.com/editions/community/docker-ce-desktop-windows) or 
[Minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/)

# Suggested

## IDE(s)
* Install [Google Cloud Code extension for VS Code](https://marketplace.visualstudio.com/items?itemName=GoogleCloudTools.cloudcode)
* Use [Google Cloud Code for IntelliJ](https://github.com/GoogleCloudPlatform/cloud-code-intellij)

## Video and tutorials

* [Develop Faster on Kubernetes With Google Container Tools and Cloud Build (Cloud Next '19)](https://www.youtube.com/watch?v=TYx0BTyFtmc)
* [Super-charge your GKE Developer Workflow in Visual Studio Code and IntelliJ (Cloud Next '19)](https://www.youtube.com/watch?v=Z2fyc3AbfKE)

## Quickstart

Set your context (use *""* if you want to use your local docker daemon registry cache and avoid to deploy to a remote private registry):

# Dev Workflows

You can run the following commands by terminal or use the facilities provided by IDE(s).

## Code-deploy-run cycle

You code in your IDE while the system listen for changes and build/redeploys to your cluster in background.
```
skaffold dev
```

(Note: you can tune the timeouts :)) 

# Code and run

You code in your IDEs and deploy/run explicitly by typing:

```
skaffold run --tag=dev
```

(Note: if you don't specify the tag, then the `latest` will be used: keep this in mind with your K8S deployments!)

Happy coding!
