package provisionamento.model;

import java.util.ArrayList;
import java.util.List;

public class GrupoComunitario extends GrupoUnitario {

    private List<Participante> participantes;
    private int comprador;
    private boolean pago;

    public boolean isPago() {
        return pago;
    }

    public void setPago(boolean pago) {
        this.pago = pago;
    }

    public List<Participante> getParticipantes() {
        return participantes;
    }

    public GrupoComunitario() {
        super();
        participantes = new ArrayList<>();
    }
    
    public void setParticipantes(ArrayList<Participante> participantes){
        this.participantes = participantes;
    }

    public void addParticipante(Participante participante) {
        //Usando arrayList para controlar a fila com o indice comprador
        if (!participantes.contains(participante)) {
            participantes.add(participante);
        }
    }

    public void removeParticipante(Participante participante) {
        participantes.remove(participante);
    }

    public void addComprador() {
        this.comprador++;
        if (this.comprador == participantes.size()) {
            this.comprador = 0;
        }
    }

    public void setComprador(int comprador) {
        this.comprador = comprador;
    }

    public Participante getComprador() {
        return this.participantes.get(comprador);
    }
    
    public int getPosComprador(){
        return this.comprador;
    }
}
