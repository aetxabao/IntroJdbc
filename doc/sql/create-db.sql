
CREATE USER 'proy'@'localhost' IDENTIFIED BY 'password';

CREATE DATABASE utx_db;

GRANT ALL PRIVILEGES ON utx_db.* TO 'proy'@'localhost';

USE utx_db;


