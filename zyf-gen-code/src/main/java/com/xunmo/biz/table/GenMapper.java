package com.xunmo.biz.table;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * Mapper: 系统角色表
 * @author kong
 */
@Mapper
public interface GenMapper {



	List<Database> queryAllDatabases();

	/**
	 * @return
	 */
	long countAllTables();

	/**
	 * @return
	 */
	List<TableInfo> queryAllTables(Map<String, Object> map);

	/**
	 * @return
	 */
	TableInfo queryTable(String tableName);

	@MapKey(value="id")
	List<Map<String, Object>> getAllSystem(Map<String, Object> map);

	@MapKey(value="id")
	List<Map<String, Object>> getParentMenus(Map<String, Object> map);

	List<Map<String, Object>> getAllFieldByTableName(String tableName);

	void addGenTable(@Param("tables") List<GenTable> tables);
	void delGenTable(Long id);
	void updateGenTable(GenTable genTable);
	GenTable getGenTableById(Long genTableId);
	long countGenTable(Map<String, Object> map);
	List<GenTable> getGenTable(Map<String, Object> map);

	void addGenField(@Param("fields") List<GenField> fields);
	void delGenField(Long genTableId);
	void updateGenField(GenField genField);
	long countGenField(Map<String, Object> map);
	List<GenField> getGenField(Map<String, Object> map);

	void addGenData(GenData genData);
	void delGenData(Long genDataId);
	void updateGenData(GenData genData);
	GenData getGenDataById(Long genDataId);
	List<GenData> getGenDataList(Map<String, Object> map);

	void addDataKv(DataKv dataKv);
	void updateDataKv(DataKv dataKv);
	void delDataKv(Map<String, Object> map);
	List<DataKv> getDataKv(Map<String, Object> map);

	List<GenKv> getGenKv(Map<String, Object> map);
	void addGenKv(@Param("genKvs") List<GenKv> genKvs);
	void delGenKv(Map<String, Object> map);

	void addDatabase(Database database);
	Database getDatabaseById(Long databaseId);
	void delDatabase(Long id);
	void updateDatabase(Database database);
}
