package com.mfu.demo.controller;

import com.mfu.demo.entity.CustomerEntity;
import com.mfu.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
@CrossOrigin
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody @Validated CustomerEntity obj){
        log.info("cusOBJ {}",obj);
        Optional<CustomerEntity> customerObj = Optional.ofNullable(customerService.createCustomer(obj));
        return ResponseEntity.status(HttpStatus.CREATED).body(customerObj);
    }

    @PutMapping
    public ResponseEntity<?> updateCustomer(@RequestBody @Validated CustomerEntity obj){
        log.info("cusOBJ {}",obj);
        Optional<CustomerEntity> customerEntityOptional = Optional.ofNullable(customerService.updateCustomer(obj));
        if (!customerEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(customerEntityOptional);
        }

    }

    @GetMapping(path = "/")
    public ResponseEntity<?> findAllCustomer(){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.findAllCustomer());
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteCustomer(@PathVariable int id){
        boolean deleteStatus = customerService.deleteCustomer(id);
        if(deleteStatus == true) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(path = "/id/{customerId}")
    public ResponseEntity<?> findCustomerById(@PathVariable int customerId){
        Optional<CustomerEntity> customerEntityOptional = customerService.findCustomerById(customerId);
        if(customerEntityOptional.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(customerEntityOptional);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(path = "/name/{customerName}")
    public ResponseEntity<?> findByCustomerName(@PathVariable String customerName){

        List<CustomerEntity> customerEntityList = customerService.findByCustomerName(customerName);
        log.info("Data : {}",customerEntityList);
        if(!customerEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(customerEntityList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

    }

    @GetMapping(path = "/name/{customerName}/flag/{activeFlag}")
    public ResponseEntity<?> findByCustomerNameAndActiveFlag(@PathVariable String customerName,@PathVariable String activeFlag){
        List<CustomerEntity> customerEntityList = customerService.findByCustomerNameAndActiveFlag(customerName,activeFlag);
        if(!customerEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(customerEntityList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/birthdate/{startDate}/{endDate}")
    public ResponseEntity<?> findByBirthdateBetween(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
                                                 @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate){

        List<CustomerEntity> customerEntityList = customerService.findByBirthdateBetween(startDate,endDate);
        if(!customerEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(customerEntityList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path = "/address/{addStr}")
    public ResponseEntity<?> findByAddress(@PathVariable String addStr){
        log.info("addStr {}",addStr);
        List<CustomerEntity> customerEntityList = customerService.findByAddress(addStr);
        log.info("customerEntityList {}",customerEntityList);
        if(!customerEntityList.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body(customerEntityList);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping(path="/amount")
    public ResponseEntity<?> getAmount(){

        return  ResponseEntity.status(HttpStatus.OK).body(customerService.getCustomerAmount());

    }


}
