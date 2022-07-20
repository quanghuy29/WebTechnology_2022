package web.nhom8.quanlyktx.controller.guest;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.LoginModel;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.UserModel;
import web.nhom8.quanlyktx.service.IUserService;
import web.nhom8.quanlyktx.utils.HttpUtil;
import web.nhom8.quanlyktx.utils.SessionUtil;
import web.nhom8.quanlyktx.utils.TokenJWTUtils;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = {"/api-login"})
public class LoginAPI extends HttpServlet {
    @Inject
    private IUserService userService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("text/plain");
        PrintWriter out = resp.getWriter();
        out.print("Hello SERVLET\n");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        // read json body mapping model
        LoginModel loginModel = HttpUtil.of(req.getReader()).toModel(LoginModel.class);
        UserModel userModel = new UserModel();
        if (loginModel.getEmail().contains("@"))
        {
            userModel = userService.findByEmailAndPasswordAndState(loginModel.getEmail(), loginModel.getPassword(), 1);
        } else
        {
            userModel = userService.findByUsernameAndPasswordAndState(loginModel.getEmail(), loginModel.getPassword(), 1);
        }
        String msg;
        int status;
        if(userModel == null)
        {
//            resp.setStatus(400);
            msg = "Username or password failed!";
            status = 400;
            System.out.println(msg);
        } else {
            //SessionUtil.getInstance().putValue(req, "USERMODEL", userModel);
            String token = TokenJWTUtils.generateToken(userModel.getUsername(), userModel.getRoleModel().getRoleCode());
            resp.setHeader("Authentication", token);
//            msg = "Login successfull!";
            msg = token;
            resp.setStatus(200);
            status = 200;
            System.out.println(msg);
        }
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(msg);
        responseObject.setStatus(status);

        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(resp.getOutputStream(), responseObject);
    }
}
