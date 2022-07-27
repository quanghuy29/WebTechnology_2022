package web.nhom8.quanlyktx.service.impl;

import web.nhom8.quanlyktx.dao.IManagerDAO;
import web.nhom8.quanlyktx.model.ManagerModel;
import web.nhom8.quanlyktx.service.IManagerService;

import javax.inject.Inject;
import java.util.List;

public class ManagerService implements IManagerService {
    @Inject
    private IManagerDAO managerDAO;
    @Override
    public ManagerModel findOne(Integer managerId) {
        return managerDAO.findOne(managerId);
    }

    @Override
    public List<ManagerModel> findAll() {
        return managerDAO.findAll();
    }

    @Override
    public ManagerModel save(ManagerModel manager) {
        return managerDAO.save(manager);
    }

    @Override
    public ManagerModel update(ManagerModel manager) {
        return managerDAO.update(manager);
    }

    @Override
    public void delete(Long id) {
        managerDAO.delete(id);
        managerDAO.resetAI();
    }

    @Override
    public int getTotalItem() {
        return managerDAO.getTotalItem();
    }
}
