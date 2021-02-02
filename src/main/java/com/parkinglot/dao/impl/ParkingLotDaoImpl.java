package com.parkinglot.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.parkinglot.dao.ParkingLotDao;
import com.parkinglot.model.Bike;
import com.parkinglot.model.Car;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.utilty.GeneralUtility;
import com.parkinglot.utilty.ParkingSpotType;
import com.parkinglot.utilty.VehicleType;

@Repository
public class ParkingLotDaoImpl implements ParkingLotDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	private static final Logger LOGGER = LoggerFactory.getLogger(ParkingLotDaoImpl.class);

	@Override
	public void addNewParking(Vehicle vehicle) {

		String query = "insert into VEHICLE (registrationNumber, type, parkingTime, inTime, outTime) values ("
				+ "?, ?, ?, ?, ?)";
		LOGGER.info("addBook sqlQuery :{} parameters: {}", query, vehicle);
		jdbcTemplate.update(query, vehicle.getRegistrationNumber(), vehicle.getType(), vehicle.getParkingTime(),
				vehicle.getInTime(), vehicle.getOutTime());
		GeneralUtility.vehicleList.add(vehicle);

	}

	@Override
	public Vehicle getParkingDetails(String registrationNumber) {
		String query = "select * from VEHICLE where registrationNumber = ?";
		LOGGER.info("getParkingDetails sqlQuery :{} parameters: {}", query, registrationNumber);
		return jdbcTemplate.queryForObject(query, new RowMapper<Vehicle>() {
			public Vehicle mapRow(ResultSet rs, int rowNum) throws SQLException {

				Vehicle vehicle = getVehicle(rs.getInt("type"));
				vehicle.setRegistrationNumber(rs.getString("registrationNumber"));
				vehicle.setInTime(rs.getString("inTime"));
				vehicle.setInTime(rs.getString("parkingTime"));
				return vehicle;
			}

		}, new Object[] { registrationNumber });
	}

	private Vehicle getVehicle(int type) {
		if (VehicleType.BIKE.getNumVal() == type)
			return new Bike();
		else
			return new Car();
	}

	@Override
	public void updateParkingSpot(VehicleType type, String registrationNumber) {
		// TODO Auto-generated method stub
		String query = "select number from PARKINGSPOT where free = ? limit=1";
		LOGGER.info("getParkingDetails sqlQuery :{} parameters: {}", query, registrationNumber);
		int number = jdbcTemplate.queryForObject(query, Integer.class, new Object[] { true });

		String query1 = "update PARKINGSPOT set registrationNumber=? where number=?";
		jdbcTemplate.update(query1, registrationNumber, number);

	}

	@Override
	public List<ParkingSpot> getAvailableSpot() {
		String query = "select * from PARKINGSPOT where free = ?";
		return jdbcTemplate.query(query, new RowMapper<ParkingSpot>() {
			public ParkingSpot mapRow(ResultSet rs, int rowNum) throws SQLException {
				ParkingSpot parkingSpot = new ParkingSpot();
				parkingSpot.setNumber(rs.getInt("number"));
				parkingSpot.setFree(rs.getBoolean("free"));
				parkingSpot.setType(getParkingSpotType(rs.getInt("type")));
				return parkingSpot;
			}
		}, new Object[] { true });
	}

	private ParkingSpotType getParkingSpotType(int type) {
		if (ParkingSpotType.MOTORBIKE.getNumVal() == type)
			return ParkingSpotType.MOTORBIKE;
		else
			return ParkingSpotType.COMPACT;
	}

}
