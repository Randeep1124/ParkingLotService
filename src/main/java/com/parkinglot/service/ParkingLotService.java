package com.parkinglot.service;

import java.util.List;

import com.parkinglot.Exception.ParkingNotAvailableException;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;

public interface ParkingLotService {

	void addNewParking(Vehicle vehicle) throws ParkingNotAvailableException;

	Vehicle getParkingDetails(String registrationNumber);

	List<ParkingSpot> getAvailableSpot();

}
