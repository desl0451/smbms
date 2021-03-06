package cn.smbms.service.user;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.smbms.dao.user.UserMapper;
import cn.smbms.pojo.User;

@Service
public class UserServiceImpl implements UserService {
	@Resource
	private UserMapper userMapper;

	@Override
	public boolean addUser(User user) throws Exception {
		boolean bl = false;
		int id = userMapper.insert(user);
		if (id > 0) {
			bl = true;
		}
		return bl;
	}

	/*
	 */
	@Override
	public List<User> getUserList(String queryUserName, Integer queryUserRole, Integer currentPageNo, Integer pageSize)
			throws Exception {
		// TODO Auto-generated method stub
		currentPageNo = (currentPageNo - 1) * pageSize;

		List<User> list = userMapper.getUserList(queryUserName, queryUserRole, currentPageNo, pageSize);
		System.out.println(list.size() + "============");
		return list;
	}

	/*
	 */
	@Override
	public int getUserCount(String queryUserName, Integer queryUserRole) throws Exception {
		// TODO Auto-generated method stub
		int id = userMapper.getUserCount(queryUserName, queryUserRole);
		return id;
	}

	/*
	 * 
	 */
	@Override
	public User selectUserCodeExist(String userCode) {
		// TODO Auto-generated method stub
		User user = userMapper.getLoginUser(userCode);
		return user;
	}

	/*
	 * 
	 */
	@Override
	public boolean deleteUserById(Integer delId) {
		boolean bl = false;
		int did = userMapper.delete(delId);
		if (did > 0) {
			bl = true;
		}
		return bl;
	}

	/*
	 * 根据ID修改信息
	 */
	@Override
	public User getUserById(String id) throws Exception {
		// TODO Auto-generated method stub
		User user = userMapper.getUserById(id);
		return user;
	}

	/*
	 * modify User对象 修改
	 */
	@Override
	public boolean modify(User user) {
		// TODO Auto-generated method stub
		boolean bl = false;
		int mid = userMapper.update(user);
		if (mid > 0) {
			bl = true;
		}
		return bl;
	}

	/**
	 * 修改用户密码
	 */
	@Override
	public boolean updatePwd(Integer id, String pwd) throws Exception {
		// TODO Auto-generated method stub
		boolean bl = false;
		int uid = userMapper.updatePwd(id, pwd);
		if (uid > 0) {
			bl = true;
		}
		return bl;
	}

	/**
	 * 用户登录
	 */
	@Override
	public User login(String userCode, String userPassword) throws Exception {
		// TODO Auto-generated method stub
		User user = null;
		user = userMapper.getLoginUser(userCode);
		// 匹配密码
		if (null != user) {
			if (!user.getUserPassword().equals(userPassword))
				user = null;
		}
		return user;
	}

}
