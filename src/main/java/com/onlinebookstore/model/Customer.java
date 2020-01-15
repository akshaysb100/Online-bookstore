package com.onlinebookstore.model;

import javax.persistence.*;

@Entity
@Table(name = "Customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String customerName;
    private String mobileNumber;
    private String pincode;
    private String address;
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
