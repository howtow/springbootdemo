package com.jerryboot.springbootdemo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jerryboot.springbootdemo.model.WorkMessages;
import com.jerryboot.springbootdemo.model.WorkMessagesDao;

@Service
@Transactional
public class WorkMessagesService {
	
	@Autowired
	private WorkMessagesDao dao;
	
	public void save(WorkMessages msg) {
		dao.save(msg);
	}
	
	public WorkMessages getFirstNewMsg() {
		return dao.findFirstByOrderByAddedDesc();
	}

}
