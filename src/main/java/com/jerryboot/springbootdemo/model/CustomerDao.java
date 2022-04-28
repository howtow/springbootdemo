package com.jerryboot.springbootdemo.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CustomerDao extends JpaRepository<Customer, Integer> {
	
	// HQL
	@Query("from Customer where name = :name")
	public List<Customer> findCustomerByName(@Param("name") String name);
	
	// 原生 SQL (nativeQuery=true)
	@Query(value="select * from customer where name = :name", nativeQuery=true)
	public List<Customer> findCustomerByName2(@Param("name") String name);

	@Query(value="select * from customer where name = :name and level = :level", nativeQuery=true)
	public List<Customer> findCustomerByName3(@Param("name") String name, @Param("level") Integer level);
	
	
//	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#transactions
	
	@Query(value="delete from customer where name = ?1", nativeQuery = true)
	@Transactional // 沒有 Service 標註此項的話，要在修改資料的地方標註
	@Modifying
	public void deleteCustomerByName(String name);
	
	
//	https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
	public List<Customer> findByLevelOrderByIdDesc(Integer level);
	
	
	
}
