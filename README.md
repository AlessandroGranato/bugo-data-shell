# Bugo Data Shell
Spring boot module used to interact with data coming from IoT and requested/used from Flutter app


## Using Docker to simplify development

Ensure that Docker Desktop or similar are active.

To start a postgresql database in a docker container, run:

```
docker-compose -f application/src/main/docker/postgresql.yml up -d
```

To start a kafka cluster in a docker container, run:

```
docker-compose -f application/src/main/docker/kafka.yml up -d
```

To stop a container (in this example postgres), run:

```
docker-compose -f application/src/main/docker/postgresql.yml stop

```

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
