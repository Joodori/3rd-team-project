package lx.edu.levelup.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		// login 체크 수행
		String userId = (String) request.getSession().getAttribute("userId"); //변수명 바뀌면 나중에 수정
		System.out.println("prehandle() userid=" + userId); //나중에 수정
		if (userId != null && userId.length() > 0) { //나중에 수정
			return true;
		}
		response.sendRedirect("login_form.do"); //경로 바뀌면 나중에 수정
		return false;

	}

}
