package web.nhom8.quanlyktx.model;

import java.sql.Date;

public class ManagerModel {
    private int ManagerId;
    private int UserId;
    private String Fullname;
    private Date DateOfBirth;
    private String Email;
    private String Address;
    private String Phone;
    private int YearOfService;
    private int State;

    public ManagerModel(int managerId, int userId, String fullname, Date dateOfBirth, String email, String address,
                        String phone, int yearOfService, int state) {
        ManagerId = managerId;
        UserId = userId;
        Fullname = fullname;
        DateOfBirth = dateOfBirth;
        Email = email;
        Address = address;
        Phone = phone;
        YearOfService = yearOfService;
        State = state;
    }

    public int getManagerId() {
        return ManagerId;
    }

    public int getUserId() {
        return UserId;
    }

    public void setUserId(int userId) {
        UserId = userId;
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

    public int getYearOfService() {
        return YearOfService;
    }

    public void setYearOfService(int yearOfService) {
        YearOfService = yearOfService;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "ManagerId=" + ManagerId +
                ", UserId=" + UserId +
                ", Fullname='" + Fullname + '\'' +
                ", DateOfBirth=" + DateOfBirth +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                ", Phone='" + Phone + '\'' +
                ", YearOfService=" + YearOfService +
                ", State=" + State +
                '}';
    }
}
