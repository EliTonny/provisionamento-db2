package provisionamento.view;

import Email.EmailSender;
import MyExceptions.DaoException;
import Sistema.AvisaCompradores;
import Sistema.FacadeCarregaArquivos;
import Sistema.Session;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import provisionamento.controller.FramesController;
import provisionamento.model.Usuario;

/**
 *
 * @author Lucas
 */
public class FrameLogin extends javax.swing.JFrame{

    public FrameLogin() {
        initComponents();
        controller = new FramesController();
    }
    private FramePrincipal framePri;
    private FrameUsuario frameUsu;
    private FramesController controller;

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        tfUsuario = new javax.swing.JTextField();
        tfSenha = new javax.swing.JPasswordField();
        btLogin = new javax.swing.JButton();
        tfUsuarioEsquecido = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Sistema de Provisionamento");

        jLabel2.setText("Usuário:");

        jLabel3.setText("Faça o seu Login para continuar:");

        jLabel4.setText("Senha:");

        tfSenha.setPreferredSize(new java.awt.Dimension(113, 20));

        btLogin.setText("Login");
        btLogin.setActionCommand("&Login");
        btLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btLoginActionPerformed(evt);
            }
        });

        tfUsuarioEsquecido.setFont(new java.awt.Font("Tahoma", 0, 9)); // NOI18N
        tfUsuarioEsquecido.setText("Véi, esquecesse a tua senha? Então clique aqui!");
        tfUsuarioEsquecido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tfUsuarioEsquecidoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 338, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(tfUsuario)
                                    .addComponent(tfSenha, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))))
                        .addContainerGap(40, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfUsuarioEsquecido)
                            .addComponent(btLogin))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(33, 33, 33)
                .addComponent(jLabel3)
                .addGap(5, 5, 5)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(tfSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btLogin)
                .addGap(5, 5, 5)
                .addComponent(tfUsuarioEsquecido)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btLoginActionPerformed
        try {
            Usuario usuario = controller.buscaUsuario(tfUsuario.getText());
            if (usuario != null) {
                if (Arrays.equals(usuario.getSenha(), tfSenha.getPassword())) {
                    tfUsuario.setText("");
                    tfSenha.setText("");
                    Session.getInstancia().setUsuarioLogado(usuario);
                    AvisaCompradores.avisar();
                    new FramePrincipal().setVisible(true);
                    FacadeCarregaArquivos.Carrega();
                    this.dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não cadastrado!");
            }
        } catch (DaoException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }//GEN-LAST:event_btLoginActionPerformed

    private void tfUsuarioEsquecidoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tfUsuarioEsquecidoMouseClicked

        Usuario user;
        try {
            if (!(tfUsuarioEsquecido.getText().isEmpty())) {
                user = controller.buscaUsuario(this.tfUsuario.getText());
                if (user != null) {
                    EmailSender emailSender = new EmailSender(user);
                } else {
                    JOptionPane.showMessageDialog(null, "Usuario não cadastrado!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Esqueceu?\nEntão insira seu usuário e clique novamente!");
            }
        } catch (DaoException ex) {
            Logger.getLogger(FrameLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_tfUsuarioEsquecidoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btLogin;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPasswordField tfSenha;
    private javax.swing.JTextField tfUsuario;
    private javax.swing.JLabel tfUsuarioEsquecido;
    // End of variables declaration//GEN-END:variables

}
