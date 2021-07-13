package com.zf.bootStudy.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class User implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String phone;
	private String password;
	
}
