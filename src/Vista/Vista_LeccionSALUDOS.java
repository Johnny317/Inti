
package Vista;
import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.concurrent.Worker;
import javax.swing.*;
import java.awt.*;
import java.io.File;

public class Vista_LeccionSALUDOS extends JPanel {
    private JFXPanel jfxPanel;
    private WebView webView;
    private WebEngine webEngine;
    private boolean isWebViewInitialized = false;
    private String htmlFilePath = "C:\\Users\\Jhony Espinoza\\OneDrive\\Documentos\\HTML_PRACTICAS\\Pag_Fruta_Fav.html";
    
    // AGREGADO: Variable para controlar si es la primera vez que se muestra
    private boolean isFirstShow = true;
    
    public Vista_LeccionSALUDOS() {
        initComponents();
        setupWebView();
    }
    
    private void setupWebView() {
        this.setLayout(new BorderLayout());
        
        // Panel para el botón
        JPanel panelBoton = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelBoton.add(jButtonCOMPLETOSALUDOS);
        panelBoton.setPreferredSize(new Dimension(1190, 50));
        
        // Panel para el WebView
        JPanel panelWeb = new JPanel(new BorderLayout());
        panelWeb.setPreferredSize(new Dimension(1190, 610));
        
        this.add(panelBoton, BorderLayout.NORTH);
        this.add(panelWeb, BorderLayout.CENTER);
        
        // Inicializar JFXPanel
        jfxPanel = new JFXPanel();
        jfxPanel.setPreferredSize(new Dimension(1190, 610));
        panelWeb.add(jfxPanel, BorderLayout.CENTER);
        
        // AGREGADO: Inicializar JavaFX Platform si no está iniciado
        if (!Platform.isFxApplicationThread()) {
            Platform.runLater(() -> {
                // Simplemente inicializar la plataforma
                System.out.println("JavaFX Platform inicializado");
            });
        }
    }
    
    private void inicializarWebView() {
        System.out.println("Inicializando WebView...");
        
        Platform.runLater(() -> {
            crearWebView();
        });
    }
    
    private void crearWebView() {
        try {
            System.out.println("Creando WebView en hilo JavaFX...");
            
            // CORREGIDO: Siempre crear nuevo WebView
            webView = new WebView();
            webEngine = webView.getEngine();
            
            // Configurar el WebEngine
            webEngine.setJavaScriptEnabled(true);
            
            // CORREGIDO: Configurar propiedades adicionales del WebEngine
            webEngine.setUserAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36");
            
            // Listener para detectar cuando se carga la página
            webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
                System.out.println("Estado de carga del WebView: " + newState);
                if (newState == Worker.State.SUCCEEDED) {
                    System.out.println("HTML cargado exitosamente");
                } else if (newState == Worker.State.FAILED) {
                    System.out.println("Error al cargar HTML");
                    // AGREGADO: Reintento en caso de fallo
                    Platform.runLater(() -> {
                        Timer retryTimer = new Timer(1000, e -> {
                            System.out.println("Reintentando cargar HTML...");
                            cargarHTML();
                            ((Timer) e.getSource()).stop();
                        });
                        retryTimer.setRepeats(false);
                        retryTimer.start();
                    });
                }
            });
            
            // CORREGIDO: Crear nueva Scene siempre
            Scene scene = new Scene(webView);
            jfxPanel.setScene(scene);
            
            // AGREGADO: Esperar un poco antes de cargar el HTML
            Timer loadTimer = new Timer(300, e -> {
                Platform.runLater(() -> {
                    cargarHTML();
                });
                ((Timer) e.getSource()).stop();
            });
            loadTimer.setRepeats(false);
            loadTimer.start();
            
