package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderService {
	/***********************************************************/
	/* 查询 */
	/***********************************************************/
	/**
	 * 根据条件查询供应商列表
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public List<Provider> getProviderList(String queryProCode, String queryProName, Integer currentPageNo,
			Integer pageSize) throws Exception;

	/**
	 * 根据条件查询供应商记录数
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getProviderCount(String queryProCode, String queryProName) throws Exception;

	/**
	 * 查询所有供应商记录
	 */
	public List<Provider> getProviderAll();

	/**
	 * 根据ID提取供应商信息
	 */
	public Provider getProviderById(Integer id);

	/****************************************************************/
	/* 添加修改删除 */
	/****************************************************************/
	/**
	 * 保存供应商信息
	 */
	public boolean add(Provider provider);
	/**
	 * 修改供应商信息
	 * @param provider
	 * @return
	 */
	public boolean update(Provider provider);

	/**
	 * 删除供应商信息
	 */
	public boolean delete(Integer id);
}
