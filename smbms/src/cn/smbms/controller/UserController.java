package cn.smbms.controller;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Role;
import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/user")
public class UserController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;

	@Resource
	private RoleService roleService;

	/**
	 * ��ʾȫ���û��б�
	 * 
	 * @param model
	 * @param queryUserName
	 * @param queryUserRole
	 * @param pageIndex
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list.html")
	public String getUserList(Model model, @RequestParam(value = "queryname", required = false) String queryUserName,
			@RequestParam(value = "queryUserRole", required = false) String queryUserRole,
			@RequestParam(value = "pageIndex", required = false) String pageIndex) throws Exception {
		logger.info("getUserList----->queryUserName:" + queryUserName);
		logger.info("getUserList----->queryUserRole:" + queryUserRole);
		logger.info("getUserList----->pageIndex:" + pageIndex);
		int _queryUserRole = 0;
		List<User> userList = null;
		// ����ҳ������
		int pageSize = Constants.pageSize;
		// ��ǰҳ��
		int currentPageNo = 1;
		if (queryUserName == null) {
			queryUserName = "";
		}
		if (queryUserRole != null && !queryUserRole.equals("")) {
			_queryUserRole = Integer.parseInt(queryUserRole);
		}
		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/user/syserror.html";
			}
		}
		// ������(��)
		int totalCount = userService.getUserCount(queryUserName, _queryUserRole);
		// ��ҳ��
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// ������ҳ��βҳ
		if (currentPageNo < 1) {
			currentPageNo = 1;
		} else if (currentPageNo > totalPageCount) {
			currentPageNo = totalPageCount;
		}
		userList = userService.getUserList(queryUserName, _queryUserRole, currentPageNo, pageSize);
		model.addAttribute("userList", userList);
		List<Role> roleList = null;
		roleList = roleService.getRoleListAll();
		model.addAttribute("roleList", roleList);
		model.addAttribute("queryUserName", queryUserName);
		model.addAttribute("queryUserRole", queryUserRole);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "user/userlist";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "common/syserror";
	}

	/**
	 * ��ת����û�����
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/useradd.html", method = RequestMethod.GET)
	public String addUser(@ModelAttribute("user") User user) {
		return "user/useradd";
	}

	

	
	/**
	 * ��ʾ�û���Ϣҳ����ת
	 * 
	 * @param id
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/view/{id}", method = RequestMethod.GET)
	public String view(@PathVariable String id, Model model) throws Exception {
		logger.debug("view id===================" + id);
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		return "user/userview";
	}

	@RequestMapping(value = "/usermodify/{id}", method = RequestMethod.GET)
	public String usermodify(Model model, @PathVariable String id) throws Exception {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		List<Role> roleList = null;
		roleList = roleService.getRoleListAll();
		model.addAttribute("roleList", roleList);
		return "user/usermodify";

	}

	/**
	 * ��֤�û����Ƿ���ȷ
	 * 
	 * @param userCode
	 * @return
	 */
	@RequestMapping(value = "/ucexist.json")
	@ResponseBody
	public Object userCodeIsExit(@RequestParam String userCode) {
		logger.debug("userCodeIsExit userCode===================== " + userCode);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userCode)) {
			resultMap.put("userCode", "null");
		} else {
			User user = userService.selectUserCodeExist(userCode);
			if (null != user)
				resultMap.put("userCode", "exist");
			else
				resultMap.put("userCode", "noexist");
		}
		// System.out.println("@###############################(" +
		// JSONArray.toJSONString(resultMap) + ")##");
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * ����ҳ�����ʱ��ʾ�Ľ�ɫ��Ϣ
	 * 
	 * @return
	 */
	@RequestMapping(value = "/rolelist.json", method = RequestMethod.GET, produces = {
			"application/json;charset=UTF-8" })
	@ResponseBody
	public List<Role> getRoleList() {
		List<Role> roleList = null;
		try {
			roleList = roleService.getRoleListAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("roleList size: " + roleList.size());
		return roleList;
	}

	/**
	 * ɾ���û���Ϣ
	 * 
	 * @param userid
	 * @return
	 */
	@RequestMapping(value = "/deleteuser.json")
	@ResponseBody
	public Object deluserinfo(@RequestParam String userid) {
		logger.debug("userCodeIsExit userCode===================== " + userid);
		HashMap<String, String> resultMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(userid)) {
			resultMap.put("delResult", "notexist");
		} else {
			if (userService.deleteUserById(Integer.parseInt(userid))) {
				resultMap.put("delResult", "true");
			} else {
				resultMap.put("delResult", "false");
			}
		}
		return JSONArray.toJSONString(resultMap);
	}

	@RequestMapping(value = "/pwdsave.html", method = RequestMethod.POST)
	public String pwdSave(@RequestParam(value = "newpassword") String newPassword, HttpSession session,
			HttpServletRequest request, Model model) {
		boolean flag = false;
		Object o = session.getAttribute(Constants.USER_SESSION);
		if (o != null && !StringUtils.isNullOrEmpty(newPassword)) {
			try {
				flag = userService.updatePwd(((User) o).getId(), newPassword);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (flag) {
				request.setAttribute(Constants.SYS_MESSAGE, "�޸�����ɹ�,���˳���ʹ�����������µ�¼��");
				model.addAttribute("message", "�޸�����ɹ�,���˳���ʹ�����������µ�¼��");
				session.removeAttribute(Constants.USER_SESSION);// sessionע��
				return "login";
			} else {
				request.setAttribute(Constants.SYS_MESSAGE, "�޸�����ʧ�ܣ�");
				model.addAttribute("message", "�޸�����ʧ�ܣ�");
			}
		} else {
			request.setAttribute(Constants.SYS_MESSAGE, "�޸�����ʧ�ܣ�");
			model.addAttribute("message", "�޸�����ʧ�ܣ�");
		}
		return "user/userlist";
	}

	@RequestMapping(value = "/view.html")
	@ResponseBody
	public User view(@RequestParam String id) {
		logger.debug("View id==============" + id);
		User user = new User();
		try {
			user = userService.getUserById(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	@RequestMapping(value = "/addsave.html", method = RequestMethod.POST)
	public String addUserSave(User user, HttpSession session, HttpServletRequest request,
			@RequestParam(value = "attachs", required = false) MultipartFile[] attachs) {
		String idPicPath = null;
		String workPicPath = null;
		String errorInfo = null;
		boolean flag = true;
		String path = request.getSession().getServletContext().getRealPath("statics" + File.separator + "uploadfiles");
		String path2 = "smbms" + File.separator + "statics" + File.separator + "uploadfiles";
		logger.debug("path====================" + path);
		logger.debug("path====================" + path2);
		logger.info("uploadFile path ============== > " + path);
		for (int i = 0; i < attachs.length; i++) {
			MultipartFile attach = attachs[i];
			if (!attach.isEmpty()) {
				if (i == 0) {
					errorInfo = "uploadFileError";
				} else if (i == 1) {
					errorInfo = "uploadWpError";
				}
				String oldFileName = attach.getOriginalFilename();// ԭ�ļ���
				logger.info("uploadFile oldFileName ============== > " + oldFileName);
				String prefix = FilenameUtils.getExtension(oldFileName);// ԭ�ļ���׺
				logger.debug("uploadFile prefix============> " + prefix);
				int filesize = 500000;
				logger.debug("uploadFile size============> " + attach.getSize());
				if (attach.getSize() > filesize) {// �ϴ���С���ó��� 500k
					request.setAttribute(errorInfo, " * �ϴ���С���ó��� 500k");
					flag = false;
				} else if (prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
						|| prefix.equalsIgnoreCase("jpeg") || prefix.equalsIgnoreCase("pneg")) {// �ϴ�ͼƬ��ʽ����ȷ
					String fileName = System.currentTimeMillis() + RandomUtils.nextInt(1000000) + "_Personal.jpg";
					logger.debug("new fileName======== " + attach.getName());
					File targetFile = new File(path, fileName);
					if (!targetFile.exists()) {
						targetFile.mkdirs();
					}
					// ����
					try {
						attach.transferTo(targetFile);
					} catch (Exception e) {
						e.printStackTrace();
						request.setAttribute(errorInfo, " * �ϴ�ʧ�ܣ�");
						flag = false;
					}
					if (i == 0) {

						idPicPath =File.separator+ path2 + File.separator + fileName;

					} else if (i == 1) {
						workPicPath = File.separator+path2 + File.separator + fileName;
					}
					logger.debug("idPicPath: " + idPicPath);
					logger.debug("workPicPath: " + workPicPath);

				} else {
					request.setAttribute(errorInfo, " * �ϴ�ͼƬ��ʽ����ȷ");
					flag = false;
				}
			}
		}
		if (flag) {
			user.setCreatedBy(((User) session.getAttribute(Constants.USER_SESSION)).getId());
			user.setCreationDate(new Date());
			user.setIdPicPath(idPicPath);
			user.setWorkPicPath(workPicPath);
			try {
				if (userService.add(user)) {
					return "redirect:/sys/user/list.html";
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "user/useradd";
	}

}
