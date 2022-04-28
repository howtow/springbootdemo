package com.jerryboot.springbootdemo.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkMessagesDao extends JpaRepository<WorkMessages, Integer> {

	public WorkMessages findFirstByOrderByAddedDesc();
}
