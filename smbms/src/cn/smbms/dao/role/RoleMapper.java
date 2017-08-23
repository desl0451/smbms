package cn.smbms.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;

public interface RoleMapper {

	/**
	 * ��ȡ��ɫ�б��ҳ
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleList(@Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize)
			throws Exception;
	/**
	 * ����ȫ����ɫ��Ϣ
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListAll() throws Exception;

	/**
	 * ��ѯȫ����¼��
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getRoleCount() throws Exception;

	/**
	 * ����ID��ѯ��ɫ��Ϣ
	 */
	public Role getRoleById(@Param("id") Integer id);
	
	/**
	 * ����roleCode��ѯ��ɫ��Ϣ
	 */
	public Role selectRoleByCode(@Param("roleCode")String roleCode);
	
	/**
	 * ��ӽ�ɫ��Ϣ
	 * @param role
	 * @return
	 */
	public int insert(@Param("role")Role role);
}
