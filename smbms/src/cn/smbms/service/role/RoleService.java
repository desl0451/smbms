package cn.smbms.service.role;

import java.util.List;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;

public interface RoleService {
	/**
	 * 获取角色列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<Role> getRoleList(Integer currentPageNo, Integer pageSize) throws Exception;

	/**
	 * 获取全部角色列表
	 * 
	 * @return
	 */
	public List<Role> getRoleListAll() throws Exception;

	/**
	 * 查询全部记录数
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getRoleCount() throws Exception;
	
	/**
	 * 根据ID查询角色信息
	 */
	public Role getRoleById(Integer id);

	/**
	 * 根据roleCode查询出User
	 * 
	 * @param userCode
	 * @return
	 */
	public Role selectRoleCodeExist(String roleCode);
}
