package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IUserDAO;
import web.nhom8.quanlyktx.mapper.UserMapper;
import web.nhom8.quanlyktx.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findOne(Integer userId) {
        StringBuilder stringBuilder = new StringBuilder("SELECT User.UserId, User.RoleId, Manager.ManagerId, User.Username, User.Password, Role.RoleCode, ");
        stringBuilder.append("Role.RoleName, User.State AS UserState, Manager.Fullname, Manager.Email, Manager.Address, ");
        stringBuilder.append("Manager.DateOfBirth, Manager.Phone, Manager.YearOfService FROM User ");
        stringBuilder.append("INNER JOIN Manager ON User.UserId = Manager.UserId ");
        stringBuilder.append("INNER JOIN Role ON User.RoleId = Role.RoleId ");
        stringBuilder.append("WHERE User.UserId = ?");

        List<UserModel> userModels = query(stringBuilder.toString(), new UserMapper(), userId);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public List<UserModel> findAll() {
        StringBuilder stringBuilder = new StringBuilder("SELECT User.UserId, User.RoleId, Manager.ManagerId, User.Username, User.Password, Role.RoleCode, ");
        stringBuilder.append("Role.RoleName, User.State AS UserState, Manager.Fullname, Manager.Email, Manager.Address, ");
        stringBuilder.append("Manager.DateOfBirth, Manager.Phone, Manager.YearOfService FROM User ");
        stringBuilder.append("INNER JOIN Manager ON User.UserId = Manager.UserId ");
        stringBuilder.append("INNER JOIN Role ON User.RoleId = Role.RoleId ");
        return query(stringBuilder.toString(), new UserMapper());
    }

    @Override
    public UserModel findByUserName(String userName) {
        String sql = "SELECT * FROM `User` WHERE `Username` = ?";
        List<UserModel> userModels = query(sql, new UserMapper(), userName);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public UserModel findByUsernameAndPasswordAndState(String userName, String password, int state) {
        StringBuilder stringBuilder = new StringBuilder("SELECT User.UserId, User.RoleId, Manager.ManagerId, User.Username, User.Password, Role.RoleCode, ");
        stringBuilder.append("Role.RoleName, User.State AS UserState, Manager.Fullname, Manager.Email, Manager.Address, ");
        stringBuilder.append("Manager.DateOfBirth, Manager.Phone, Manager.YearOfService FROM User ");
        stringBuilder.append("INNER JOIN Manager ON User.UserId = Manager.UserId ");
        stringBuilder.append("INNER JOIN Role ON User.RoleId = Role.RoleId ");
        stringBuilder.append("WHERE `Username` = ? AND `Password` = ? AND User.State = ?");

        List<UserModel> userModels = query(stringBuilder.toString(), new UserMapper(), userName, password, state);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public UserModel findByEmailAndPasswordAndState(String email, String password, int state) {
        StringBuilder stringBuilder = new StringBuilder("SELECT User.UserId, User.RoleId, Manager.ManagerId, User.Username, User.Password, Role.RoleCode, ");
        stringBuilder.append("Role.RoleName, User.State AS UserState, Manager.Fullname, Manager.Email, Manager.Address, ");
        stringBuilder.append("Manager.DateOfBirth, Manager.Phone, Manager.YearOfService FROM User ");
        stringBuilder.append("INNER JOIN Manager ON User.UserId = Manager.UserId ");
        stringBuilder.append("INNER JOIN Role ON User.RoleId = Role.RoleId ");
        stringBuilder.append("WHERE Manager.Email = ? AND `Password` = ? AND User.State = ?");

        List<UserModel> userModels = query(stringBuilder.toString(), new UserMapper(), email, password, state);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public Long addNewUser(UserModel newUserModel) {
        String sql = "INSERT INTO User (UserId, RoleId, Username, Password, State) VALUES (NULL, ?, ?, ?, ?)";
        Long result = insert(sql, newUserModel.getRoleId(), newUserModel.getUsername(),
                newUserModel.getPassword(), newUserModel.getUserState());
        if(result == null) result = Long.valueOf(-1);
        return result;
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        String sql = "UPDATE User SET RoleId = ?, Username = ?, Password = ?, State = ?" +
                " WHERE UserId = ?";
        update(sql, userModel.getRoleId(), userModel.getUsername(), userModel.getPassword(),
                userModel.getUserState(), userModel.getUserId());
        return findOne(userModel.getUserId());
    }
}
