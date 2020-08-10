package seckill.com.seckilljmvc.jingzhi;

import com.esotericsoftware.reflectasm.MethodAccess;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.freemarker.FreeMarkerConfigurationFactory;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lijingzhi
 * 中文名 李敬芝
 * @create 2019/1/29
 */
public abstract class JingMVC extends HttpServlet {
    MethodAccess access;
    @Autowired
    public  Configuration configuration;
    @Override
    public void init() throws ServletException {
        super.init();
        Class<? extends JingMVC> clazz = this.getClass();
        access = MethodAccess.get(clazz);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb=req.getRequestURL();
        String url=sb.toString();
        int last=url.lastIndexOf("/");
        String subUrl = url.substring(last+1);
        Map<String, Object> map = new HashMap<>();
        int index = access.getIndex(subUrl, HttpServletRequest.class, HttpServletResponse.class,Map.class);
        String viewUrl= (String) access.invoke(this, index, req, resp,map);
        Template t = configuration.getTemplate(viewUrl, "UTF-8");
        String content= null;
        try {
            content = FreeMarkerTemplateUtils.processTemplateIntoString(t,map);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().append(content);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        StringBuffer sb=req.getRequestURL();
        String url=sb.toString();
        int last=url.lastIndexOf("/");
        String subUrl = url.substring(last+1);
        Map<String, Object> map = new HashMap<>();
        int index = access.getIndex(subUrl, HttpServletRequest.class, HttpServletResponse.class,Map.class);
        String viewUrl= (String) access.invoke(this, index, req, resp,map);
        Template t = configuration.getTemplate(viewUrl, "UTF-8");
        String content= null;
        try {
            content = FreeMarkerTemplateUtils.processTemplateIntoString(t,map);
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        resp.setContentType("text/html; charset=utf-8");
        resp.getWriter().append(content);
    }
}
