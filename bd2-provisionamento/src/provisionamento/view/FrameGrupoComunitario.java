package provisionamento.view;

import MyExceptions.DaoException;
import Sistema.*;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import provisionamento.controller.FramesController;
import provisionamento.model.*;

public class FrameGrupoComunitario extends javax.swing.JFrame implements Observer {

    private FrameCategoria frameCategoria;
    private DefaultListModel<Usuario> listaParticipantes = new DefaultListModel<>();
    private DefaultListModel<Usuario> listaUsuarios = new DefaultListModel();
    private boolean fechar;
    private FramesController controller;
    private Mensagem mensagemFonte;
    
    public FrameGrupoComunitario() {
        initComponents();
        controller = new FramesController();
        tfCriador.setText(Session.getInstancia().getUsuarioLogado().getNome());
        cbCategoria.removeAllItems();
        lsParticipantesGrupo.setModel(listaParticipantes);
        try {
            List<Categoria> categorias = controller.buscaCategoria();

            for (Categoria categoria : categorias) {
                cbCategoria.addItem(categoria);
            }

            this.iniListaUsuario();
            lsMembrosRepublica.setModel(listaUsuarios);

        } catch (DaoException ex) {
            Logger.getLogger(FrameGrupoComunitario.class.getName()).log(Level.SEVERE, null, ex);
        }
        ConcreteSubject.getInstancia().registerObserver(this);
    }

