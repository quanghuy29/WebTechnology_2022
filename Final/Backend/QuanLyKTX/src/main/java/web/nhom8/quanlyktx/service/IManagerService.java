package web.nhom8.quanlyktx.service;

import web.nhom8.quanlyktx.model.ManagerModel;

import java.util.List;

public interface IManagerService {
    ManagerModel findOne(Integer managerId);
    List<ManagerModel> findAll();
    ManagerModel save(ManagerModel manager);
    ManagerModel update(ManagerModel manager);
    void delete(Long id);
    int getTotalItem();
}
