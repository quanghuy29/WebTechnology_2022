package web.nhom8.quanlyktx.dao;

import web.nhom8.quanlyktx.model.ManagerModel;

import java.util.List;

public interface IManagerDAO extends GenericDAO<ManagerModel> {
    ManagerModel findOne(Integer managerId);
    List<ManagerModel> findAll();
    ManagerModel save(ManagerModel manager);
    ManagerModel update(ManagerModel manager);
    void delete(Long id);
    int getTotalItem();
    int getIdMax();
    void resetAI();
}
