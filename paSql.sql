
DROP TABLE IF EXISTS insurance_bonds;
DROP TABLE IF EXISTS insurance_services;
DROP TABLE IF EXISTS insurance_companies;
DROP TABLE IF EXISTS cars;
DROP TABLE IF EXISTS users;


CREATE TABLE users (

	email TEXT PRIMARY KEY NOT NULL,
	salary INTEGER,
	CHECK (salary >= 0)

);

CREATE TABLE cars (

	license_plate TEXT PRIMARY KEY NOT NULL,
	manufacturer TEXT NOT NULL,
	model_name TEXT NOT NULL

);

CREATE TABLE insurance_companies (

	name TEXT PRIMARY KEY NOT NULL
	
);

CREATE TABLE insurance_services (

	service_name TEXT PRIMARY KEY NOT NULL,
	minimum_salary INTEGER,
	insurance_length INTEGER,
	issuer TEXT,
	CHECK (minimum_salary >= 0 AND insurance_length >= 1)

);

CREATE TABLE insurance_bonds(

	issued_date INTEGER NOT NULL,
	user_email TEXT,
	car_license_plate TEXT,
	insurance_service TEXT,
	FOREIGN KEY (user_email) REFERENCES users(email) ON DELETE CASCADE,
	FOREIGN KEY (insurance_service) REFERENCES insurance_services(service_name)

);


----------------------------- Add New User ------------------------------

INSERT INTO users (email,salary) VALUES ('dummyEmail@codecoolPa.com',500);
INSERT INTO users (email,salary) VALUES ('userToDelete@codecoolPa.com',500);

-------------------------------------------------------------------------


----------------------------- List Users --------------------------------

SELECT email FROM users;

-------------------------------------------------------------------------

----------------------------- User Details ------------------------------

SELECT * FROM users WHERE email = 'dummyEmail@codecoolPa.com';

-------------------------------------------------------------------------

----------------------------- Delete Existing User ----------------------

DELETE FROM users WHERE email = 'userToDelete@codecoolPa.com';

-------------------------------------------------------------------------

----------------------------- List users with no salary -----------------

SELECT email FROM users WHERE salary = 0;

-------------------------------------------------------------------------

----------------------------- Add New Car -------------------------------

INSERT INTO cars (license_plate,manufacturer,model_name)
VALUES (

	'JRL-447',
	'Suzuki',
	'Ignis'

);

INSERT INTO cars (license_plate,manufacturer,model_name)
VALUES (

	'DEL-373',
	'Suzuki',
	'Swift'

);

-------------------------------------------------------------------------

----------------------------- List cars ---------------------------------

SELECT manufacturer,model_name FROM cars;

-------------------------------------------------------------------------

----------------------------- List insured cars -------------------------

SELECT * FROM cars WHERE license_plate IN (SELECT car_license_plate FROM insurance_bonds);

-------------------------------------------------------------------------

----------------------------- List uninsured cars -----------------------

SELECT * FROM cars WHERE license_plate NOT IN (SELECT car_license_plate FROM insurance_bonds);

-------------------------------------------------------------------------

----------------------------- Delete existing car -----------------------

DELETE FROM cars 
WHERE license_plate = 'JRL-447' 
AND license_plate NOT IN (SELECT car_license_plate FROM insurance_bonds);

	

----------------------------- Add new insurance company -----------------

BEGIN;
	INSERT INTO insurance_companies (name) VALUES ('testCompany');
	INSERT INTO insurance_services (service_name,minimum_salary,insurance_length,issuer)
	VALUES (

		'serviceForTestCompany',
		400,
		1,
		'testCompany'

	);
COMMIT;

---------------------------------------------------------------------------

----------------------------- List all insurance companies ----------------

SELECT * FROM insurance_companies;

---------------------------------------------------------------------------

----------------------------- Delete insurance company --------------------

DELETE FROM insurance_companies WHERE name = 'testCompany'
AND name NOT IN (SELECT issuer FROM insurance_services);

----------------------------------------------------------------------------

----------------------------- Add new insurance service --------------------

INSERT INTO insurance_services (service_name,minimum_salary,insurance_length,issuer)
VALUES ('dummyService',400,2,'testCompany');

----------------------------------------------------------------------------

----------------------------- List all insurance services ------------------

SELECT * FROM insurance_services;

----------------------------------------------------------------------------

----------------------------- List insurance company services --------------

SELECT service_name, issuer, minimum_salary, insurance_length 
FROM insurance_services
WHERE issuer = 'testCompany';

----------------------------------------------------------------------------

----------------------------- Delete existing insurance service ------------

DELETE FROM insurance_services
WHERE service_name = 'addNewService' 
AND service_name NOT IN (SELECT insurance_service FROM insurance_bonds);

----------------------------------------------------------------------------


----------------------------- Insure Car -----------------------------------

BEGIN;
	INSERT INTO insurance_bonds (issued_date,user_email,car_license_plate,insurance_service)
	VALUES (date_part('year', current_date),'dummyEmail@codecoolPa.com','JRL-447','dummyService');
	UPDATE users SET salary = salary-(SELECT minimum_salary FROM insurance_services WHERE service_name = 'dummyService');
	UPDATE users SET salary = salary+(SELECT minimum_salary FROM insurance_services WHERE service_name = 'dummyService');
COMMIT;

----------------------------------------------------------------------------

----------------------------- Lengthten insurance bond ---------------------

UPDATE insurance_bonds SET issued_date = date_part('year',current_date) 
WHERE user_email = 'dummyEmail@codecoolPa.com' AND car_license_plate = 'JRL-447';

----------------------------------------------------------------------------

----------------------------- List invalid insurance bonds -----------------

SELECT issued_date, insurance_services.insurance_length,user_email,car_license_plate, insurance_service FROM insurance_bonds
JOIN insurance_services ON insurance_bonds.insurance_service = insurance_services.service_name
WHERE issued_date+insurance_length<date_part('year',current_date);


----------------------------------------------------------------------------

----------------------------- List valid insurance bonds -----------------

SELECT issued_date, insurance_services.insurance_length,user_email,car_license_plate, insurance_service FROM insurance_bonds
JOIN insurance_services ON insurance_bonds.insurance_service = insurance_services.service_name
WHERE issued_date+insurance_length>date_part('year',current_date);


----------------------------------------------------------------------------


----------------------------- List number of insurance bonds by companies --

SELECT name, count(insurance_bonds.insurance_service) FROM insurance_services
JOIN insurance_bonds ON insurance_bonds.insurance_service = insurance_services.service_name
JOIN insurance_companies ON insurance_companies.name = insurance_services.issuer
GROUP BY insurance_companies.name

-----------------------------------------------------------------------------


