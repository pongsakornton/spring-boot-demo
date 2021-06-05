package com.mfu.demo.service;

import com.mfu.demo.entity.OrdersEntity;
import com.mfu.demo.repository.OrdersRepository;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import java.rmi.server.ExportException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.*;

@Service
@Slf4j
public class OrdersService {

    @Autowired
    OrdersRepository ordersRepository;

    public OrdersEntity createOrder(OrdersEntity obj){
        try {
            //add order received day
            obj.setOrderReceivedDate(addDays(obj.getOrderDate(),3));
            return ordersRepository.save(obj);
        } catch(Exception e){
            throw e;
        }
    }

    public OrdersEntity updateOrder(OrdersEntity ordersObj){

        try{
            Optional<OrdersEntity> ordersEntityOptional = ordersRepository.findById(ordersObj.getOrderId());
            if(ordersEntityOptional.isPresent()){
                ordersObj.setOrderReceivedDate(addDays(ordersObj.getOrderDate(),3));
                return ordersRepository.save(ordersObj);
            } else {
                return null;
            }
        }catch(Exception e){
            throw e;
        }
    }

    public List<OrdersEntity> findAllCustomer(){
        return (List<OrdersEntity>) ordersRepository.findAll();
    }

    public boolean delateById(int id) {
        try{
            ordersRepository.deleteById(id);
            return true;
        }catch(EmptyResultDataAccessException e){
            return false;
        }
    }

    public Optional<OrdersEntity> findOrderById(int id){
        try{
            return ordersRepository.findById(id);
        } catch(Exception e){
            throw e;
        }
    }

    public List<OrdersEntity> findOrderByName(String name){
        try{
            return ordersRepository.findByOrderName(name);
        }catch(Exception e){
            throw e;
        }
    }

    public List<OrdersEntity> findOrdersBetweenYear(String yearStart, String yearEnd){
        log.info("yearStart {}",yearStart);
        log.info("yearEnd {}",yearEnd);
        try{
            return ordersRepository.findOrdersByBetweenYear(yearStart, yearEnd);
        }catch(Exception e){
            throw e;
        }
    }

    public Object findAmountOrder(Date startDate , Date endDate){
        try{
            List<OrdersEntity> ordersList = ordersRepository.findByOrderDateBetween(startDate,endDate);

            HashMap<String, Integer> orderAmount = new HashMap<String, Integer>();
            orderAmount.put("amount", ordersList.size() );

            return orderAmount;
        }catch(Exception e){
            throw e;
        }
    }

    private Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

}
