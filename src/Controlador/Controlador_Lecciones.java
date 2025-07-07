package Controlador;

import Modelo.Usuario;
import Modelo.Modelo_Progreso_Usuario;
import Vista.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.time.LocalDateTime;

/**
 * Controlador mejorado para manejar todas las lecciones
 */
public class Controlador_Lecciones {
    
    private final JPanel vistaLeccion;
    private final ControladorDashboard controladorDashboard;
    private final Connection conn;
    private final String cedula;
    private final int numeroLeccion;
    private final int idUsuario;
    private final int ID_UNIDAD = 1;
    
    // Tipos de lecciones
    public static final int LECCION_SALUDOS = 1;
    public static final int LECCION_FONOLOGIA = 2;
    public static final int LECCION_PRONOMBRES = 3;
    
    public Controlador_Lecciones(JPanel vistaLeccion, ControladorDashboard controladorDashboard, 
                                         Connection conn, String cedula, int numeroLeccion) {
        this.vistaLeccion = vistaLeccion;
        this.controladorDashboard = controladorDashboard;
        this.conn = conn;
        this.cedula = cedula;
        this.numeroLeccion = numeroLeccion;
        this.idUsuario = Usuario.obtenerIdPorCedula(cedula);
        
        configurarVistaLeccion();
        agregarListeners();
    }
    
    private void configurarVistaLeccion() {
        System.out.println("Configurando vista de lección: " + numeroLeccion);
        
        // CORREGIDO: Configurar vista después de que sea visible
        SwingUtilities.invokeLater(() -> {
            if (vistaLeccion instanceof Vista_LeccionSALUDOS) {
                Vista_LeccionSALUDOS vistaSaludos = (Vista_LeccionSALUDOS) vistaLeccion;
                
                // AGREGADO: Esperar un poco antes de configurar el WebView
                Timer timer = new Timer(300, e -> {
                    System.out.println("Configurando WebView para lección de saludos...");
                    
                    // CORREGIDO: Verificar si el WebView está funcionando
                    if (!vistaSaludos.isWebViewWorking()) {
                        System.out.println("WebView no está funcionando, forzando reinicialización...");
                        vistaSaludos.forzarReinicializacion();
                    }
                    
                    ((Timer) e.getSource()).stop();
                });
                timer.setRepeats(false);
                timer.start();
            }
        });
        
        // Configurar otros tipos de lecciones según sea necesario
        configurarSegunTipoLeccion();
    }
    
    private void configurarSegunTipoLeccion() {
        switch (numeroLeccion) {
            case LECCION_SALUDOS:
                configurarLeccionSaludos();
                break;
            case LECCION_FONOLOGIA:
                configurarLeccionFonologia();
                break;
            case LECCION_PRONOMBRES:
                configurarLeccionPronombres();
                break;
        }
    }
    
    private void configurarLeccionSaludos() {
        // Configuración específica para lección de saludos
        if (vistaLeccion instanceof Vista_LeccionSALUDOS) {
            Vista_LeccionSALUDOS vista = (Vista_LeccionSALUDOS) vistaLeccion;
            SwingUtilities.invokeLater(() -> {
                vista.jButtonCOMPLETOSALUDOS.setText("COMPLETAR LECCIÓN DE SALUDOS");
                
                // AGREGADO: Mostrar estado del WebView en el botón temporalmente
                Timer statusTimer = new Timer(1000, e -> {
                    String status = vista.getWebViewStatus();
                    System.out.println("Estado del WebView: " + status);
                    ((Timer) e.getSource()).stop();
                });
                statusTimer.setRepeats(false);
                statusTimer.start();
            });
        }
    }
    
    private void configurarLeccionFonologia() {
        // Configuración específica para lección de fonología
        if (vistaLeccion instanceof Vista_LeccionFONOLOGIA) {
            Vista_LeccionFONOLOGIA vista = (Vista_LeccionFONOLOGIA) vistaLeccion;
            vista.jButtonCOMPLETOFONOLOGIA.setText("COMPLETAR LECCIÓN DE FONOLOGÍA");
        }
    }
    
    private void configurarLeccionPronombres() {
        // Configuración específica para lección de pronombres
        if (vistaLeccion instanceof Vista_LeccionPRONOMBRES) {
            Vista_LeccionPRONOMBRES vista = (Vista_LeccionPRONOMBRES) vistaLeccion;
            vista.jButtonCOMPLETOPRONOMBRES.setText("COMPLETAR LECCIÓN DE PRONOMBRES");
        }
    }
    
