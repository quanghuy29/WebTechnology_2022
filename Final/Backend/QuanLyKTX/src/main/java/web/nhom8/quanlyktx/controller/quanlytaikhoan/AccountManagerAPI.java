package web.nhom8.quanlyktx.controller.quanlytaikhoan;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.RoomModel;
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
import java.util.List;

@WebServlet(urlPatterns = {"/api-account_manager"})
public class AccountManagerAPI extends HttpServlet {

    @Inject
    private IUserService userService;
    // search student[s]
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        //RequestObject requestObject = HttpUtil.of(req.getReader()).toModel(RequestObject.class);
        String action = req.getParameter("action");
        String message = req.getParameter("message");
        ObjectMapper mapper = new ObjectMapper();

        if(action.equalsIgnoreCase("findAll"))
        {
            List<UserModel> userModels = userService.findAll();
            mapper.writeValue(resp.getOutputStream(), userModels);

        } else if (action.equalsIgnoreCase("findByUserId"))
        {
            UserModel userModel = userService.findOne(Integer.parseInt(message));
            mapper.writeValue(resp.getOutputStream(), userModel);

        } else if (action.equalsIgnoreCase("findByClassRoom"))
        {
//            List<StudentModel> studentModels = studentService.findByStudentClass(message);
//            mapper.writeValue(resp.getOutputStream(), studentModels);
        }
    }

    // add new student
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
        Long result = userService.addNewUser(userModel);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(result.toString());
        if(-1 == result) {
            responseObject.setStatus(400);
            responseObject.setMessage("Failed to add new user");
        } else {
            responseObject.setStatus(200);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), responseObject);
    }

    // update student info

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
        String userId = req.getParameter("userId");
        if (userId == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Not have id user to update!!");
            mapper.writeValue(resp.getOutputStream(), responseFail);
        } else {
            Integer idUser = Integer.parseInt(userId);
            userModel.setUserId(idUser);
            userModel = userService.updateUser(userModel);
            if (userModel == null){
                ResponseObject responseFail = new ResponseObject();
                responseFail.setStatus(0);
                responseFail.setMessage("Update user failed!!");
                mapper.writeValue(resp.getOutputStream(), responseFail);
            }
            else mapper.writeValue(resp.getOutputStream(), userModel);
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        req.setCharacterEncoding("UTF-8");
//        resp.setCharacterEncoding("UTF-8");
//        resp.setContentType("application/json");
//
//        RequestObject requestObject = HttpUtil.of(req.getReader()).toModel(RequestObject.class);
//        List<Long> studentIds = Arrays.asList(requestObject.getMessage().split(",")).stream()
//                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
//        studentService.deleteStudent(studentIds);
//        ObjectMapper objectMapper = new ObjectMapper();
//        objectMapper.writeValue(resp.getOutputStream(), "{}");
    }
}
