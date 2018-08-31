# spring-microservice
New project that implements spring boot with docker 

## Get Started

You can check out the project by following these steps:

### 1. Clone this Repository
```
git clone https://github.com/abhi32ag/spring-microservice.git
```

### 2. Run a MongoDB docker container
```
docker run \
  --name="mongo-27018" \
  --publish 27018:27017 \
  --detach \
  mongo:latest
```

### 3. Run a Gradle build for the dependencies
```
gradle clean cleanEclipse eclipse build --refresh-dependencies
```

### 4. Build your Docker Image using Gradle 
```
gradle build distDocker --refresh-dependencies
```

### 6. Build three Docker containers pointing to the same application
```
docker run \
  --name="spring-microservice-A" \
  --publish 9001:8080 \
  --network="bridge" \
  --detach \
  -e "MONGO_URL=mongodb://$(ifconfig en0 | awk '/ *inet /{print $2}'):27018" \
  -e "MONGO_DBNAME=visitors" \
  spring-microservice:latest

docker run \
  --name="spring-microservice-B" \
  --publish 9002:8080 \
  --network="bridge" \
  --detach \
  -e "MONGO_URL=mongodb://$(ifconfig en0 | awk '/ *inet /{print $2}'):27018" \
  -e "MONGO_DBNAME=visitors" \
  spring-microservice:latest

docker run \
  --name="spring-microservice-C" \
  --publish 9003:8080 \
  --network="bridge" \
  --detach \
  -e "MONGO_URL=mongodb://$(ifconfig en0 | awk '/ *inet /{print $2}'):27018" \
  -e "MONGO_DBNAME=visitors" \
  spring-microservice:latest
```

### 7. Test using Rest calls
```
reset && curl   -H 'Content-Type:application/json'  -X POST 'http://localhost:9002/visitors' -d '{ "name" : "Abhinav Garg" }' # | python -m json.tool
`
