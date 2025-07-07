package Controlador;

import Vista.*;
import Modelo.Modelo_Progreso_Usuario;
import Modelo.Usuario;
import java.sql.Connection;

public class Controlador_Actividades {

    private final javax.swing.JPanel vista;
    private final ControladorDashboard controladorDashboard;
    private final Connection conn;
    private final String cedula;
    private final int idActividad;

    public Controlador_Actividades(javax.swing.JPanel vista, ControladorDashboard controladorDashboard, Connection conn, String cedula, int idActividad) {
        this.vista = vista;
        this.controladorDashboard = controladorDashboard;
        this.conn = conn;
        this.cedula = cedula;
        this.idActividad = idActividad;

        agregarEventoCompletar();
    }

    private void agregarEventoCompletar() {
        int idUsuario = Usuario.obtenerIdPorCedula(cedula);

        if (vista instanceof Vista_Actividad1U1 act1) {
            act1.jButtonCOMPLETOACTV1.addActionListener(e -> completarActividad(idUsuario));
        } else if (vista instanceof Vista_Actividad2U1 act2) {
            act2.jButtonCOMPLETOACTV2.addActionListener(e -> completarActividad(idUsuario));
        }
    }

    private void completarActividad(int idUsuario) {
        Modelo_Progreso_Usuario progreso = ControladorProgresoUsuario.obtenerProgreso(idUsuario, 1);
        boolean actualizado = ControladorProgresoUsuario.actualizarActividad(progreso, idActividad);
        if (actualizado) {
            System.out.println("Actividad " + idActividad + " completada");
        }

        Vista_Unidad1 vistaUnidad1 = new Vista_Unidad1();
      new Controlador_Unidad1(vistaUnidad1, conn, controladorDashboard, cedula); // ✅ CORRECTO
        controladorDashboard.getVista().mostrarVista(vistaUnidad1);
    }
}

