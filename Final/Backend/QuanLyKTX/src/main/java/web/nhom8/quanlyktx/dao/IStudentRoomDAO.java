package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.model.StudentRoomModel;

import java.util.List;

public interface IStudentRoomDAO {
    List<StudentRoomModel> findAllStudentByRoom(Long idRoom);
    List<StudentRoomModel> findAll();
    StudentRoomModel save(StudentRoomModel model);
    void delete(Long idStudent);
    StudentRoomModel update(StudentRoomModel model);
    StudentRoomModel findOne(Long id);
    StudentRoomModel findByStudentId(Long idStudent);
}
