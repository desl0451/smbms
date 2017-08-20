package cn.smbms.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	 * 显示全部用户列表
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
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
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
		// 总数量(表)
		int totalCount = userService.getUserCount(queryUserName, _queryUserRole);
		// 总页数
		PageSupport pages = new PageSupport();
		pages.setCurrentPageNo(currentPageNo);
		pages.setPageSize(pageSize);
		pages.setTotalCount(totalCount);
		int totalPageCount = pages.getTotalPageCount();
		// 控制首页和尾页
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
		return "userlist";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "syserror";
	}

	/**
	 * 跳转添加用户界面
	 * 
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/useradd.html", method = RequestMethod.GET)
	public String addUser(@ModelAttribute("user") User user) {
		return "useradd";
	}

	/**
	 * 跳转到pwdmodify.jsp页面
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pwdmodify.html", method = RequestMethod.GET)
	public String pwdmodify(HttpSession session) {
		if (session.getAttribute(Constants.USER_SESSION) == null) {
			return "redirect:/user/login.html";
		}
		return "pwdmodify";
	}

	/**
	 * pwdmodify.JSP页判断用户名是否正确
	 * 
	 * @param oldpassword
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/pwdmodify.json", method = RequestMethod.POST)
	@ResponseBody
	public Object getPwdByUserId(@RequestParam String oldpassword, HttpSession session) {
		logger.debug("getPwdByUserId oldpassword ===================== " + oldpassword);
		HashMap<String, String> resultMap = new HashMap<String, String>();

		if (StringUtils.isNullOrEmpty(oldpassword)) {
			resultMap.put("result", "error");
		} else {
			User user = (User) session.getAttribute(Constants.USER_SESSION);
			if (user == null) {
				resultMap.put("result", "sessionerror");
			} else if (user.getUserPassword().equals(oldpassword)) {
				resultMap.put("result", "true");
			} else {
				resultMap.put("result", "false");
			}
		}

		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * 显示用户信息页面跳转
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
		return "userview";
	}

	@RequestMapping(value = "/usermodify/{id}",method=RequestMethod.GET)
	public String usermodify(Model model, @PathVariable String id) throws Exception {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		List<Role> roleList = null;
		roleList = roleService.getRoleListAll();
		model.addAttribute("roleList", roleList);
		return "usermodify";

	}

	/**
	 * 验证用户名是否正确
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
		System.out.println("@###############################(" + JSONArray.toJSONString(resultMap) + ")##");
		return JSONArray.toJSONString(resultMap);
	}

	/**
	 * 返回页面加载时显示的角色信息
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
	 * 删除用户信息
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

	@RequestMapping(value = "/pwdsave.html")
	public String pwdSave(@RequestParam(value = "newpassword") String newPassword, HttpSession session,
			HttpServletRequest request) {
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
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码成功,请退出并使用新密码重新登录！");
				session.removeAttribute(Constants.USER_SESSION);// session注销
				return "login";
			} else {
				request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
			}
		} else {
			request.setAttribute(Constants.SYS_MESSAGE, "修改密码失败！");
		}
		return "pwdmodify";
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
}
