package asm.examples.samples;

import io.swagger.jaxrs.config.BeanConfig;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BootstrapServlet extends HttpServlet{

    private static final long serialVersionUID = 8320267972392260667L;
    
    static final String BASEURL = "";

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init();
        
      //TODO-- read from servlet parameter
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle("Jaxrs Examples Petstore");
        beanConfig.setDescription("");
        beanConfig.setVersion("1.0.0");
        beanConfig.setSchemes(new String[]{"http"});
        beanConfig.setBasePath(BASEURL);
        beanConfig.setResourcePackage("org.teiid.jboss.rest");
        beanConfig.setScan(true);
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String location = buildLocation(req, resp);
        resp.sendRedirect(location);
    }

    private String buildLocation(HttpServletRequest request, HttpServletResponse resp) {
        String path = request.getContextPath();
        String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
//        System.out.println(path);
//        System.out.println(basePath);
        String base = path + "/teiid.html";
        String restPrefix = "rest";
        String swagger = "/swagger.json";
        String param = "/url=" + basePath + restPrefix + swagger;
        return base + "?" + param;
    }

}
