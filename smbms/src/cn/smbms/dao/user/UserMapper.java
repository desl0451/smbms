package cn.smbms.dao.user;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.smbms.pojo.User;

public interface UserMapper {
	/**
	 * ͨ��userCode��ȡUser
	 * 
	 * @param userCode
	 * @return
	 * @throws Exception
	 */
	public User getLoginUser(@Param("userCode") String userCode);

	/**
	 * �����û���Ϣ
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int add(User user) throws Exception;

	/**
	 * ͨ��������ѯ-userList
	 * 
	 * @param userName
	 * @param userRole
	 * @param currentPageNo
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	public List<User> getUserList(@Param("userName") String userName, @Param("userRole") Integer userRole,
			@Param("from") Integer currentPageNo, @Param("pageSize") Integer pageSize) throws Exception;

	/**
	 * ͨ��������ѯ-�û����¼��
	 * 
	 * @param userName
	 * @param userRole
	 * @return
	 * @throws Exception
	 */
	public int getUserCount(@Param("userName") String userName, @Param("userRole") Integer userRole) throws Exception;

	/**
	 * ͨ��userIdɾ��user
	 * 
	 * @param delId
	 * @return
	 * @throws Exception
	 */
	public int deleteUserById(@Param("id") Integer id);

	/**
	 * ͨ��userId��ȡuser
	 * 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public User getUserById(@Param("id") String id) throws Exception;

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param user
	 * @return
	 * @throws Exception
	 */
	public int modify(User user) throws Exception;

	/**
	 * �޸ĵ�ǰ�û�����
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(@Param("id") Integer id, @Param("userPassword") String pwd) throws Exception;
}
