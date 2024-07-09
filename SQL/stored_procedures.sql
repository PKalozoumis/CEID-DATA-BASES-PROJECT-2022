USE db_project_2022;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS add_driver$

CREATE PROCEDURE add_driver(IN new_AT CHAR(10), IN new_name VARCHAR(20), IN new_lname VARCHAR(20), IN new_salary FLOAT(7,2), IN new_license ENUM('A', 'B', 'C', 'D'), IN new_route ENUM('LOCAL', 'ABROAD'), IN new_xp TINYINT)
BEGIN
	DECLARE bcode INT;
	
	SELECT branch.br_code
	INTO bcode
	FROM branch
	LEFT JOIN worker ON branch.br_code = worker.wrk_br_code
	LEFT JOIN driver ON worker.wrk_AT = driver.drv_AT
	GROUP BY branch.br_code
	ORDER BY COUNT(driver.drv_AT) ASC, branch.br_code ASC
	LIMIT 0,1;
	
	INSERT INTO worker VALUES
		(new_AT, new_name, new_lname, new_salary, bcode);
		
	INSERT INTO driver VALUES
		(new_AT, new_license, new_route, new_xp);
		
	SELECT bcode AS 'Added new driver to branch:';
	
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS delete_admin$
CREATE PROCEDURE delete_admin(IN in_name VARCHAR(20), IN in_lname VARCHAR(20))
BEGIN

	DECLARE temp_at CHAR(10);
	DECLARE finished BOOLEAN;
	
	DECLARE cur CURSOR FOR
	SELECT adm_AT
	FROM worker
	INNER JOIN admin ON worker.wrk_AT = admin.adm_AT
	WHERE wrk_name = in_name AND wrk_lname = in_lname;
	
	DECLARE CONTINUE HANDLER FOR NOT FOUND
	SET finished = true;
	
	SET finished = false;
	
	OPEN cur;
	
	REPEAT
	
		FETCH cur INTO temp_at;
		
		IF (NOT finished) THEN
			IF (temp_at IN (SELECT mng_adm_AT FROM manages)) THEN
				SELECT 'Cannot delete a manager' AS 'ERROR';
			ELSE
				DELETE FROM admin WHERE adm_AT = temp_AT;
				SELECT 'Admin deleted successfully' AS 'Success';
			END IF;
		END IF;
	
	UNTIL (finished)
	END REPEAT;
	
	CLOSE cur;
	
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS resoff_from_lname;

CREATE PROCEDURE resoff_from_lname(IN lname VARCHAR(20))
BEGIN
	SELECT resoff_name AS Name, lname AS Lastname, resoff_off_id AS 'Offer ID', count(*) AS People
	FROM reservation_offer
	WHERE resoff_lname = lname
	GROUP BY resoff_name, resoff_off_id
	ORDER BY resoff_name, resoff_off_id;
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS show_reservation_offer$

CREATE PROCEDURE show_reservation_offer(IN a FLOAT(7,2), IN b FLOAT(7,2))
BEGIN
	SELECT resoff_name, resoff_lname, resoff_deposit
	FROM reservation_offer
	WHERE resoff_deposit BETWEEN a AND b;
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS show_trips$
CREATE PROCEDURE show_trips(IN in_code INT, IN date1 DATETIME, IN date2 DATETIME)
BEGIN
	
	DECLARE temp_id INT;
	DECLARE reserve_num INT;
	DECLARE temp_date DATETIME;
	DECLARE finished BOOLEAN;
	
	DECLARE cur CURSOR FOR
	SELECT tr_id, tr_departure
	FROM  trip
	WHERE tr_br_code = in_code;
	
	DECLARE CONTINUE HANDLER FOR NOT FOUND
	SET finished = true;
	
	SET finished = false;
	
	OPEN cur;
	
	REPEAT
	
		FETCH cur INTO temp_id, temp_date;
		
		IF (NOT finished) THEN
			
			IF (temp_date BETWEEN date1 AND date2) THEN
			
				SELECT COUNT(*)
				INTO reserve_num
				FROM reservation
				WHERE res_tr_id = temp_id;
				
				SELECT
				tr_cost AS 'Cost',
				tr_maxseats as 'Max Seats',
				reserve_num AS 'Reservations',
				tr_maxseats - reserve_num AS 'Available Seats',
				w1.wrk_name AS 'Guide Name',
				w1.wrk_lname AS 'Guide Lastname',
				w2.wrk_name AS 'Driver Name',
				w2.wrk_lname AS 'Driver Lastname',
				tr_departure as 'Departure',
				tr_return AS 'Return'
				FROM trip
				INNER JOIN driver ON tr_drv_AT = drv_AT
				INNER JOIN guide ON tr_gui_AT = gui_AT
				INNER JOIN worker AS w1 ON w1.wrk_AT = gui_AT
				INNER JOIN worker AS w2 ON w2.wrk_AT = drv_AT
				WHERE tr_id = temp_id;
				
			END IF;
			
		END IF;
	
	UNTIL (finished)
	END REPEAT;
	
	CLOSE cur;

END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS expenses$

CREATE PROCEDURE expenses(IN in_code INT, OUT out_expenses FLOAT)
BEGIN
	SELECT sum(wrk_salary)
	INTO out_expenses
	FROM worker
	WHERE wrk_br_code = in_code;
	
	SELECT wrk_AT AS 'Worker', wrk_salary AS 'Salary'
	FROM worker
	WHERE wrk_br_code = in_code;
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS profit$

CREATE PROCEDURE profit(IN in_code INT, OUT out_profit FLOAT)
BEGIN
	SELECT sum(Profit)
	INTO out_profit
	FROM
	(
		SELECT count(*)*tr_cost AS 'Profit'
		FROM reservation
		INNER JOIN trip ON res_tr_id = tr_id
		WHERE tr_br_code = in_code
		GROUP BY tr_id
	)
	AS profit_table;
	
	SELECT tr_id AS 'Trip ID', count(*) AS 'Reservations', tr_cost AS 'Cost', count(*)*tr_cost AS 'Profit'
	FROM reservation
	INNER JOIN trip ON res_tr_id = tr_id
	WHERE tr_br_code = in_code
	GROUP BY tr_id;
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP PROCEDURE IF EXISTS show_employees$

CREATE PROCEDURE show_employees(IN in_code INT, OUT out_salary FLOAT)
BEGIN
	SET out_salary = 0;
	
	SELECT worker.wrk_name AS 'Name', worker.wrk_lname AS 'Lastname', worker.wrk_salary AS 'Salary'
	FROM branch
	INNER JOIN worker ON branch.br_code = worker.wrk_br_code
	WHERE branch.br_code = in_code;
	
	SELECT sum(worker.wrk_salary)
	INTO out_salary
	FROM branch
	INNER JOIN worker ON branch.br_code = worker.wrk_br_code
	WHERE branch.br_code = in_code;
	
END$

DELIMITER ;

-- ===========================================================================================================================================