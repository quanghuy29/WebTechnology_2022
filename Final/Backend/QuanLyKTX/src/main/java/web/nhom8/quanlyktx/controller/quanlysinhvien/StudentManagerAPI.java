package web.nhom8.quanlyktx.controller.quanlysinhvien;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.RequestObject;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.service.IStudentService;
import web.nhom8.quanlyktx.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = {"/api-student_manager"})
public class StudentManagerAPI extends HttpServlet {

    @Inject
    private IStudentService studentService;
    // search student[s]
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        //RequestObject requestObject = HttpUtil.of(req.getReader()).toModel(RequestObject.class);
        String action = req.getParameter("action");
        String message = req.getParameter("message");
        ObjectMapper mapper = new ObjectMapper();

        if(action.equalsIgnoreCase("findAll"))
        {
            List<StudentModel> studentModels = studentService.findAll();
            mapper.writeValue(resp.getOutputStream(), studentModels);

        } else if (action.equalsIgnoreCase("findByStudentCode"))
        {
            StudentModel studentModel = studentService.findByStudentCode(message);
            mapper.writeValue(resp.getOutputStream(), studentModel);

        } else if (action.equalsIgnoreCase("findByClassRoom"))
        {
            List<StudentModel> studentModels = studentService.findByStudentClass(message);
            mapper.writeValue(resp.getOutputStream(), studentModels);
        }
    }

    // add new student
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        StudentModel studentModel = HttpUtil.of(req.getReader()).toModel(StudentModel.class);
        Long result = studentService.addNewStudent(studentModel);
        ResponseObject responseObject = new ResponseObject();
        responseObject.setMessage(result.toString());
        if(-1 == result.longValue())
        {
            responseObject.setStatus(400);
            responseObject.setMessage("Failed to add new Student!");
        } else {
            responseObject.setStatus(200);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), responseObject);
    }

    // update student info

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        StudentModel studentModel = HttpUtil.of(req.getReader()).toModel(StudentModel.class);
        studentModel = studentService.updateStudentInfo(studentModel);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), studentModel);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        RequestObject requestObject = HttpUtil.of(req.getReader()).toModel(RequestObject.class);
        List<Long> studentIds = Arrays.asList(requestObject.getMessage().split(",")).stream()
                .map(s -> Long.parseLong(s.trim())).collect(Collectors.toList());
        studentService.deleteStudent(studentIds);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(resp.getOutputStream(), "{}");
    }
}
