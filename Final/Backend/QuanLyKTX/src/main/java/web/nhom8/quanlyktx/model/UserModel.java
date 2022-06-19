package web.nhom8.quanlyktx.model;

public class UserModel {
    private int UserId;
    private int RoleId;
    private String Username;
    private String Password;
    private int State;

    public UserModel(int userId, int roleId, String username, String password, int state) {
        UserId = userId;
        RoleId = roleId;
        Username = username;
        Password = password;
        State = state;
    }

    public int getUserId() {
        return UserId;
    }


    public int getRoleId() {
        return RoleId;
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getState() {
        return State;
    }

    public void setState(int state) {
        State = state;
    }

    @Override
    public String toString() {
        return "User{" +
                "UserId=" + UserId +
                ", RoleId=" + RoleId +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", State=" + State +
                '}';
    }
}
