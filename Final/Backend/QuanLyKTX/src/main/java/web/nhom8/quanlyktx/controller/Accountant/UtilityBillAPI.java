package web.nhom8.quanlyktx.controller.Accountant;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.service.IUtilityBillService;
import web.nhom8.quanlyktx.model.UtilityBillModel;
import web.nhom8.quanlyktx.model.ResponseObject;
import web.nhom8.quanlyktx.utils.HttpUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/room/utility"})
public class UtilityBillAPI extends HttpServlet{
    @Inject
    private IUtilityBillService utilityBillService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        UtilityBillModel model = HttpUtil.of(request.getReader()).toModel(UtilityBillModel.class);
        model = utilityBillService.save(model);

        if (model == null) {
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Add bill failed!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            mapper.writeValue(response.getOutputStream(), model);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("roomId");
        Long roomId = Long.parseLong(id);
        List<UtilityBillModel> listModel = new ArrayList<>();
        listModel = utilityBillService.findBillByRoom(roomId);
        mapper.writeValue(response.getOutputStream(), listModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("billId");
        Long billId = Long.parseLong(id);
        UtilityBillModel model = HttpUtil.of(request.getReader()).toModel(UtilityBillModel.class);
        model.setBillId(billId);
        model.setRoomId(utilityBillService.findOne(billId).getRoomId());
        model = utilityBillService.update(model);

        if (model == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Update bill failed!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            mapper.writeValue(response.getOutputStream(), model);
        }
    }
}
