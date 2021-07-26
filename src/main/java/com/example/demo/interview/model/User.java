package com.example.demo.interview.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.PrePersist;


public class User {

	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userId", insertable = false, updatable = false, nullable = false)
	private UUID userId;

	@PrePersist
	public void initializeUUID() {
	    if (userId == null) {
	        userId = UUID.randomUUID();
	    }
	}

	private String id1;

	private String id2;
	
	public UUID getUserId() {
		return userId;
    }

	public void setId(UUID id) {
		this.userId = id;
	}
	
	public String getId1() {
		return id1;
	}

	public void setId1(String id1) {
		this.id1 = id1;
	}

	public String getId2() {
		return id2;
	}

	public void setId2(String id2) {
		this.id2 = id2;
	}

	@Override
	public String toString() {
		return "User [id1 =" + id1 + ", id2 =" + id2 + "]";
	}

}
