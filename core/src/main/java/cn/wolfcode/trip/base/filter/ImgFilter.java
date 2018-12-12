package cn.wolfcode.trip.base.filter;

import cn.wolfcode.trip.base.util.ImageUploadUtil;
import org.springframework.util.FileCopyUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

public class ImgFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        //将请求的路径拿到
        System.out.println(req.getRequestURI());
        File file = new File(ImageUploadUtil.DICTIONERY, req.getRequestURI());
        //判断是否存在文件
        if(file.exists()){
            //获取请求所在真实路径的文件
            //获取文件的byte数组
            byte[] fileBytes = FileCopyUtils.copyToByteArray(file);
            response.getOutputStream().write(fileBytes);
        }else{
            chain.doFilter(request,response);
        }
    }
    @Override
    public void destroy() {

    }
}
