package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.dao.IUserDAO;
import web.nhom8.quanlyktx.model.UserModel;
import web.nhom8.quanlyktx.service.IUserService;

import javax.inject.Inject;
import java.util.List;

public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;

    @Override
    public List<UserModel> findAll() {
        return userDAO.findAll();
    }

    @Override
    public UserModel findOne(int userId) {
        return userDAO.findOne(userId);
    }

    @Override
    public UserModel findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public UserModel findByUsernameAndPasswordAndState(String userName, String password, int state) {
        return userDAO.findByUsernameAndPasswordAndState(userName, password, state);
    }

    @Override
    public UserModel findByEmailAndPasswordAndState(String email, String password, int state) {
        return userDAO.findByEmailAndPasswordAndState(email, password, state);
    }

    @Override
    public Long addNewUser(UserModel newUserModel) {
        newUserModel.setUserState(1);
        return userDAO.addNewUser(newUserModel);
    }

    @Override
    public UserModel updateUser(UserModel userModel) {
        return userDAO.updateUser(userModel);
    }
}
