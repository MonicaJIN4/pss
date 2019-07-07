package ui.ctrl;

import bean.*;
import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import service.*;
import service.impl.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(UIConst.AREAPATH+"/SaleReturn")
public class SaleReturnServlet extends HttpServlet {

    SaleReturnService saleReturnService = new SaleReturnServiceImpl();
    ReturnNoteService returnNoteService = new ReturnNoteServiceImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    CargoService cargoService =new CargoServiceImpl();
    SupplierService supplierService =new SupplierServiceImpl();
    StaffService staffService = new StaffServiceImpl();
    SaleOutService saleOutService = new SaleOutServiceImpl();

    List<Cargo> cargoList = cargoService.list();
    List<Supplier> supList = supplierService.list();
    List<Staff> staffList = staffService.list();

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
        //javax.servlet.http.HttpSession session = request.getSession();
        //javax.servlet.ServletContext application = request.getServletContext();
        // 调用继承的方法来获取config对象
        //javax.servlet.ServletConfig config = getServletConfig();
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        //java.io.PrintWriter out = response.getWriter();
        /* ----------------------------------------------------------------- */


        //检测是否登录
        //String toURL = checkLogin(request,response);
        //if(toURL!=null){
        //response.sendRedirect(toURL);
        //return;
        //}

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
                try {
                    insertDeal(request, response); // 添加处理
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case "update":
                updateView(request, response); // 添加处理
                break;
            case "updatedeal":
                try {
                    updateDeal(request, response); //修改处理
                } catch (ParseException e) {
                    e.printStackTrace();
                }
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
        List<SaleReturn> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
        rowCount = saleReturnService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = saleReturnService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/SaleReturn_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        String searchId = request.getParameter("searchId");
        int view = Integer.parseInt(request.getParameter("view"));

        // 显示请求数据
        request.setAttribute("searchName", searchName);
        request.setAttribute("searchId",searchId);


        List<SaleReturn> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName) && SysFun.isNullOrEmpty(searchId)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = saleReturnService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = saleReturnService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());

        } else if (SysFun.isNullOrEmpty(searchName) && !SysFun.isNullOrEmpty(searchId)){
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = saleReturnService.countById(searchId);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = saleReturnService.pagerBySaleId(searchId,pagerItem.getPageNum(), pagerItem.getPageSize());
        }else{
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = saleReturnService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = saleReturnService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }




        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        if(view == 2){
            //转发到只有列表的页面
            String toPage = UIConst.VIEWPATH + "/SaleReturn_smallList.jsp";
            request.getRequestDispatcher(toPage).forward(request, response);
        }else{
            //转发到页面
            String toPage = UIConst.VIEWPATH + "/SaleReturn_list.jsp";
            request.getRequestDispatcher(toPage).forward(request, response);
        }


    }

    protected void insertView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("cargoList",cargoList);
        request.setAttribute("supList",supList);
        request.setAttribute("staffList",staffList);

        // 取得主键，再根据主键，获取记录
