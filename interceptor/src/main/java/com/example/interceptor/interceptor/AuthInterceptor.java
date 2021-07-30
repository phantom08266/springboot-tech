package com.example.interceptor.interceptor;

import com.example.interceptor.dto.User;
import com.example.interceptor.exception.AuthException;
import com.example.interceptor.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Optional;

/*
    preHandle의 반환값이 ture일 경우 인터셉터 처리 후 controller코드로 넘어갑니다.

    afterCompletion에서 발생한 예외는 @RestControllerAdvice에서 ExceptionHandler로 잡을 수 없다. 로그라도 잘 찍어둬야 한다.
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle");
        HttpSession httpSession = request.getSession();

        Optional.ofNullable(httpSession.getAttribute("userId"))
                .orElseThrow(() ->new AuthException("로그인된 사용자가 아닙니다."));

        String userName = httpSession.getAttribute("userId").toString();
        System.out.println("안녕하세요~ " + userName + "님.");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("afterCompletion");
    }
}
