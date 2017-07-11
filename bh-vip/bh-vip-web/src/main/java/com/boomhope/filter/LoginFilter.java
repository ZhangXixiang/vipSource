package com.boomhope.filter;



import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * @version 2017-03-08
 * @author zy
 *
 */
public class LoginFilter extends OncePerRequestFilter {  
	
	private static final Log log = LogFactory.getLog(LoginFilter.class);
	
    @Override  
    protected void doFilterInternal(HttpServletRequest request,  
            HttpServletResponse response, FilterChain filterChain)  
            throws ServletException, IOException {  
    	
        // 不拦截的url  
        String[] notFilter = new String[] {"/login","/index","/camera/sendFaceImgVip","/pad/uploadPadStatus","/pad/sendFaceRec"};  
  
        // 请求的url  
        String url = request.getRequestURI();  
        if(url.indexOf("bh-vip-web") != -1){  
            boolean doFilter = chek(notFilter,url);  
            if(doFilter){  
                Object obj = request.getSession().getAttribute("loginUser");  
                if(null==obj){  
                	request.setCharacterEncoding("GBK");
					response.setCharacterEncoding("GBK");
                    // 如果session中不存在登录者实体，则弹出框提示重新登录  
                    PrintWriter out = response.getWriter();  
                    String loginPage = request.getContextPath()+"/index.jsp";  
                    StringBuilder builder = new StringBuilder();
					builder.append("<script type=\"text/javascript\">");
					//builder.append("alert('网页已过期 请重新登录！');");
					builder.append("window.top.location.href='");
					builder.append(loginPage);
					builder.append("';");
					builder.append("</script>");
					out.print(builder.toString()); 
                    out.print(builder.toString());  
                }else {  
                    filterChain.doFilter(request, response);  
                }  
            }else{  
                filterChain.doFilter(request, response);  
            }  
        }else{  
            filterChain.doFilter(request, response);  
        }  
    }  
      
    /** 
     * @param notFilter 不拦截的url 
     * @param url ：请求的url 
     * @return false：不拦截 
     *         true：拦截 
     */  
    public boolean chek(String[] notFilter,String url){  
        //url以css和js结尾的不进行拦截  
        if(url.endsWith(".css") || url.endsWith(".js") || url.endsWith(".jpg") || url.endsWith(".png")){  
            return false;  
        }  
        //含有notFilter中的任何一个则不进行拦截  
        for (String s : notFilter) {  
            if (url.indexOf(s) != -1) {  
                return false;  
            }  
        }  
        return true;  
    }  
  
}