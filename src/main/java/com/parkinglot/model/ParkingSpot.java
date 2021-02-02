package com.parkinglot.model;

import com.parkinglot.utilty.ParkingSpotType;

public class ParkingSpot {

	private int number;
	private boolean free;
	private ParkingSpotType type;
	private String registrationNumber;
	
	

	public ParkingSpot() {
	}

	public ParkingSpot(int number, boolean free, ParkingSpotType type) {
		this.number = number;
		this.free = free;
		this.type = type;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public boolean isFree() {
		return free;
	}

	public void setFree(boolean free) {
		this.free = free;
	}

	public ParkingSpotType getType() {
		return type;
	}

	public void setType(ParkingSpotType type) {
		this.type = type;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

}
