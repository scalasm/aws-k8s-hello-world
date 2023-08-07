# Current situation
- when running from inside a container, Kubernetes cannot see the docker image built with skaffold. I believe this is related to how Skaffold and JIB work together - the image built by JIB inside the container is not visible to the docker daemon. No clue.

It works when running locally.

# Hello World w/ Spring Boot and Skaffold

This is a sample project for demo purposes, providing a simple RESTful API with a simple HTTP POST endpoint.

It shows how to 
1. package a Spring Boot app into a Docker image, using [Skaffold](https://skaffold.dev/) and [Google Jib](https://github.com/GoogleContainerTools/jib);
2. Deploy it into your Kubernetes cluster.

# Requirements

You will need the following in your system:
* [Skaffold](https://skaffold.dev) 0.15.0 or better
* [Kubectl](https://kubernetes.io/docs/tasks/tools/install-kubectl/) 1.17.0 or better
* a local Kubernetes cluster like [Docker for Desktop CE](https://hub.docker.com/editions/community/docker-ce-desktop-windows) 
or [Minikube](https://kubernetes.io/docs/setup/learning-environment/minikube/) if you want to develop with your local cluster.

(Note: if you want to work directly with your remote AWS cluster, having a local cluster is not strictly needed but strongly advised!)

# Suggested

## IDE(s)
* If your are a **Visual Studio Code** user, install [Google Cloud Code extension for VS Code](https://marketplace.visualstudio.com/items?itemName=GoogleCloudTools.cloudcode)
* If you have **IntelliJ**, use [Google Cloud Code for IntelliJ](https://github.com/GoogleCloudPlatform/cloud-code-intellij)

## Video and tutorials

* [Develop Faster on Kubernetes With Google Container Tools and Cloud Build (Cloud Next '19)](https://www.youtube.com/watch?v=TYx0BTyFtmc)
* [Super-charge your GKE Developer Workflow in Visual Studio Code and IntelliJ (Cloud Next '19)](https://www.youtube.com/watch?v=Z2fyc3AbfKE)

## Quickstart

Set your context (use *""* if you want to use your local docker daemon registry cache and avoid to deploy to a remote private registry):

Please take a look at Skaffold docs for more commands and configurations!

# Dev Workflows

You can run the following commands by terminal or use the facilities provided by IDE(s).

## Code-deploy-run cycle

You code in your IDE while the system listen for changes and build/redeploys to your cluster in background.
```
skaffold dev
```

(Note: you can tune the timeouts :)) 

## Code and run

You code in your IDEs and deploy/run explicitly by typing:

```
skaffold run --tag=dev
```

(Note: if you don't specify the tag, then the `latest` will be used: keep this in mind with your K8S deployments!)

Happy coding!

## Delete your deployment

```
skaffold delete
```

# Open API

This project uses [SpringDoc](https://springdoc.org/). 

By default the UI is accessible at http://localhost:8080/swagger-ui.html .

# FAQ

## 1. How do I push images built locally to remote ECR repository?

You have to do the docker login first and then run Skaffold. For example, when using AWS CLI v2 (as always, change 
account id and region):

```shell script
mario@Sharkey:~/src/medium-articles/aws-k8s-hello-world$ aws ecr get-login-password --region eu-central-1 | docker login --username AWS --password-stdin xxxxxxxxxxxxxxx.dkr.ecr.eu-central-1.amazonaws.com
Login Succeeded
```

```shell script
mario@Sharkey:~/src/medium-articles/aws-k8s-hello-world$ skaffold run -p aws --tag=dev --default-repo xxxxxxxxxxxxxxx.dkr.ecr.eu-central-1.amazonaws.com/hello-world-app
Generating tags...
 - hello-world -> xxxxxxxxxxxxxxx.dkr.ecr.eu-central-1.amazonaws.com/hello-world-app/hello-world:dev
Checking cache...
 - hello-world: Found. Pushing
...
...
```
