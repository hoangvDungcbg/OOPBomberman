Thực hiện các truy vấn sau để tạo csdl:



DROP DATABASE IF EXISTS bomberman;

CREATE DATABASE bomberman;

CREATE TABLE bomberman.rank ( name VARCHAR(100) NOT NULL , score INT(8) NOT NULL ) ENGINE = InnoDB;
