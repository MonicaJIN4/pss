package ui.ctrl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import  bean.*;
import  dao.*;
import  service.*;
import  service.impl.*;
import java.util.*;

@WebServlet(UIConst.AREAPATH + "/Login")
public class LoginServlet extends HttpServlet {

    private UserService UserService = new UserServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */
        /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
        // 设置请求对象的编码方式
        //request.setCharacterEncoding("utf-8");
        //设置响应对象的编码方式
        // response.setCharacterEncoding("utf-8");
        // 设置响应的内容类型为text/html
        //response.setContentType("text/html; charset=utf-8");
        // ***** Servlet的doxxx方法中的6个标准对象（含request和response）
        // 从request里获取session对象和application对象
        javax.servlet.http.HttpSession session = request.getSession();
        //javax.servlet.ServletContext application = request.getServletContext();
        // 调用继承的方法来获取config对象
        javax.servlet.ServletConfig config = getServletConfig();
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        /* ----------------------------------------------------------------- */

        //取得操作类型
        String oper = request.getParameter("oper");

        if (oper != null && oper.equalsIgnoreCase("loginDeal")){
            //处理提交：登录处理
            loginDeal(request,response);
        }else if (oper != null && oper.equalsIgnoreCase("logoutDeal")){
            //处理提交：注销处理
            logoutDeal(request,response);
        }else{
            //显示页面：登录
            loginView(request,response);
        }
    }

    protected void loginView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String toPage = UIConst.VIEWPATH + "/Login.jsp";
        request.getRequestDispatcher(toPage).forward(request,response);
    }

    protected void loginDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */
        /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
        //request.setCharacterEncoding("utf-8");
        // 设置响应对象的编码方式
        //response.setCharacterEncoding("utf-8");
        //设置响应的内容类型为text/html
        //response.setContentType("text/html; charset=utf-8");
        // ***** Servlet的doxxx方法中的6个标准对象(含request和response)
        // 从request里获取session对象和application对象
        javax.servlet.http.HttpSession session = request.getSession();


        //javax.servlet.ServletContext application = request.getServletContext();
        // 调用继承的方法来获取config对象
        javax.servlet.ServletConfig config = getServletConfig();
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        /* ----------------------------------------------------------------- */
        String userName = request.getParameter("userName");
        String userPass = request.getParameter("userPass");
        String validateCode = request.getParameter("validateCode");

        request.setAttribute("userName",userName);
//        验证数据
//        登录失败时，转发的页面
        String toPage = UIConst.VIEWPATH + "/Login.jsp";
        String msg ="";
        request.setAttribute("msg",msg);
        if(userName == null|| userName.isEmpty()){
            msg = "请填写用户名";
            request.setAttribute("msg","请填写用户名");
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }
        if(userPass == null|| userPass.isEmpty()){
            msg = "请填写密码";
            request.setAttribute("msg","请填写密码");
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }
        if(validateCode == null|| validateCode.isEmpty()){
            msg = "请填写验证码";
            request.setAttribute("msg","请填写验证码");
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }

        String strCode = (String) session.getAttribute(UIConst.BG_VALLDATE_CODE_KEY);

        if (!validateCode.equals(strCode)){
            msg = "验证码不正确";
            request.setAttribute("msg",msg);
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }

//        数据库验证
        User bean = UserService.loadByName(userName);
        if (bean == null){
            request.setAttribute("msg","该用户名不存在");
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }

        if (!bean.getUserPass().equals(userPass)){
            request.setAttribute("msg","密码错误");
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }
        //权限验证
        if (bean.getRoleId() == 2){//未通过
            request.setAttribute("msg","用户未通过管理员验证");
            request.getRequestDispatcher(toPage).forward(request,response);
            return;
        }


        // 业务处理
        session.setAttribute(UIConst.BG_LOGINUSER_KEY,bean);
        String toURL =request.getContextPath()+UIConst.AREAPATH + "/Main";
        response.sendRedirect(toURL);

    }

    protected void logoutDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        javax.servlet.http.HttpSession session= request.getSession();
        session.invalidate();//会话失败

        //重定向导登录URL
        String toURL = request.getContextPath()+UIConst.AREAPATH+"/Login";
        response.sendRedirect(toURL);
    }
}