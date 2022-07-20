package web.nhom8.quanlyktx.model;

public class UserModel {
    private Integer UserId;
    private Long RoleId;
    private String Username;
    private String Password;
    private RoleModel roleModel;

    public RoleModel getRoleModel() {
        return roleModel;
    }

    public void setRoleModel(RoleModel roleModel) {
        this.roleModel = roleModel;
    }

    public ManagerModel getManagerModel() {
        return managerModel;
    }

    public void setManagerModel(ManagerModel managerModel) {
        this.managerModel = managerModel;
    }

    private ManagerModel managerModel;
    private int UserState;

    public UserModel(Integer userId, Long roleId, String username, String password, int state) {
        UserId = userId;
        RoleId = roleId;
        Username = username;
        Password = password;
        UserState = state;
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

    public int getUserState() {
        return UserState;
    }

    public void setUserState(int userState) {
        UserState = userState;
    }

    @Override
    public String toString() {
        return "{" +
                "UserId:" + UserId +
                ", RoleId:" + RoleId +
                ", Username:'" + Username + '\'' +
                ", Password:'" + Password + '\'' +
                ", State=" + UserState +
                '}';
    }
}