            isWebViewInitialized = true;
            System.out.println("WebView creado correctamente");
            
        } catch (Exception e) {
            System.out.println("Error al crear WebView: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // NUEVO: Método separado para cargar el HTML
    private void cargarHTML() {
        try {
            File htmlFile = new File(htmlFilePath);
            if (htmlFile.exists()) {
                String url = htmlFile.toURI().toURL().toString();
                System.out.println("Cargando HTML desde: " + url);
                webEngine.load(url);
            } else {
                System.out.println("ERROR: Archivo HTML no encontrado: " + htmlFilePath);
                // AGREGADO: Cargar contenido alternativo en caso de error
                webEngine.loadContent("<html><body><h1>Error: Archivo HTML no encontrado</h1><p>Ruta: " + htmlFilePath + "</p></body></html>");
            }
        } catch (Exception e) {
            System.out.println("Error al cargar HTML: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // CORREGIDO: Método para limpiar completamente el WebView
    public void limpiarWebView() {
        System.out.println("Limpiando WebView...");
        
        Platform.runLater(() -> {
            realizarLimpieza();
        });
    }
    
    private void realizarLimpieza() {
        try {
            if (webEngine != null) {
                webEngine.load("about:blank");
            }
            
            if (jfxPanel != null) {
                jfxPanel.setScene(null);
            }
            
            webView = null;
            webEngine = null;
            isWebViewInitialized = false;
            System.out.println("WebView limpiado correctamente");
        } catch (Exception e) {
            System.out.println("Error al limpiar WebView: " + e.getMessage());
        }
    }
    
    // CORREGIDO: Método para recargar el WebView
    public void recargarHTML() {
        System.out.println("Recargando HTML...");
        
        // CORREGIDO: Limpiar y reinicializar inmediatamente
        Platform.runLater(() -> {
            realizarLimpieza();
            
            // AGREGADO: Esperar un poco más antes de recrear
            Timer timer = new Timer(800, e -> {
                Platform.runLater(() -> {
                    crearWebView();
                });
                ((Timer) e.getSource()).stop();
            });
            timer.setRepeats(false);
            timer.start();
        });
    }
    
    // CORREGIDO: Método que se llama cuando la vista se hace visible
    @Override
    public void setVisible(boolean visible) {
        super.setVisible(visible);
        
        if (visible) {
            System.out.println("Vista_LeccionSALUDOS se está mostrando...");
            
            if (isFirstShow) {
                // Primera vez que se muestra
                System.out.println("Primera vez mostrando la vista, inicializando WebView...");
                isFirstShow = false;
                
                Timer timer = new Timer(500, e -> {
                    inicializarWebView();
                    ((Timer) e.getSource()).stop();
                });
                timer.setRepeats(false);
                timer.start();
                
            } else {
                // Reapertura de la vista
                System.out.println("Reabriendo vista, recargando WebView...");
                recargarHTML();
            }
        } else {
            System.out.println("Vista_LeccionSALUDOS se está ocultando...");
        }
    }
    
    // NUEVO: Método para forzar reinicialización completa
    public void forzarReinicializacion() {
        System.out.println("Forzando reinicialización completa...");
        isFirstShow = true;
        isWebViewInitialized = false;
        
        Platform.runLater(() -> {
            realizarLimpieza();
            
            SwingUtilities.invokeLater(() -> {
                Timer timer = new Timer(1000, e -> {
                    inicializarWebView();
                    ((Timer) e.getSource()).stop();
                });
                timer.setRepeats(false);
                timer.start();
            });
        });
    }
    
    // NUEVO: Método para verificar si el WebView está funcionando
    public boolean isWebViewWorking() {
        return isWebViewInitialized && webView != null && webEngine != null;
    }
    
    // AGREGADO: Método para obtener el estado del WebView
    public String getWebViewStatus() {
        if (!isWebViewInitialized) {
            return "No inicializado";
        }
        
        if (webView == null || webEngine == null) {
            return "WebView nulo";
        }
        
        if (webEngine.getLoadWorker() != null) {
            return "Estado: " + webEngine.getLoadWorker().getState();
        }
        
        return "Inicializado";
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButtonCOMPLETOSALUDOS = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(1190, 660));
        setMinimumSize(new java.awt.Dimension(1190, 660));
        setPreferredSize(new java.awt.Dimension(1190, 660));

        jButtonCOMPLETOSALUDOS.setText("SIGUIENTE");
        add(jButtonCOMPLETOSALUDOS);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton jButtonCOMPLETOSALUDOS;
    // End of variables declaration//GEN-END:variables
}
