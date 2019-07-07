package ui.ctrl;

import bean.CusContact;
import bean.CusOper;
import bean.Staff;
import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import service.CusContactService;
import service.CusOperService;
import service.StaffService;
import service.impl.CusContactServiceImpl;
import service.impl.CusOperServiceImpl;
import service.impl.StaffServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(UIConst.AREAPATH + "/CusOper")
public class CusOperServlet extends HttpServlet {

    private CusOperService cusOperService = new CusOperServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        // 设置请求对象的编码方式
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

//        //验证是否已登录
//        String toURL=checkLogin(request,response);
//        if (toURL!= null){
//            response.sendRedirect(toURL);
//            return;
//        }


        String oper = request.getParameter("oper");
        if (oper == null){
            oper = "";
        }else {
            oper = oper.trim().toLowerCase();
        }
        switch (oper){
            case "list":
                listView(request,response);//列表页面
                break;
            case "listdeal":
                listDeal(request,response);//列表处理
                break;
            case "insert":
                insertView(request,response);//添加页面
                break;
            case "insertdeal":
                insertDeal(request,response);//添加处理
                break;
            case "update":
                updateView(request,response);//更新页面
                break;
            case "updatedeal":
                updateDeal(request,response);//更新处理
                break;
            case "detail":
                detailView(request,response);//查看页面
                break;
            case "deletedeal":
                deleteDeal(request,response);//删除处理
                break;
            default:
                System.out.println("oper不存在。");
                break;
        }
    }
    protected void listView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<CusOper> vDataList =null;
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();

        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成 其它方法
        rowCount = cusOperService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);

        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList =  cusOperService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem",pagerItem);
        request.setAttribute("DataList",vDataList);

        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/CusOper_list.jsp";
        request.getRequestDispatcher(toPage).forward(request,response);



    }
    protected void listDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //获取请求参数
        String searchName = request.getParameter("searchName");
        //回显请求数据
        request.setAttribute("searchName",searchName);

        List<CusOper> vDataList = null;
        // -----------------------------------------------------------------------        
        // 分页步骤1. 创建PagerIter对象, 处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));
        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根 据实际换成其它方法
            rowCount = cusOperService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成 其它方法
            vDataList = cusOperService.pager(pagerItem.getPageNum(),pagerItem.getPageSize());
        }else{
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根 据实际换成其它方法
            rowCount = cusOperService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算      
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成 其它方法
            vDataList = cusOperService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // -----------------------------------------------------------------------
        // 转发到页面
        String toPage = UIConst.VIEWPATH + "/CusOper_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);



    }
    protected void insertView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CusContactService cusContactService = new CusContactServiceImpl();
        List<CusContact> cusList =cusContactService.list();
        request.setAttribute("cusList",cusList);

        StaffService staffService = new StaffServiceImpl();
        List<Staff> staffList = staffService.list();
        request.setAttribute("staffList",staffList);

        String toPage = UIConst.VIEWPATH + "/CusOper_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request,response);

    }
    protected void insertDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页 面的编码

        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        Integer cusId = Integer.valueOf(request.getParameter("cusId"));
        String cusTel = request.getParameter("cusTel");
        String cusAddr = request.getParameter("cusAddr");
        Integer staffId = Integer.valueOf(request.getParameter("staffId"));
        String faxNum = request.getParameter("faxNum");
        String comName = request.getParameter("comName");

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("cusId",cusId);
        request.setAttribute("cusTel",cusTel);
        request.setAttribute("cusAddr",cusAddr);
        request.setAttribute("staffId",staffId);
        request.setAttribute("faxNum",faxNum);
        request.setAttribute("comName",comName);


        //服务端验证
        String vMsg = "";
        if(SysFun.isNullOrEmpty(String.valueOf(cusId)))
        {
            vMsg+= "客户Id不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if(SysFun.isNullOrEmpty(cusTel))
        {
            vMsg+= "客户电话不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if(SysFun.isNullOrEmpty((cusAddr)))
        {
            vMsg+= "客户地址不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        if (SysFun.isNullOrEmpty(String.valueOf(staffId))) {
            vMsg += "员工不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg",vMsg);
            System.out.println(vMsg);
            insertView(request,response);
            return;
        }
        if(SysFun.isNullOrEmpty((faxNum)))
        {
            vMsg+= "传真编号不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if(SysFun.isNullOrEmpty((comName)))
        {
            vMsg+= "公司名字不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }
        if(SysFun.isNullOrEmpty(String.valueOf((cusId))))
        {
            vMsg+= "客户ID不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if(!SysFun.isNullOrEmpty(vMsg)){
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }


        CusOper bean = new CusOper();
        bean.setCusId(cusId);
        bean.setCusTel(cusTel);
        bean.setCusAddr(cusAddr);
        bean.setStaffId(staffId);
        bean.setFaxNum(faxNum);
        bean.setComName(comName);


        Long result  = 0L;
        try {
            result=cusOperService.insert(bean);
        }catch(Exception e){
            vMsg="添加失败." + e.getMessage();
            // TODO: handle exception    
        }

        if(result > 0){
            System.out.println("添加成功");
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        }else{
            request.setAttribute("msg",vMsg);
            System.out.println(vMsg);
            insertView(request,response);
        }




    }
    protected void updateView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CusContactService cusContactService = new CusContactServiceImpl();
        List<CusContact> cusList =cusContactService.list();
        request.setAttribute("cusList",cusList);

        StaffService staffService = new StaffServiceImpl();
        List<Staff> staffList = staffService.list();
        request.setAttribute("staffList",staffList);


        // 从response对象里获取out对象——response.getWriter()之前，要先设 置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if(!SysFun.isNullOrEmpty(vId)){
            Long iId = SysFun.parseLong(vId);
            CusOper bean = cusOperService.load(iId);

            if (bean != null){
                // 使用对象来回显    
                //     // request.setAttribute("bean", bean);     
                //     // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面 中进行获取
                request.setAttribute("operId",bean.getOperId());
                request.setAttribute("cusId",bean.getCusId());
                request.setAttribute("cusTel",bean.getCusTel());
                request.setAttribute("staffId",bean.getStaffId());
                request.setAttribute("cusAddr",bean.getCusAddr());
                request.setAttribute("faxNum",bean.getFaxNum());
                request.setAttribute("comName",bean.getComName());

                String toPage =UIConst.VIEWPATH+ "/CusOper_update.jsp";
                request.getRequestDispatcher(toPage).forward(request,response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected void updateDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先 设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // (0) 获取请求数据
        Integer vId = Integer.valueOf(request.getParameter("operId"));
        Integer cusId = Integer.valueOf(request.getParameter("cusId"));
        String cusTel = request.getParameter("cusTel");
        String cusAddr = request.getParameter("cusAddr");
        Integer staffId = Integer.valueOf(request.getParameter("staffId"));
        String faxNum = request.getParameter("faxNum");
        String comName = request.getParameter("comName");
        // 为了在输入页面回显原来的旧值,需要将旧值放到作用域,页面中进行获取
        request.setAttribute("operId", vId);
        request.setAttribute("cusId",cusId);
        request.setAttribute("cusTel",cusTel);
        request.setAttribute("staffId",staffId);
        request.setAttribute("cusAddr",cusAddr);
        request.setAttribute("faxNum",faxNum);
        request.setAttribute("comName",comName);

        // (1) 服务端验证
        String vMsg = "";
        if (SysFun.isNullOrEmpty(String.valueOf(vId))) {
            vMsg += "订单Id不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }
        if (SysFun.isNullOrEmpty(String.valueOf(cusId))) {
            vMsg += "客户名不能为空";
        }

        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }


        // (2) 数据库验证
        Long iId = SysFun.parseLong(String.valueOf(vId));
        CusOper bean = cusOperService.load(iId);
        if (bean == null) {
            vMsg = "数据不存在";
        }
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
            return;
        }

        // (3) 真正处理
        bean.setOperId(Math.toIntExact(iId));
        bean.setCusId(cusId);
        bean.setCusTel(cusTel);
        bean.setCusAddr(cusAddr);
        bean.setStaffId(staffId);
        bean.setFaxNum(faxNum);
        bean.setComName(comName);



        Long result = 0L;
        try {
            result = cusOperService.update(bean);
        } catch (Exception e) {
            vMsg = "修改失败." + e.getMessage();
            // TODO: handle exception
        }
        if (result > 0) {
            System.out.println("修改成功");
            //  // listView(request, response);
            // 如果修改成功，则父窗口页面的地址栏重新加载
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        } else {
            request.setAttribute("msg", vMsg);
            //System.out.println("修改失败");
            updateView(request, response);
        }
    }

    protected void detailView(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的 编码
        java.io.PrintWriter out = response.getWriter();
        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if(!SysFun.isNullOrEmpty(vId)){
            Long iPK = SysFun.parseLong(vId);
            CusOper bean = cusOperService.load(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean",bean);
                String toPage = UIConst.VIEWPATH + "/CusOper_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request,response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");
    }


    protected void deleteDeal(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页 面的编码
        java.io.PrintWriter out = response.getWriter();
        //取得逐渐，在根据主键，获取记录
        String vId = request.getParameter("id");
        System.out.println("===="+vId);
        if (!SysFun.isNullOrEmpty(vId)){
            Long iId=SysFun.parseLong(vId);

            Long result = 0L;
            result = cusOperService.delete(iId);

            if (result>0){
                out.print("ok");
                return;
            }
        }
        out.println("no ok");

    }

//    //登录检查
//    protected String checkLogin(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        javax.servlet.http.HttpSession session=request.getSession();
//        String toURL=null;
//        Object obj=session.getAttribute(UIConst.BG_LOGINUSER_KEY);
//        if (obj== null){
//            toURL=request.getContextPath()+UIConst.AREAPATH+"/Login";
//        }
//        return toURL;
//
//    }
}
