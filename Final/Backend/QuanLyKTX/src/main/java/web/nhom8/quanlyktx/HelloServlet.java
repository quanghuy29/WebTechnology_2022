package web.nhom8.quanlyktx;

import web.nhom8.quanlyktx.dao.MysqlDao;
import web.nhom8.quanlyktx.model.RoleModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }
    public static String toJSON(List<RoleModel> roleModelList)
    {
        StringBuilder stringBuilder = new StringBuilder("[");
        int cnt = 0;
        for (RoleModel roleModel:
                roleModelList) {
            cnt++;
            stringBuilder.append(roleModel.toString());
            if(cnt != roleModelList.size())
            {
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("application/json; charset=UTF-8");

        List<RoleModel> roleModelList = new ArrayList<>();

        MysqlDao mysqlDao = new MysqlDao();
        roleModelList = mysqlDao.query();
        // Hello
        PrintWriter out = response.getWriter();
        out.print(toJSON(roleModelList));
        out.flush();
    }

    public void destroy() {
    }
}