package com.example.multipledatasources.model.member;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
// @Table(name = "members", schema = "memberdb")
@Table(name = "members")
@Data
public class Member {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String memberId;
}
