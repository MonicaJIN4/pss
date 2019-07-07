package ui.ctrl;

import javax.servlet.Filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@SuppressWarnings("ALL")
//@WebFilter("/*")
public class EncodingFilter implements Filter {
    //预防处理
    //设置一个默认值
    private String encode = "UTF-8";
    private String contentType = "text/html;charset=UTF-8";


    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //常用的req和resp 不是servlet的而是HTTP
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //设置字符集
        response.setCharacterEncoding(encode);
        response.setContentType(contentType);
        //在放行每个请求之前呢 我们说预处理就可以开始操作了
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        //config 通过config可以获取到我们的配置文件中的全局变量参数
        //getServletContext 全局上下文对象
        String paramEncode = config.getServletContext().getInitParameter("encode");
        if (paramEncode != null) {
            encode = paramEncode;
        }
        String paramContentType = config.getServletContext().getInitParameter("contentType");
        if (paramContentType != null) {
            contentType = paramContentType;
        }

    }

}
