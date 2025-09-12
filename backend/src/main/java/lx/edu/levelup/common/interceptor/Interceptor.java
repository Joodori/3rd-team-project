package lx.edu.levelup.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 화이트리스트 방식 : 허용할 주소 몇 개만 빼고 나머지 전부 로그인 필요
public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		// 세션에서 로그인 여부 확인
		String userId = (String) request.getSession().getAttribute("userId");
		String uri = request.getRequestURI(); // 사용자 요청 주소

		System.out.println("preHandle() 요청 URI=" + uri + ", userId=" + userId);

		// 로그인 안 되어 있으면 차단 (화이트리스트는 WebMVCConfig 에서 제외함)
		if (userId == null) {
			// 로그인 필요 경로인데 비로그인 상태면 -> 로그인 페이지로 이동
			response.sendRedirect("/login");
			return false;
		}

		// 로그인 되어 있으면 요청 계속 진행
		return true;
	}

}

// 나중에 아래 코드 WebMVCConfig에 추가해야됨!!!!!!!
/* public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(loginInterceptor())
            .addPathPatterns("/**") // 모든 요청 가로채기
            .excludePathPatterns(
                "/",          // 홈
                "/login",     // 로그인 페이지
                "/sign-up",   // 회원가입
                "/resources/**"
            );
}
*/
