/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package conexion;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author APRENDIZ SENA
 */
public class Cruplogin extends conecion implements Interfaz {
    /*
    EL QUE MUESTRA DE LA TABLA PRIMERA_INICIO
    */
    @Override
    public boolean mostrar_usuario(int id, String correo, String usuario, String contraseña) {
        ResultSet rs = null;
        Connection con = getConexion();
        CallableStatement ps = null;
        String sql = " select *from usuarios  ";
        boolean responder = false;

        try {
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            responder = rs.next();
            if (responder) {
                do {
                    System.out.println("id: " + rs.getInt(1));
                    System.out.println("correo: " + rs.getString(2));
                    System.out.println("usuario: " + rs.getString(3));
                    System.out.println("contraseña: " + rs.getString(4));

                } while (rs.next());
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {

                System.out.println(e.toString());
            }
        }
    }
     /*
    EL QUE VALIDA EL INGRESO DE LA PRIMERA DE INICIO
    */

    @Override
    public boolean acceder_usuario(String usuario, String contraseña) {
        ResultSet rs = null;
        Connection con = getConexion();
        CallableStatement ps = null;
        String sql = "{call validar_ingreso(?,?)} ";
        boolean responder = false;

        try {
            ps = con.prepareCall(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs = ps.executeQuery();
            responder = rs.next();
            if (responder) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {

                System.out.println(e.toString());
            }
        }
    }

    /*
    EL QUE REGISTRA DE LA SEGUNDA_REGISTRO
    */
    @Override
    public boolean registrar_usuario(int id, String correo, String usuario, String contraseña) {
        Connection con = getConexion();
        CallableStatement cst = null;
        String sql = "{call insertar_usuarios(?,?,?,?)} ";
        try {
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.setString(2, correo);
            cst.setString(3, usuario);
            cst.setString(4, contraseña);
            cst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);

            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        }
    }
    /*
    EL QUE INSERTA O GUARDA EN "AGENDAR1"
    */
    @Override
    public boolean Agendar_cita(int id, String nombrem, String especie, String fecha, String enfermedad) {
        Connection con = getConexion();
        CallableStatement cst = null;
        String sql = "{call INSERTAR_AGENDAR(?,?,?,?,?)} ";
        try {
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.setString(2, nombrem);
            cst.setString(3, especie);
            cst.setString(4, enfermedad);
            cst.setString(5, fecha);
            cst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);

            
            
            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;

            }
        }
    }
   
    @Override
    public boolean eliminar_citas(int id) {
        Connection con = getConexion();
        CallableStatement cst = null;
        String sql = "{call  ELIMINAR_AGENDAR (?)} ";
        try {
            cst = con.prepareCall(sql);
            cst.setInt(1, id);
            cst.execute();
            return true;
        } catch (SQLException e) {
            System.err.println(e);

            return false;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;

            }
            
        }

    }
/***
 * buscar citas de la tabla consultar
 * @param id
 * @return 
 */
    @Override
    public boolean buscar_cita(int id) {
      ResultSet rs = null;
        Connection con = getConexion();
        CallableStatement ps = null;
        String sql = " {call MOSTRA_CITA(?)}  ";
        boolean responder = false;

        try {
            ps = con.prepareCall(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            responder = rs.next();
            if (responder) {
                do {
                    System.out.println(": "+rs.getInt(1));
                    System.out.println(": " + rs.getString(2));
                    System.out.println(": " + rs.getString(3));
                    System.out.println(": " + rs.getString(4));
                    System.out.println(": " + rs.getString(5));

                } while (rs.next());
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            System.out.println(e);
            return false;

        } finally {
            try {
                con.close();
            } catch (SQLException e) {

                System.out.println(e.toString());

            }
            
        }
    }
      

}
