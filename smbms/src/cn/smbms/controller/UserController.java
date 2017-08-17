package cn.smbms.controller;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
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

	@RequestMapping(value = "/adduser.html", method = RequestMethod.GET)
	public String useradd(@ModelAttribute("user") User user) {
		return "useradd";
	}

	@RequestMapping(value = "/pwdmodify.html")
	public String pwdmodify() {
		return "pwdmodify";
	}

	@RequestMapping(value = "/userview.html")
	public String userview(Model model, @RequestParam(value = "userid", required = false) Integer queryId)
			throws Exception {
		User user = userService.getUserById(queryId);
		model.addAttribute("user", user);
		return "userview";
	}

	@RequestMapping(value = "/userdel.html")
	public String userdel(@RequestParam(value = "userid", required = false) Integer id) throws Exception {
		boolean i = userService.deleteUserById(id);
		if (i == true) {
			return "userlist";
		} else {
			return "redirect:/user/syserror.html";
		}
	}

	@RequestMapping(value = "/usermodify.html")
	public String usermodify(Model model, @RequestParam(value = "userid", required = false) Integer id)
			throws Exception {
		User user = userService.getUserById(id);
		model.addAttribute("user", user);
		List<Role> roleList = null;
		roleList = roleService.getRoleListAll();
		model.addAttribute("roleList", roleList);
		return "usermodify";

	}

	@RequestMapping(value = "/ucexist.html")
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
}
