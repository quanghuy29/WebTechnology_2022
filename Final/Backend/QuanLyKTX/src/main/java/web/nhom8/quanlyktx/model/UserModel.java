package web.nhom8.quanlyktx.model;

public class UserModel {
    private Integer UserId;
    private Long RoleId;
    private String Username;
    private String Password;
    private int State;

    public UserModel(Integer userId, Long roleId, String username, String password, int state) {
        UserId = userId;
        RoleId = roleId;
        Username = username;
        Password = password;
        State = state;
    }

    public UserModel() {
    }

    public void setUserId(Integer userId) {
        UserId = userId;
    }

    public void setRoleId(Long roleId) {
        RoleId = roleId;
    }

    public int getUserId() {
        return UserId;
    }

    public long getRoleId() {
        return RoleId;
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
        return "{" +
                "UserId:" + UserId +
                ", RoleId:" + RoleId +
                ", Username:'" + Username + '\'' +
                ", Password:'" + Password + '\'' +
                ", State=" + State +
                '}';
    }
}
