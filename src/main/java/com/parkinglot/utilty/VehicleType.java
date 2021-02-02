package com.parkinglot.utilty;

public enum VehicleType {
	BIKE(0), CAR(1);

	private int numVal;

	VehicleType(int numVal) {
		this.numVal = numVal;
	}

	public int getNumVal() {
		return numVal;
	}
}
