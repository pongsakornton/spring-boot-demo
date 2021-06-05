package com.mfu.demo.repository;

import com.mfu.demo.entity.CustomerEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CustomerRepository extends CrudRepository<CustomerEntity,Integer> {

    public List<CustomerEntity> findByCustomerName(String name);// jpql

    public List<CustomerEntity> findByCustomerNameAndActiveFlag(String name,String flag);

    public List<CustomerEntity> findByBirthdateBetween(Date startDate , Date endDate);

    @Query(value = "SELECT * FROM customer c WHERE c.address LIKE %?1% ORDER BY customer_id",nativeQuery = true)
    public List<CustomerEntity> findByAddress(String address);

}
