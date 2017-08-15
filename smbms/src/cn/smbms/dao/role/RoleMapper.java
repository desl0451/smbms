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

	public List<Role> getRoleListAll() throws Exception;

	/**
	 * ��ѯȫ����¼��
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getRoleCount() throws Exception;
}
