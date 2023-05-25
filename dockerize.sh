#!/bin/sh

# Getting the user inputs for variables
echo "Please provide the image name with the tag"
read image_name

echo "Please provide the container name"
read container_name

echo "Please provide the spring profile name"
read spring_profile_name

echo "Please provide the spring server port"
read spring_server_port

echo "Please provide the docker external port"
read docker_external_port

# Stop and remove any existing containers with the specified name
if [ "$(docker ps -a -q -f name=${container_name})" ]; then
echo "Stopping and removing existing container with name ${container_name}..."
docker stop ${container_name}
docker rm ${container_name}
fi

# Remove any existing images with the specified name
if [ "$(docker images -q ${image_name})" ]; then
echo "Removing existing image with name ${image_name}..."
docker rmi ${image_name}
fi

# Build and package the Spring Boot application using Maven
mvn clean package

# Build the new Docker image
docker build -t ${image_name} .
# Run the new Docker container
echo ${SPRING_DATASOURCE_URL}
docker run --name ${container_name} -d -p ${docker_external_port}:${spring_server_port} -e SPRING_PROFILES_ACTIVE=${spring_profile_name} -e SERVER_PORT=${spring_server_port} -e SPRING_DATASOURCE_URL=${SPRING_DATASOURCE_URL} -e SPRING_DATASOURCE_USERNAME=${SPRING_DATASOURCE_USERNAME} -e SPRING_DATASOURCE_PASSWORD=${SPRING_DATASOURCE_PASSWORD} ${image_name}

echo "Completed..."