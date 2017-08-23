package cn.smbms.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.Role;
import cn.smbms.service.role.RoleService;
import cn.smbms.tools.Constants;
import cn.smbms.tools.PageSupport;

@Controller
@RequestMapping("/sys/role")
public class RoleController {
	private Logger logger = Logger.getLogger(UserController.class);

	@Resource
	private RoleService roleService;

	@RequestMapping(value = "/list.html")
	public String getRoleList(Model model, @RequestParam(value = "pageIndex", required = false) String pageIndex)
			throws Exception {
		logger.info("getUserList----->pageIndex:" + pageIndex);
		int _queryUserRole = 0;
		List<Role> roleList = null;
		// 设置页面容量
		int pageSize = Constants.pageSize;
		// 当前页码
		int currentPageNo = 1;

		if (pageIndex != null) {
			try {
				currentPageNo = Integer.valueOf(pageIndex);
			} catch (NumberFormatException e) {
				return "redirect:/role/syserror.html";
			}
		}
		// 总数量(表)
		int totalCount = roleService.getRoleCount();
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
		roleList = roleService.getRoleList(currentPageNo, pageSize);
		model.addAttribute("roleList", roleList);
		model.addAttribute("totalPageCount", totalPageCount);
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("currentPageNo", currentPageNo);
		return "role/rolelist";
	}

	@RequestMapping(value = "/syserror.html")
	public String sysError() {
		return "common/syserror";
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/view.html")
	@ResponseBody
	public Role view(@RequestParam String id) {
		logger.debug("View id==============" + id);
		Role role = new Role();
		try {
			role = roleService.getRoleById(Integer.parseInt(id));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return role;
	}

	/**
	 * 角色管理->添加角色
	 * 
	 * @return
	 */
	@RequestMapping(value = "/roleadd.html", method = RequestMethod.GET)
	public String addRole() {
		logger.debug("addView");
		return "role/roleadd";
	}

	/**
	 * 角色管理->添加角色->角色编码检查
	 */
	@RequestMapping(value = "/roleExist.json")
	@ResponseBody
	public Object selectRoleExist(@RequestParam String roleCode) {
		logger.debug("selectRoleExist==================" + roleCode);
		HashMap<String, String> hashMap = new HashMap<String, String>();
		if (StringUtils.isNullOrEmpty(roleCode)) {
			hashMap.put("roleCode", "null");
		} else {
			Role role = roleService.selectRoleCodeExist(roleCode);
			if (role != null) {
				hashMap.put("roleCode", "error");
			} else {
				hashMap.put("roleCode", "true");
			}
		}
		return JSONArray.toJSONString(hashMap);
	}

	/**
	 * 
	 */
	@RequestMapping(value = "/insert.html", method = RequestMethod.POST)
	public String addRole(Role role) {
		role.setCreatedBy(1);
		role.setCreationDate(new Date());
		if (roleService.insertRole(role)) {
			return "redirect:/sys/role/list.html";
		}
		return "redirect:/sys/role/list.html";
	}
}
