package com.mfu.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="customer")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "birthdate")
    @JsonFormat(shape = JsonFormat.Shape.STRING,timezone = "Asia/Bangkok")
    private Date birthdate;

    @Column(name = "address")
    private String address;

    @Column(name = "active_flag")
    private  String activeFlag;

}
