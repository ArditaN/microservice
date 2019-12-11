/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rocks.process.acrm.data.domain.Onlinetest;
import rocks.process.acrm.data.repository.OnlinetestRepository;

import javax.validation.Valid;
import java.util.List;

@Service
@Validated
public class OnlinetestService {

	@Autowired
	private OnlinetestRepository onlinetestRepository;
	@Autowired
	private TeacherService teacherService;

	public Onlinetest editOnlinetest(@Valid Onlinetest onlinetest) throws Exception {
		if (onlinetest.getId() == null) {
			if (onlinetestRepository.findByOnlinecode(onlinetest.getOnlinecode()) == null) {
				onlinetest.setTeacher(teacherService.getCurrentTeacher());
				return onlinetestRepository.save(onlinetest);
			}
			throw new Exception("Online Code " + onlinetest.getOnlinecode() + " already assigned to a test.");
		}
		if (onlinetestRepository.findByOnlinecodeAndId(onlinetest.getOnlinecode(), onlinetest.getId()) == null) {
			if (onlinetest.getTeacher() == null) {
				onlinetest.setTeacher(teacherService.getCurrentTeacher());
			}
			return onlinetestRepository.save(onlinetest);
		}
		throw new Exception("Online Code " + onlinetest.getOnlinecode() + " already assigned to a online test.");
	}

	public void deleteOnlinetest(Long onlinetestId)
	{
		onlinetestRepository.deleteById(onlinetestId);
	}
	
	public Onlinetest findOnlinetestById(Long teacherId) throws Exception {
		List<Onlinetest> onlinetestList = onlinetestRepository.findByIdAndTeacherId(teacherId, teacherService.getCurrentTeacher().getId());
		if(onlinetestList.isEmpty()){
			throw new Exception("No teacher with ID "+ teacherId +" found.");
		}
		return onlinetestList.get(0);
	}

	public List<Onlinetest> findAllOnlinetests() {
		return onlinetestRepository.findByTeacherId(teacherService.getCurrentTeacher().getId());
	}
	
}
