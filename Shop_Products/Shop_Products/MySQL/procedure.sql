DELIMITER $$

DROP PROCEDURE IF EXISTS `shop`.`setFinalPrice` $$
CREATE PROCEDURE `shop`.`setFinalPrice` (IN mult DOUBLE)
BEGIN
   DECLARE a, c DOUBLE;
   DECLARE b INT;
   DECLARE cur1 CURSOR FOR SELECT a_price FROM data;
   DECLARE CONTINUE HANDLER FOR NOT FOUND SET b = 1;
   OPEN cur1;
   
   SET b = 0;
   
   WHILE b = 0 DO
		FETCH cur1 INTO a;
		IF b = 0 THEN
			SET c = a * mult;
			UPDATE data SET f_price = c WHERE a_price = a;
		END IF;
   END WHILE;
   
   CLOSE cur1;
   
END $$

DELIMITER ;