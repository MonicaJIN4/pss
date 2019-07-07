package ui.ctrl;

import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import  bean.Supplier;
import  service.SupplierService;
import  service.impl.SupplierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/Supplier")
public class SupplierServlet extends HttpServlet {
    private SupplierService SupplierService = new SupplierServiceImpl();
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
        //request.setCharacterEncoding("utf-8");
        // 设置响应对象的编码方式
        // response.setCharacterEncoding("utf-8");
        // 设置响应的内容类型为text/html
        // response.setContentType("text/html; charset=utf-8");
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
            case "list":
                listView(request, response);
                break;
            case "listdeal":
                listDeal(request, response);
                break;
            case "insert":
                insertView(request, response);
                break;
            case "insertdeal":
                insertDeal(request, response);
                break;
            case "update":
                updateView(request, response);
                break;
            case "updatedeal":
                updateDeal(request, response);
                break;
            case "detail":
                detailView(request, response);
                break;
            case "deletedeal":
                deleteDeal(request, response);
                break;
            default:
                // listView(request, response); // 列表页面 : 默认
                System.out.println("oper不存在。");
                break;
        }
    }

    protected void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Supplier> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
        rowCount = SupplierService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = SupplierService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Supplier_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 回显请求数据
        request.setAttribute("searchName", searchName);

        List<Supplier> vDataList = null;
        // ------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = SupplierService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = SupplierService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其它方法
            rowCount = SupplierService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = SupplierService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }

        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(), request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Supplier_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void insertView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/Supplier_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void insertDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 获取请求数据
        String Address = request.getParameter("address");
        String Tel = request.getParameter("tel");
//        int SupId = Integer.parseInt(request.getParameter("supId"));
        String Name = request.getParameter("name");


        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("tel", Tel);
//        request.setAttribute("SupId", SupId);
        request.setAttribute("address", Address);
        request.setAttribute("name", Name);

        //服务端验证
        String vMsg = "";
//        if (SysFun.isNullOrEmpty(String.valueOf(SupId))) {
//            vMsg += "供应商ID不能为空";
//        }
//        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
//        if (!SysFun.isNullOrEmpty(vMsg)) {
//            request.setAttribute("msg", vMsg);
//            System.out.println(vMsg);
//            insertView(request, response);
//            return;
//        }
        if (SysFun.isNullOrEmpty(Name)) {
            vMsg += "供应商名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(Tel)){
            vMsg += "联系电话不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(Address)){
            vMsg += "地址不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }



        //真正处理
        Supplier bean = new Supplier();
//        bean.setSupId(SupId);
        bean.setName(Name);
        bean.setTel(Tel);
        bean.setAddress(Address);

        Long result = 0L;
        try {
            result = SupplierService.insert(bean);
        } catch (Exception e) {
            vMsg = "添加失败." + e.getMessage();
            // TODO: handle exception
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
            Supplier bean = SupplierService.load(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
                request.setAttribute("supId", bean.getSupId());
                request.setAttribute("name", bean.getName());
                request.setAttribute("tel", bean.getTel());
                request.setAttribute("address", bean.getAddress());

                String toPage = UIConst.VIEWPATH +
                    "/Supplier_update.jsp";
                request.getRequestDispatcher(toPage).forward(request,
                    response);
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
        // (0) 获取请求数据
        String vId = request.getParameter("supId");
        String Name = request.getParameter("name");
        String Tel = request.getParameter("tel");
        String Address = request.getParameter("address");

        // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
        request.setAttribute("supId", vId);
        request.setAttribute("name", Name);
        request.setAttribute("tel", Tel);
        request.setAttribute("address", Address);

        // (1) 服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(Name)) {
            vMsg += "供应商名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }
        if (SysFun.isNullOrEmpty(Tel)) {
            vMsg += "联系电话不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if (SysFun.isNullOrEmpty(Address)) {
            vMsg += "地址不能为空";
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
        Supplier bean = SupplierService.load(iId);
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
        bean.setName(Name);
        bean.setTel(Tel);
        bean.setAddress(Address);

        Long result = 0L;
        try {
            result = SupplierService.update(bean);
        } catch (Exception e) {
            vMsg = "修改失败." + e.getMessage();
            // TODO: handle exception
        }
        if (result > 0) {
            // System.out.println("修改成功");
            // listView(request, response);
            // 如果修改成功，则父窗口页面的地址栏重新加载
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        } else {
            request.setAttribute("msg", vMsg);
            //System.out.println("修改失败");
            updateView(request, response);
        }
    }

    protected void detailView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
// 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
// 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            Supplier bean = SupplierService.load(iPK);
            if (bean != null) {
// 使用对象来回显
                request.setAttribute("bean", bean);
                String toPage = UIConst.VIEWPATH + "/Supplier_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");
    }

    protected void deleteDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Long result = 0L;
            result = SupplierService.delete(iId);
            if (result > 0) {
                out.print("ok"); // 不要使用println()
                return;
            }
        }
        out.println("nook");
    }
}
