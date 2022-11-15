package com.xunmo.biz.table;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GenDataVO implements Serializable  {

	private static final long serialVersionUID = 1L;

	private List<DataKv> dataKvList;
	private GenData genData;

}
