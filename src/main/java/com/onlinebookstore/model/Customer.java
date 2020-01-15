package com.onlinebookstore.model;

import javax.persistence.*;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$")
    private String customerName;


    @Pattern(regexp = "(91)\\s[7-9][0-9]{9}")
    private String mobileNumber;

    @Pattern(regexp = "^[0-9]{6}$|^[0-9]{3}\\s{1}[0-9]{3}$")
    private String pincode;

    private String address;

    @Pattern(regexp = "^[A-Z]{1}[a-z]{2,}$")
    private String city;

    public Customer() {
    }

    public Customer(Long id, String customerName, String mobileNumber, String pincode, String address, String city) {
        this.id = id;
        this.customerName = customerName;
        this.mobileNumber = mobileNumber;
        this.pincode = pincode;
        this.address = address;
        this.city = city;
    }

    public String getCustomerName() {
        return customerName;
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
