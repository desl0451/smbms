package cn.smbms.service.role;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.smbms.dao.role.RoleMapper;
import cn.smbms.pojo.Role;

@Service
public class RoleServiceImpl implements RoleService {

	@Resource
	private RoleMapper roleMapper;

	@Override
	public List<Role> getRoleList(Integer currentPageNo, Integer pageSize) throws Exception {
		currentPageNo = (currentPageNo - 1) * pageSize;
		return roleMapper.getRoleList(currentPageNo, pageSize);
	}

	@Override
	public List<Role> getRoleListAll() throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.getRoleListAll();
	}

	/**
	 * 根据roleCode查询角色信息
	 */
	@Override
	public Role selectRoleCodeExist(String roleCode) {
		// TODO Auto-generated method stub
		return roleMapper.selectRoleByCode(roleCode);
	}

	/**
	 * 查询全部记录数
	 */
	@Override
	public int getRoleCount() throws Exception {
		// TODO Auto-generated method stub
		return roleMapper.getRoleCount();
	}

	/**
	 * 根据ID查询角色信息
	 */
	@Override
	public Role getRoleById(Integer id) {
		// TODO Auto-generated method stub
		return roleMapper.getRoleById(id);
	}
}
