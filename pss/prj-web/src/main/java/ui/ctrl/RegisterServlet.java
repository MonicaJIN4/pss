package ui.ctrl;

import bean.Role;
import bean.User;
import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import service.RoleService;
import service.UserService;
import service.impl.RoleServiceImpl;
import service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(UIConst.AREAPATH + "/Register")
public class RegisterServlet extends HttpServlet {
    private UserService UserService = new UserServiceImpl();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */
        /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
        request.setCharacterEncoding("utf-8");
        // 设置响应对象的编码方式
         response.setCharacterEncoding("utf-8");
        // 设置响应的内容类型为text/html
         response.setContentType("text/html; charset=utf-8");
        // ***** Servlet的doxxx方法中的6个标准对象（含request和response）
        // 从request里获取session对象和application对象
        javax.servlet.http.HttpSession session = request.getSession();
        //javax.servlet.ServletContext application = request.getServletContext();
        // 调用继承的方法来获取config对象
        javax.servlet.ServletConfig config = getServletConfig();
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        /* ----------------------------------------------------------------- */

        //获取操作类型
        String oper = request.getParameter("oper");
        if (oper == null){
            //处理提交：登录处理
            oper = "";
        }else {
            oper = oper.trim().toLowerCase();
        }

        //根据不同的操作类型，调用不同的处理方法
        switch (oper){
            case "insertregisterview":
                insertRegisterView(request,response);
                break;
            case "insertregister":
                insertRegister(request,response);
                break;
            default:
                // listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }
    }


    protected void insertRegisterView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Register.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }


    protected void insertRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        javax.servlet.http.HttpSession session = request.getSession();

        // 获取请求数据
        String userPass = request.getParameter("userPass");
        String userName = request.getParameter("userName");
        String validateCode = request.getParameter("validateCode");


        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("userName", userName);
        request.setAttribute("userPass", userPass);

        //服务端验证
        String vMsg = "";
        String toPage = UIConst.VIEWPATH + "/Register.jsp";
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


        //真正处理
        User bean = new User();
        bean.setUserName(userName);
        bean.setUserPass(userPass);

        Long result = 0L;
        try {
            result = UserService.insertRegister(bean);
        } catch (Exception e) {
            vMsg = "添加失败." + e.getMessage();
        }
        if (result > 0) {
            System.out.println("添加成功");
            out.println("<script>");
            out.println("alert(‘注册成功’)");
//            out.println("parent.window.location.reload();");
            out.println("</script>");
            // 转发到页面
            String toURL =request.getContextPath()+UIConst.AREAPATH + "/Login";
            response.sendRedirect(toURL);
        } else if(result == -1L){
            vMsg += "用户名已存在，请更换";
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertRegisterView(request, response);
        } else {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertRegisterView(request, response);
        }
    }


}
