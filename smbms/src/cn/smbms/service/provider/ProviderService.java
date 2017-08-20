package cn.smbms.service.provider;

import java.util.List;

import cn.smbms.pojo.Provider;

public interface ProviderService {
	/**
	 * ����������ѯ��Ӧ���б�
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public List<Provider> getProviderList(String queryProCode, String queryProName, Integer currentPageNo,
			Integer pageSize) throws Exception;

	/**
	 * ����������ѯ��Ӧ�̼�¼��
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getProviderCount(String queryProCode, String queryProName) throws Exception;

	/**
	 * ��ѯ���й�Ӧ�̼�¼
	 */
	public List<Provider> getProviderAll();
	
	/**
	 * ����ID��ȡ��Ӧ����Ϣ
	 */
	public Provider getProviderById(Integer id);
	
	/**
	 * ���湩Ӧ����Ϣ
	 */
	public boolean add(Provider provider);
}
