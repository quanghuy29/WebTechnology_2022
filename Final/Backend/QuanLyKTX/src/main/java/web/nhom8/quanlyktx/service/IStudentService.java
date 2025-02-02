package web.nhom8.quanlyktx.service;

import web.nhom8.quanlyktx.model.StudentModel;

import java.util.List;

public interface IStudentService {
    List<StudentModel> findAll();
    StudentModel findByStudentId(int studentId);

    StudentModel findByStudentCode(String studentCode);

    List<StudentModel> findByStudentClass(String studentClass);

    // add new student
    Long addNewStudent(StudentModel newStudentModel);
    // update student info
    StudentModel updateStudentInfo(StudentModel newStudentModel);

    void deleteStudent(List<Long> studentIds);

}
