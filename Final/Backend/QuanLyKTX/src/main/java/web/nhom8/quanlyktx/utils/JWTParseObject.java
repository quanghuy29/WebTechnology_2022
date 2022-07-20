package web.nhom8.quanlyktx.utils;

public class JWTParseObject {
    private String UserId;
    private String RoleCode;

    public String getUserId() {
        return UserId;
    }

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getRoleCode() {
        return RoleCode;
    }

    public void setRoleCode(String roleCode) {
        RoleCode = roleCode;
    }
}
