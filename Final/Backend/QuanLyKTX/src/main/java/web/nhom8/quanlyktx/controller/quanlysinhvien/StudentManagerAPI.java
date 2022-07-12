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
}
