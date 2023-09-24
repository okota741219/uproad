FROM mysql:8.0.33
COPY  ./morse.sql  /docker-entrypoint-initdb.d/
ENV MYSQL_ROOT_PASSWORD=dev
ENV MYSQL_DATABASE=db_dev
