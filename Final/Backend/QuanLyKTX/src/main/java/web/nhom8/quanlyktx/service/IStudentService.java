package web.nhom8.quanlyktx.service;

import web.nhom8.quanlyktx.model.StudentModel;

import java.util.List;

public interface IStudentService {
    List<StudentModel> findAll();

    StudentModel findByStudentCode(String studentCode);

    List<StudentModel> findByStudentClass(String studentClass);

}
