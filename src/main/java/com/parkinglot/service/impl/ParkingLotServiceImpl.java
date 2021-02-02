package com.parkinglot.service.impl;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parkinglot.Exception.ParkingNotAvailableException;
import com.parkinglot.dao.ParkingLotDao;
import com.parkinglot.model.ParkingLot;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;
import com.parkinglot.service.ParkingLotService;
import com.parkinglot.utilty.GeneralUtility;

@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	@Autowired
	ParkingLotDao parkingLotDao;

	@Override
	public void addNewParking(Vehicle vehicle) throws ParkingNotAvailableException {

		if (!ParkingLot.isFull(vehicle.getType())) {
			vehicle.setParkingTime(GeneralUtility.getCurrentDate());
			ParkingLot.incrementSpotCount(vehicle.getType());
			parkingLotDao.addNewParking(vehicle);
			parkingLotDao.updateParkingSpot(vehicle.getType(),vehicle.getRegistrationNumber());
		} else {
			throw new ParkingNotAvailableException("Parking space not available");
		}

	}

	@Override
	public Vehicle getParkingDetails(String registrationNumber) {
		Vehicle vehicle = parkingLotDao.getParkingDetails(registrationNumber);
		setParkingCharge(vehicle);
		return vehicle;
	}

	private void setParkingCharge(Vehicle vehicle) {
		long timeDiff;
		double parkingChage;
		try {
			timeDiff = GeneralUtility.getTimeDifference(vehicle.getParkingTime());
			parkingChage = GeneralUtility.getParkingChage(timeDiff, vehicle.getType());
			vehicle.setParkingCharge(parkingChage);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<ParkingSpot> getAvailableSpot() {
		return parkingLotDao.getAvailableSpot();
	}

}
