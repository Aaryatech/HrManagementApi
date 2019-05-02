package com.ats.hrmgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ats.hrmgt.model.Setting;

public interface SettingRepo extends JpaRepository<Setting, Integer>{

	Setting findByKey(String key);

}
