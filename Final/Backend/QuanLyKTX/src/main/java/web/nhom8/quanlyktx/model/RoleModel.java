package web.nhom8.quanlyktx.model;

public class RoleModel {
    private int RoleId;
    private String RoleName;
    private String RoleCode;



    public RoleModel(int roleId, String roleName, String roleCode) {
        RoleId = roleId;
        RoleName = roleName;
        RoleCode = roleCode;
    }

    public RoleModel() {
        super();
    }

    public void setRoleId(int roleId) {
        RoleId = roleId;
    }

    public int getRoleId() {
        return RoleId;
    }

    public String getRoleName() {
        return RoleName;
    }

    public void setRoleName(String roleName) {
        if(roleName.length() > 50)
        {
            System.out.println("RoldName: dai hon 50 ky tu");
        }
        else {
            RoleName = roleName;
        }
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String roleCode) {
        if(roleCode.length() > 50)
        {
            System.out.println("RoleCode: dai hon 50 ky tu");
        }
        else {
            RoleCode = roleCode;
        }
    }

    @Override
    public String toString() {
        return "{" +
                "\"RoleId\":" + RoleId +
                ",\"RoleName\":\"" + RoleName + '\"' +
                ",\"RoleCode\":\"" + RoleCode + '\"' +
                '}';
    }
}
