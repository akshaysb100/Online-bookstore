package com.onlinebookstore.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$")
    private String customerName;

    @Pattern(regexp = "[7-9][0-9]{9}")
    private String mobileNumber;

    @Pattern(regexp = "^[0-9]{6}$|^[0-9]{3}\\s{1}[0-9]{3}$")
    private String pincode;

    private String address;

    private String country;

    @Pattern(regexp = "^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$")
    private String emailId;

    public String getCountry() {
        return country;
    }

    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$")
    private String city;

    public Customer() {
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public String getPincode() {
        return pincode;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "customerName='" + customerName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", pincode='" + pincode + '\'' +
                ", address='" + address + '\'' +
                ", city='" + city + '\'' +
                '}';
    }
}
