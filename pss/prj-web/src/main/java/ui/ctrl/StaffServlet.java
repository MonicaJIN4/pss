package ui.ctrl;

import bean.Staff;
import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import service.StaffService;
import service.impl.StaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(UIConst.AREAPATH+"/Staff")
public class StaffServlet extends HttpServlet {

    StaffService staffService = new StaffServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */ /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
        //request.setCharacterEncoding("utf-8");
        // 设置响应对象的编码方式
        // response.setCharacterEncoding("utf-8");
        // 设置响应的内容类型为text/html
        // response.setContentType("text/html; charset=utf-8");
        // ***** Servlet的doxxx方法中的6个标准对象(含request和response)
        // 从request里获取session对象和application对象
        javax.servlet.http.HttpSession session = request.getSession();
        javax.servlet.ServletContext application = request.getServletContext();
        // 调用继承的方法来获取config对象
        javax.servlet.ServletConfig config = getServletConfig();
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        /* ----------------------------------------------------------------- */

        // 取得操作类型
        String oper = request.getParameter("oper");
        if (oper == null) {
            oper = "";
        } else {
            oper = oper.trim().toLowerCase();
        }
        // 根据不同的操作类型,调用不同的处理方法
        switch (oper) {
            case "list":
                listView(request, response); // 列表页面
                break;
            case "listdeal":
                listDeal(request, response); // 列表处理
                break;
            case "insert":
                insertView(request, response); // 添加页面
                break;
            case "insertdeal":
                insertDeal(request, response); // 添加处理
                break;
            case "update":
                updateView(request, response); // 添加处理
                break;
            case "updatedeal":
                updateDeal(request, response); //修改处理
                break;
            case "detail":
                detailView(request, response);// 查看页面
                break;
            case "deletedeal":
                deleteDeal(request, response);// 删除处理
                break;
            default:
                System.out.println("oper不存在。");
                break;
        }
    }

    protected void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Staff> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
        rowCount = staffService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = staffService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Staff_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 显示请求数据
        request.setAttribute("searchName", searchName);

        List<Staff> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = staffService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = staffService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());

        } else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = staffService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = staffService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }

        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Staff_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void insertView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Staff_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void insertDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String cName = request.getParameter("cName");
        String eName = request.getParameter("eName");
        String phone = request.getParameter("phone");
        String mPhone = request.getParameter("mPhone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("cName", cName);
        request.setAttribute("eName", eName);
        request.setAttribute("phone", phone);
        request.setAttribute("mPhone", mPhone);
        request.setAttribute("email", email);
        request.setAttribute("address", address);

        //服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(cName)) {
            vMsg += "cName不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(mPhone)) {
            vMsg += "mPhone不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(email)) {
            vMsg += "email不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        

        Staff bean = new Staff();
        bean.setcName(cName);
        bean.seteName(eName);
        bean.setPhone(phone);
        bean.setmPhone(mPhone);
        bean.setEmail(email);
        bean.setAddress(address);

        Long result = 0L;
        try {
            result = staffService.insert(bean);
        } catch (Exception e) {
            vMsg = "添加失败." + e.getMessage();
        }

        if (result > 0) {
            System.out.println("添加成功");
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        } else {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
        }


    }

    protected void updateView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");

        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Staff bean = staffService.loadByStaffId(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取

                request.setAttribute("staffId",bean.getStaffId());
                request.setAttribute("cName", bean.getcName());
                request.setAttribute("eName", bean.geteName());
                request.setAttribute("phone", bean.getPhone());
                request.setAttribute("mPhone", bean.getmPhone());
                request.setAttribute("email", bean.getEmail());
                request.setAttribute("address", bean.getAddress());

                String toPage = UIConst.VIEWPATH + "/Staff_update.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;

            }
        }

        out.println("<script>");
        out.println("alert('数据不存在');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected void updateDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        String vId = request.getParameter("staffId");
        String cName = request.getParameter("cName");
        String eName = request.getParameter("eName");
        String phone = request.getParameter("phone");
        String mPhone = request.getParameter("mPhone");
        String email = request.getParameter("email");
        String address = request.getParameter("address");


        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("staffId",vId);
        request.setAttribute("cName", cName);
        request.setAttribute("eName", eName);
        request.setAttribute("phone", phone);
        request.setAttribute("mPhone", mPhone);
        request.setAttribute("email", email);
        request.setAttribute("address", address);


        //服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(cName)) {
            vMsg += "cName不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }


        if (SysFun.isNullOrEmpty(mPhone)) {
            vMsg += "mPhone不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(email)) {
            vMsg += "email不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        // (2) 数据库验证
        Long iId = SysFun.parseLong(vId);
        Staff bean = staffService.loadByStaffId(iId);
        if (bean == null) {
            vMsg = "数据不存在";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
            return;
        }
        // (3) 真正处理

        bean.setcName(cName);
        bean.seteName(eName);
        bean.setPhone(phone);
        bean.setmPhone(mPhone);
        bean.setEmail(email);
        bean.setAddress(address);

        Long result = 0L;
        try {
            result = staffService.update(bean);
        } catch (Exception e) {
            vMsg = "修改失败." + e.getMessage();
        }

        if (result > 0) {
            //System.out.println("修改成功");

            //如果修改成功，则父窗口页面的地址栏重新加载
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        } else {
            request.setAttribute("msg", vMsg);
            System.out.println("修改失败");
            updateView(request, response);
        }
    }

    protected void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Long result = 0L;
            result = staffService.delete(iId);
            if (result > 0) {
                out.print("ok"); // 不要使用println()
                return;
            }
        }
        out.println("nook");

    }

    protected void detailView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            Staff bean = staffService.loadByStaffId(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);

                String toPage = UIConst.VIEWPATH + "/Staff_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }
}
