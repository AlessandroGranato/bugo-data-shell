# Bugo Data Shell
Spring boot module used to interact with data coming from IoT and requested/used from Flutter app

## Using Docker to deploy
The steps to run the application in docker are the following:

### Ensure you have a working Docker instance
Ensure that Docker Desktop or similar are active.

### Ensure you have a docker network already up
If you don't have it, run the following command:
```
docker network create boogle-network
```

### Create docker images
To create the docker images of the db and the application, go on the root of the project and run:
```
mvn clean install -Plocal-image
```

If you only want to create only the db image, run the previous command from packager-db sumbodule instead of root.

If you only want to create only the application image, run the previous command from packager sumbodule instead of root.

### Run db docker container

To run a docker db container, run the following command and populate the db variables as you need:

```
docker run --name boogle-bugo-data-shell-db --network=boogle-network -e POSTGRES_DB=dbBds -e POSTGRES_USER=dbBds -e POSTGRES_PASSWORD=dbBds -dp 127.0.0.1:5434:5432 pyrosandro/boogle-bugo-data-shell-db-image:0.0.1-SNAPSHOT
```

Command explanation:
1. **docker run:** This is the command to run a Docker container.
2. **--name boogle-bugo-data-shell-db:** This option specifies the name of the container as "boogle-bugo-data-shell-db". The --name flag allows you to assign a custom name to the container instead of Docker generating a random one.
3. **--network=boogle-network:** This option specifies the network to which the container should be attached. It connects the container to the "boogle-network" Docker network.
4. **-e POSTGRES_DB=dbBds:** This option sets the environment variable POSTGRES_DB inside the container to "dbBds". This variable is used to specify the name of the PostgreSQL database to be created inside the container.
5. **-e POSTGRES_USER=dbBds:** This option sets the environment variable POSTGRES_USER inside the container to "dbBds". This variable is used to specify the username for connecting to the PostgreSQL database.
6. **-e POSTGRES_PASSWORD=dbBds:** This option sets the environment variable POSTGRES_PASSWORD inside the container to "dbBds". This variable is used to specify the password for connecting to the PostgreSQL database.
7. **-dp 127.0.0.1:5434:5432:** This option specifies the port mapping for the container. It maps port 5432 on the container to port 5434 on the host machine (127.0.0.1). The -d flag tells Docker to run the container in detached mode (in the background), and the -p flag specifies the port mapping.
8. **pyrosandro/boogle-bugo-data-shell-db-image:0.0.1-SNAPSHOT:** This is the name of the Docker image to use for creating the container. It specifies the image "pyrosandro/boogle-bugo-data-shell-db-image" with the tag "0.0.1-SNAPSHOT".

### Install liquibase scripts

Once the db is installed, you can run your liquibase scripts by going into liquibase folder and run the following command:

```
mvn install -Pliquibase
```

Note: if you need to rollback your scripts, run the following command (in the example, we rollback the last 2 scripts from master.xml):

```
mvn clean -Pliquibase -Dliquibase.rollbackCount=2
```

### Run app docker container

To run a docker app container, run the following command and populate the variables as needed.

```
docker run --name boogle-bugo-data-shell --network=boogle-network -e "SPRING_CONFIG_ADDITIONAL_LOCATION=/config/external-props.yml" -v C:\Users\PyroSandro\Desktop\PublicRepos\boogle-extra\bugo-data-shell-external-props.yml:/config/external-props.yml -dp 127.0.0.1:8082:8082 pyrosandro/boogle-bugo-data-shell-image:0.0.1-SNAPSHOT
```

Command explanation:
1. **docker run:** This is the command used to run a Docker container.
2. **--name boogle-bugo-data-shell:** This option sets the name of the container to "boogle-bugo-data-shell". The --name flag allows you to assign a custom name to the container instead of Docker generating a random one.
3. **--network=boogle-network:** This option specifies the network to which the container should be attached. It connects the container to the Docker network named "boogle-network".
4. **-e "SPRING_CONFIG_ADDITIONAL_LOCATION=/config/external-props.yml":** This option sets an environment variable within the container. It defines an additional location for Spring configuration properties (external-props.yml). This environment variable allows the application inside the container to load configuration from an external file.
5. **-v C:\Users\PyroSandro\Desktop\PublicRepos\boogle-extra\bugo-data-shell-external-props.yml:/config/external-props.yml:** This option mounts a volume from the host machine to the container. It maps the local file external-props.yml located on the host machine's desktop (C:\Users\PyroSandro\Desktop\PublicRepos\boogle-extra\bugo-data-shell-external-props.yml) to the container's /config/external-props.yml path. This volume mounting allows the containerized application to access configuration files from the host machine.
6. **-dp 127.0.0.1:8082:8082:** This option specifies the port mapping for the container. It maps port 8082 on the container to port 8082 on the host machine (127.0.0.1). The -d flag runs the container in detached mode (in the background), and the -p flag specifies the port mapping.
7. **pyrosandro/boogle-bugo-data-shell-image:0.0.1-SNAPSHOT:** This part of the command specifies the Docker image to use for creating the container. It specifies the image "pyrosandro/boogle-bugo-data-shell-image" with the tag "0.0.1-SNAPSHOT".

Note: The external-props.yml file should contain the values of the variables needed in application.yml file. For an example, you can see the file application-localdev.yml

## Design choices

### Device and User
In BugoDataShell, user is tied to a device so that he can track all the devices sensors and info. For this reason,
all the following design choices have been selected:
- A Device does not contain user info. A device can self register itself and it is independent of users.
- When we register a User, we need to provide Device identifier. This is because a user is 
  registering to a device in order to track its info
- A user can be registered to only one device
    - This can be changed in future, but for now we want a user to be linked to only one device. A device can be used by many users
- When we ask info about users, we also retrieve the device info
- When updating a user, we cannot update the device he's linked to (if other users are registered to the same device,
  they would receive unwanted modifications)  
