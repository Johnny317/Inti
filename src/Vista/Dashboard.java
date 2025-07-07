package Vista;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Dashboard extends javax.swing.JPanel {

    private String correoUsuario;

    public Dashboard(String correoUsuario) {
        initComponents();
        this.correoUsuario = correoUsuario;
        btnDashboard.setOpaque(false);
        btnDashboard.setContentAreaFilled(false);
        btnDashboard.setBorderPainted(false);

        btnCuenta.setOpaque(false);
        btnCuenta.setContentAreaFilled(false);
        btnCuenta.setBorderPainted(false);

        btnCertificado.setOpaque(false);
        btnCertificado.setContentAreaFilled(false);
        btnCertificado.setBorderPainted(false);

        btnJuegos.setOpaque(false);
        btnJuegos.setContentAreaFilled(false);
        btnJuegos.setBorderPainted(false);

        btnSalir.setOpaque(false);
        btnSalir.setContentAreaFilled(false);
        btnSalir.setBorderPainted(false);
    }

    public String getCorreoUsuario() {
        return correoUsuario;
    }

    public JLabel getLblLogo() {
        return LblLogo;
    }

    public JLabel getLblNombreProyecto() {
        return LblNombreProyecto;
    }

    public JLabel getLblPanelSuperior() {
        return LblPanelSuperior;
    }

    public JLabel getLblRol() {
        return LblRol;
    }

    public JLabel getLblimagen() {
        return Lblimagen;
    }

    public JPanel getPanelVistas() {
        return PanelVistas;
    }

    public JButton getBtnCertificado() {
        return btnCertificado;
    }

    public JButton getBtnCuenta() {
        return btnCuenta;
    }

    public JButton getBtnDashboard() {
        return btnDashboard;
    }

    public JButton getBtnJuegos() {
        return btnJuegos;
    }

    public JButton getBtnSalir() {
        return btnSalir;
    }

    public JPanel getjPanel1() {
        return jPanel1;
    }

    public JLabel getLblNombre() {
        return lblNombre;
    }

    public void mostrarVista(JPanel nuevaVista) {
        PanelVistas.removeAll();
        PanelVistas.add(nuevaVista);
        PanelVistas.revalidate();
        PanelVistas.repaint();
        System.out.println("Vista mostrada: " + nuevaVista.getClass().getSimpleName());
    }

    public void setNombreUsuario(String nombre) {
        lblNombre.setText(nombre);
    }

    public void setRolUsuario(String rol) {
        LblRol.setText(rol);
    }

    public void actualizarSaludo(String saludo) {
        LblSaludo.setText(saludo);
    }

    public void setCorreoUsuario(String correo) {
        this.correoUsuario = correo;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        PanelVistas = new javax.swing.JPanel();
        LblSaludo = new javax.swing.JLabel();
        Lblimagen = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        LblRol = new javax.swing.JLabel();
        btnDashboard = new javax.swing.JButton();
        btnCuenta = new javax.swing.JButton();
        btnCertificado = new javax.swing.JButton();
        btnJuegos = new javax.swing.JButton();
        LblNombreProyecto = new javax.swing.JLabel();
        LblLogo = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();
        LblPanelSuperior = new javax.swing.JLabel();

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PanelVistas.setBackground(new java.awt.Color(255, 255, 255));
        PanelVistas.setLayout(new java.awt.BorderLayout());
        jPanel1.add(PanelVistas, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 110, 1190, 660));

        LblSaludo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        LblSaludo.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(LblSaludo, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 24, 380, 60));

        Lblimagen.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Users.png"))); // NOI18N
        jPanel1.add(Lblimagen, new org.netbeans.lib.awtextra.AbsoluteConstraints(1180, 10, 90, 90));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        lblNombre.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(lblNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 20, 130, 40));

        LblRol.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        LblRol.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.add(LblRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 60, 130, 30));

        btnDashboard.setBackground(new java.awt.Color(102, 102, 102));
        btnDashboard.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnDashboard.setForeground(new java.awt.Color(255, 255, 255));
        btnDashboard.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/menu.png"))); // NOI18N
        btnDashboard.setText("  Dashboard");
        btnDashboard.setBorder(null);
        btnDashboard.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnDashboard.setOpaque(true);
        btnDashboard.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDashboardActionPerformed(evt);
            }
        });
        jPanel1.add(btnDashboard, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 220, 160, 42));

        btnCuenta.setBackground(new java.awt.Color(102, 102, 102));
        btnCuenta.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCuenta.setForeground(new java.awt.Color(255, 255, 255));
        btnCuenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/icono-usuario.png"))); // NOI18N
        btnCuenta.setText("  Cuenta");
        btnCuenta.setBorder(null);
        btnCuenta.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCuenta.setOpaque(true);
        btnCuenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCuentaActionPerformed(evt);
            }
        });
        jPanel1.add(btnCuenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 280, 160, 42));

        btnCertificado.setBackground(new java.awt.Color(102, 102, 102));
        btnCertificado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnCertificado.setForeground(new java.awt.Color(255, 255, 255));
        btnCertificado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/certificado.png"))); // NOI18N
        btnCertificado.setText("  Certificado");
        btnCertificado.setBorder(null);
        btnCertificado.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnCertificado.setOpaque(true);
        jPanel1.add(btnCertificado, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 340, 160, 42));

        btnJuegos.setBackground(new java.awt.Color(102, 102, 102));
        btnJuegos.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnJuegos.setForeground(new java.awt.Color(255, 255, 255));
        btnJuegos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/play.png"))); // NOI18N
        btnJuegos.setText("  Interactivo");
        btnJuegos.setBorder(null);
        btnJuegos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnJuegos.setOpaque(true);
        btnJuegos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnJuegosActionPerformed(evt);
            }
        });
        jPanel1.add(btnJuegos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 400, 160, 42));

        LblNombreProyecto.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        LblNombreProyecto.setForeground(new java.awt.Color(255, 255, 255));
        LblNombreProyecto.setText("INTI");
        jPanel1.add(LblNombreProyecto, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 130, -1, -1));

        LblLogo.setForeground(new java.awt.Color(255, 255, 255));
        LblLogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo 90 x90.png"))); // NOI18N
        jPanel1.add(LblLogo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 40, -1, -1));

        btnSalir.setBackground(new java.awt.Color(102, 102, 102));
        btnSalir.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnSalir.setForeground(new java.awt.Color(255, 255, 255));
        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/cierre-de-sesión.png"))); // NOI18N
        btnSalir.setText("  Salir");
        btnSalir.setBorder(null);
        btnSalir.setOpaque(true);
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel1.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 710, 168, 42));

        LblPanelSuperior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/fondo login.png"))); // NOI18N
        jPanel1.add(LblPanelSuperior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1370, 770));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnJuegosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnJuegosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnJuegosActionPerformed

    private void btnCuentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCuentaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCuentaActionPerformed

    private void btnDashboardActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDashboardActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnDashboardActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSalirActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JLabel LblLogo;
    public javax.swing.JLabel LblNombreProyecto;
    public javax.swing.JLabel LblPanelSuperior;
    public javax.swing.JLabel LblRol;
    public javax.swing.JLabel LblSaludo;
    public javax.swing.JLabel Lblimagen;
    public javax.swing.JPanel PanelVistas;
    public javax.swing.JButton btnCertificado;
    public javax.swing.JButton btnCuenta;
    public javax.swing.JButton btnDashboard;
    public javax.swing.JButton btnJuegos;
    public javax.swing.JButton btnSalir;
    public javax.swing.JPanel jPanel1;
    public javax.swing.JLabel lblNombre;
    // End of variables declaration//GEN-END:variables
}
