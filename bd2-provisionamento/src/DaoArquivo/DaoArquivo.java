package DaoArquivo;

import MyExceptions.DaoException;
import MyExceptions.DeletaDadosException;
import MyExceptions.GravaDadosException;
import Resources.Resources;
import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;

public abstract class DaoArquivo<T extends ModeloBase> implements Serializable {

    protected Dados<T> dados;
    private File arquivo;

    public DaoArquivo(String nomeArquivo) throws DaoException {
        this.arquivo = new File(Resources.CAMINHO_ARQUIVOS + nomeArquivo);
        dados = new Dados<>();
        this.dados.Carrega(arquivo);
        this.AtualizaReferencias();
    }

    protected abstract void AtualizaReferencias() throws DaoException;

    public void grava(T object) throws GravaDadosException {
        try {
            if (object.id == -1) {
                insereId(object);
            }
            this.dados.insere(object);
            this.dados.Persiste(arquivo);
        } catch (Exception ex) {
            throw new GravaDadosException(ex);
        }
    }

    public T deleta(T object) throws DeletaDadosException {
        try {
            T removido = this.dados.remove(object);
            this.dados.Persiste(arquivo);
            return removido;
        } catch (Exception ex) {
            throw new DeletaDadosException("Erro ao deletar " + object.getClass().getSimpleName(), ex);
        }
    }

    public T busca(int id) {
        return this.dados.busca(id);
    }

    public ArrayList<T> busca() {
        return new ArrayList<>(this.dados.buscaTodos());
    }

    protected void insereId(T object) throws DaoException {
        try {
            Field fileId = procuraCampoId(object.getClass());
            if (fileId == null) {
                throw new DaoException(object.getClass().getSimpleName() + " não possui campo id");
            }
            fileId.setInt(object, dados.getProximoId());
            dados.setProximoId(dados.getProximoId() + 1);
        } catch (Exception ex) {
            throw new DaoException("Erro ao acessar id de " + object.getClass().getSimpleName(), ex);
        }
    }

    protected Field procuraCampoId(Class classe) throws DaoException {
        try {

            Class aux = classe.getSuperclass();
            while (aux != null) {
                if (aux.equals(ModeloBase.class)) {
                    break;
                }
                aux = aux.getSuperclass();
            }
            if (aux == null) {
                return null;
            }

            return aux.getDeclaredField("id");

        } catch (NoSuchFieldException | SecurityException ex) {
            throw new DaoException(ex);
        }
    }

    public void abre() throws DaoException {
        //Serve apenas para criar a instancia dessa classe
        //e carregar os dados na memória.
    }

    protected boolean estaGravado(ModeloBase object) {
        if (object == null) {
            return true;
        }

        return object.getId() >= 0;
    }
}
