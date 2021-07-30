package com.example.filter.filter;

import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/*
    Filter의 개념
    - 인터셉터와 마찬가지로 서블릿 단위에서 실행된다.
    - DispatcherServlet이 실행되기 전에 호출되며 스프링 컨텍스트 외부에 존재하여 스프링과 무관하게 동작한다.
    - 인코딩 변환, 로그인 여부확인, 권한체크, XSS방어 등의 요청에 대한 처리

    1) HttpServletRequest, HttpServletResponse를 통해서 읽게되면 inputstream의 커서 위치가 이미 끝에 위치하게되므로 Controller는 읽일 수 없게된다.

    그래서 ContentCachingRequestWrapper, ContentCachingResponseWrapper를 통해서 캐시된 데이터를 사용하도록 해야한다.
    이때 주의할점이 ContentCachingRequestWrapper은 생성자를 보면 알다싶이 데이터의 크기만 설정함으로 실제 데이터는 dofilter메서드를 수행해야지만 등록된다는 것을 인지해야한다.

    2) 그리고 @ServletComponentScan을 잊지말고 꼭 설정해줘야 한다. 그래야 스캔대상이 되어 Filter가 정상동작한다.

    3) Response도 마찬가지로 한번 읽게되면 커서의 위치가 끝으로 가게되서 client가 받지 못하는 현상이 밠생될 수 있다.
    그래서 copyBodyToResponse메서드를 꼭 수행해줘야 한다.
 */
@WebFilter(urlPatterns = "/*")
public class GlobalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

        System.out.println("filter 초기");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//        BufferedReader bufferedReader = httpServletRequest.getReader();
//        bufferedReader.lines().forEach(System.out::println);

        // 전처리 부분
        ContentCachingRequestWrapper contentCachingRequestWrapper = new ContentCachingRequestWrapper((HttpServletRequest) request);
        ContentCachingResponseWrapper contentCachingResponseWrapper = new ContentCachingResponseWrapper((HttpServletResponse) response);

        // 실제 뒷단으로 전달
        chain.doFilter(contentCachingRequestWrapper, contentCachingResponseWrapper);

        // 후처리 부분
        String reqContent = new String(contentCachingRequestWrapper.getContentAsByteArray());
        System.out.println(reqContent);

        String resContent = new String(contentCachingResponseWrapper.getContentAsByteArray());
        int httpStatus = contentCachingResponseWrapper.getStatus();

//        contentCachingResponseWrapper.copyBodyToResponse();

        System.out.println("status : "+httpStatus+", body : "+resContent);
    }

    @Override
    public void destroy() {
        System.out.println("filter 해제");
    }
}
