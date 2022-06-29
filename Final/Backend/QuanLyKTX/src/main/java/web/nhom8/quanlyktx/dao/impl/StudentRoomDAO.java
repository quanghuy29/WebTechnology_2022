package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IStudentRoomDAO;
import web.nhom8.quanlyktx.mapper.StudentRoomMapper;
import web.nhom8.quanlyktx.model.StudentRoomModel;

import java.util.ArrayList;
import java.util.List;

public class StudentRoomDAO extends AbstractDAO<StudentRoomModel> implements IStudentRoomDAO {
    @Override
    public List<StudentRoomModel> findAllStudentByRoom(Long idRoom) {
        String sql = "SELECT * FROM StudentRoom WHERE RoomId = ?";
        List<StudentRoomModel> models = new ArrayList<>();
        models = query(sql, new StudentRoomMapper(), idRoom);
        return models;
    }

    @Override
    public List<StudentRoomModel> findAll() {
        String sql = "SELECT * FROM StudentRoom ";
        return query(sql, new StudentRoomMapper());
    }

    @Override
    public StudentRoomModel save(StudentRoomModel model) {
        String sql = "INSERT INTO StudentRoom (StudentId, RoomId, PayMoneyRemain, " +
                "PaymentState) VALUES(?, ? ,? ,?)";
        Long id = insert(sql, model.getStudentId(), model.getRoomId(),
                model.getPayMoneyRemain(), model.getPaymentState());;
        return findOne(id);
    }

    @Override
    public void delete(Long idStudent) {
        String sql = "DELETE FROM StudentRoom WHERE StudentId = ?";
        update(sql, idStudent);
    }

    @Override
    public StudentRoomModel update(StudentRoomModel model) {
        String sql = "UPDATE StudentRoom SET PayMoneyRemain = ?, PaymentState = ? " +
                "WHERE StudentId = ?";
        update(sql, model.getPayMoneyRemain(), model.getPaymentState(), model.getId());
        return findOne(model.getStudentId());
    }

    @Override
    public StudentRoomModel findOne(Long id) {
        String sql = "SELECT * FROM StudentRoom WHERE StudentRoomId = ?";
        List<StudentRoomModel> models = query(sql, new StudentRoomMapper(), id);
        return models.isEmpty() ? null : models.get(0);
    }

    @Override
    public StudentRoomModel findByStudentId(Long idStudent) {
        String sql = "SELECT * FROM StudentRoom WHERE StudentId = ?";
        List<StudentRoomModel> models = query(sql, new StudentRoomMapper(), idStudent);
        return models.isEmpty() ? null : models.get(0);
    }
}