    private void agregarListeners() {
        // Agregar listeners según el tipo de vista
        if (vistaLeccion instanceof Vista_LeccionSALUDOS) {
            Vista_LeccionSALUDOS vista = (Vista_LeccionSALUDOS) vistaLeccion;
            vista.jButtonCOMPLETOSALUDOS.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    completarLeccion();
                }
            });
            
            // AGREGADO: Botón para reinicializar WebView (temporal para debugging)
            if (vista.getComponentCount() > 0) {
                JPanel panelBoton = (JPanel) vista.getComponent(0);
                JButton btnReiniciar = new JButton("Reiniciar WebView");
                btnReiniciar.addActionListener(e -> {
                    System.out.println("Reiniciando WebView manualmente...");
                    vista.forzarReinicializacion();
                });
                panelBoton.add(btnReiniciar);
            }
            
        } else if (vistaLeccion instanceof Vista_LeccionFONOLOGIA) {
            Vista_LeccionFONOLOGIA vista = (Vista_LeccionFONOLOGIA) vistaLeccion;
            vista.jButtonCOMPLETOFONOLOGIA.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    completarLeccion();
                }
            });
        } else if (vistaLeccion instanceof Vista_LeccionPRONOMBRES) {
            Vista_LeccionPRONOMBRES vista = (Vista_LeccionPRONOMBRES) vistaLeccion;
            vista.jButtonCOMPLETOPRONOMBRES.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    completarLeccion();
                }
            });
        }
    }
    
    private void completarLeccion() {
        try {
            // Obtener o crear progreso para el usuario
            Modelo_Progreso_Usuario progreso = Modelo_Progreso_Usuario.obtenerProgreso(idUsuario, ID_UNIDAD);
            
            if (progreso == null) {
                // Crear nuevo progreso si no existe
                progreso = new Modelo_Progreso_Usuario(
                    0, idUsuario, ID_UNIDAD, 0, 0, false, 0, LocalDateTime.now()
                );
                
                // Guardar el progreso inicial
                if (!Modelo_Progreso_Usuario.guardarProgreso(progreso)) {
                    JOptionPane.showMessageDialog(
                        vistaLeccion,
                        "Error al crear el progreso del usuario",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }
                
                // Obtener el progreso recién creado
                progreso = Modelo_Progreso_Usuario.obtenerProgreso(idUsuario, ID_UNIDAD);
            }
            
            // Verificar si esta lección ya fue completada
            if (progreso.getLeccionesCompletadas() >= numeroLeccion) {
                int respuesta = JOptionPane.showConfirmDialog(
                    vistaLeccion,
                    "Ya has completado esta lección. ¿Quieres marcarla como completada nuevamente?",
                    "Lección ya completada",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE
                );
                
                if (respuesta != JOptionPane.YES_OPTION) {
                    return;
                }
            }
            
            // Actualizar progreso de lecciones
            if (progreso.getLeccionesCompletadas() < numeroLeccion) {
                progreso.setLeccionesCompletadas(numeroLeccion);
                progreso.setFechaActualizacion(LocalDateTime.now());
                
                if (Modelo_Progreso_Usuario.actualizarProgreso(progreso)) {
                    mostrarMensajeExito();
                    actualizarInterfaz();
                } else {
                    JOptionPane.showMessageDialog(
                        vistaLeccion,
                        "Error al actualizar el progreso",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                    );
                }
            } else {
                // La lección ya estaba completada, pero el usuario quiso marcarla nuevamente
                progreso.setFechaActualizacion(LocalDateTime.now());
                Modelo_Progreso_Usuario.actualizarProgreso(progreso);
                mostrarMensajeExito();
                actualizarInterfaz();
            }
            
        } catch (Exception e) {
            System.err.println("Error al completar lección: " + e.getMessage());
            e.printStackTrace();
            JOptionPane.showMessageDialog(
                vistaLeccion,
                "Error inesperado al completar la lección: " + e.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE
            );
        }
    }
    
    private void mostrarMensajeExito() {
        String nombreLeccion = obtenerNombreLeccion();
        JOptionPane.showMessageDialog(
            vistaLeccion,
            "¡Felicitaciones! Has completado la " + nombreLeccion + " exitosamente.",
            "Lección Completada",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
    
    private String obtenerNombreLeccion() {
        switch (numeroLeccion) {
            case LECCION_SALUDOS:
                return "Lección de Saludos";
            case LECCION_FONOLOGIA:
                return "Lección de Fonología";
            case LECCION_PRONOMBRES:
                return "Lección de Pronombres";
            default:
                return "Lección " + numeroLeccion;
        }
    }
    
    private void actualizarInterfaz() {
        // Regresar a la vista de la unidad para mostrar el progreso actualizado
        Vista_Unidad1 vistaUnidad1 = new Vista_Unidad1();
        new Controlador_Unidad1(vistaUnidad1, conn, controladorDashboard, cedula);
        controladorDashboard.getVista().mostrarVista(vistaUnidad1);
    }
    
    // Método para obtener estadísticas de progreso
    public String obtenerEstadisticasProgreso() {
        Modelo_Progreso_Usuario progreso = Modelo_Progreso_Usuario.obtenerProgreso(idUsuario, ID_UNIDAD);
        if (progreso == null) {
            return "No hay progreso registrado";
        }
        
        int totalLecciones = 3; // Saludos, Fonología, Pronombres
        int totalActividades = 2; // Ajusta según tu unidad
        
        double porcentajeLecciones = (double) progreso.getLeccionesCompletadas() / totalLecciones * 100;
        double porcentajeActividades = (double) progreso.getActividadesCompletadas() / totalActividades * 100;
        
        return String.format(
            "Lecciones: %d/%d (%.1f%%) | Actividades: %d/%d (%.1f%%) | Evaluación: %s | Calificación: %d",
            progreso.getLeccionesCompletadas(), totalLecciones, porcentajeLecciones,
            progreso.getActividadesCompletadas(), totalActividades, porcentajeActividades,
            progreso.isEvaluacionAprobada() ? "Aprobada" : "Pendiente",
            progreso.getCalificacion()
        );
    }
}
