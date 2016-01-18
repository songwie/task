package com.songwie.task.dao;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.songwie.task.model.ScheduleJobBean;

@Repository
public interface ScheduleJobDao extends JpaRepository<ScheduleJobBean, Serializable> {
 
 
}
