package com.parkinglot.dao;

import java.util.List;

import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.utilty.VehicleType;

public interface ParkingLotDao {

	void addNewParking(Vehicle vehicle);

	Vehicle getParkingDetails(String registrationNumber);

	void updateParkingSpot(VehicleType type, String registrationNumber);

	List<ParkingSpot> getAvailableSpot();

}
