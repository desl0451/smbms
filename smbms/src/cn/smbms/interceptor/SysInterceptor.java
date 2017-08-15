package cn.smbms.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.smbms.pojo.User;
import cn.smbms.tools.Constants;

public class SysInterceptor extends HandlerInterceptorAdapter {
	/**
	 * �ж��û��Ƿ�Ϊ�����Ϊ����·ת��404ҳ��
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.USER_SESSION);
		if (user == null) {
			response.sendRedirect(request.getContextPath() + "/404.jsp");
			return false;
		}
		return true;
	}
}
