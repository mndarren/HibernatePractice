package com.pPoint.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Vehicle {
	@Id @GeneratedValue
	private int vehicleId;
	private String name;
	@ManyToOne
	@JoinColumn(name="USER_ID")
	private UserProfile user;
	
	
	public UserProfile getUser() {
		return user;
	}
	public void setUser(UserProfile user) {
		this.user = user;
	}
	public Vehicle( String name) {
		super();
		this.name = name;
	}
	public int getVehicleId() {
		return vehicleId;
	}
	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Vehicle [vehicleId=" + vehicleId + ", name=" + name + "]";
	}
	
	

}
