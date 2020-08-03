DEFINIR

sudo docker build -t pupposoft/trustly-git-connector-service:1.1.0 .
sudo docker container run --name teste -p 8080:35000 pupposoft/trustly-git-connector-service:1.1.0