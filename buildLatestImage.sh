#!/usr/bin/env bash
# build image and tag to latest
sbt clean docker
gid=$(git rev-parse --short=7 HEAD)
docker tag dapengsoa/biz/dapeng-mesh-auth_service:$gid dapengsoa/dapeng-mesh-auth:latest
echo "Tagging image dapengsoa/biz/dapeng-mesh-auth_service:$gid with name: dapengsoa/dapeng-mesh-auth:latest"
echo "push image: docker push dapengsoa/dapeng-mesh-auth:latest"
docker push dapengsoa/dapeng-mesh-auth:latest
