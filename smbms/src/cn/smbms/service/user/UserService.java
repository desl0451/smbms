package cn.smbms.service.user;

import java.util.List;

import cn.smbms.pojo.User;

public interface UserService {

	/**
	 * �û���¼
	 * 
	 * @param userCode
	 * @param userPassword
	 * @return
	 */
	public User login(String userCode, String userPassword) throws Exception;

	/**
	 * �����û���Ϣ
	 * 
	 * @param user
	 * @return
	 */
	public boolean add(User user) throws Exception;

	/**
	 * ����������ѯ�û��б�
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public List<User> getUserList(String queryUserName, Integer queryUserRole, Integer currentPageNo, Integer pageSize)
			throws Exception;

	/**
	 * ����������ѯ�û����¼��
	 * 
	 * @param queryUserName
	 * @param queryUserRole
	 * @return
	 */
	public int getUserCount(String queryUserName, Integer queryUserRole) throws Exception;

	/**
	 * ����userCode��ѯ��User
	 * 
	 * @param userCode
	 * @return
	 */
	public User selectUserCodeExist(String userCode);

	/**
	 * ����IDɾ��user
	 * 
	 * @param delId
	 * @return
	 */
	public boolean deleteUserById(Integer delId) throws Exception;

	/**
	 * ����ID����user
	 * 
	 * @param id
	 * @return
	 */
	public User getUserById(Integer id) throws Exception;

	/**
	 * �޸��û���Ϣ
	 * 
	 * @param user
	 * @return
	 */
	public boolean modify(User user) throws Exception;

	/**
	 * ����userId�޸�����
	 * 
	 * @param id
	 * @param pwd
	 * @return
	 */
	public boolean updatePwd(Integer id, String pwd) throws Exception;
}
