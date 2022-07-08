package web.nhom8.quanlyktx.mapper;

import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.model.UserModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentMapper implements RowMapper<StudentModel> {

    @Override
    public StudentModel mapRow(ResultSet rs) {
        StudentModel studentModel = new StudentModel();
        try {
            studentModel.setStudentId(rs.getInt("StudentId"));
            studentModel.setStudentCode(rs.getString("StudentCode"));
            studentModel.setFullname(rs.getString("Fullname"));
            studentModel.setDateOfBirth(rs.getDate("DateOfBirth"));
            studentModel.setEmail(rs.getString("Email"));
            studentModel.setAddress(rs.getString("Address"));
            studentModel.setPhone(rs.getString("Phone"));
            studentModel.setStudentClass(rs.getString("Class"));
            studentModel.setYearSchool(rs.getInt("YearSchool"));
            studentModel.setState(rs.getInt("State"));
            return studentModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
