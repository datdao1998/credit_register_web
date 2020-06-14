package api.model.dao;

import api.model.connectdb.ConnectDB;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<R> {

    public static ConnectDB connectDB = ConnectDB.getInstance();

    List<R> getAll();

    Optional<R> save(R r);

    Optional<R> findById(Integer id);

}
