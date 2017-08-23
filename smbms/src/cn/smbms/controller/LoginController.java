package cn.smbms.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import cn.smbms.pojo.User;
import cn.smbms.service.user.UserService;
import cn.smbms.tools.Constants;

@Controller
public class LoginController {
	private Logger logger = Logger.getLogger(LoginController.class);

	@Resource
	private UserService userService;

	@RequestMapping(value = "/login.html")
	public String login() {
		logger.debug("LoginController welcome SMBMS==================");
		return "login";
	}

	@RequestMapping(value = "/dologin.html", method = RequestMethod.POST)
	public String doLogin(@RequestParam String userCode, @RequestParam String userPassword, HttpServletRequest request,
			HttpSession session) throws Exception {
		logger.debug("doLogin====================================");
		// ����service�����������û�ƥ��
		User user = userService.login(userCode, userPassword);
		if (null != user) {// ��¼�ɹ�
			// ����session
			session.setAttribute(Constants.USER_SESSION, user);
			// ҳ����ת��frame.jsp��
			return "redirect:/sys/main.html";
		} else {
			// ҳ����ת��login.jsp��������ʾ��Ϣ--ת��
			request.setAttribute("error", "�û��������벻��ȷ");
			return "login";
		}
	}

	@RequestMapping(value = "/logout.html")
	public String logout(HttpSession session) {
		// ���session
		session.removeAttribute(Constants.USER_SESSION);
		return "common/login";
	}

	@RequestMapping(value = "/sys/main.html")
	public String main() {
		return "common/frame";
	}
}