    public FrameGrupoComunitario(Mensagem mensagem) {
        initComponents();
        controller = new FramesController();
        this.mensagemFonte = mensagem;
        GrupoComunitario grupoComunitario = (GrupoComunitario) mensagem.getGrupo();
        cbCategoria.removeAllItems();
        cbCategoria.addItem(grupoComunitario.getCategoria());
        cbCategoria.setEnabled(false);
        btAddCategoria.setEnabled(false);
        tfCriador.setText(Session.getInstancia().getUsuarioLogado().getNome());

        this.iniListaUsuario();
        List<Participante> participantes = grupoComunitario.getParticipantes();
        for (Participante participante : participantes) {
            if (!Session.getInstancia().getUsuarioLogado().equals(participante.getUsuario())) {
                listaParticipantes.addElement(participante.getUsuario());
                listaUsuarios.removeElement(participante.getUsuario());
            }
        }
        listaParticipantes.addElement(grupoComunitario.getCriador());
        listaUsuarios.removeElement(grupoComunitario.getCriador());
        lsMembrosRepublica.setModel(listaUsuarios);
        lsParticipantesGrupo.setModel(listaParticipantes);
        this.fechar = true;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        cbCategoria = new javax.swing.JComboBox();
        btAddCategoria = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        tfCriador = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        btPraCa = new javax.swing.JButton();
        btPraLa = new javax.swing.JButton();
        btAddGrupoComun = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsMembrosRepublica = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsParticipantesGrupo = new javax.swing.JList();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        btFechar = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        tfValor = new javax.swing.JFormattedTextField();
        tfDiasVencimento = new javax.swing.JFormattedTextField();
        tfDiasNotificacao = new javax.swing.JFormattedTextField();
        tfQuantidade = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Novo Grupo Comunitário");

        jLabel2.setText("Categoria:");

        cbCategoria.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        btAddCategoria.setText("+");
        btAddCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddCategoriaActionPerformed(evt);
            }
        });

        jLabel3.setText("Criador:");

        jLabel4.setText("Quantidade:");

        jLabel6.setText("Vencimento:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        jLabel5.setText("Quantos dias faltam para o vencimento do grupo.");

        tfCriador.setEnabled(false);

        jLabel7.setText("Membros da República");

        jLabel8.setText("Participantes do Grupo");

        btPraCa.setText("<<");
        btPraCa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPraCaActionPerformed(evt);
            }
        });

        btPraLa.setText(">>");
        btPraLa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPraLaActionPerformed(evt);
            }
        });

        btAddGrupoComun.setText("Adicionar Grupo");
        btAddGrupoComun.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddGrupoComunActionPerformed(evt);
            }
        });

        lsMembrosRepublica.setPreferredSize(new java.awt.Dimension(135, 210));
        jScrollPane3.setViewportView(lsMembrosRepublica);

        jScrollPane4.setViewportView(lsParticipantesGrupo);

        jLabel9.setText("dias antes do vencimento.");

        jLabel10.setText("Notificar:");

        btFechar.setText("Fechar");
        btFechar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btFecharActionPerformed(evt);
            }
        });

        jLabel11.setText("Valor:");

        tfValor.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0.00"))));
        tfValor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfValorActionPerformed(evt);
            }
        });

        tfDiasVencimento.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        tfDiasNotificacao.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        tfQuantidade.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(new java.text.DecimalFormat("#0"))));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(btAddGrupoComun)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btFechar))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel3)
                            .addComponent(jLabel11))
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(tfCriador)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfDiasNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel9))
                                .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(btAddCategoria))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(tfDiasVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(jLabel5))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btPraLa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btPraCa, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(64, 64, 64)
                                .addComponent(jLabel7)))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(65, 65, 65)))))
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(jLabel6)
                        .addGap(9, 9, 9)
                        .addComponent(jLabel10)
                        .addGap(15, 15, 15)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cbCategoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btAddCategoria)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfQuantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDiasVencimento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tfDiasNotificacao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel9))
                        .addGap(6, 6, 6)
                        .addComponent(tfCriador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tfValor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(133, 133, 133)
                        .addComponent(btPraLa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btPraCa))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(btAddGrupoComun))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btFechar)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btFecharActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btFecharActionPerformed
        ConcreteSubject.getInstancia().removeOberser(this);
        this.dispose();
    }//GEN-LAST:event_btFecharActionPerformed

    private void btAddCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddCategoriaActionPerformed
        frameCategoria = new FrameCategoria();
        frameCategoria.setVisible(true);
    }//GEN-LAST:event_btAddCategoriaActionPerformed

    private void btPraLaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPraLaActionPerformed
        try {
            List<Usuario> usariosSelecionados = lsMembrosRepublica.getSelectedValuesList();
            for (Usuario usariosSelecionado : usariosSelecionados) {
                listaParticipantes.addElement(usariosSelecionado);
                listaUsuarios.removeElement(usariosSelecionado);
            }
            lsMembrosRepublica.setSelectedIndex(0);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btPraLaActionPerformed

    private void btPraCaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPraCaActionPerformed
        try {
            List<Usuario> participantesSelecionados = lsParticipantesGrupo.getSelectedValuesList();
            Iterator itParSel = participantesSelecionados.iterator();
            Usuario usuario;
            while (itParSel.hasNext()) {
                usuario = (Usuario) itParSel.next();
                listaUsuarios.addElement(usuario);
                listaParticipantes.removeElement(usuario);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btPraCaActionPerformed

    private void btAddGrupoComunActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddGrupoComunActionPerformed

        try {
            boolean ok = true;
            int qtdItens = 0;
            int qtdDiasVencimento = 0;
            int qtdDiasNotificaocao = 0;
            Date prazo = new Date();
            Participante participante;

            if (tfQuantidade.getText().equals("")) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Quantidade de itens não informada!");
            }

            if (tfDiasVencimento.getText().equals("")) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Prazo de vencimento não informado!");
            }

            if (tfDiasNotificacao.getText().equals("")) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Prazo de notificação não informado!");
            }

            if (tfValor.getText().trim().equals("")) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Valor não informado!");
            }

            if (listaParticipantes.isEmpty()) {
                ok = false;
                JOptionPane.showMessageDialog(this, "Lista de participantes deve conter ao menous um participante!");
            }

            if (ok == true) {

                qtdItens = Integer.parseInt(tfQuantidade.getText());
                qtdDiasVencimento = Integer.parseInt(tfDiasVencimento.getText());
                qtdDiasNotificaocao = Integer.parseInt(tfDiasNotificacao.getText());
                prazo.setTime(prazo.getTime() + ((24 * 3600000) * qtdDiasVencimento));

                GrupoComunitario grupoComunitario = new GrupoComunitario();
                grupoComunitario.setCriador(Session.getInstancia().getUsuarioLogado());
                grupoComunitario.setCategoria((Categoria) cbCategoria.getSelectedItem());
                grupoComunitario.setQuantidade(qtdItens);
                grupoComunitario.setValorCompra(Double.parseDouble(tfValor.getText().replace(',', '.')));
                grupoComunitario.setQrdDiasNotificacao(qtdDiasNotificaocao);
                grupoComunitario.setPrazoValidade(prazo);

                while (!listaParticipantes.isEmpty()) {
                    participante = new Participante();
                    participante.setUsuario(listaParticipantes.firstElement());
                    controller.grava(participante);
                    grupoComunitario.addParticipante(participante);

                    listaParticipantes.removeElement(participante.getUsuario());
                }
                controller.grava(grupoComunitario);

                tfDiasNotificacao.setText(null);
                tfDiasVencimento.setText(null);
                tfQuantidade.setText(null);
                tfValor.setText(null);

                this.iniListaUsuario();
                if(mensagemFonte != null){
                    mensagemFonte.getGrupo().setFinalizado(true);
                    controller.notificar(mensagemFonte);
                    controller.notificar(mensagemFonte.getGrupo());
                }                    
            
                JOptionPane.showMessageDialog(null, "Grupo cadastrado com sucesso!");
                if (this.fechar) {
                    ConcreteSubject.getInstancia().removeOberser(this);
                    this.dispose();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btAddGrupoComunActionPerformed

    private void tfValorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfValorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfValorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddCategoria;
    private javax.swing.JButton btAddGrupoComun;
    private javax.swing.JButton btFechar;
    private javax.swing.JButton btPraCa;
    private javax.swing.JButton btPraLa;
    private javax.swing.JComboBox cbCategoria;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JList lsMembrosRepublica;
    private javax.swing.JList lsParticipantesGrupo;
    private javax.swing.JTextField tfCriador;
    private javax.swing.JFormattedTextField tfDiasNotificacao;
    private javax.swing.JFormattedTextField tfDiasVencimento;
    private javax.swing.JFormattedTextField tfQuantidade;
    private javax.swing.JFormattedTextField tfValor;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object obj) {
        if (obj instanceof Categoria) {
            cbCategoria.addItem((Categoria) obj);
        } else if (obj instanceof Usuario) {
            listaUsuarios.addElement((Usuario) obj);
        }
    }

    private void iniListaUsuario() {
        try {
            List<Usuario> usuarios = controller.buscaUsuario();
            listaUsuarios.removeAllElements();
            for (Usuario usuario : usuarios) {
                listaUsuarios.addElement(usuario);
            }
            listaUsuarios.removeElement(Session.getInstancia().getUsuarioLogado());
        } catch (DaoException ex) {
            Logger.getLogger(FrameGrupoComunitario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
