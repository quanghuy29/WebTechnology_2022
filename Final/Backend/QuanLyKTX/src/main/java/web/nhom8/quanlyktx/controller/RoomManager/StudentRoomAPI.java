package web.nhom8.quanlyktx.controller.RoomManager;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.Service.IStudentRoomService;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.StudentRoomModel;
import web.nhom8.quanlyktx.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/room/student"})
public class StudentRoomAPI extends HttpServlet {
    @Inject
    private IStudentRoomService studentRoomService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        StudentRoomModel model = HttpUtil.of(request.getReader()).toModel(StudentRoomModel.class);
        model = studentRoomService.save(model);
        mapper.writeValue(response.getOutputStream(), model);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("idRoom");
        if (id == null){
            List<StudentRoomModel> listModel = new ArrayList<>();
            listModel = studentRoomService.findAll();
            mapper.writeValue(response.getOutputStream(), listModel);
        }
        else {
            Long idRoom = Long.parseLong(id);
            List<StudentRoomModel> models = new ArrayList<>();
            models = studentRoomService.findAllStudentByRoom(idRoom);
            mapper.writeValue(response.getOutputStream(), models);
        }
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("idStudent");
        if (id == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Not have id room to update!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            Long idStudent = Long.parseLong(id);
            StudentRoomModel model = HttpUtil.of(request.getReader()).toModel(StudentRoomModel.class);
            model.setStudentId(idStudent);
            model.setRoomId(studentRoomService.findByStudentId(idStudent).getRoomId());
            model.setId(studentRoomService.findByStudentId(idStudent).getId());
            model = studentRoomService.update(model);
            if (model == null){
                ResponseObject responseFail = new ResponseObject();
                responseFail.setStatus(0);
                responseFail.setMessage("add student to room failed!!");
                mapper.writeValue(response.getOutputStream(), responseFail);
            } else {
                mapper.writeValue(response.getOutputStream(), model);
            }
        }
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("idStudent");
        if (id == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Not have id student to delete!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            Long idStudent = Long.parseLong(id);
            studentRoomService.delete(idStudent);
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(1);
            responseFail.setMessage("Remove student success!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        }
    }
}
