#!/bin/sh

docker run --name pg -p 5432:5432 -e POSTGRES_USER=admin -e POSTGRES_PASSWORD=admin -e POSTGRES_DB=CHub -d postgres
