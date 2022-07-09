package web.nhom8.quanlyktx.controller.quanlysinhvien;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.RequestObject;
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
import java.util.List;

@WebServlet(urlPatterns = {"/api-student_manager"})
public class StudentManagerAPI extends HttpServlet {

    @Inject
    private IStudentService studentService;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        RequestObject requestObject = HttpUtil.of(req.getReader()).toModel(RequestObject.class);

        ObjectMapper mapper = new ObjectMapper();

        if(requestObject.getAction().equalsIgnoreCase("findAll"))
        {
            List<StudentModel> studentModels = studentService.findAll();
            mapper.writeValue(resp.getOutputStream(), studentModels);

        } else if (requestObject.getAction().equalsIgnoreCase("findByStudentCode"))
        {
            StudentModel studentModel = studentService.findByStudentCode(requestObject.getMessage());
            mapper.writeValue(resp.getOutputStream(), studentModel);

        } else if (requestObject.getAction().equalsIgnoreCase("findByClassRoom"))
        {
            List<StudentModel> studentModels = studentService.findByStudentClass(requestObject.getMessage());
            mapper.writeValue(resp.getOutputStream(), studentModels);
        }
    }
}
