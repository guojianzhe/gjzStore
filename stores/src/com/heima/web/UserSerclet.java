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
    
	
	//自己的方法
	
	//到注册页面
	public String registerUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		
		return "/jsp/register.jsp";
	}
	//跳转到登陆页面
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		return "/jsp/login.jsp";
	}
	//用户注册功能
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
			String emailMsg = "这是一封激活邮件,请<a href='http://localhost/stores/user?method=active&code="+user.getCode()+"'>点击激活</a>";
			MailUtils.sendMail(email, emailMsg);
			request.setAttribute("msg", "亲,注册成功啦,赶快去激活吧..");
		} catch (Exception e) {
			
			e.printStackTrace();
			request.setAttribute("msg", "注册失败,请稍后重试...");
			return "/jsp/info.jsp";
		} 
		return "/jsp/info.jsp";
	}
	
	//点击激活功能
	public String active(HttpServletRequest request, HttpServletResponse response) {
		try {
			String code = request.getParameter("code");
			UserService userService = new UserServiceImpl();
			
			User user = userService.findByCode(code);
			//判断存在用户没有
			if(user==null) {
				request.setAttribute("msg", "亲,激活失效已经过期了,需要您重新注册...");
				return "/jsp/info.jsp";
			}
			user.setState(1);
			
			userService.update(user);
			request.setAttribute("msg", "亲,激活成功,请登录去吧...");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("msg", "激活失败,请稍后再试");
			return "/jsp/info.jsp";
			
		}
		return "/jsp/info.jsp";
	}
	//用户登录功能
	public String login(HttpServletRequest request, HttpServletResponse response) {
		try {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			
			UserService userService = new UserServiceImpl();
			User user = userService.login(username,password);
			//判断是否登陆成功
			if(user==null) {
				request.setAttribute("msg", "用户名或者密码错误");
				
				return "/jsp/login.jsp";
			}
			//判断用户是否激活
			if(user.getState()!=1) {
				request.setAttribute("msg", "亲,你还没有激活,请先去邮箱激活再来登录..");
				return "/jsp/info.jsp";
			}
			//已经激活,将用户信息存到页面之中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			
			response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("msg", "登陆失败");
			return "/jsp/info.jsp";
		}
		
		return null;
	}
	//用户退出
	public String quit(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		
		return null;
	}
}
