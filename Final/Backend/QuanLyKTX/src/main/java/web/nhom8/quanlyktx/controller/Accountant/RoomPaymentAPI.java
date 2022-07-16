package web.nhom8.quanlyktx.controller.Accountant;

import org.codehaus.jackson.map.ObjectMapper;
import web.nhom8.quanlyktx.service.IRoomPaymentService;
import web.nhom8.quanlyktx.model.RoomPaymentModel;
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

@WebServlet(urlPatterns = {"/room/student/payment"})
public class RoomPaymentAPI extends HttpServlet{
    @Inject
    private IRoomPaymentService roomPaymentService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        RoomPaymentModel model = HttpUtil.of(request.getReader()).toModel(RoomPaymentModel.class);
        model = roomPaymentService.save(model);

        if (model == null) {
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Add room payment failed!!");
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

        String parameter = request.getParameter("studentRoomId");
        Long studentRoomId = Long.parseLong(parameter);
        List<RoomPaymentModel> listModel = new ArrayList<>();
        listModel = roomPaymentService.findPaymentByStudent(studentRoomId);
        mapper.writeValue(response.getOutputStream(), listModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");

        String id = request.getParameter("paymentId");
        Long paymentId = Long.parseLong(id);
        RoomPaymentModel model = HttpUtil.of(request.getReader()).toModel(RoomPaymentModel.class);
        model.setPaymentId(paymentId);
        model.setStudentRoomId(roomPaymentService.findOne(paymentId).getStudentRoomId());
        model = roomPaymentService.update(model);
        if (model == null){
            ResponseObject responseFail = new ResponseObject();
            responseFail.setStatus(0);
            responseFail.setMessage("Update payment room failed!!");
            mapper.writeValue(response.getOutputStream(), responseFail);
        } else {
            mapper.writeValue(response.getOutputStream(), model);
        }
    }
}
