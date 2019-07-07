
package ui.ctrl;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "Filter")
public class Filter implements javax.servlet.Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        //拦截所有 但是仍然有一些缺陷-->我的login这个请求 login.jsp这个请求不用拦截
        //第一步先将请求和响应转成Http的
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //先获取到当前的一个访问路径
        String uri = request.getRequestURI();//获取到当前的请求路径
        String path = uri.substring(uri.lastIndexOf("/") + 1);//获取到尾部路径
        System.out.println("拦截到的路径:"+path);


        //不需要过滤的url
        String[] urls = {"Login",".js",".css",".ico",".jpg",".png","ValidateCode",".ttf",".woff","Register"};

        //判断用户是否登陆
        HttpSession session = request.getSession();
        //从session域中获取看看有没有存储用户信息
        Object sessionUser = session.getAttribute(UIConst.BG_LOGINUSER_KEY);
        if (sessionUser == null) {
            //对拦截路径进行遍历比较
            boolean flag = false;
            //利用foreach进行比较
            for (String str : urls) {
                if (path.lastIndexOf(str) != -1) {
                    flag =true;
                    break;
                }
            }
            //如果你的路径请求是登陆的 那就不拦截 直接放行
            if (flag) {
                chain.doFilter(request, response);
            } else {
                //如果又没有session信息  又访问的不是login请求路径
                //这时候就给他返回到登陆的界面
                response.sendRedirect(request.getContextPath()+UIConst.AREAPATH+"/Login");
            }
        } else {
            //这里是有session用户信息
            chain.doFilter(request, response);
        }



    }

    public void init(FilterConfig config) throws ServletException {

    }

}

