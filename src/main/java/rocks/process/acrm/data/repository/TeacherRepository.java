/*
 * Copyright (c) 2018. University of Applied Sciences and Arts Northwestern Switzerland FHNW.
 * All rights reserved.
 */

package rocks.process.acrm.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.process.acrm.data.domain.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
	Teacher findByEmail(String email);
	Teacher findByEmailAndIdNot(String email, Long agentId);
}
