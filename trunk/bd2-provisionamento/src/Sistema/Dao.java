package Sistema;

import MyExceptions.DaoException;
import java.util.List;
import org.bson.types.ObjectId;

public interface Dao<T> {
    void grava(T objeto) throws DaoException;
    void deleta(T objeto) throws DaoException;
    T busca(String nome) throws DaoException;
    T busca(ObjectId id) throws DaoException;
    List<T> busca() throws DaoException;
    //void abre() throws DaoException;
}
