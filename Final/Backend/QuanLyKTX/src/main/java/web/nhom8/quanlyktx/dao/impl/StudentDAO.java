package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IStudentDAO;
import web.nhom8.quanlyktx.mapper.StudentMapper;
import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.model.UserModel;

import java.util.List;

public class StudentDAO extends AbstractDAO<StudentModel> implements IStudentDAO {
    @Override
    public List<StudentModel> findAll() {
        java.lang.String sql = "SELECT * FROM Student";
        List<StudentModel> studentModels = query(sql, new StudentMapper());
        return studentModels;
    }

    @Override
    public StudentModel findByStudentCode(java.lang.String studentCode) {
        java.lang.String sql = "SELECT * FROM Student WHERE StudentCode = ?";
        List<StudentModel> studentModels = query(sql, new StudentMapper(), studentCode);
        return studentModels.isEmpty() ? null : studentModels.get(0);
    }

    @Override
    public List<StudentModel> findByStudentClass(java.lang.String studentClass) {
        String sql = "SELECT * FROM Student WHERE Class = ?";
        List<StudentModel> studentModels = query(sql, new StudentMapper(), studentClass);
        return studentModels;
    }

    @Override
    public Long addNewStudent(StudentModel newStudentModel) {
        StringBuilder sql = new StringBuilder();
        sql.append("INSERT INTO Student (StudentId, StudentCode, Fullname, DateOfBirth, Email, Address, Phone, ");
        sql.append("YearSchool, Class, State) VALUES (NULL, ?, ?, ?, ?, ? , ?, ? , ? , ?)");

        Long result =  insert(sql.toString(), newStudentModel.getStudentCode(), newStudentModel.getFullname(),
                newStudentModel.getDateOfBirth(), newStudentModel.getEmail(), newStudentModel.getAddress(),
                newStudentModel.getPhone(), newStudentModel.getYearSchool(), newStudentModel.getStudentClass(),
                newStudentModel.getState());
        if (result == null)
        {
            result = Long.valueOf(-1);
        }
        return result;
    }

    @Override
    public void updateStudentInfo(StudentModel newStudentModel) {
        StringBuilder sql = new StringBuilder();
        sql.append("UPDATE Student SET StudentCode = ?, Fullname = ?, DateOfBirth = ?, Email = ?,");
        sql.append("Address = ?, Phone = ?, YearSchool = ?, Class = ?, State = ? WHERE StudentId = ?");

        update(sql.toString(), newStudentModel.getStudentCode(), newStudentModel.getFullname(),
                newStudentModel.getDateOfBirth(), newStudentModel.getEmail(), newStudentModel.getAddress(),
                newStudentModel.getPhone(), newStudentModel.getYearSchool(), newStudentModel.getStudentClass(),
                newStudentModel.getState(), newStudentModel.getStudentId());
    }

    @Override
    public void deleteStudent(Long studentId) {
        String sql = "UPDATE Student SET State = 0 WHERE StudentId = ?";
        update(sql, studentId);
    }

}
