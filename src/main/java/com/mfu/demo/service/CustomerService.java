package com.mfu.demo.service;

import com.mfu.demo.entity.CustomerEntity;
import com.mfu.demo.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public CustomerEntity createCustomer(CustomerEntity customerObj){
        try {
            return customerRepository.save(customerObj);
        } catch (Exception e){
            throw e;
        }
    }

    public CustomerEntity updateCustomer(CustomerEntity customerObj){
        try {

            Optional<CustomerEntity> customerEntityOptional = customerRepository.findById(customerObj.getCustomerId());
            if (customerEntityOptional.isPresent()){
                return customerRepository.save(customerObj);
            }else {
                return null;
            }
        } catch (Exception e){
            throw e;
        }

    }

    public List<CustomerEntity> findAllCustomer(){
        return (List<CustomerEntity>)customerRepository.findAll();
    }

    public boolean deleteCustomer(int id){
        try {
            customerRepository.deleteById(id);
            return true;
        } catch(EmptyResultDataAccessException e){
            return false;
        }
    }


    public Optional<CustomerEntity> findCustomerById(int id){
        try{
            return customerRepository.findById(id);
        }catch(Exception e){
            throw e;
        }
        
    }

    public List<CustomerEntity> findByCustomerName(String name){
        try{
            return customerRepository.findByCustomerName(name);
        }catch(Exception e){
            throw e;
        }

        
    }

    public List<CustomerEntity> findByCustomerNameAndActiveFlag(String name , String flag){
        try{

        }catch(Exception e){
            throw e;
        }
        return customerRepository.findByCustomerNameAndActiveFlag(name,flag);
    }

    public List<CustomerEntity> findByBirthdateBetween(Date strDate, Date endDate){
        try{
            return customerRepository.findByBirthdateBetween(strDate,endDate);
        }catch(Exception e){
            throw e;
        }
    }

    public List<CustomerEntity> findByAddress(String address){
        try{
            return customerRepository.findByAddress(address);
        }catch (Exception e){
            throw e;
        }
    }

    public Integer getCustomerAmount(){
        try{
            List<CustomerEntity> customerList =  (List<CustomerEntity>)customerRepository.findAll();
            return customerList.size();
        }catch (Exception e){
            throw e;
        }
    }
}
