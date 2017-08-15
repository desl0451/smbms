package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderMapper {

	/**
	 * 查询供应商总数量
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Provider> getProviderList(@Param("proCode") String proCode, @Param("proName") String proName,
			@Param("form") Integer currentPageNo, @Param("pageSize") Integer pageSize) throws Exception;

	/**
	 * 查询全部记录数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getProviderCount(@Param("proCode") String proCode, @Param("proName") String proName) throws Exception;

	/**
	 * 查询供应商总数量
	 */
	public List<Provider> getProviderAll() throws Exception;
}
