DROP DATABASE IF EXISTS db_project_2022;
CREATE DATABASE db_project_2022;

USE db_project_2022;

-- ===========================================================================================================================================

CREATE TABLE branch
(
	br_code INT(11) ZEROFILL NOT NULL AUTO_INCREMENT,
	br_street VARCHAR(30) NOT NULL,
	br_num INT NOT NULL,
	br_city VARCHAR(30) NOT NULL,
	
	PRIMARY KEY(br_code)
);

-- ===========================================================================================================================================

CREATE TABLE phones
(
	ph_br_code INT(11) ZEROFILL NOT NULL,
	ph_number CHAR(10) NOT NULL,
	
	PRIMARY KEY(ph_br_code, ph_number),
	
	CONSTRAINT phones_branch
	FOREIGN KEY(ph_br_code) REFERENCES branch(br_code)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE worker
(
	wrk_AT CHAR(10) NOT NULL,
	wrk_name VARCHAR(20),
	wrk_lname VARCHAR(20),
	wrk_salary FLOAT(7,2) NOT NULL,
	wrk_br_code INT(11) ZEROFILL NOT NULL,
	
	PRIMARY KEY(wrk_AT),
	
	CONSTRAINT worker_branch
	FOREIGN KEY(wrk_br_code) REFERENCES branch(br_code)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE guide
(
	gui_AT CHAR(10) NOT NULL,
	gui_cv TEXT,
	
	PRIMARY KEY(gui_AT),
	
	CONSTRAINT guide_worker
	FOREIGN KEY(gui_AT) REFERENCES worker(wrk_AT)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE languages
(
	lng_gui_AT CHAR(10) NOT NULL,
	lng_language VARCHAR(30) NOT NULL,
	
	PRIMARY KEY(lng_gui_AT, lng_language),
	
	CONSTRAINT languages_guide
	FOREIGN KEY(lng_gui_AT) REFERENCES guide(gui_AT)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE admin
(
	adm_AT char(10) NOT NULL,
	adm_type ENUM('LOGISTICS', 'ADMINISTRATIVE', 'ACCOUNTING') NOT NULL,
	adm_diploma VARCHAR(200) NOT NULL,
	
	PRIMARY KEY(adm_AT),
	
	CONSTRAINT admin_worker
	FOREIGN KEY(adm_AT) REFERENCES worker(wrk_AT)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE manages
(
	mng_adm_AT char(10) NOT NULL,
	mng_br_code INT(11) ZEROFILL NOT NULL,
	
	PRIMARY KEY(mng_adm_AT, mng_br_code),
	
	CONSTRAINT manages_admin
	FOREIGN KEY(mng_adm_AT) REFERENCES admin(adm_AT)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	
	CONSTRAINT manages_branch
	FOREIGN KEY(mng_br_code) REFERENCES branch(br_code)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE driver
(
	drv_AT CHAR(10) NOT NULL,
	drv_license ENUM('A', 'B', 'C', 'D') NOT NULL,
	drv_route ENUM('LOCAL', 'ABROAD') NOT NULL,
	drv_experience TINYINT DEFAULT 0,
	
	PRIMARY KEY(drv_AT),
	
	CONSTRAINT driver_worker
	FOREIGN KEY(drv_AT) REFERENCES worker(wrk_AT)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE trip
(
	tr_id INT(11) ZEROFILL NOT NULL AUTO_INCREMENT,
	tr_departure DATETIME NOT NULL,
	tr_return DATETIME NOT NULL,
	tr_maxseats TINYINT NOT NULL,
	tr_cost FLOAT(7,2) NOT NULL,
	tr_br_code INT(11) ZEROFILL,
	tr_gui_AT CHAR(10),
	tr_drv_AT CHAR(10),
	
	PRIMARY KEY(tr_id),
	
	CONSTRAINT trip_branch
	FOREIGN KEY(tr_br_code) REFERENCES branch(br_code)
	ON UPDATE CASCADE
	ON DELETE SET NULL,
	
	CONSTRAINT trip_guide
	FOREIGN KEY(tr_gui_AT) REFERENCES guide(gui_AT)
	ON UPDATE CASCADE
	ON DELETE SET NULL,
	
	CONSTRAINT trip_driver
	FOREIGN KEY(tr_drv_AT) REFERENCES driver(drv_AT)
	ON UPDATE CASCADE
	ON DELETE SET NULL
	
);

-- ===========================================================================================================================================

CREATE TABLE event
(
	ev_tr_id INT(11) ZEROFILL NOT NULL,
	ev_start DATETIME NOT NULL,
	ev_end DATETIME NOT NULL,
	ev_descr TEXT,
	
	PRIMARY KEY(ev_tr_id, ev_start),
	
	CONSTRAINT event_trip
	FOREIGN KEY(ev_tr_id) REFERENCES trip(tr_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE destination
(
	dst_id INT(11) ZEROFILL NOT NULL AUTO_INCREMENT,
	dst_name VARCHAR(50) NOT NULL,
	dst_descr TEXT,
	dst_rtype ENUM('LOCAL', 'ABROAD') NOT NULL,
	dst_language VARCHAR(30),
	dst_location INT(11) ZEROFILL,
	
	PRIMARY KEY(dst_id),
	
	CONSTRAINT destination_destination
	FOREIGN KEY(dst_location) REFERENCES destination(dst_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE travel_to
(
	to_tr_id INT(11) ZEROFILL NOT NULL,
	to_dst_id INT(11) ZEROFILL NOT NULL,
	to_arrival DATETIME NOT NULL,
	to_departure DATETIME NOT NULL,
	
	PRIMARY KEY(to_tr_id, to_dst_id),
	
	CONSTRAINT travel_trip
	FOREIGN KEY(to_tr_id) REFERENCES trip(tr_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE,
	
	CONSTRAINT travel_destination
	FOREIGN KEY(to_dst_id) REFERENCES destination(dst_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
	
);

-- ===========================================================================================================================================

CREATE TABLE reservation
(
	res_tr_id INT(11) ZEROFILL NOT NULL,
	res_seatnum TINYINT(4) ZEROFILL NOT NULL,
	res_name VARCHAR(20),
	res_lname VARCHAR(20),
	res_isadult ENUM('ADULT', 'MINOR') NOT NULL,
	
	PRIMARY KEY(res_tr_id, res_seatnum),
	
	CONSTRAINT reservation_trip
	FOREIGN KEY(res_tr_id) REFERENCES trip(tr_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE it_worker
(
	it_AT CHAR(10) NOT NULL,
	it_username VARCHAR(16) UNIQUE NOT NULL,
	it_password VARCHAR(10) DEFAULT 'password' NOT NULL,
	it_start_date DATETIME NOT NULL,
	it_end_date DATETIME,
	
	PRIMARY KEY(it_AT),
	
	CONSTRAINT itworker_worker
	FOREIGN KEY(it_AT) REFERENCES worker(wrk_AT)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE log
(
	log_entry_id INT(11) ZEROFILL AUTO_INCREMENT NOT NULL,
	log_it_AT CHAR(10),
	log_table ENUM('TRIP', 'RESERVATION', 'EVENT', 'TRAVEL_TO', 'DESTINATION') NOT NULL,
	log_action ENUM('INSERTED', 'UPDATED', 'DELETED') NOT NULL,
	log_time DATETIME DEFAULT CURRENT_TIMESTAMP NOT NULL,
	
	PRIMARY KEY(log_entry_id),
	
	CONSTRAINT log_it_worker
	FOREIGN KEY(log_it_AT) REFERENCES it_worker(it_AT)
	ON UPDATE CASCADE
	ON DELETE SET NULL
);

-- ===========================================================================================================================================

CREATE TABLE offer
(
	off_id INT(11) ZEROFILL AUTO_INCREMENT NOT NULL,
	off_destination INT(11) ZEROFILL NOT NULL,
	off_start DATETIME NOT NULL,
	off_end DATETIME NOT NULL,
	off_cost FLOAT(7,2) NOT NULL,
	
	PRIMARY KEY(off_id),
	
	CONSTRAINT offer_destination
	FOREIGN KEY(off_destination) REFERENCES destination(dst_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);

-- ===========================================================================================================================================

CREATE TABLE reservation_offer
(
	resoff_id INT(11) ZEROFILL AUTO_INCREMENT NOT NULL,
	resoff_off_id INT(11) ZEROFILL NOT NULL,
	resoff_name VARCHAR(20),
	resoff_lname VARCHAR(20),
	resoff_deposit FLOAT(7,2),
	
	PRIMARY KEY(resoff_id),
	
	CONSTRAINT reservation_offer_offer
	FOREIGN KEY(resoff_off_id) REFERENCES offer(off_id)
	ON UPDATE CASCADE
	ON DELETE CASCADE
);