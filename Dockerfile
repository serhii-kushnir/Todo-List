FROM mysql:8.2.0
ENV MYSQL_USER user
ENV MYSQL_PASSWORD user
ENV MYSQL_ROOT_PASSWORD root
ENV MYSQL_ALLOW_EMPTY_PASSWORD false
ENV MYSQL_RANDOM_ROOT_PASSWORD random
ENV MYSQL_DATABASE todo

#docker build -t sk/mysql .
#docker run --name mysql -d -p 3366:3306 sk/mysql
#docker start container mysql