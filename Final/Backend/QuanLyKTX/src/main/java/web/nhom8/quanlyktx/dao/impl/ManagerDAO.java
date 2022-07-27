package web.nhom8.quanlyktx.dao.impl;

import web.nhom8.quanlyktx.dao.IManagerDAO;
import web.nhom8.quanlyktx.mapper.ManagerMapper;
import web.nhom8.quanlyktx.model.ManagerModel;

import java.util.List;

public class ManagerDAO extends AbstractDAO<ManagerModel> implements IManagerDAO {
    @Override
    public ManagerModel findOne(Integer managerId) {
        String sql = "SELECT * FROM Manager WHERE ManagerId = ?";
        List<ManagerModel> managers = query(sql, new ManagerMapper(), managerId);
        return managers.isEmpty() ? null : managers.get(0);
    }

    @Override
    public List<ManagerModel> findAll() {
        String sql = "SELECT * FROM Manager";
        return query(sql, new ManagerMapper());
    }

    @Override
    public ManagerModel save(ManagerModel manager) {
        Long id = insert("INSERT INTO Manager (UserId, Fullname, DateOfBirth, Email," +
                " Address, Phone, YearOfService, State) VALUES(?, ?, ?, ?, ?, ?, ?, ?)",
                manager.getUserId(), manager.getFullname(), manager.getDateOfBirth(), manager.getEmail(),
                manager.getAddress(), manager.getPhone(), manager.getYearOfService(), manager.getState());
        return findOne(Math.toIntExact(id));
    }

    @Override
    public ManagerModel update(ManagerModel manager) {
        String sql = "UPDATE Manager SET UserId = ?, Fullname = ?, DateOfBirth = ?, Email = ?," +
                " Address = ?, Phone = ?, YearOfService = ?, State = ? WHERE ManagerId = ?";
        update(sql, manager.getUserId(), manager.getFullname(), manager.getDateOfBirth(), manager.getEmail(),
                manager.getAddress(), manager.getPhone(), manager.getYearOfService(), manager.getState(),
                manager.getManagerId());
        return findOne(Math.toIntExact(manager.getManagerId()));
    }

    @Override
    public void delete(Long id) {
        String sql = "DELETE FROM Manager WHERE ManagerId = ?";
        update(sql, id);
    }

    @Override
    public int getTotalItem() {
        String sql = "SELECT count(*) FROM Manager";
        return count(sql);
    }

    @Override
    public int getIdMax() {
        String sql = "SELECT MAX(ManagerId) FROM Manager";
        return count(sql) == 0 ? 1 : count(sql);
    }

    @Override
    public void resetAI() {
        String sql = "ALTER TABLE Manager AUTO_INCREMENT = ?";
        update(sql, getIdMax());
    }
}
