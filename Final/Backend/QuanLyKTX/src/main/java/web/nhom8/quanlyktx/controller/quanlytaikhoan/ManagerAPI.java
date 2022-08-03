package web.nhom8.quanlyktx.controller.quanlytaikhoan;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.model.ManagerModel;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.service.IManagerService;
import web.nhom8.quanlyktx.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api-manager"})
public class ManagerAPI extends HttpServlet {
    @Inject
    private IManagerService managerService;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ManagerModel managerModel = HttpUtil.of(request.getReader()).toModel(ManagerModel.class);
        managerModel = managerService.save(managerModel);
        if (managerModel == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(400);
            responseFail.setMessage("Add manager failed!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        }
        else mapper.writeValue(response.getOutputStream(), managerModel);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        String id = request.getParameter("managerId");
        if (id == null) {
            List<ManagerModel> list = new ArrayList<>();
            list = managerService.findAll();
            mapper.writeValue(response.getOutputStream(), list);
        }
        else {
            Integer idManager = Integer.parseInt(id);
            ManagerModel manager = managerService.findOne(idManager);
            mapper.writeValue(response.getOutputStream(), manager);
        }
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("managerId");
        int idManager = Integer.parseInt(id);
        ManagerModel manager = HttpUtil.of(request.getReader()).toModel(ManagerModel.class);
        manager.setManagerId(idManager);
        manager = managerService.update(manager);

        if (manager == null) {
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(400);
            responseFail.setMessage("Update manager failed!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            mapper.writeValue(response.getOutputStream(), manager);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("managerId");
        if (id == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(400);
            responseFail.setMessage("Not have id maanger to delete!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        }
        else {
            Long managerId = Long.parseLong(id);
            managerService.delete(managerId);
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(200);
            responseFail.setMessage("Remove manager success!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        }
    }
}
