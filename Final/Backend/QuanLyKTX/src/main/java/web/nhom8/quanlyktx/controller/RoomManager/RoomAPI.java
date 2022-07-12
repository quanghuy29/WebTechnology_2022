package web.nhom8.quanlyktx.controller.RoomManager;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.service.IRoomService;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.RoomModel;
import web.nhom8.quanlyktx.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/room"})
public class RoomAPI extends HttpServlet {
    @Inject
    private IRoomService roomService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
                throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        RoomModel roomModel = HttpUtil.of(request.getReader()).toModel(RoomModel.class);
        roomModel = roomService.save(roomModel);
        if (roomModel == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Add room failed!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        }
        else mapper.writeValue(response.getOutputStream(), roomModel);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("idRoom");
        if (id == null){
            List<RoomModel> listRoom = new ArrayList<>();
            listRoom = roomService.findAll();
            mapper.writeValue(response.getOutputStream(), listRoom);
        }
        else {
            Long idRoom = Long.parseLong(id);
            RoomModel room = new RoomModel();
            room = roomService.findOne(idRoom);
            mapper.writeValue(response.getOutputStream(), room);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("idRoom");
        if (id == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Not have id room to update!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            Long idRoom = Long.parseLong(id);
            RoomModel room = HttpUtil.of(request.getReader()).toModel(RoomModel.class);
            room.setRoomId(idRoom);
            room = roomService.update(room);
            if (room == null){
                ResponseObject responseFail = new ResponseObject();
                responseFail.setStatus(0);
                responseFail.setMessage("Update room failed!!");
                mapper.writeValue(response.getOutputStream(), responseFail);
            }
            else mapper.writeValue(response.getOutputStream(), room);
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("idRoom");
        if (id == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Not have id room to delete!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            Long idRoom = Long.parseLong(id);
            roomService.delete(idRoom);
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(1);
            responseFail.setMessage("Delete room success!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        }
    }
}


