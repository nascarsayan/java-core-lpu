PRAGMA foreign_keys = ON;

DROP TABLE IF EXISTS Laptop;
DROP TABLE IF EXISTS PC;
DROP TABLE IF EXISTS Printer;
DROP TABLE IF EXISTS Product;

CREATE TABLE Laptop (
                        code INTEGER NOT NULL PRIMARY KEY,
                        model TEXT NOT NULL,
                        speed INTEGER NOT NULL,
                        ram INTEGER NOT NULL,
                        hd REAL NOT NULL,
                        price REAL NULL,
                        screen INTEGER NOT NULL,
                        FOREIGN KEY (model) REFERENCES Product (model)
);

CREATE TABLE PC (
                    code INTEGER NOT NULL PRIMARY KEY,
                    model TEXT NOT NULL,
                    speed INTEGER NOT NULL,
                    ram INTEGER NOT NULL,
                    hd REAL NOT NULL,
                    cd TEXT NOT NULL,
                    price REAL NULL,
                    FOREIGN KEY (model) REFERENCES Product (model)
);

CREATE TABLE Product (
                         maker TEXT NOT NULL,
                         model TEXT NOT NULL PRIMARY KEY,
                         type TEXT NOT NULL
);

CREATE TABLE Printer (
                         code INTEGER NOT NULL PRIMARY KEY,
                         model TEXT NOT NULL,
                         color TEXT CHECK (LENGTH(color) = 1) NOT NULL,
                         "type" TEXT NOT NULL,
                         price REAL NULL,
                         FOREIGN KEY (model) REFERENCES Product (model)
);

/*----Product------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
insert into Product values('B','1121','PC')
                         ,('A','1232','PC')
                         ,('A','1233','PC')
                         ,('E','1260','PC')
                         ,('A','1276','Printer')
                         ,('D','1288','Printer')
                         ,('A','1298','Laptop')
                         ,('C','1321','Laptop')
                         ,('A','1401','Printer')
                         ,('A','1408','Printer')
                         ,('D','1433','Printer')
                         ,('E','1434','Printer')
                         ,('B','1750','Laptop')
                         ,('A','1752','Laptop')
                         ,('E','2113','PC')
                         ,('E','2112','PC');



/*----PC------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
insert into PC values(1,'1232',500,64,5,'12x',600)
                    ,(2,'1121',750,128,14,'40x',850)
                    ,(3,'1233',500,64,5,'12x',600)
                    ,(4,'1121',600,128,14,'40x',850)
                    ,(5,'1121',600,128,8,'40x',850)
                    ,(6,'1233',750,128,20,'50x',950)
                    ,(7,'1232',500,32,10,'12x',400)
                    ,(8,'1232',450,64,8,'24x',350)
                    ,(9,'1232',450,32,10,'24x',350)
                    ,(10,'1260',500,32,10,'12x',350)
                    ,(11,'1233',900,128,40,'40x',980)
                    ,(12,'1233',800,128,20,'50x',970)
;


/*----Laptop------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
insert into Laptop values(1,'1298',350,32,4,700,11)
                        ,(2,'1321',500,64,8,970,12)
                        ,(3,'1750',750,128,12,1200,14)
                        ,(4,'1298',600,64,10,1050,15)
                        ,(5,'1752',750,128,10,1150,14)
                        ,(6,'1298',450,64,10,950,12)
;



/*----Printer------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------ */
insert into Printer values(1,'1276','n','Laser',400)
                         ,(2,'1433','y','Jet',270)
                         ,(3,'1434','y','Jet',290)
                         ,(4,'1401','n','Matrix',150)
                         ,(5,'1408','n','Matrix',270)
                         ,(6,'1288','n','Laser',400)
;