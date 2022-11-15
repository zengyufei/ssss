package com.xunmo.biz.table;

import java.io.Serializable;

import lombok.Data;

@Data
public class Database implements Serializable {

	private static final long serialVersionUID = 1L;


	// ---------- 表中字段 ----------
	/**
	 *  
	 */
	private Long id;

	/**
	 *  
	 */
	private String name;	

	/**
	 *  
	 */
	private String type;	

	/**
	 *  
	 */
	private String username;	

	/**
	 *  
	 */
	private String password;	

	/**
	 *  
	 */
	private String conType;	

	/**
	 *  
	 */
	private String host;	

	/**
	 *  
	 */
	private Integer port;	

	/**
	 *  
	 */
	private String dataSchema;

	/**
	 *  
	 */
	private String jdbcUrl;	

	/**
	 *  
	 */
	private String createTime;	





	


}
