package com.mfu.demo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name="orders")
public class OrdersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="order_id")
    private int orderId;

    @Column(name="order_name")
    private String orderName;

    @Column(name = "order_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,timezone = "Asia/Bangkok")
    private Date orderDate;

    @Column(name = "order_detail")
    private String orderDetail;

    @Column(name = "order_received_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING,timezone = "Asia/Bangkok")
    private Date orderReceivedDate;
}
