CREATE DATABASE mydb_repository;
    
    CREATE TABLE mydb_repository.product (
        maker VARCHAR(50) NOT NULL,
        model VARCHAR(50) PRIMARY KEY NOT NULL,
        type VARCHAR(50) NOT NULL
	);
    
    CREATE TABLE mydb_repository.pc (
		code INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        model VARCHAR(50) NOT NULL,
        INDEX fk_model (model),
        FOREIGN KEY (model)
			REFERENCES mydb_repository.product(model),
        speed SMALLINT NOT NULL,
        ram SMALLINT NOT NULL,
        hd REAL NOT NULL,
        cd VARCHAR(10) NOT NULL,
        price DECIMAL(10, 2) NULL
	);
        
	
        
	CREATE TABLE mydb_repository.laptop (
        code INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        model VARCHAR(50) NOT NULL,
        INDEX fk_model (model),
        FOREIGN KEY (model)
			REFERENCES mydb_repository.product(model),
        speed SMALLINT NOT NULL,
        ram SMALLINT NOT NULL,
        hd REAL NOT NULL,
        price DECIMAL(10, 2) NULL,
        screen TINYINT NOT NULL
	);
    
    CREATE TABLE mydb_repository.printer (
        code INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
        model VARCHAR(50) NOT NULL,
        INDEX fk_model (model),
        FOREIGN KEY (model)
			REFERENCES mydb_repository.product(model),
        color CHAR(1) NOT NULL,
        type VARCHAR(10) NOT NULL,
        price DECIMAL(10, 2) NULL
	);