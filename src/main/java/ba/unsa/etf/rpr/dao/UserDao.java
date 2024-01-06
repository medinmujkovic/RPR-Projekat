package ba.unsa.etf.rpr.dao;

import ba.unsa.etf.rpr.domain.entities.User;

import java.sql.SQLException;

public interface UserDao extends Dao<User> {
    User getByUsername(String username) throws SQLException;
}
