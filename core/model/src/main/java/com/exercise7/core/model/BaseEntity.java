package com.exercise7.core.model;

import javax.persistence.MappedSuperclass;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;

@MappedSuperclass
public abstract class BaseEntity {
	private Long id;

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", unique=true, nullable=false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long input) {
		this.id = input;
	}
}
