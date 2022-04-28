package com.jerryboot.springbootdemo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jerryboot.springbootdemo.model.Customer;
import com.jerryboot.springbootdemo.model.CustomerDao;

@RestController
public class CustomerController {

	@Autowired
	private CustomerDao dao;

	@PostMapping("customer/insert")
	public Customer insertCustomer() {
		Customer cus = new Customer();
		cus.setName("館長");
		cus.setLevel(3);
		Customer resCus = dao.save(cus);
		return resCus;
	}

	@PostMapping("customer/insert2")
	public Customer insertCustomer2(@RequestBody Customer cus) {
//		@RequestBody : 傳到 Controller 內的
//		@ResponseBody : Controller 傳給外面的

		Customer resCus = dao.save(cus);

		return resCus;
	}

	@PostMapping("customer/insert3")
	public List<Customer> insertCustomer3(@RequestBody List<Customer> cus) {
		List<Customer> resCus = dao.saveAll(cus);
		return resCus;

	}

	@GetMapping("customer/get")
	public Customer getCustomerById(@RequestParam Integer id) {
		// http://localhost:8080/myapp/customer/get?id=

		Optional<Customer> optional = dao.findById(id);

		if (optional.isPresent()) {
			Customer resCustomer = optional.get();
			return resCustomer;
		}

		return null;
	}

	@GetMapping("customer/get2/{id}")
	public Customer getCustomerById2(@PathVariable Integer id) {
		Optional<Customer> optional = dao.findById(id);

		if (optional.isPresent()) {
			Customer resCustomer = optional.get();
			return resCustomer;
		}

		return null;
	}
	
	@GetMapping("customer/getAll")
	public List<Customer> findAllCustomer(){
		List<Customer> allCustomerList = dao.findAll();
		return allCustomerList;
	}
	
	@PostMapping("customer/delete")
	public void deleteById(@RequestParam Integer id) {

		dao.deleteById(id);
	}
	
	@PostMapping("customer/delete2")
	public void deleteById2(@RequestParam Integer id) {
		Customer cus = dao.getById(id);
		
		dao.delete(cus);
	}
	
	@GetMapping("customer/page/{pageNumber}")
	public List<Customer> findByPage(@PathVariable Integer pageNumber) {
		Pageable pgb = PageRequest.of(pageNumber-1, 3, Sort.Direction.ASC, "id");
		
		Page<Customer> page = dao.findAll(pgb);
		
		List<Customer> list = page.getContent();
		
		return list;
	}

	@GetMapping("customer/page12/{pageNumber1}")
	public List<Customer> findByPage2(@PathVariable Integer pageNumber1){
		Pageable pgb = PageRequest.of(pageNumber1 - 1, 5, Sort.Direction.ASC, "level");
		Page<Customer> page = dao.findAll(pgb);
		List<Customer> list = page.getContent();

		return list;

	}
	
	@GetMapping("customer/findByName")
	public List<Customer> findByName(@RequestParam("name") String name){
		List<Customer> list = dao.findCustomerByName(name);
		return list;
	}
	
	@GetMapping("customer/findByName2")
	public List<Customer> findByName2(@RequestParam("name") String name){
		List<Customer> list = dao.findCustomerByName2(name);
		return list;
	}
	
	@GetMapping("customer/findByName3")
	public List<Customer> findByName3(@RequestParam("name") String name, @RequestParam("level") Integer level){
		List<Customer> list = dao.findCustomerByName3(name, level);
		return list;
	}
	
	@GetMapping("customer/delete")
	public boolean deleteCustomer(@RequestParam("name") String name) {
		dao.deleteCustomerByName(name);
		return true;
	}
	
	@GetMapping("customer/level/{level}")
	public List<Customer> findByLevelOrderById(@PathVariable("level") Integer level){
		return dao.findByLevelOrderByIdDesc(level);
	}

}
