package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.model.StudentModel;

import java.util.List;

public interface IStudentDAO extends GenericDAO<StudentModel>{
    List<StudentModel> findAll();

    StudentModel findByStudentCode(String studentCode);

    List<StudentModel> findByStudentClass(String studentClass);

    int addNewStudent(StudentModel newStudentModel);
}
