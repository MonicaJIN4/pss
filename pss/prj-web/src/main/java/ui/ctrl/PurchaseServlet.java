package ui.ctrl;

import bean.*;
import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import dao.CargoDao;
import dao.impl.CargoDaoImpl;
import dao.impl.StockDaoImpl;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.CargoService;
import service.PurchaseService;
import service.StockService;
import service.SupplierService;
import service.impl.CargoServiceImpl;
import service.impl.PurchaseServiceImpl;
import service.impl.StockServiceImpl;
import service.impl.SupplierServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
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

@WebServlet(UIConst.AREAPATH+"/Purchase")
public class PurchaseServlet extends HttpServlet {

    PurchaseService purchaseService = new PurchaseServiceImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    CargoService cargoService =new CargoServiceImpl();
    SupplierService supplierService =new SupplierServiceImpl();
//    StockService stockService = new StockServiceImpl();



    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        /* **************************************************************** */
        /* ********** Servlet的doXXX方法中的编码方式和6个标准对象 ********** */ /* **************************************************************** */
        // ***** Servlet的doXXX方法中的编码方式
        // 设置请求对象的编码方式
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
            case "excel":
                excel(request,response);//导出
                break;

            case "redetail":
                reDetail(request,response);//退货详情
                break;

            default:
                System.out.println("oper不存在。");
                break;
        }

    }


    protected void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Purchase> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
        rowCount = purchaseService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = purchaseService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Purchase_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 显示请求数据
        request.setAttribute("searchName", searchName);

        List<Purchase> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = purchaseService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = purchaseService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());

        } else {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = purchaseService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = purchaseService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }

        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Purchase_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void insertView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cargo> cargoList = cargoService.list();
        List<Supplier> supList = supplierService.list();

        request.setAttribute("cargoList",cargoList);
        request.setAttribute("supList",supList);


        /*-------------------------------------------------------------------*/
        //库存预警
        int stockId = Integer.parseInt(request.getParameter("stockId"));
        Long sid = Long.valueOf(stockId);

        System.out.println("------------------");
        System.out.println(stockId);
        System.out.println("------------------");

        StockService stockService = new StockServiceImpl();
        Stock stock = stockService.loadByStockId(sid);

        request.setAttribute("cargoName",stock.getCargoName());
        request.setAttribute("cargoId",stock.getCargoId());

        Cargo cargoBean = cargoService.load(Long.valueOf(stock.getCargoId()));
        request.setAttribute("unit",cargoBean.getUnit());
        request.setAttribute("adviceBuyPrice",cargoBean.getBuyPrice());

        System.out.println("------------------");
        System.out.println(stock.getCargoName());
        System.out.println(stock.getCargoId());
        System.out.println(cargoBean.getUnit());
        System.out.println(cargoBean.getBuyPrice());
        System.out.println("------------------");
        /*-------------------------------------------------------------------*/

        if (stockId > 0 ){
            //转发到页面
            String toPage = UIConst.VIEWPATH + "/Purchase_insert_msg.jsp";
            request.getRequestDispatcher(toPage).forward(request, response);
        }else {
            //转发到页面
            String toPage = UIConst.VIEWPATH + "/Purchase_insert.jsp";
            request.getRequestDispatcher(toPage).forward(request, response);
        }


    }

    protected void insertDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        int supId = Integer.parseInt(request.getParameter("supId"));
        Date date = sdf.parse(request.getParameter("date"));

        int number = Integer.parseInt(request.getParameter("number"));
        BigDecimal buyPrice = new BigDecimal(request.getParameter("buyPrice"));
        BigDecimal total =buyPrice.multiply(new BigDecimal(number));
        int cargoId = Integer.parseInt(request.getParameter("cargoId"));

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取

        request.setAttribute("supId",supId);
        request.setAttribute("date",date);
        request.setAttribute("total",total);
        request.setAttribute("cargoId",cargoId);
        request.setAttribute("number",number);
        request.setAttribute("buyPrice",buyPrice);

        //服务端验证
        String vMsg = "";
        if (supId==0) {
            vMsg += "供应商不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        Purchase bean = new Purchase();
        bean.setSupId(supId);
        bean.setDate(date);
        bean.setTotal(total);
        bean.setCargoId(cargoId);
        bean.setNumber(number);
        bean.setBuyPrice(buyPrice);


        Long result = 0L;
        try {
            result = purchaseService.insert(bean);
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
        List<Cargo> cargoList = cargoService.list();
        List<Supplier> supList = supplierService.list();

        request.setAttribute("cargoList",cargoList);
        request.setAttribute("supList",supList);

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Purchase bean = purchaseService.load(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                // 为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
                request.setAttribute("purId", bean.getPurId());
                request.setAttribute("supId",bean.getSupId());
                request.setAttribute("date",bean.getDate());
                request.setAttribute("total",bean.getTotal());
                request.setAttribute("cargoId",bean.getCargoId());
                request.setAttribute("number",bean.getNumber());
                request.setAttribute("buyPrice",bean.getBuyPrice());
                // 外键
                Cargo cargoBean = cargoService.load(iId);
                request.setAttribute("cargoName",cargoBean.getCargoName());
                request.setAttribute("adviceBuyPrice",cargoBean.getBuyPrice());
                request.setAttribute("unit",cargoBean.getUnit());


                String toPage = UIConst.VIEWPATH + "/Purchase_update.jsp";
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
        int vId = Integer.parseInt(request.getParameter("purId"));
        int supId = Integer.parseInt(request.getParameter("supId"));
        int number = Integer.parseInt(request.getParameter("number"));
        BigDecimal buyPrice = new BigDecimal(request.getParameter("buyPrice"));
        BigDecimal total =buyPrice.multiply(new BigDecimal(number));

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("purId", vId);
        request.setAttribute("supId",supId);
//        request.setAttribute("date",date);
        request.setAttribute("total",total);
//        request.setAttribute("cargoId",cargoId);
        request.setAttribute("number",number);
        request.setAttribute("buyPrice",buyPrice);

        //服务端验证
        String vMsg = "";

        if (supId==0) {
            vMsg += "供应商名不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        // (2) 数据库验证

        Purchase bean = purchaseService.load((long) vId);
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
        bean.setPurId(vId);
        bean.setSupId(supId);
//        bean.setDate(date);
        bean.setTotal(total);
//        bean.setCargoId(cargoId);
        bean.setNumber(number);
        bean.setBuyPrice(buyPrice);


        Long result = 0L;
        try {
            result = purchaseService.update(bean);
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
        } else if(result == 0L){
            vMsg += "库存不足，修改失败";
            request.setAttribute("msg", vMsg);
            System.out.println("库存不足，修改失败");
            updateView(request, response);
        } else{
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
            result = purchaseService.delete(iId);
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
            Purchase bean = purchaseService.load(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);

                String toPage = UIConst.VIEWPATH + "/Purchase_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected void excel(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置相应头和编码方式
        response.setCharacterEncoding("utf-8");
        //设置响应头格式，此格式浏览器默认为下载文件
        response.setHeader("Content-Disposition","attachment; ");

        //获取response输出流，像浏览器输出文件内容

        //获取数据库数据
        ServletOutputStream out = response.getOutputStream();
        List<Purchase> list = purchaseService.list_Excel();

        //导出
        XSSFWorkbook workbook = null;
        try {
            workbook = Excel.toWrite(list,1,10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //将workbook写入相应流中
        workbook.write(out);
        //立即刷新相应流
        out.flush();
        //关闭相应流
        out.close();


    }


    protected void reDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");
        if (!SysFun.isNullOrEmpty(vId)) {
            Long iPK = SysFun.parseLong(vId);
            Purchase bean = purchaseService.load(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);
                String toPage = UIConst.VIEWPATH + "/Purchase_returnDetail.jsp";
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
