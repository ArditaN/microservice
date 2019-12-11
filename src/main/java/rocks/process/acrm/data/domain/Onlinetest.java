package rocks.process.acrm.data.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Onlinetest {

	@Id
	@GeneratedValue
	private Long id;
	private String name;


	private String onlinecode;
	@ManyToOne
	@JsonBackReference
	private Teacher teacher;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getOnlinecode() { return onlinecode; }

	public void setOnlinecode(String onlinecode) { this.onlinecode = onlinecode; }

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

}
