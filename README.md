sudo apt install docker.io
docker --version
sudo apt-cache policy docker-ce
sudo usermod -aG docker mathusharma
su -mathusharma
docker info
sudo groupadd docker
newgrp docker
systemctl start docker
sudo systemctl status docker

docker network create tokyo-network
docker network ls

#install and configure mysql
docker container run --name mysqldb --network tokyo-network -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=tokyo-supermix -d mysql:8.0

#run mysql
docker container logs -f mysqldb

#view inside mysql
docker container exec -it mysqldb bash

mysql -uroot -proot

#build image file
docker build . -t supermix
docker build . -t gate-way
docker build . -t service-registry
docker build . -t auth-service

#build contianer file to execute
docker container run --network tokyo-network --name supermix --restart=always -p 8085:8085 -d supermix
docker container run --network tokyo-network --name gate-way --restart=unless-stopped -p 8762:8762 -d gate-way
docker container run --network tokyo-network --name service-registry --restart=unless-stopped -p 8761:8761 -d service-registry
docker container run --network tokyo-network --name auth-service --restart=always -p 1725:1725 -d auth-service

#execute container files
docker container logs -f supermix
docker container logs -f gate-way
docker container logs -f service-registry
docker container logs -f auth-service

#mysql
docker start mysqldb
docker restart mysqldb

