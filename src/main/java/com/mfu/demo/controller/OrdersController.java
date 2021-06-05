package com.mfu.demo.controller;

import com.mfu.demo.entity.OrdersEntity;
import com.mfu.demo.service.OrdersService;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    OrdersService ordersService;

    @PostMapping()
    public ResponseEntity<?> createOrder(@RequestBody @Validated OrdersEntity obj){
        Optional<OrdersEntity> orderOpt = Optional.ofNullable(ordersService.createOrder(obj));
        return ResponseEntity.status(HttpStatus.CREATED).body(orderOpt);
    }

    @PutMapping()
    public ResponseEntity<?> updateOrder(@RequestBody @Validated OrdersEntity updateObj){
        Optional<OrdersEntity> ordersEntityOptional = Optional.ofNullable(ordersService.updateOrder(updateObj));
        if(ordersEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(ordersEntityOptional);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/")
    public ResponseEntity<?> findAllOrder(){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.findAllCustomer());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable int id){
        boolean deleteStatus = ordersService.delateById(id);
        if(deleteStatus == true){
            return ResponseEntity.status(HttpStatus.OK).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<?> findOrderById(@PathVariable int id){
        Optional<OrdersEntity> optionalOrdersEntity = ordersService.findOrderById(id);
        if(optionalOrdersEntity.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(optionalOrdersEntity);
        }else {
            // return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<?> findOrderByNmae(@PathVariable String name){
        List<OrdersEntity> ordersEntityList = ordersService.findOrderByName(name);
        if(!ordersEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(ordersEntityList);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/year/{yearStart}/{yearEnd}")
    public ResponseEntity<?> findOrdersBetweenYear( @PathVariable @DateTimeFormat(pattern="yyyy") String yearStart,
                                                    @PathVariable @DateTimeFormat(pattern="yyyy") String yearEnd) {
        List<OrdersEntity> orderList = ordersService.findOrdersBetweenYear(yearStart, yearEnd);
        if(orderList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(orderList);
        }

    }

    @GetMapping(path = "/amount/{startDate}/{endDate}")
    public ResponseEntity<?> findOrderAmountByOrderDate(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                        @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){
        return ResponseEntity.status(HttpStatus.OK).body(ordersService.findAmountOrder(startDate,endDate));
    }
}
