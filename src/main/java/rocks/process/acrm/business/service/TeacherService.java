/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.business.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import rocks.process.acrm.data.domain.Teacher;
import rocks.process.acrm.data.repository.TeacherRepository;

import javax.annotation.PostConstruct;
import javax.validation.Valid;
import javax.validation.Validator;

@Service
@Validated
public class TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;
    @Autowired
    Validator validator;

    public void saveTeacher(@Valid Teacher teacher) throws Exception {
        if (teacher.getId() == null) {
            if (teacherRepository.findByEmail(teacher.getEmail()) != null) {
                throw new Exception("Email address " + teacher.getEmail() + " already assigned another teacher.");
            }
        } else if (teacherRepository.findByEmailAndIdNot(teacher.getEmail(), teacher.getId()) != null) {
            throw new Exception("Email address " + teacher.getEmail() + " already assigned another teacher.");
        }
        teacherRepository.save(teacher);
    }

    public Teacher getCurrentTeacher() {
        String userEmail = "demo@demo.ch";
        return teacherRepository.findByEmail(userEmail);
    }

    @PostConstruct
    private void init() throws Exception {
        Teacher teacher = new Teacher();
        teacher.setName("Demo");
        teacher.setEmail("demo@demo.ch");
        teacher.setPassword("password");
        this.saveTeacher(teacher);
    }
}
