package cn.smbms.dao.role;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.Role;

public interface RoleMapper {

	/**
	 * 获取角色列表分页
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleList(@Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize)
			throws Exception;

	public List<Role> getRoleListAll() throws Exception;

	/**
	 * 查询全部记录数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getRoleCount() throws Exception;
}
