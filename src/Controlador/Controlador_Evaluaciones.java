package Controlador;

import Vista.*;
import Modelo.Modelo_Progreso_Usuario;
import Modelo.Usuario;
import java.sql.Connection;

public class Controlador_Evaluaciones {

    private final Vista_EvaluacionU1 vista;
    private final ControladorDashboard controladorDashboard;
    private final Connection conn;
    private final String cedula;
    private final int idUnidad;

    public Controlador_Evaluaciones(Vista_EvaluacionU1 vista, ControladorDashboard controladorDashboard, Connection conn, String cedula, int idUnidad) {
        this.vista = vista;
        this.controladorDashboard = controladorDashboard;
        this.conn = conn;
        this.cedula = cedula;
        this.idUnidad = idUnidad;

        agregarEventoFinalizar();
    }

    private void agregarEventoFinalizar() {
        vista.jButtonCOMPLETOEVALUACION1.addActionListener(e -> {
            int idUsuario = Usuario.obtenerIdPorCedula(cedula);
            Modelo_Progreso_Usuario progreso = ControladorProgresoUsuario.obtenerProgreso(idUsuario, idUnidad);
            boolean actualizado = ControladorProgresoUsuario.aprobarEvaluacion(progreso, 100);
            if (actualizado) {
                System.out.println("Evaluación aprobada");
            }

            Vista_Unidad1 vistaUnidad1 = new Vista_Unidad1();
            new Controlador_Unidad1(vistaUnidad1, conn, controladorDashboard, cedula);
            controladorDashboard.getVista().mostrarVista(vistaUnidad1);
        });
    }
}



