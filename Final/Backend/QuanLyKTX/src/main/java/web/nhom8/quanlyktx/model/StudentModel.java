package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class StudentModel {
    private int StudentId;
    private String StudentCode;
    private String Fullname;
    private Date DateOfBirth;
    private String Email;
    private String Address;
    private String Phone;
    private int YearSchool;

    public String getStudentClass() {
        return StudentClass;
    }

    public void setStudentClass(String studentClass) {
        StudentClass = studentClass;
    }

    private String StudentClass;
    private int State;

    public StudentModel() {
    }

    public StudentModel(int studentId, String studentCode, String fullname, Date dateOfBirth, String email, String address,
                        String phone, int yearSchool, String studentClass, int state) {
        StudentId = studentId;
        StudentCode = studentCode;
        Fullname = fullname;
        DateOfBirth = dateOfBirth;
        Email = email;
        Address = address;
        Phone = phone;
        YearSchool = yearSchool;
        StudentClass = studentClass;
        State = state;
    }

    public void setStudentId(int studentId) {
        StudentId = studentId;
    }

    public int getStudentId() {
        return StudentId;
    }

    public String getStudentCode() {
        return StudentCode;
    }

    public void setStudentCode(String studentCode) {
        StudentCode = studentCode;
    }

    public String getFullname() {
        return Fullname;
    }

    public void setFullname(String fullname) {
        Fullname = fullname;
    }

    public Date getDateOfBirth() {
        return DateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        DateOfBirth = dateOfBirth;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public int getYearSchool() {
        return YearSchool;
    }

    public void setYearSchool(int yearSchool) {
        YearSchool = yearSchool;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId=" + StudentId +
                ", StudentCode='" + StudentCode + '\'' +
                ", Fullname='" + Fullname + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone='" + Phone + '\'' +
                ", YearSchool=" + YearSchool +
                ", StudentClass='" + StudentClass + '\'' +
                ", State=" + State +
                '}';
    }
}
