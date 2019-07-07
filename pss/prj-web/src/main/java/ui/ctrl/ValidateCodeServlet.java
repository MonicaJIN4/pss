package ui.ctrl;

import com.liuvei.common.RandFun;
import com.liuvei.common.ValidateCodeFun;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(UIConst.AREAPATH+"/ValidateCode")
public class ValidateCodeServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //从request里获取session对象和application对象
        javax.servlet.http.HttpSession session =request.getSession();

        //禁止图像缓存
        response.setHeader("Pragma","no-cache");
        response.setHeader("Cache-Control","no-cache");
        response.setContentType("image/jpeg");

        // 1）生成4位随机数字组成的字符串
        String strCode = RandFun.rand4Num().toString();
        // 2)随机字符串放入会话
        session.setAttribute(UIConst.BG_VALLDATE_CODE_KEY,strCode);
        // 3)随机字符串转换为图片
        java.awt.image.BufferedImage image = ValidateCodeFun.generalImage(strCode);
        // 4) BufferedImage图片，作为响应管道的输出流
        // 将图像输出到Servlet输出流中。
        javax.servlet.ServletOutputStream outStream = response.getOutputStream();
        javax.imageio.ImageIO.write(image,"jpeg",outStream);
        outStream.close();
        response.flushBuffer();
    }
}
