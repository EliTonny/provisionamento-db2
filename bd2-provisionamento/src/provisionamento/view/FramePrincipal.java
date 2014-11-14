package provisionamento.view;

import PDF.MeusDevedoresReport;
import PDF.MinhasDividasReport;
import Sistema.ConcreteSubject;
import Sistema.Observer;
import Sistema.Session;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import provisionamento.controller.FramesController;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.GrupoUnitario;
import provisionamento.model.Mensagem;

public class FramePrincipal extends javax.swing.JFrame implements Observer {

    FramesController controller;

    public FramePrincipal() {
        try {
            initComponents();
            controller = new FramesController();

            List<Mensagem> mensagens = Session.getInstancia().getUsuarioLogado().getMensagens();
            for (Mensagem mensagem : mensagens) {
                listaMensagens.addElement(mensagem);
            }

            lsNotificacoes.setModel(listaMensagens);

            lbUsuLogado.setText(lbUsuLogado.getText() + Session.getInstancia().getUsuarioLogado().getNome() + "!");

            ArrayList<GrupoComunitario> grupoParticipante =
                    controller.getGruposParticipante(
                    Session.getInstancia().getUsuarioLogado());

            ArrayList<GrupoComunitario> grupoCriador =
                    controller.getGruposCriador(
                    Session.getInstancia().getUsuarioLogado());

            ArrayList<GrupoUnitario> grupoPessoal =
                    controller.getGruposPessoais(
                    Session.getInstancia().getUsuarioLogado());

            DefaultListModel<GrupoComunitario> modelSeusGrupos =
                    new DefaultListModel<>();

            DefaultListModel<GrupoUnitario> modelGruposPessoais =
                    new DefaultListModel<>();

            DefaultListModel<GrupoComunitario> modelGruposParticipantes =
                    new DefaultListModel<>();

            lsSeusGrupos.setModel(modelSeusGrupos);
            lsGruposPessoais.setModel(modelGruposPessoais);
            lsGruposComunParticipa.setModel(modelGruposParticipantes);

            for (GrupoComunitario grupoComunitario : grupoCriador) {
                modelSeusGrupos.addElement(grupoComunitario);
            }

            for (GrupoUnitario grupoUnitario : grupoPessoal) {
                modelGruposPessoais.addElement(grupoUnitario);
            }

            for (GrupoComunitario grupoComunitario : grupoParticipante) {
                modelGruposParticipantes.addElement(grupoComunitario);
            }

            ConcreteSubject.getInstancia().registerObserver(this);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
    private FrameGrupoUnitario frameGrupo;
    private FrameGrupoComunitario frameGrupoComunitario;
    private JFileChooser SeletorPDF;
    private DefaultListModel<Mensagem> listaMensagens = new DefaultListModel<>();

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel2 = new javax.swing.JLabel();
        lbUsuLogado = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        btGrupo = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsSeusGrupos = new javax.swing.JList();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        lsGruposComunParticipa = new javax.swing.JList();
        btGrupoComunitario = new javax.swing.JButton();
        btLogout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        rbMinhasDividasReport = new javax.swing.JRadioButton();
        rbMeusDevedoresReporr = new javax.swing.JRadioButton();
        BtAddUsuario = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        lsGruposPessoais = new javax.swing.JList();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        lsNotificacoes = new javax.swing.JList();
        btAtender = new javax.swing.JButton();
        btRemover = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        tfCaminhoReport = new javax.swing.JTextField();
        btBrowseReport = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Sistema de Provisionamento");

        jLabel2.setText("Suas Notificacões");

        lbUsuLogado.setText("Bom vê-lo novamente por aqui, ");

        btGrupo.setText("Adicionar Grupo Pessoal");
        btGrupo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGrupoActionPerformed(evt);
            }
        });

        jLabel4.setText("Seus Grupos Comunitários");

        lsSeusGrupos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lsSeusGruposMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(lsSeusGrupos);

        jLabel5.setText("Grupos em que você esta devendo");

        jScrollPane3.setViewportView(lsGruposComunParticipa);

        btGrupoComunitario.setText("Adicionar Grupo Comunitário");
        btGrupoComunitario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGrupoComunitarioActionPerformed(evt);
            }
        });

        btLogout.setText("Logout");
        btLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLogoutActionPerformed(evt);
            }
        });

        jLabel3.setText("Relatórios");

        buttonGroup1.add(rbMinhasDividasReport);
        rbMinhasDividasReport.setSelected(true);
        rbMinhasDividasReport.setText("Relatórios das Minha Dívidas");

        buttonGroup1.add(rbMeusDevedoresReporr);
        rbMeusDevedoresReporr.setText("Relatórios de Meus Devedores");

        BtAddUsuario.setText("Cadastrar Usuario");
        BtAddUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtAddUsuarioActionPerformed(evt);
            }
        });

        jScrollPane4.setViewportView(lsGruposPessoais);

        jLabel6.setText("Grupos pessoais");

        lsNotificacoes.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(lsNotificacoes);

        btAtender.setText("Atender");
        btAtender.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAtenderActionPerformed(evt);
            }
        });

        btRemover.setText("Remover");
        btRemover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoverActionPerformed(evt);
            }
        });

        jLabel7.setText("Salvar em:");

        tfCaminhoReport.setEditable(false);

        btBrowseReport.setText("Browse...");
        btBrowseReport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btBrowseReportActionPerformed(evt);
            }
        });

        jButton3.setText("Gerar Relatorio");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jSeparator2)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 619, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btRemover, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btAtender, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 26, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbUsuLogado)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(rbMeusDevedoresReporr)
                                    .addComponent(rbMinhasDividasReport)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(tfCaminhoReport, javax.swing.GroupLayout.PREFERRED_SIZE, 301, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btBrowseReport))
                                    .addComponent(jButton3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel4))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btGrupoComunitario, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btGrupo, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(346, 346, 346))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(61, 61, 61)
                                .addComponent(btLogout)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtAddUsuario)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(BtAddUsuario)
                            .addComponent(btLogout))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbUsuLogado)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btAtender)
                        .addGap(16, 16, 16)
                        .addComponent(btRemover)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4)
                    .addComponent(jScrollPane3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btGrupoComunitario)
                    .addComponent(btGrupo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbMinhasDividasReport)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(rbMeusDevedoresReporr)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfCaminhoReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btBrowseReport))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGrupoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGrupoActionPerformed
        try {
            frameGrupo = new FrameGrupoUnitario();
            frameGrupo.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btGrupoActionPerformed

    private void btGrupoComunitarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGrupoComunitarioActionPerformed
        try {
            frameGrupoComunitario = new FrameGrupoComunitario();
            frameGrupoComunitario.setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btGrupoComunitarioActionPerformed

    private void btLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLogoutActionPerformed
        Session.getInstancia().setUsuarioLogado(null);
        new FrameLogin().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btLogoutActionPerformed

    private void BtAddUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtAddUsuarioActionPerformed
        new FrameUsuario().setVisible(true);
    }//GEN-LAST:event_BtAddUsuarioActionPerformed

    private void lsSeusGruposMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lsSeusGruposMouseClicked
        GrupoComunitario com = (GrupoComunitario) lsSeusGrupos.getSelectedValue();
        new FrameGrupoComunitarioSituacao(com).setVisible(true);
    }//GEN-LAST:event_lsSeusGruposMouseClicked

    private void btAtenderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAtenderActionPerformed
        try {
            Mensagem mensagem = (Mensagem) lsNotificacoes.getSelectedValue();
            if (mensagem.getGrupo() instanceof GrupoComunitario) {
                frameGrupoComunitario = new FrameGrupoComunitario(mensagem);
                frameGrupoComunitario.setVisible(true);
            } else {
                frameGrupo = new FrameGrupoUnitario(mensagem);
                frameGrupo.setVisible(true);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btAtenderActionPerformed

    private void btRemoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoverActionPerformed
        try {
            Mensagem mensagem = (Mensagem) lsNotificacoes.getSelectedValue();
            mensagem.getGrupo().setFinalizado(true);
            ConcreteSubject.getInstancia().notifyObservers(mensagem.getGrupo());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btRemoverActionPerformed

    private void btBrowseReportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btBrowseReportActionPerformed
        String nomeCompleto;

        SeletorPDF = new javax.swing.JFileChooser();
        SeletorPDF.setFileSelectionMode(JFileChooser.FILES_ONLY);
        SeletorPDF.setApproveButtonText("OK");
        SeletorPDF.setDialogType(javax.swing.JFileChooser.SAVE_DIALOG);
        SeletorPDF.setCurrentDirectory(new java.io.File("C:\\"));

        int i = SeletorPDF.showSaveDialog(null);

        if (i == 1) {
            tfCaminhoReport.setText("");
        } else {
            File arquivo = SeletorPDF.getSelectedFile();
            nomeCompleto = arquivo.getName();

            if (!(nomeCompleto.contains("."))) {
                tfCaminhoReport.setText(arquivo.getPath() + ".pdf");
            } else {
                tfCaminhoReport.setText(arquivo.getPath());
            }
        }

        SeletorPDF.setVisible(true);

    }//GEN-LAST:event_btBrowseReportActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        try {
            String extensao;
            String nomeArqu;

            if (!(tfCaminhoReport.getText().equals(""))) {
                nomeArqu = tfCaminhoReport.getText();
                extensao = nomeArqu.substring(nomeArqu.lastIndexOf("."), nomeArqu.length());

                if (!(extensao.equals(".pdf"))) {
                    JOptionPane.showMessageDialog(null, "Extensão Incorreta.");
                } else {
                    try {
                        if (rbMinhasDividasReport.isSelected()) {
                            MinhasDividasReport mdr = new MinhasDividasReport(tfCaminhoReport.getText());
                            tfCaminhoReport.setText("");
                        }
                        if (rbMeusDevedoresReporr.isSelected()) {
                            MeusDevedoresReport mder = new MeusDevedoresReport(tfCaminhoReport.getText());
                            tfCaminhoReport.setText("");
                        }
                    } catch (Exception ex) {
                        Logger.getLogger(FramePrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "Informe diretório para salvar o arquivo.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_jButton3ActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtAddUsuario;
    private javax.swing.JButton btAtender;
    private javax.swing.JButton btBrowseReport;
    private javax.swing.JButton btGrupo;
    private javax.swing.JButton btGrupoComunitario;
    private javax.swing.JButton btLogout;
    private javax.swing.JButton btRemover;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JLabel lbUsuLogado;
    private javax.swing.JList lsGruposComunParticipa;
    private javax.swing.JList lsGruposPessoais;
    private javax.swing.JList lsNotificacoes;
    private javax.swing.JList lsSeusGrupos;
    private javax.swing.JRadioButton rbMeusDevedoresReporr;
    private javax.swing.JRadioButton rbMinhasDividasReport;
    private javax.swing.JTextField tfCaminhoReport;
    // End of variables declaration//GEN-END:variables

    @Override
    public void update(Object obj) {
        if (obj instanceof GrupoComunitario) {

            GrupoComunitario grupo = (GrupoComunitario) obj;

            if (grupo.getCriador().equals(Session.getInstancia().getUsuarioLogado())) {
                DefaultListModel<GrupoComunitario> modelSeusGrupos =
                        (DefaultListModel<GrupoComunitario>) lsSeusGrupos.getModel();
                if (!grupo.isFinalizado() && !grupo.isPago()) {
                    modelSeusGrupos.addElement(grupo);
                } else {
                    modelSeusGrupos.removeElement(grupo);
                }
            } else {
                DefaultListModel<GrupoComunitario> modelGruposParticipantes =
                        (DefaultListModel<GrupoComunitario>) lsGruposComunParticipa.getModel();
                if (!grupo.isFinalizado() && !grupo.isPago()) {
                    modelGruposParticipantes.addElement(grupo);
                } else {
                    modelGruposParticipantes.removeElement(grupo);
                }
            }

        } else if (obj instanceof GrupoUnitario) {

            DefaultListModel<GrupoUnitario> modelGruposPessoais =
                    (DefaultListModel<GrupoUnitario>) lsGruposPessoais.getModel();

            GrupoUnitario grupoUnitario = (GrupoUnitario) obj;
            if (grupoUnitario.isFinalizado() == false) {
                if (grupoUnitario.getCriador().equals(Session.getInstancia().getUsuarioLogado())) {
                    modelGruposPessoais.addElement(grupoUnitario);
                }
            } else {
                modelGruposPessoais.removeElement(grupoUnitario);
            }
        } else if (obj instanceof Mensagem) {
            Mensagem mensagem = (Mensagem) obj;
            listaMensagens.removeElement(mensagem);
        }
    }
}
