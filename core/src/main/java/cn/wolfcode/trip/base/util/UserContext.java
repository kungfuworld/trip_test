package cn.wolfcode.trip.base.util;


import cn.wolfcode.trip.base.domain.User;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UserContext {

    /*private static ServletRequestAttributes requestAttributes
            = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();*/

    /**
     * 获得session
     * @return : session
     */
    public static HttpSession getSession() {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.
                getRequest().
                getSession();
    }

    /**
     * 获得请求
     * @return : 当前请求
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 获得响应对象
     * @return : 响应对象
     */
    public static HttpServletResponse getResponse() {
        ServletRequestAttributes requestAttributes
                = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getResponse();
    }

    /**
     * 给session设置当前员工对象
     * @param currentUser : 员工对象
     */
    public static void setCurrentUser(User currentUser) {
        HttpSession session = getSession();
        if (currentUser == null) {
            getSession().invalidate();
            return;
        }
        getSession()
                .setAttribute("USER_IN_SESSION", currentUser);
    }

    /**
     * 从session中获得员工对象
     * @return : 员工对象
     */
    public static User getCurrentUser() {
        return (User) getSession().getAttribute("USER_IN_SESSION");
    }

    /**
     * 设置权限表达式在session中
     * @param expressions
     */
    /*public static void setExpressions(Set<String> expressions) {
        getSession().setAttribute("PERMISSION_IN_SESSION", expressions);
    }*/

    /**
     * 从session中获得权限表达式
     * @return
     */
    /*public static Set<String> getExpressions() {
        return (Set<String>) getSession().getAttribute("PERMISSION_IN_SESSION");
    }*/


    /*public static Cookie[] getCookies() {
        return getRequest().getCookies();
    }*/

    /*public static void addCookies(User user) throws UnsupportedEncodingException {
        Cookie rememberMeCookie = new Cookie("rememberMe", URLEncoder.encode(user.getName(),"utf-8"));
        rememberMeCookie.setMaxAge(60 * 60 * 24 * 7);
        HttpServletResponse response = getResponse();
        response.addCookie(rememberMeCookie);
    }*/

    /*public static void removeCookies() {
        for (Cookie cookie : getCookies()) {
            if (cookie.getName().equals("rememberMe")) {
                cookie.setMaxAge(0);
                getResponse().addCookie(cookie);
            }
        }
    }*/

    //shiro相关:获得主体
 /*   public static Subject getSubject(){
        return SecurityUtils.getSubject();
    }

    public static User getCurrentUser(){
        return (User)getSubject().getPrincipal();
    }

    public static boolean isAdminOrManager(){
        Subject subject = getSubject();
        User user = (User)subject.getPrincipal();
        return user.getAdmin() || subject.hasRole("PRIMARY MANAGER");
    }*/
}
