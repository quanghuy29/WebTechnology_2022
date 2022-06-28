package web.nhom8.quanlyktx.service;

import web.nhom8.quanlyktx.model.UserModel;

import java.util.List;

public interface IUserService {
    List<UserModel> findAll();
    UserModel findOne(int userId);

    UserModel findByUserName(String userName);

    UserModel findByUsernameAndPasswordAndState(String userName, String password, int state);
}
