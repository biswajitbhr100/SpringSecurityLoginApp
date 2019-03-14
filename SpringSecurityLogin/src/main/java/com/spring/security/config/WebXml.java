package com.spring.security.config;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
public class WebXml extends AbstractAnnotationConfigDispatcherServletInitializer{
	@Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { ServletXml.class };
    }
  
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return null;
    }
  
    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }
}
