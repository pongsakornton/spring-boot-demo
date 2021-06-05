package com.mfu.demo.repository;

import java.util.Date;
import java.util.List;

import com.mfu.demo.entity.OrdersEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface OrdersRepository extends CrudRepository<OrdersEntity,Integer> {
    public List<OrdersEntity> findByOrderName(String name);

    @Query(value="select * from orders o where substring(order_date,1,4) BETWEEN ?1 and ?2",nativeQuery= true)
    public List<OrdersEntity> findOrdersByBetweenYear(String yearStart,String yearEnd);

    public List<OrdersEntity> findByOrderDateBetween(Date startDate , Date endDate);

}
