package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.model.UserModel;

import java.util.List;

public interface IStudentDAO extends GenericDAO<StudentModel>{
    List<StudentModel> findAll();
    StudentModel findByStudentId(int studentId);

    StudentModel findByStudentCode(String studentCode);

    List<StudentModel> findByStudentClass(String studentClass);

    Long addNewStudent(StudentModel newStudentModel);

    void updateStudentInfo(StudentModel newStudentModel);

    void deleteStudent(Long studentId);

}
