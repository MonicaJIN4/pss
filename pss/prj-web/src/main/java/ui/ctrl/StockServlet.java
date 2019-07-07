package ui.ctrl;

import bean.Cargo;
import bean.Excel;
import bean.Stock;
import com.liuvei.common.PagerItem;
import com.liuvei.common.SysFun;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import service.CargoService;
import service.StockService;
import service.impl.CargoServiceImpl;
import service.impl.StockServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet(UIConst.AREAPATH+"/Stock")
public class StockServlet extends HttpServlet {

    StockService stockService = new StockServiceImpl();
    CargoService cargoService = new CargoServiceImpl();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

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
//        java.io.PrintWriter out = response.getWriter();
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

            case "msg":
                try {
                    msgView(request, response);//预警处理
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;

            case "msgnum":
                msgNumber(request,response);
                break;
            case "excel":
                excel(request,response);
                break;
            default:
                System.out.println("oper不存在。");
                break;
        }
    }

    protected void listView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Stock> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
        rowCount = stockService.count();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = stockService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Stock_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void listDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取请求参数
        String searchName = request.getParameter("searchName");
        // 显示请求数据
        request.setAttribute("searchName", searchName);

        List<Stock> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        if (SysFun.isNullOrEmpty(searchName)) {
            // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = stockService.count();
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = stockService.pager(pagerItem.getPageNum(), pagerItem.getPageSize());

        } else {
            // 分页步骤2.2.  根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
            rowCount = stockService.countByName(searchName);
            // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
            pagerItem.changeRowCount(rowCount);
            // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
            vDataList = stockService.pagerByName(searchName, pagerItem.getPageNum(), pagerItem.getPageSize());
        }

        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);


        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Stock_list.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);

    }

    protected void insertView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //连接到cargo表
        List<Cargo> cargoList = cargoService.list();
        request.setAttribute("cargoList",cargoList);

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Stock_insert.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }


    protected void insertDeal(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();
        // 获取请求数据
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer cargoId = Integer.parseInt(request.getParameter("cargoId"));
        Date buyDate = sdf.parse(request.getParameter("buyDate"));
        Date sellDate = sdf.parse(request.getParameter("sellDate"));

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("number", number);
        request.setAttribute("cargoId", cargoId);
        request.setAttribute("buyDate", buyDate);
        request.setAttribute("sellDate", sellDate);

        //服务端验证
        String vMsg = "";
        if (cargoId == 0) {
            vMsg += "cargoId不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }

        Stock bean = new Stock();
        bean.setNumber(number);
        bean.setCargoId(cargoId);
        bean.setBuyDate(buyDate);
        bean.setSellDate(sellDate);


        Long result = 0L;
        try {
            result = stockService.insert(bean);
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
        //连接到cargo表
        List<Cargo> cargoList = cargoService.list();
        request.setAttribute("cargoList",cargoList);

        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        // 取得主键，再根据主键，获取记录
        String vId = request.getParameter("id");

        if (!SysFun.isNullOrEmpty(vId)) {
            Long iId = SysFun.parseLong(vId);
            Stock bean = stockService.loadByStockId(iId);
            if (bean != null) {
                // 使用对象来回显
                // request.setAttribute("bean", bean);
                //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取

                request.setAttribute("stockId",bean.getStockId());
                request.setAttribute("number", bean.getNumber());
                request.setAttribute("cargoId", bean.getCargoId());
                request.setAttribute("buyDate", bean.getBuyDate());
                request.setAttribute("sellDate", bean.getSellDate());

                String toPage = UIConst.VIEWPATH + "/Stock_update.jsp";
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
        Integer vId = Integer.parseInt(request.getParameter("stockId"));
        Integer number = Integer.parseInt(request.getParameter("number"));
        Integer cargoId = Integer.parseInt(request.getParameter("cargoId"));
        Date buyDate = sdf.parse(request.getParameter("buyDate"));
        Date sellDate = sdf.parse(request.getParameter("sellDate"));

        //为了在输入页面回显原来的旧值，将旧值放到作用域，页面中进行获取
        request.setAttribute("stockId",vId);
        request.setAttribute("number", number);
        request.setAttribute("cargoId", cargoId);
        request.setAttribute("buyDate", buyDate);
        request.setAttribute("sellDate", sellDate);

        //服务端验证
        String vMsg = "";
        if (cargoId == 0) {
            vMsg += "cargoId不能为空";
        }
        // 如果验证失败,则将失败内容放到作用域变量,并转发到页面
        if (!SysFun.isNullOrEmpty(vMsg)) {
            request.setAttribute("msg", vMsg);
            System.out.println(vMsg);
            insertView(request, response);
            return;
        }


        // (2) 数据库验证
        Long iId = Long.valueOf(vId);
        Stock bean = stockService.loadByStockId(iId);
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
        bean.setCargoId(cargoId);
        bean.setBuyDate(buyDate);
        bean.setSellDate(sellDate);

        Long result = 0L;
        try {
            result = stockService.update(bean);
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
            result = stockService.delete(iId);
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
            Stock bean = stockService.loadByStockId(iPK);
            if (bean != null) {
                // 使用对象来回显
                request.setAttribute("bean", bean);

                String toPage = UIConst.VIEWPATH + "/Stock_detail.jsp";
                request.getRequestDispatcher(toPage).forward(request, response);
                return;
            }
        }
        out.println("<script>");
        out.println("alert('数据不存在.');");
        out.println("parent.window.location.reload();");
        out.println("</script>");

    }

    protected void msgView(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
        List<Stock> vDataList = null;
        // ---------------------------------------------------------------------------
        // 分页步骤1. 创建PagerIter对象，处理url传过来的pagesize和pageindex
        PagerItem pagerItem = new PagerItem();
        pagerItem.parsePageSize(request.getParameter(pagerItem.getParamPageSize()));
        pagerItem.parsePageNum(request.getParameter(pagerItem.getParamPageNum()));

        // 分页步骤2.1. 定义记录数变量
        Long rowCount = 0L;
        // 分页步骤2.2. 根据条件，查找符合条件的所有记录数 ***** count()要根据实际换成其他方法
        rowCount = stockService.countBySafe();
        // 分页步骤2.3. 将记录数赋给pagerItem，以便进行分页的各类计算
        pagerItem.changeRowCount(rowCount);
        // 分页步骤2.4. 从数据库取指定分页的数据 ***** pager()要根据实际换成其它方法
        vDataList = stockService.pagerBySafe(pagerItem.getPageNum(), pagerItem.getPageSize());
        // 分页步骤2.5. 设置页面的跳转url
        pagerItem.changeUrl(SysFun.generalUrl(request.getRequestURI(),
                request.getQueryString()));
        // 分页步骤3. 将分页对象和数据列表,放到作用域,以便页面可以访问
        request.setAttribute("pagerItem", pagerItem);
        request.setAttribute("DataList", vDataList);
        // ------------------------------------------------------------------------

        //转发到页面
        String toPage = UIConst.VIEWPATH + "/Stock_msg.jsp";
        request.getRequestDispatcher(toPage).forward(request, response);
    }

    protected void msgNumber(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        // 从response对象里获取out对象——response.getWriter()之前，要先设置页面的编码
        java.io.PrintWriter out = response.getWriter();

        Long rowCount = 0L;
        rowCount = stockService.countBySafe();
        out.print(rowCount);
    }

    //库存预警导出
    protected void excel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //设置相应头和编码方式
        response.setCharacterEncoding("utf-8");
        //设置响应头格式，此格式浏览器默认为下载文件
        response.setHeader("Content-Disposition","attachment;");
        //获取response输出流，像浏览器输出文件内容

        //获取数据库数据
        ServletOutputStream out = response.getOutputStream();
        List<Stock> list = stockService.listBySafe();
        //导出
        XSSFWorkbook workbook = null;
        try {
            workbook = Excel.toWrite(list,1,6);
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
}
