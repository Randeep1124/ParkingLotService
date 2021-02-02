package com.parkinglot.model;

import javax.persistence.Transient;

import com.parkinglot.utilty.VehicleType;

public abstract class Vehicle {

	private String registrationNumber;
	private final VehicleType type;
	private String parkingTime;
	private String inTime;
	private String outTime;
	@Transient
	private double parkingCharge;

	public Vehicle(VehicleType type) {
		this.type = type;
	}

	public String getRegistrationNumber() {
		return registrationNumber;
	}

	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}

	public String getParkingTime() {
		return parkingTime;
	}

	public void setParkingTime(String parkingTime) {
		this.parkingTime = parkingTime;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	public String getOutTime() {
		return outTime;
	}

	public void setOutTime(String outTime) {
		this.outTime = outTime;
	}

	public VehicleType getType() {
		return type;
	}

	public double getParkingCharge() {
		return parkingCharge;
	}

	public void setParkingCharge(double parkingCharge) {
		this.parkingCharge = parkingCharge;
	}

}
