package DaoArquivo;

import Sistema.Dao;
import MyExceptions.DaoException;
import provisionamento.model.Categoria;

public class DaoArquivoCategoria extends DaoArquivo<Categoria> implements Dao<Categoria> {

    private static DaoArquivoCategoria instancia;

    private DaoArquivoCategoria() throws DaoException {
        super(Categoria.class.getSimpleName());
    }

    public static DaoArquivoCategoria getInstancia() throws DaoException {
        if (instancia == null) {
            instancia = new DaoArquivoCategoria();
        }
        return instancia;
    }

    @Override
    public Categoria busca(String descricao) {
        for (Categoria val : this.dados.buscaTodos()) {
            if (val.getDescricao().equals(descricao)) {
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
