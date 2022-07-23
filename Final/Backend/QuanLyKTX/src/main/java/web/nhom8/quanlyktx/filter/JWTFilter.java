package web.nhom8.quanlyktx.filter;


import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.utils.JWTParseObject;
import web.nhom8.quanlyktx.utils.TokenJWTUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

@WebFilter(asyncSupported = true, urlPatterns = {"/*"})
public class JWTFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String url = request.getRequestURI();
        System.out.println(url);
        String rolecode = "NAN";

        if (!url.startsWith("/QuanLyKTX_war_exploded/api-login"))
        {
            String method = request.getHeader("Access-Control-Request-Headers");
            if (Objects.equals(method, "authorization") || Objects.equals(method, "authorization,content-type") ){
                setHeaderResponse(response);
                response.setStatus(HttpServletResponse.SC_ACCEPTED);
                return;
            }

            String jwtToken = request.getHeader("Authorization");
            if(TokenJWTUtils.validateToken(jwtToken)) {
                System.out.println(jwtToken);
                JWTParseObject jwtParseObject = TokenJWTUtils.getUserIdAndRoleCodeFromJWT(jwtToken);
                System.out.println(jwtParseObject.getUserId());
                System.out.println(jwtParseObject.getRoleCode());
                rolecode = jwtParseObject.getRoleCode();
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
        if (    url.startsWith("/QuanLyKTX_war_exploded/api-student_manager") ||
                url.startsWith("/QuanLyKTX_war_exploded/api-account_manager")
            ) {
            if (rolecode.equalsIgnoreCase("QLSV") || rolecode.equalsIgnoreCase("QLT"))
            {
                filterChain.doFilter(servletRequest, servletResponse);
            } else sendMessageError(response);
        } else if (url.startsWith("/QuanLyKTX_war_exploded/*")) {
            if (rolecode.equalsIgnoreCase("QLT"))
            {
                filterChain.doFilter(servletRequest, servletResponse);
            } else sendMessageError(response);
        } else if (url.startsWith("/QuanLyKTX_war_exploded/room") || url.startsWith("/QuanLyKTX_war_exploded/room/student")) {
            if (rolecode.equalsIgnoreCase("QLPO"))
            {
                filterChain.doFilter(servletRequest, servletResponse);
            } else sendMessageError(response);
        } else if (url.startsWith("/QuanLyKTX_war_exploded/")) {
            if (rolecode.equalsIgnoreCase("KT"))
            {
                filterChain.doFilter(servletRequest, servletResponse);
            } else sendMessageError(response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }

    public void setHeaderResponse(HttpServletResponse response){
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods","GET, OPTIONS, HEAD, PUT, POST, DELETE");
        response.addHeader("Access-Control-Allow-Credentials","true");
        response.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
    }

    private void sendMessageError(HttpServletResponse response) throws IOException{
        setHeaderResponse(response);
        String msg = "Unauthorize!";
        int status = 300;
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(msg);
        responseObject.setStatus(status);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getOutputStream(), responseObject);
    }
}
