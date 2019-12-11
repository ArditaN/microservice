/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.acrm.data.domain.Onlinetest;

import java.util.List;

@Repository
public interface OnlinetestRepository extends JpaRepository<Onlinetest, Long> {
	Onlinetest findByOnlinecode(String onlinecode);
	Onlinetest findByOnlinecodeAndId(String on, Long teacherId);
	List<Onlinetest> findByTeacherId(Long teacherId);
	List<Onlinetest> findByIdAndTeacherId(Long onlinetestId, Long teacherId);
}
