package cn.smbms.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.mysql.jdbc.StringUtils;

import cn.smbms.pojo.User;
import cn.smbms.service.role.RoleService;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;
@Controller
@RequestMapping(value="/sys/pass")
public class PassController {
	private Logger logger = Logger.getLogger(UserController.class);
	@Resource
	private UserService userService;

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
		return "pass/pwdmodify";
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

}
