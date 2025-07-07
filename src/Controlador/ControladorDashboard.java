package Controlador;

import Conexion.Conexion;
import Vista.*;
import Modelo.*;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class ControladorDashboard {

    private Dashboard vista;
    private Login loginFrame;

    private Vista_PanelUnidades panelUnidades;
    private Controlador_Unidades controladorUnidades;

    public ControladorDashboard(Dashboard vista, Login loginFrame) {
        this.vista = vista;
        this.loginFrame = loginFrame;

        agregarEventos();

        // Crear UNA vez el panel unidades y su controlador
        panelUnidades = new Vista_PanelUnidades();
        controladorUnidades = new Controlador_Unidades(panelUnidades, this, vista.getCorreoUsuario());

        // Mostrar panel unidades por defecto
        vista.mostrarVista(panelUnidades);

        cargarDatosUsuario(vista.getCorreoUsuario());
    }

    private void agregarEventos() {
        vista.btnJuegos.addActionListener((ActionEvent e) -> abrirVistaJuego());

        vista.btnSalir.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas salir?", "Cerrar sesión", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                loginFrame.mostrarPanelEnPanel1(loginFrame.getPanelLoginOriginal());
                loginFrame.limpiarCampos();
            }
        });
    }

    private void abrirVistaJuego() {
        VistaJuego va = new VistaJuego();
        Conexion conexion = new Conexion();
        Juego modeloJuego = new Juego(conexion.getConexion());
        ControladorJuego controlador = new ControladorJuego(va, modeloJuego);
        vista.mostrarVista(va);
    }

    private void cargarDatosUsuario(String correo) {
        System.out.println("Correo recibido: " + correo);
        String[] datos = Usuario.obtenerNombreYRol(correo);
        if (datos[0] != null && datos[1] != null) {
            vista.setNombreUsuario(datos[0]);
            vista.setRolUsuario(datos[1]);
            Saludo saludo = new Saludo("Bienvenido");
            ControladorSaludo controladorSaludo = new ControladorSaludo(vista, saludo);
            controladorSaludo.inicializarVista();
        }
    }

    // Getters para usar desde otros controladores
    public Vista_PanelUnidades getPanelUnidades() {
        return panelUnidades;
    }

    public Controlador_Unidades getControladorUnidades() {
        return controladorUnidades;
    }

    public Dashboard getVista() {
        return vista;
    }
}

