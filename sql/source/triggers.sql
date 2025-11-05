USE db_project_2022;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS edit_date$

CREATE TRIGGER edit_date
BEFORE UPDATE ON trip
FOR EACH ROW
BEGIN
	IF ((NEW.tr_departure <> OLD.tr_departure) OR (NEW.tr_return <> OLD.tr_return) OR (NEW.tr_cost <> OLD.tr_cost)) THEN
		IF (OLD.tr_id IN (SELECT res_tr_id FROM reservation)) THEN
			SIGNAL SQLSTATE VALUE '45000'
			SET message_text = 'Cannot modify trip dates or cost after reservation has been made.';
		END IF;
	END IF;
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS trip_insert$

CREATE TRIGGER trip_insert
AFTER INSERT ON trip
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'TRIP', 'INSERTED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS trip_update$

CREATE TRIGGER trip_update
AFTER UPDATE ON trip
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'TRIP', 'UPDATED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS trip_delete$

CREATE TRIGGER trip_delete
AFTER DELETE ON trip
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'TRIP', 'DELETED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS reservation_insert$

CREATE TRIGGER reservation_insert
AFTER INSERT ON reservation
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'RESERVATION', 'INSERTED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS reservation_update$

CREATE TRIGGER reservation_update
AFTER UPDATE ON reservation
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'RESERVATION', 'UPDATED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS reservation_delete$

CREATE TRIGGER reservation_delete
AFTER DELETE ON reservation
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'RESERVATION', 'DELETED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS event_insert$

CREATE TRIGGER event_insert
AFTER INSERT ON event
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'EVENT', 'INSERTED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS event_update$

CREATE TRIGGER event_update
AFTER UPDATE ON event
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'EVENT', 'UPDATED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS event_delete$

CREATE TRIGGER event_delete
AFTER DELETE ON event
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'EVENT', 'DELETED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS travel_to_insert$

CREATE TRIGGER travel_to_insert
AFTER INSERT ON travel_to
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'TRAVEL_TO', 'INSERTED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS travel_to_update$

CREATE TRIGGER travel_to_update
AFTER UPDATE ON travel_to
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'TRAVEL_TO', 'UPDATED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS travel_to_delete$

CREATE TRIGGER travel_to_delete
AFTER DELETE ON travel_to
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'TRAVEL_TO', 'DELETED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS destination_insert$

CREATE TRIGGER destination_insert
AFTER INSERT ON destination
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'DESTINATION', 'INSERTED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS destination_update$

CREATE TRIGGER destination_update
AFTER UPDATE ON destination
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'DESTINATION', 'UPDATED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS destination_delete$

CREATE TRIGGER destination_delete
AFTER DELETE ON destination
FOR EACH ROW
BEGIN
	DECLARE user CHAR(10);
	SELECT user_AT INTO user FROM logged_in_user LIMIT 1;
	
	INSERT INTO log VALUES (DEFAULT, user, 'DESTINATION', 'DELETED', DEFAULT);
END$

DELIMITER ;

-- ===========================================================================================================================================

DELIMITER $

DROP TRIGGER IF EXISTS reject_salary_reduction$

CREATE TRIGGER reject_salary_reduction
BEFORE UPDATE ON worker
FOR EACH ROW
BEGIN
	IF (NEW.wrk_salary < OLD.wrk_salary) THEN
		SIGNAL SQLSTATE VALUE '45000'
		SET message_text = 'Cannot reduce a worker''s salary!';
	END IF;
END$

DELIMITER ;

-- ===========================================================================================================================================