//        String vId = request.getParameter("id");
        String saleId = request.getParameter("saleId");





        if (!SysFun.isNullOrEmpty(saleId)) {
            Long iId = SysFun.parseLong(saleId);
            SaleOut saleBean = saleOutService.load(iId);
            if (saleBean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
                request.setAttribute("saleId",saleId);
                request.setAttribute("cargoId",saleBean.getCargoId());
                request.setAttribute("number",saleBean.getNumber());
                request.setAttribute("price",saleBean.getPrice());

                // 外键
                Cargo cargoBean = cargoService.load((long)saleBean.getCargoId());
                request.setAttribute("cargoName",cargoBean.getCargoName());
                request.setAttribute("unit",cargoBean.getUnit());


                String toPage = UIConst.VIEWPATH + "/SaleReturn_insert.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;

            }
        }


        //转发到页面
        String toPage = UIConst.VIEWPATH + "/SaleReturn_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void insertDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据


        int cargoId = Integer.parseInt(request.getParameter("cargoId"));
        int saleId = Integer.parseInt(request.getParameter("saleId"));
        int number = Integer.parseInt(request.getParameter("number"));
        BigDecimal price = new BigDecimal(request.getParameter("price"));
        String remark =request.getParameter("remark");
        Date returnDate = sdf.parse(request.getParameter("returnDate"));
        BigDecimal total =price.multiply(new BigDecimal(number));





        // 添加失败后的回显： 为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("saleId",saleId);
        request.setAttribute("cargoId",cargoId);
        request.setAttribute("number",number);
        request.setAttribute("price",price);
        request.setAttribute("remark",remark);
        request.setAttribute("returnDate",returnDate);
        request.setAttribute("total",total);


        //服务端验证
        String vMsg = "";
        if (number==0) {
            vMsg += "数量不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        SaleReturn bean = new SaleReturn();
        bean.setSaleId(saleId);
        bean.setCargoId(cargoId);
        bean.setNumber(number);
        bean.setPrice(price);
        bean.setRemark(remark);
        bean.setReturnDate(returnDate);
        bean.setTotal(total);


        Long result = 0L;
        try {
            result = saleReturnService.insert(bean);
        } catch (Exception e) {
            vMsg = "添加失败." + e.getMessage();
            //TODO: handle exception
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
        request.setAttribute("cargoList",cargoList);
        request.setAttribute("supList",supList);
        request.setAttribute("staffList",staffList);

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");//这个id是resaleId
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            SaleReturn bean = saleReturnService.load(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
                request.setAttribute("reSaleId", bean.getReSaleId());
                request.setAttribute("saleId",bean.getSaleId());
                request.setAttribute("cargoId",bean.getCargoId());
                request.setAttribute("number",bean.getNumber());
                request.setAttribute("price",bean.getPrice());
                request.setAttribute("remark",bean.getRemark());
                request.setAttribute("returnDate",bean.getReturnDate());
                request.setAttribute("total",bean.getTotal());
                // 外键
                Cargo cargoBean = cargoService.load((long)bean.getCargoId());
                request.setAttribute("cargoName",cargoBean.getCargoName());
                request.setAttribute("unit",cargoBean.getUnit());

                SaleOut saleBean = saleOutService.load((long)bean.getSaleId());
                request.setAttribute("sellPrice",saleBean.getPrice());
                request.setAttribute("sellNumber",saleBean.getNumber());
                request.setAttribute("staffName",saleBean.getcName());



                String toPage = UIConst.VIEWPATH + "/SaleReturn_update.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;

            }
        }

        out.println("<script>");
        out.println("alert('数据不存在');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected void updateDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        int vId = Integer.parseInt(request.getParameter("reSaleId"));
        int number = Integer.parseInt(request.getParameter("number"));
        BigDecimal returnPrice = new BigDecimal(request.getParameter("returnPrice"));
        String remark =request.getParameter("remark");
        Date returnDate = sdf.parse(request.getParameter("returnDate"));
        BigDecimal total =returnPrice.multiply(new BigDecimal(number));


        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("number",number);
        request.setAttribute("returnPrice",returnPrice);
        request.setAttribute("remark",remark);
        request.setAttribute("returnDate",returnDate);
        request.setAttribute("total",total);

        //服务端验证
        String vMsg = "";

//        if (supId==0) {
//            vMsg += "供应商名不能为空";
//        }
//        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
//        if (!SysFun.isNullOrEmpty(vMsg)) {
//            request.setAttribute("msg", vMsg);
//            System.out.println(vMsg);
//            insertView(request, response);
//            return;
//        }

        // (2) 数据库验证

        SaleReturn bean = saleReturnService.load((long) vId);
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
        bean.setNumber(number);
        bean.setPrice(returnPrice);
        bean.setRemark(remark);
        bean.setReturnDate(returnDate);
        bean.setTotal(total);


        Long result = 0L;
        try {
            result = saleReturnService.update(bean);
        } catch (Exception e) {
            vMsg = "修改失败." + e.getMessage();
            //TODO: handle exception
        }

        if (result > 0) {
            //System.out.println("修改成功");

            //如果修改成功，则父窗口页面的地址栏重新加载
            out.println("<script>");
            out.println("parent.window.location.reload();");
            out.println("</script>");
        }else if(result == -1L){
            vMsg += "库存不足以退货";
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            updateView(request, response);
        }else {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
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
            result = saleReturnService.delete(iId);
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
            ReturnNote bean = returnNoteService.load(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);

                String toPage = UIConst.VIEWPATH + "/SaleReturn_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected String checkLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        javax.servlet.http.HttpSession session = request.getSession();
        String toURL = null;
        Object obj = session.getAttribute(UIConst.BG_LOGINUSER_KEY);
        // 如果获取对象为空，说明未登录，转到Login页面
        if (obj == null){
            toURL = request.getContextPath() + UIConst.AREAPATH + "/Login";
        }
        return toURL;
    }
}
