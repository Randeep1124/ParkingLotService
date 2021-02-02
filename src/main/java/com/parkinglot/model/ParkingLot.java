package com.parkinglot.model;

import com.parkinglot.utilty.VehicleType;

public class ParkingLot {

	private int carSpotCount;
	private int bikeSpotCount;
	private final int maxCompactCount = 5;
	private final int maxMotorbikeCount = 5;

	private static volatile ParkingLot parkingLot = null;
	
	private ParkingLot() {

	}

	public static ParkingLot getParkingLot() {
		if (parkingLot == null) {
			synchronized (ParkingLot.class) {
				if (parkingLot == null) {
					parkingLot = new ParkingLot();
				}
			}
		}
		return parkingLot;
	}

	public static boolean isFull(VehicleType type) {
		ParkingLot.getParkingLot();
		if (type.equals(VehicleType.BIKE)) {
			return parkingLot.bikeSpotCount < parkingLot.maxMotorbikeCount ? true : false;
		} else if (type.equals(VehicleType.CAR)) {
			return parkingLot.carSpotCount < parkingLot.maxCompactCount ? true : false;
		}

		return true;
	}

	public static void incrementSpotCount(VehicleType type) {
		ParkingLot.getParkingLot();
		if (type.equals(VehicleType.BIKE)) {
			parkingLot.bikeSpotCount++;
		} else if (type.equals(VehicleType.CAR)) {
			parkingLot.carSpotCount++;
		}
	}
	
	public static void decrementSpotCount(VehicleType type) {
		ParkingLot.getParkingLot();
		if (type.equals(VehicleType.BIKE)) {
			parkingLot.bikeSpotCount--;
		} else if (type.equals(VehicleType.CAR)) {
			parkingLot.carSpotCount--;
		}
	}

}
