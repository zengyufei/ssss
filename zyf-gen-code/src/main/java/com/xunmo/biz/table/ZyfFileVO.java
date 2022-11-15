package com.xunmo.biz.table;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class ZyfFileVO implements Serializable  {

	private static final long serialVersionUID = 1L;

	private String name;
	private List<?> children;

}
