package Controlador;

import Modelo.Saludo;
import Vista.Dashboard;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControladorSaludo {

    private Dashboard vista;
    private Saludo modelo;
    private boolean mostrandoBienvenido = true;
    private Timer timer;

    public ControladorSaludo(Dashboard vista, Saludo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    private void iniciarTimerSaludo() {
        timer = new Timer(10000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (mostrandoBienvenido) {
                    modelo.setMensaje("Bienvenido");
                    mostrandoBienvenido = false;
                } else {
                    modelo.setMensaje("Alli Shamushka");
                    mostrandoBienvenido = true;
                }

                // Concatenar el nombre solo una vez, en el mensaje final
                String mensajeCompleto = modelo.getMensaje() + " " + vista.getLblNombre().getText();
                vista.actualizarSaludo(mensajeCompleto);
            }
        });
        timer.start();
    }

    public void inicializarVista() {
        // Mostrar el primer saludo
        String mensajeInicial = modelo.getMensaje() + " " + vista.getLblNombre().getText();
        vista.actualizarSaludo(mensajeInicial);
        iniciarTimerSaludo();
    }
}
