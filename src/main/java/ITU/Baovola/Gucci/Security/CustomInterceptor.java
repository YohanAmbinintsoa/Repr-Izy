package ITU.Baovola.Gucci.Security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;

import ITU.Baovola.Gucci.Models.User;

@Component
public class CustomInterceptor implements HandlerInterceptor{
    JwtUtils utils=new JwtUtils();

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        // TODO Auto-generated method stub
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "3600");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type, authorization");
        if (handler instanceof  HandlerMethod) {
            HandlerMethod method=(HandlerMethod) handler;
            Method meth=method.getMethod();
            if (meth.isAnnotationPresent(Authority.class)) {
              String token=JwtUtils.getToken(request);
              System.out.println(token);
              if (isAuthenticated(token)) {
                  Authority auth=meth.getAnnotation(Authority.class);
                  Role[] roles=auth.role();
                  String userRole=this.utils.getRoleFromToken(token);
                  for (Role role : roles) {
                    if (role.toString().equals(userRole)) {
                        User user=new User();
                        user.setId(this.utils.getUserIdFromToken(token));
                        user=(User)MyContext.getRequester().select(null, user).get(0);
                        MyContext.setUser(user);
                        return true;
                    }
                  }
                  this.sendErrorResponse(response, "Privileges Insuffisants!");
              } else {
                  this.sendErrorResponse(response, "Veuillez vous connecter!");
              }
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean isAuthenticated(String token) throws Exception{
        boolean valiny=false;
        if (!token.equals("")&&utils.validateJwtToken(token)) {
            valiny=true;
        }
        return valiny;
    }    

    private void sendErrorResponse(HttpServletResponse response, String errorMessage) throws Exception {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        Map<String, String> errorResponse = new HashMap<>();
        errorResponse.put("error", errorMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonErrorResponse = objectMapper.writeValueAsString(errorResponse);
        response.getWriter().write(jsonErrorResponse);
    }
}
