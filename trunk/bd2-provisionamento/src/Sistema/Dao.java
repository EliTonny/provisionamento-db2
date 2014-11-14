package Sistema;

import MyExceptions.DaoException;
import java.util.List;

public interface Dao<T> {
    void grava(T objeto) throws DaoException;
    T deleta(T objeto) throws DaoException;
    T busca(String nome) throws DaoException;
    T busca(int id) throws DaoException;
    List<T> busca() throws DaoException;
    void abre() throws DaoException;
}
