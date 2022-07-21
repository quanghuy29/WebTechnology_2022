package web.nhom8.quanlyktx.controller.guest;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.UserModel;
import web.nhom8.quanlyktx.service.IUserService;
import web.nhom8.quanlyktx.utils.HttpUtil;
import web.nhom8.quanlyktx.utils.SessionUtil;

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
        UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
        userModel = userService.findByUsernameAndPasswordAndState(userModel.getUsername(), userModel.getPassword(), 1);
        String msg;
        int status;
        if(userModel == null)
        {
            msg = "Username or password failed!";
            status = 400;
            System.out.println(msg);
        } else {
            SessionUtil.getInstance().putValue(req, "USERMODEL", userModel);
            msg = "Login successfull!";
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
