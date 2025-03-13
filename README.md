docker run --name postgres -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=12345 -p 5432:5432 -d postgres

docker exec -it postgres-terraz psql -U admin -d postgres