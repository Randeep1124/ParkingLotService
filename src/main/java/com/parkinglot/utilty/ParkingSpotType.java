package com.parkinglot.utilty;

public enum ParkingSpotType {
	MOTORBIKE(0), COMPACT(1);

	private int numVal;

	ParkingSpotType(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
