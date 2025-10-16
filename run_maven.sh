#!/bin/bash
docker-compose -f docker/docker-compose.yml down
docker rmi best_insurance/api
./mvnw spring-boot:build-image && docker-compose -f docker/docker-compose.yml up