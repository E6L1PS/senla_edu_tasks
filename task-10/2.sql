-- 1
SELECT model, speed, hd 
FROM mydb_repository.pc 
WHERE price<500;
-- 2
SELECT maker 
FROM mydb_repository.product 
WHERE type = 'Printer';
-- 3
SELECT model, hd, screen 
FROM mydb_repository.laptop 
WHERE price>1000;
-- 4
SELECT * 
FROM mydb_repository.printer 
WHERE color='y';
-- 5
SELECT model 
FROM mydb_repository.pc 
WHERE (cd='12x' or cd= '24x') and price<600;
-- 6
SELECT product.maker, laptop.speed 
FROM mydb_repository.laptop 
JOIN mydb_repository.product ON laptop.model = product.model 
WHERE hd>=100;
-- 7?
SELECT product.model, laptop.price 
FROM mydb_repository.laptop
JOIN mydb_repository.product ON laptop.model = product.model 
WHERE maker='B'
UNION
SELECT product.model, pc.price 
FROM mydb_repository.pc
JOIN mydb_repository.product ON pc.model = product.model 
WHERE maker='B'
UNION
SELECT product.model, printer.price 
FROM mydb_repository.printer
JOIN mydb_repository.product ON printer.model = product.model 
WHERE maker='B';
-- 8?
-- 9
SELECT product.maker
FROM mydb_repository.pc 
JOIN mydb_repository.product ON pc.model = product.model
WHERE speed>=450;
-- 10
SELECT model, MAX(price) 
FROM mydb_repository.printer;
-- 11
SELECT AVG(speed) 
FROM mydb_repository.pc;
-- 12
SELECT AVG(speed) 
FROM mydb_repository.laptop
WHERE price>1000;
-- 13
SELECT AVG(pc.speed) 
FROM mydb_repository.pc 
JOIN mydb_repository.product ON pc.model = product.model
WHERE maker='A';
-- 14wtf
SELECT pc.speed, AVG(pc.price)
FROM mydb_repository.pc 
GROUP BY pc.speed;
-- 15
SELECT hd 
FROM mydb_repository.pc 
GROUP BY (hd) HAVING COUNT(code) >= 2;
-- 16?
-- 17
SELECT product.type, laptop.model, laptop.speed
FROM mydb_repository.laptop 
JOIN mydb_repository.product ON laptop.model = product.model
WHERE laptop.speed < (
SELECT MIN(speed) FROM mydb_repository.pc
);
-- 18
SELECT product.maker, MIN(printer.price)
FROM mydb_repository.printer 
JOIN mydb_repository.product ON printer.model = product.model
WHERE color='y';
-- 19
SELECT product.maker, AVG(laptop.screen)
FROM mydb_repository.product, mydb_repository.laptop
WHERE product.model = laptop.model
GROUP BY product.maker;
-- 20
SELECT maker, COUNT(model)
FROM mydb_repository.product
WHERE type='pc'
GROUP BY maker
HAVING COUNT(model)>=3;
-- 21
SELECT product.maker, MAX(pc.price)
FROM mydb_repository.product, mydb_repository.pc
WHERE product.model = pc.model
GROUP BY product.maker;
-- 22?
SELECT pc.speed, AVG(pc.price)
FROM mydb_repository.pc 
WHERE speed>600
GROUP BY pc.speed;
-- 23?
-- 24?
-- 25?