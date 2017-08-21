package cn.smbms.dao.provider;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Provider;

public interface ProviderMapper {

	/**
	 * ��ѯ��Ӧ��������
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Provider> getProviderList(@Param("proCode") String proCode, @Param("proName") String proName,
			@Param("form") Integer currentPageNo, @Param("pageSize") Integer pageSize) throws Exception;

	/**
	 * ��ѯȫ����¼��
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getProviderCount(@Param("proCode") String proCode, @Param("proName") String proName) throws Exception;

	/**
	 * ��ѯ��Ӧ��������
	 */
	public List<Provider> getProviderAll();

	/**
	 * ����ID��ȡ��Ӧ��
	 */
	public Provider getProviderById(@Param("id") Integer id);

	/**
	 * ��ӹ�Ӧ��
	 */
	public int insert(@Param("provider") Provider provider);

	/**
	 * ɾ����Ӧ��
	 */
	public int delete(@Param("id") Integer id);
}
