package com.pPoint.dto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.CollectionId;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.JoinColumn;

@Entity
@Table(name = "user_info")
public class UserProfile {
	
	@Id
	@Column(name = "user_id")
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int userId;
	/**
	 * @EmbeddedId
	 * private LoginName userId;
	 * if LoginName is an object and a primary key
	 */
	@Column(name="first_name")
	private String firstName;
	@Transient
	private String lastName;
	@Column(name="joined_date")
	@Temporal(TemporalType.DATE)
	private Date joinedDate;
	private String email;
	@Lob
	private String description;
	/**These 2 lines are for one to one.
	 * @OneToOne
	 *@JoinColumn(name="VEHICLE_ID")
	 */
	
	/**
	 * This part is for relationship table. If we use "mappedBy", 
	 * R table will not be needed.
	 * @JoinTable(name="USER_VEHICLE",
			joinColumns=@JoinColumn(name="USER_ID"),
			inverseJoinColumns=@JoinColumn(name="VEHICLE_ID")
			)
	 */
	@OneToMany(fetch=FetchType.LAZY, cascade = {CascadeType.ALL},mappedBy="user")
	private Collection<Vehicle> vehicles = new ArrayList<>();
	
	
	public Collection<Vehicle> getVehicles() {
		return vehicles;
	}
	public void setVehicle(Collection<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
	@ElementCollection//(fetch=FetchType.EAGER)
    @JoinTable(name="USER_ADDRESS",  
    		joinColumns=@JoinColumn(name="USER_ID"))//import JoinColumn to solve the problem
	/**
	 * These two lines is to generate a primary key for the collection,
	 * but it affects retrieve the data from database.
	 * @GenericGenerator(name = "sequence-gen", strategy = "sequence")//hilo-gen deprecated
	 * @CollectionId(columns = { @Column(name="ADDRESS_ID") }, generator = "sequence-gen", type = @Type(type = "long"))
	 * */
	private Collection<Address> listOfAddresses = new ArrayList<>();
	public void setListOfAddresses(Collection<Address> listOfAddresses) {
		this.listOfAddresses = listOfAddresses;
	}
	/**
	 * Set cannot be used to generate joined table primary key,
	 * so change the collection type into ArrayList
	 * private Set<Address> listOfAddresses = new HashSet<>();
	 */
	/**
	 * This part introduces how to deal with 2 instances from one class.
	 * @Embedded
	@AttributeOverrides({
		@AttributeOverride(name="street", column=@Column(name="HOME_STREET_NAME")),
		@AttributeOverride(name="city", column=@Column(name="HOME_CITY_NAME")),
		@AttributeOverride(name="state", column=@Column(name="HOME_STATE_NAME")),
		@AttributeOverride(name="pincode", column=@Column(name="HOME_PIN_CODE"))})
	private Address homeAddress;
	public Address getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(Address homeAddress) {
		this.homeAddress = homeAddress;
	}
	public Address getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(Address officeAddress) {
		this.officeAddress = officeAddress;
	}
	@Embedded
	private Address officeAddress;
	*/
	
	public Collection<Address> getListOfAddresses() {
		return listOfAddresses;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getJoinedDate() {
		return joinedDate;
	}
	public void setJoinedDate(Date joinedDate) {
		this.joinedDate = joinedDate;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Basic (fetch=FetchType.LAZY)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "UserProfile [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", joinedDate="
				+ joinedDate + ", email=" + email + ", description=" + description + ", vehicles=" + vehicles
				+ ", listOfAddresses=" + listOfAddresses + "]";
	}
	
			
}
