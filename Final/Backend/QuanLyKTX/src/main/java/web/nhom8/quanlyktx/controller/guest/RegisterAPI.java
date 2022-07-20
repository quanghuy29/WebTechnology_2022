package web.nhom8.quanlyktx.controller.guest;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.UserModel;
import web.nhom8.quanlyktx.service.IUserService;
import web.nhom8.quanlyktx.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-register"})
public class RegisterAPI extends HttpServlet {

    @Inject
    private IUserService userService;
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
        Long result = userService.addNewUser(userModel);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setStatus(200);
        if(result == -1) responseObject.setStatus(400);
        responseObject.setMessage(result.toString());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), responseObject);
    }
}
