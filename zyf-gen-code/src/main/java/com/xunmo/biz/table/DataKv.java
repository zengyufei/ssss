package com.xunmo.biz.table;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;

@Data
public class DataKv implements Serializable  {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String key;
	private Long dataId;
	private String source;
	private String type;
	private JSONObject value;

}
