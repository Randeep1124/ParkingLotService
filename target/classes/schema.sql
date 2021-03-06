DROP TABLE IF EXISTS VEHICLE;
DROP TABLE IF EXISTS PARKINGSPOT;

  
CREATE TABLE VEHICLE (
  registrationNumber VARCHAR(250) NOT NULL,
  vehicleType INT,
  parkingTime VARCHAR(250) DEFAULT NULL,
  inTime VARCHAR(250) DEFAULT NULL,
  outTime VARCHAR(250) DEFAULT NULL
);

CREATE TABLE PARKINGSPOT(
    spotNumber INT PRIMARY KEY,
	free BOOLEAN,
	vehicleType INT,
	registrationNumber VARCHAR(250)
);

INSERT INTO PARKINGSPOT VALUES(1, true, 0, NULL);
INSERT INTO PARKINGSPOT VALUES(2, true, 0, NULL);
INSERT INTO PARKINGSPOT VALUES(3, true, 0, NULL);
INSERT INTO PARKINGSPOT VALUES(4, true, 0, NULL);
INSERT INTO PARKINGSPOT VALUES(5, true, 0, NULL);

INSERT INTO PARKINGSPOT VALUES(6, true, 1, NULL);
INSERT INTO PARKINGSPOT VALUES(7, true, 1, NULL);
INSERT INTO PARKINGSPOT VALUES(8, true, 1, NULL);
INSERT INTO PARKINGSPOT VALUES(9, true, 1, NULL);
INSERT INTO PARKINGSPOT VALUES(10, true, 1, NULL);
