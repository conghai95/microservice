package com.example.cmc.entity;

import javax.persistence.*;

public class Role {

	private Long id;

	@Enumerated(EnumType.STRING)
	@Column(length = 60)
	private RoleName name;

	public Role() {

	}

	public Role(RoleName name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
}