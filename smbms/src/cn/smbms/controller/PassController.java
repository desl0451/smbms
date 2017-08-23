package cn.smbms.controller;

import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	 * ��ת��pwdmodify.jspҳ��
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
	 * pwdmodify.JSPҳ�ж��û����Ƿ���ȷ
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
	 * ����������
	 * @param newPassword
	 * @param session
	 * @param request
	 * @param model
	 * @return
	 */
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
}
