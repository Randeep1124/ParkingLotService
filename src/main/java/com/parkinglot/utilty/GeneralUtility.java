package com.parkinglot.utilty;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.parkinglot.model.Bike;
import com.parkinglot.model.Car;
import com.parkinglot.model.ParkingRequest;
import com.parkinglot.model.ParkingSpot;
import com.parkinglot.model.Vehicle;

public class GeneralUtility {

	private static final Logger LOGGER = LoggerFactory.getLogger(GeneralUtility.class);
	
	public static List<Vehicle> vehicleList = new ArrayList<>();
	public static List<ParkingSpot> ParkingSpotList = new ArrayList<>();


	public static Map<VehicleType, Integer> parkingCharges = new HashMap<>();

	static {
		parkingCharges.put(VehicleType.BIKE, 50);
		parkingCharges.put(VehicleType.CAR, 100);
		ParkingSpot p1 = new ParkingSpot(1, true, ParkingSpotType.COMPACT);
		ParkingSpot p2 = new ParkingSpot(2, true, ParkingSpotType.COMPACT);
		ParkingSpot p3 = new ParkingSpot(3, true, ParkingSpotType.COMPACT);
		ParkingSpot p4 = new ParkingSpot(4, true, ParkingSpotType.COMPACT);
		
		ParkingSpot p5 = new ParkingSpot(5, true, ParkingSpotType.MOTORBIKE);
		ParkingSpot p6 = new ParkingSpot(6, true, ParkingSpotType.MOTORBIKE);
		ParkingSpot p7 = new ParkingSpot(7, true, ParkingSpotType.MOTORBIKE);
		ParkingSpot p8 = new ParkingSpot(8, true, ParkingSpotType.MOTORBIKE);

		ParkingSpotList.add(p1);
		ParkingSpotList.add(p2);
		ParkingSpotList.add(p3);
		ParkingSpotList.add(p4);
		ParkingSpotList.add(p5);
		ParkingSpotList.add(p6);
		ParkingSpotList.add(p7);
		ParkingSpotList.add(p8);

		
	}

	private GeneralUtility() {
		throw new IllegalStateException("Utility class");
	}

	public static boolean isEmpty(Collection<?> collection) {
		if (Objects.isNull(collection) || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		if (Objects.isNull(map) || map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object object) {
		if (Objects.isNull(object)) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object[] array) {
		if (Objects.isNull(array) || array.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String string) {
		if (Objects.isNull(string) || string.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmptyNumber(Double value) {
		if (value == null) {
			return true;
		}
		return false;

	}

	public static Vehicle getVehicleFromRequest(ParkingRequest parkingRequest) {
		Vehicle vehicle = getVehicle(parkingRequest.getVehicleType());
		vehicle.setInTime(getCurrentDate());
		vehicle.setRegistrationNumber(parkingRequest.getRegistrationNumber());

		return vehicle;

	}

	private static Vehicle getVehicle(String vehicleType) {
		switch (vehicleType) {
		case "CAR":
			return new Car();
		case "BIKE":
			return new Bike();
		}
		return null;
	}

	public static String getCurrentDate() {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		return dateFormat.format(cal.getTime());
	}

	public static long getTimeDifference(String parkingTime) throws ParseException {

		SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		long difference = dateFormat.parse(parkingTime).getTime() - dateFormat.parse(getCurrentDate()).getTime();

		return difference / (60 * 60 * 1000) % 24;
	}

	public static double getParkingChage(long timeDiff, VehicleType type) {
		return parkingCharges.get(type) * timeDiff;
	}

}
