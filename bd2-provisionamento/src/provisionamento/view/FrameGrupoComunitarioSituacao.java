package provisionamento.view;

import MyExceptions.DaoException;
import Sistema.ConcreteSubject;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import provisionamento.controller.FramesController;
import provisionamento.model.GrupoComunitario;
import provisionamento.model.Participante;

public class FrameGrupoComunitarioSituacao extends javax.swing.JFrame {

    private GrupoComunitario grupoComunitario;
    private FramesController controller;

    public FrameGrupoComunitarioSituacao(GrupoComunitario grupo) {

        initComponents();
        controller = new FramesController();
        grupoComunitario = grupo;

        DefaultTableModel model = (DefaultTableModel) this.TableGrupos.getModel();
        for (Participante participante : grupoComunitario.getParticipantes()) {
            model.addRow(new Object[]{participante.getUsuario().getNome(),
                grupoComunitario.getValorCompra() / grupoComunitario.getParticipantes().size(),
                participante.isPago()});
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        TableGrupos = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        TableGrupos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Usuario", "Valor", "Pago"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Double.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(TableGrupos);

        jButton2.setText("Gravar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btGrava(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(9, 9, 9)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btGrava(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btGrava
        try {
            int cont = 0;
            boolean todosPagaram = true;
            for (Participante p : grupoComunitario.getParticipantes()) {
                boolean isPago = (boolean) this.TableGrupos.getValueAt(cont++, 2);
                controller.AtualizarSituacaoParticipante(p, isPago);
                if (!isPago) {
                    todosPagaram = false;
                }
            }
            if (todosPagaram) {
                controller.PagarGrupo(grupoComunitario);
                JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!\nTodos usuarios pagaram, o grupo será finalizado.");
                ConcreteSubject.getInstancia().notifyObservers(grupoComunitario);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Pagamento realizado com sucesso!");
            }

        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_btGrava
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TableGrupos;
    private javax.swing.JButton jButton2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
