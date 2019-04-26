package com.MBAssignment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
/**
 * Manager model class
 * @author Mayur
 *
 */
@Entity
@Table(name="manager")
public class Manager {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="manager_id")
	private Long managerId;
	
	@Column(name="email",length = 100)
	private String email;
	
	@Column(name="first_name",length = 100)
	private String firstName;
	
	@Column(name="last_name",length = 100)
	private String lastName;
	
	@Column(name="password",length = 100)
	private String password;
	
	@Column(name="address",length = 200)
	private String address;
	
	@Column(name="city",length = 100)
	private String city;
	
	@Column(name="dob")
	private Date dob;

	@Transient
	private String dateOfBirth;
	
	public Long getManagerId() {
		return managerId;
	}

	public void setManagerId(Long managerId) {
		this.managerId = managerId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
}
