package web.nhom8.quanlyktx.controller.RoomManager;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.Service.IRoomService;
import web.nhom8.quanlyktx.model.RoomModel;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/room"})
public class RoomAPI extends HttpServlet {
    @Inject
    private IRoomService roomService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

    }
}
