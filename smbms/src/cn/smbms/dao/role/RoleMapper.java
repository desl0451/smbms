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
	/**
	 * 返回全部角色信息
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleListAll() throws Exception;

	/**
	 * 查询全部记录数
	 * 
	 * @return
	 * @throws Exception
	 */
	public int getRoleCount() throws Exception;

	/**
	 * 根据ID查询角色信息
	 */
	public Role getRoleById(@Param("id") Integer id);
	
	/**
	 * 根据roleCode查询角色信息
	 */
	public Role selectRoleByCode(@Param("roleCode")String roleCode);
	
	/**
	 * 添加角色信息
	 * @param role
	 * @return
	 */
	public int insert(@Param("role")Role role);
}
