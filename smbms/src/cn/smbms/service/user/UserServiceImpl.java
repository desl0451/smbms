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
	public boolean add(User user) throws Exception {
		boolean bl = false;
		int id = userMapper.add(user);
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
		System.out.println(list.size()+"============");
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
	public User selectUserCodeExist(String userCode) throws Exception {
		// TODO Auto-generated method stub
		User user = userMapper.getLoginUser(userCode);
		return user;
	}

	/*
	 * 
	 */
	@Override
	public boolean deleteUserById(Integer delId) throws Exception {
		boolean bl = false;
		int did = userMapper.deleteUserById(delId);
		if (did > 0) {
			bl = true;
		}
		return bl;
	}

	/*
	 * 根据ID修改信息
	 */
	@Override
	public User getUserById(Integer id) throws Exception {
		// TODO Auto-generated method stub
		User user = userMapper.getUserById(id);
		return user;
	}

	/*
	 * modify User对象 修改
	 */
	@Override
	public boolean modify(User user) throws Exception {
		// TODO Auto-generated method stub
		boolean bl = false;
		int mid = userMapper.modify(user);
		if (mid > 0) {
			bl = true;
		}
		return bl;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cn.smbms.service.user.UserService#updatePwd(java.lang.Integer,
	 * java.lang.String)
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
