package Sistema;

import MyExceptions.DaoException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.GrupoUnitario;

/**
 *
 * @author Eli T. de Souza
 */
public class AvisaCompradores {
    
    private AvisaCompradores(){
    }
    
    public static void avisar(){
        
        try {
            Dao<GrupoComunitario> dao = Factoring.getDaoGrupoComunitario();
            List<GrupoComunitario> grupos = dao.busca();
            Dao<GrupoUnitario> daoUni  = Factoring.getDaoGrupoUnitario();
            List<GrupoUnitario> gruposUni = daoUni.busca();
            
            Date dataAtual = new Date(); 
            Date prazo = new Date();
            Session.getInstancia().getUsuarioLogado().iniMensagens();
            Session.getInstancia().getUsuarioLogado().removeMensagens();

            for (GrupoComunitario grupoComunitario : grupos) {
                prazo.setTime(grupoComunitario.getPrazoValidade().getTime() - ((24 * 3600000) * (grupoComunitario.getQrdDiasNotificacao())));
                if(dataAtual.after(prazo)){
                    if(Session.getInstancia().getUsuarioLogado().equals(grupoComunitario.getComprador().getUsuario())
                       && grupoComunitario.isFinalizado() == false){
                        Session.getInstancia().getUsuarioLogado().setMensagem("Você precisa comprar mais " + grupoComunitario.getCategoria().getDescricao(),
                                                                               grupoComunitario);

                    }
                }
            }
            
            for (GrupoUnitario grupoUnitario : gruposUni) {
                prazo.setTime(grupoUnitario.getPrazoValidade().getTime() - ((24 * 3600000) * (grupoUnitario.getQrdDiasNotificacao())));
                if(dataAtual.after(prazo)){
                    if(Session.getInstancia().getUsuarioLogado().equals(grupoUnitario.getCriador())
                       && grupoUnitario.isFinalizado() == false){
                        Session.getInstancia().getUsuarioLogado().setMensagem("Você precisa comprar mais " + grupoUnitario.getCategoria().getDescricao(),
                                                                               grupoUnitario);

                    }
                }
            }

        } catch (DaoException ex) {
            Logger.getLogger(AvisaCompradores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}