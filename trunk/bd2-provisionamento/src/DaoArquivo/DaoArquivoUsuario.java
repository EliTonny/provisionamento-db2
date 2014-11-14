package DaoArquivo;

import Sistema.Dao;
import MyExceptions.DaoException;
import provisionamento.model.Usuario;

public class DaoArquivoUsuario extends DaoArquivo<Usuario> implements Dao<Usuario> {

    private static DaoArquivoUsuario instancia;

    private DaoArquivoUsuario() throws DaoException {
        super(Usuario.class.getSimpleName());
    }

    public static DaoArquivoUsuario getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoArquivoUsuario();
        }
        return instancia;
    }

    @Override
    public Usuario busca(String nome) {
        for (Usuario val : this.dados.buscaTodos()) {
            if (val.getNome().equals(nome)) {
                return val;
            }
        }
        return null;
    }

    @Override
    protected void AtualizaReferencias() throws DaoException {
        //Do nothing
    }
}
