package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.model.StudentModel;
import web.nhom8.quanlyktx.model.UserModel;

import java.util.List;

public interface IUserDAO extends GenericDAO<UserModel> {
    UserModel findOne(Integer userId);
    List<UserModel> findAll();

    UserModel findByUserName(String userName);

    UserModel findByUsernameAndPasswordAndState(String userName, String password, int state);
    UserModel findByEmailAndPasswordAndState(String email, String password, int state);
    Long addNewUser(UserModel newUserModel);
    UserModel updateUser(UserModel userModel);
}
