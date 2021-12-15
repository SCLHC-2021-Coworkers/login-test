@echo off

docker run -it -p 33060:3306 --name spring-security-db ^
    -e MYSQL_ROOT_PASSWORD=dnjsehddus ^
    -e MYSQL_DATABASE=test ^
    -d mariadb:10.3.11 ^
    --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci
