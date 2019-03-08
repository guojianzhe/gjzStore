package com.heima.web;

import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.beanutils.BeanUtils;
import com.heima.domain.User;
import com.heima.service.UserService;
import com.heima.serviceImpl.UserServiceImpl;
import com.heima.utils.MailUtils;
import com.heima.utils.UUIDUtils;

public class UserSerclet extends BaseServlet {
	private static final long serialVersionUID = 1L;
    
	
	//�Լ��ķ���
	
	//��ע��ҳ��
	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		return "/jsp/register.jsp";
	}
	//��ת����½ҳ��
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/jsp/login.jsp";
	}
	//�û�ע�Ṧ��
	public String register(HttpServletRequest request, HttpServletResponse response) {
		try {
			Map<String, String[]> map = request.getParameterMap();
			
			User user = new User();
			
			BeanUtils.populate(user, map);
			
			user.setUid(UUIDUtils.getUUID());
			user.setState(0);
			user.setCode(UUIDUtils.getUUID()+UUIDUtils.getUUID());
			
			UserService userService = new UserServiceImpl();
			userService.save(user);
			String email = user.getEmail();
			String emailMsg = "����һ�⼤���ʼ�,��<a href='http://localhost/stores/user?method=active&code="+user.getCode()+"'>�������</a>";
			MailUtils.sendMail(email, emailMsg);
			request.setAttribute("msg", "��,ע��ɹ���,�Ͽ�ȥ�����..");
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("msg", "ע��ʧ��,���Ժ�����...");
			return "/jsp/info.jsp";
		} 
		return "/jsp/info.jsp";
	}
	
	//��������
	public String active(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			UserService userService = new UserServiceImpl();
			
			User user = userService.findByCode(code);
			//�жϴ����û�û��
			if(user==null) {
				request.setAttribute("msg", "��,����ʧЧ�Ѿ�������,��Ҫ������ע��...");
				return "/jsp/info.jsp";
			}
			user.setState(1);
			
			userService.update(user);
			request.setAttribute("msg", "��,����ɹ�,���¼ȥ��...");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "����ʧ��,���Ժ�����");
			return "/jsp/info.jsp";
			
		}
		return "/jsp/info.jsp";
	}
	//�û���¼����
	public String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserService userService = new UserServiceImpl();
			User user = userService.login(username,password);
			//�ж��Ƿ��½�ɹ�
			if(user==null) {
				request.setAttribute("msg", "�û��������������");
				
				return "/jsp/login.jsp";
			}
			//�ж��û��Ƿ񼤻�
			if(user.getState()!=1) {
				request.setAttribute("msg", "��,�㻹û�м���,����ȥ���伤��������¼..");
				return "/jsp/info.jsp";
			}
			//�Ѿ�����,���û���Ϣ�浽ҳ��֮��
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "��½ʧ��");
			return "/jsp/info.jsp";
		}
		
		return null;
	}
	//�û��˳�
	public String quit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		
		return null;
	}
}
