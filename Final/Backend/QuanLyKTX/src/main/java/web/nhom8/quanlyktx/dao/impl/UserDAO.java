package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IUserDAO;
import web.nhom8.quanlyktx.mapper.UserMapper;
import web.nhom8.quanlyktx.model.UserModel;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {
    @Override
    public UserModel findOne(Integer userId) {
        String sql = "SELECT * FROM User WHERE UserId = ?";
        List<UserModel> userModels = query(sql, new UserMapper(), userId);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public List<UserModel> findAll() {
        String sql = "SELECT * FROM User";
        return query(sql, new UserMapper());
    }

    @Override
    public UserModel findByUserName(String userName) {
        String sql = "SELECT * FROM `User` WHERE `Username` = ?";
        List<UserModel> userModels = query(sql, new UserMapper(), userName);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public UserModel findByUsernameAndPasswordAndState(String userName, String password, int state) {
        String sql = "SELECT * FROM `User` WHERE `Username` = ? AND `Password` = ? AND `State` = ?";
        List<UserModel> userModels = query(sql, new UserMapper(), userName, password, state);
        return userModels.isEmpty() ? null : userModels.get(0);
    }

    @Override
    public Long addNewUser(UserModel newUserModel) {
        String sql = "INSERT INTO User (UserId, RoleId, Username, Password, State) VALUES (NULL, ?, ?, ?, ?)";
        Long result = insert(sql, newUserModel.getRoleId(), newUserModel.getUsername(),
                newUserModel.getPassword(), newUserModel.getState());
        if(result == null) result = Long.valueOf(-1);
        return result;
    }
}
