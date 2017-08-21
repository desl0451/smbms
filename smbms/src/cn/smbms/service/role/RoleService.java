package cn.smbms.service.role;

import java.util.List;

import cn.smbms.pojo.Role;

public interface RoleService {
	/**
	 * ��ȡ��ɫ�б�
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleList(Integer currentPageNo, Integer pageSize) throws Exception;

	/**
	 * ��ȡȫ����ɫ�б�
	 * 
	 * @return
	 */
	public List<Role> getRoleListAll() throws Exception;

	/**
	 * ��ѯȫ����¼��
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getRoleCount() throws Exception;
	
	/**
	 * ����ID��ѯ��ɫ��Ϣ
	 */
	public Role getRoleById(Integer id);

}
