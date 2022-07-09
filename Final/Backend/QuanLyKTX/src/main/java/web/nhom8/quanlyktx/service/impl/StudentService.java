package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.dao.IStudentDAO;
import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.service.IStudentService;

import javax.inject.Inject;
import java.util.List;

public class StudentService implements IStudentService {
    @Inject
    private IStudentDAO studentDAO;

    @Override
    public List<StudentModel> findAll() {
        return studentDAO.findAll();
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
    public int addNewStudent(StudentModel newStudentModel) {
        return studentDAO.addNewStudent(newStudentModel);
    }
}
