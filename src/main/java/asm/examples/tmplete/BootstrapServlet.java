package asm.examples.tmplete;


import io.swagger.jaxrs.config.BeanConfig;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BootstrapServlet
  extends HttpServlet
{
  @Override
  public void init(ServletConfig paramServletConfig)
    throws ServletException
  {
    BeanConfig localBeanConfig = new BeanConfig();
    localBeanConfig.setTitle("vdbName");
    localBeanConfig.setDescription("");
    localBeanConfig.setVersion("version");
    localBeanConfig.setBasePath("baseUrl");
    localBeanConfig.setResourcePackage("packages");
    localBeanConfig.setScan(true);
  }
  
  @Override
  public void doGet(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {
    doPost(paramHttpServletRequest, paramHttpServletResponse);
  }
  
  @Override
  public void doPost(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse)
    throws ServletException, IOException
  {}
}