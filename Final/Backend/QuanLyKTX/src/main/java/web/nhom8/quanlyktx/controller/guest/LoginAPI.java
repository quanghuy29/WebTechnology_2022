package web.nhom8.quanlyktx.controller.guest;

import com.google.gson.Gson;
import web.nhom8.quanlyktx.model.UserModel;
import web.nhom8.quanlyktx.service.IUserService;

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
        System.out.println(req.getParameter("username"));
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        UserModel userModel = userService.findByUserName(req.getParameter("username"));
        if(userModel != null)
        {
            System.out.println(userModel);
            if(userModel.getPassword().equals(req.getParameter("password")) && userModel.getState() == 1) {
                System.out.println("Dang nhap thanh cong");
            } else {
                System.out.println("tai khoan da bi vo hieu hoa!");
            }
        }
        PrintWriter out = resp.getWriter();
        Gson gson = new Gson();
        String userJson = gson.toJson(userModel);
        out.print(userJson);
        out.flush();
    }
}
