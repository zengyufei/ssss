package com.xunmo.biz.table;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class GenView implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String name;
	private String fullPath;
	private String parentDir;
	private String suffix;
	private String fileName;
	private String language;
	private String code;




}
