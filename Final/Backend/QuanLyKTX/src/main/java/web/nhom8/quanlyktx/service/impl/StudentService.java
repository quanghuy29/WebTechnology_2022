package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.dao.IStudentDAO;
import web.nhom8.quanlyktx.dao.IStudentRoomDAO;
import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.model.StudentRoomModel;
import web.nhom8.quanlyktx.service.IStudentRoomService;
import web.nhom8.quanlyktx.service.IStudentService;

import javax.inject.Inject;
import java.util.List;

public class StudentService implements IStudentService {
    @Inject
    private IStudentDAO studentDAO;
    @Inject
    private IStudentRoomDAO studentRoomDAO;

    @Override
    public List<StudentModel> findAll() {
        return studentDAO.findAll();
    }

    @Override
    public StudentModel findByStudentId(int studentId) {
        return studentDAO.findByStudentId(studentId);
    }

    @Override
    public StudentModel findByStudentCode(String studentCode) {
        return studentDAO.findByStudentCode(studentCode);
    }

    @Override
    public List<StudentModel> findByStudentClass(String studentClass) {
        return studentDAO.findByStudentClass(studentClass);
    }

    @Override
    public Long addNewStudent(StudentModel newStudentModel) {
        return studentDAO.addNewStudent(newStudentModel);
    }

    @Override
    public StudentModel updateStudentInfo(StudentModel newStudentModel) {
        studentDAO.updateStudentInfo(newStudentModel);
        StudentRoomModel student = studentRoomDAO.findByStudentId((long)newStudentModel.getStudentId());
        student.setStudentCode(newStudentModel.getStudentCode());
        student.setStudentName(newStudentModel.getFullname());
        studentRoomDAO.updateName(student);
        return studentDAO.findByStudentCode(newStudentModel.getStudentCode());
    }

    @Override
    public void deleteStudent(List<Long> studentIds) {
        for (Long i:
             studentIds) {
           studentDAO.deleteStudent(i);
        }
    }
}
