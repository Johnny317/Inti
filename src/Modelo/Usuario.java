package Modelo;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Usuario {

    // Constructor si necesitas inyectar conexión (no lo estás usando en métodos static)
    private Connection conn;

    public Usuario(Connection conn) {
        this.conn = conn;
    }

    // Verifica si el usuario ya tiene contraseña establecida
    public static boolean tieneContrasena(String email) {
        String sql = "SELECT contrasena FROM usuarios WHERE correo = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String pass = rs.getString("contrasena");
                    return pass != null && !pass.trim().isEmpty();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Actualizar contraseña o registrar nuevo usuario
    public static boolean actualizarContrasena(String email, String nuevaContrasena, String nombre) {
        String sqlUpdate = "UPDATE usuarios SET contrasena = ?, nombre =? WHERE correo = ?";
        String sqlInsert = "INSERT INTO usuarios (nombre, correo, contrasena) VALUES (?, ?, ?)";

        try (Connection conn = Conexion.conectar()) {
            PreparedStatement psUpdate = conn.prepareStatement(sqlUpdate);
            psUpdate.setString(1, nuevaContrasena);
            psUpdate.setString(2, nombre);
            psUpdate.setString(3, email);
            int filasAfectadas = psUpdate.executeUpdate();

            if (filasAfectadas > 0) {
                return true; // Actualizado
            }

            // Insertar si no existe
            PreparedStatement psInsert = conn.prepareStatement(sqlInsert);
            psInsert.setString(1, nombre);
            psInsert.setString(2, email);
            psInsert.setString(3, nuevaContrasena);
            return psInsert.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Verificar si existe un usuario
    public static boolean existeUsuario(String email) {
        String sql = "SELECT COUNT(*) FROM usuarios WHERE correo = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Verificar contraseña ingresada
    public static boolean verificarContrasena(String email, String contrasenaIngresada) {
        String sql = "SELECT contrasena FROM usuarios WHERE correo = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, email);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    String contrasenaGuardada = rs.getString("contrasena");
                    return contrasenaGuardada != null && contrasenaGuardada.equals(contrasenaIngresada);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Almacenamiento temporal en memoria de imagenes por usuario
    private static java.util.Map<String, String> imagenesUsuarios = new HashMap<>();

    public static void guardarRutaImagen(String correo, String rutaImagen) {
        imagenesUsuarios.put(correo.toLowerCase(), rutaImagen);
    }

    public static String obtenerRutaImagen(String correo) {
        return imagenesUsuarios.getOrDefault(correo.toLowerCase(), "src/Imagenes/Users.png");
    }

    // Actualiza imagen en la base de datos
    public static boolean actualizarImagenUsuario(String correo, String ruta) {
        try (Connection conn = Conexion.conectar()) {
            String sql = "UPDATE usuarios SET ruta_imagen = ? WHERE correo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, ruta);
            stmt.setString(2, correo);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Obtiene ruta de imagen desde la base
    public static String obtenerImagenUsuario(String correo) {
        try (Connection conn = Conexion.conectar()) {
            String sql = "SELECT ruta_imagen FROM usuarios WHERE correo = ?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, correo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("ruta_imagen");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Obtiene el correo a partir del nombre de usuario
    public static String obtenerCorreoDesdeUsuario(String usuarioOCorreo) {
        String sql = "SELECT correo FROM usuarios WHERE usuario = ?";
        try (Connection conn = Conexion.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, usuarioOCorreo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("correo");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static boolean estaRegistrado(String correo) {
    boolean existe = false;
    try {
        Connection conn = Conexion.conectar();
        PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM usuarios WHERE correo = ?");
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();
        existe = rs.next();
        rs.close();
        ps.close();
        conn.close();
    } catch (Exception e) {
        e.printStackTrace();
    }
    return existe;
}
public static boolean verificarCodigo(String correo, String codigoIngresado) {
    String sql = "SELECT codigo FROM usuarios WHERE correo = ?";
    try (Connection conn = Conexion.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, correo);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                String codigoGuardado = rs.getString("codigo");
                return codigoGuardado != null && codigoGuardado.equals(codigoIngresado);
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return false;
}
public static String[] obtenerNombreYRol(String correo) {
    String[] datos = new String[2];
    Connection conn = new Conexion().getConexion();

    try {
        String sql = "SELECT u.nombre, r.nombre_rol " +
                     "FROM usuarios u " +
                     "JOIN roles r ON u.id_rol = r.id_rol " +
                     "WHERE u.correo = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1, correo);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            datos[0] = rs.getString("nombre");
            datos[1] = rs.getString("nombre_rol");
            System.out.println("Nombre: " + datos[0]);
            System.out.println("Rol: " + datos[1]);
        } else {
            System.out.println("No se encontraron resultados para el correo.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }

    return datos;
}
public static int obtenerIdPorCedula(String cedula) {
        String sql = "SELECT id_usuario FROM usuarios WHERE cedula = ?";
        try (Connection conn = Conexion.conectar(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // No encontrado
    }
public static String obtenerCedulaPorCorreo(String correo) {
    String sql = "SELECT cedula FROM usuarios WHERE correo = ?";
    try (Connection conn = Conexion.conectar();
         PreparedStatement ps = conn.prepareStatement(sql)) {
        ps.setString(1, correo);
        try (ResultSet rs = ps.executeQuery()) {
            if (rs.next()) {
                return rs.getString("cedula");
            }
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return null;
}

}
