/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import View.*;
import com.mysql.cj.CoreSession;
import conexion.Cruplogin;
import java.awt.event.ActionEvent;
import java.time.Instant;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.text.html.CSS;
import javax.xml.crypto.Data;
import view.agendar1;
import view.clientes;
import view.consultarcita;
import view.medicamentoss;

public class control implements ActionListener {

    primera_inicio principal;

    clientes cliente = new clientes();
    agendar1 agendas = new agendar1();
    consultarcita consulta_cita = new consultarcita();
    medicamentoss medicamento = new medicamentoss();
    segunda_registro registro = new segunda_registro();
    Cruplogin login = new Cruplogin();

    private int idusuario = 0;
    private String usuario = null;
    private String contraseña = null;
    private String correo = null;

    //
    private int idcitas = 0;
    private String nombrem = null;
    private String fecha = null;
    private String especie;
    private String enfermedad;

    //
    private int ideliminar = 0;

    //
    private int idconsul = 0;

    public control(primera_inicio principal) {
        this.principal = principal;
        this.iniciar();
        this.cliente.agendarcitas.addActionListener(this);
        this.cliente.consultarcitas.addActionListener(this);

        this.principal.registrarse.addActionListener(this);
        this.registro.ya_tengo.addActionListener(this);
        this.principal.mostrar.addActionListener(this);
        this.cliente.medicamentos.addActionListener(this);
        this.cliente.volvercliente.addActionListener(this);
        this.principal.ingresar.addActionListener(this);
        this.registro.registrarse.addActionListener(this);
        this.agendas.guardarcitas.addActionListener(this);
        this.consulta_cita.mostracita.addActionListener(this);
        this.consulta_cita.cancelarcita.addActionListener(this);

    }

    public void iniciar() {
        this.principal.setVisible(true);
        this.principal.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        /**
         * *
         * son 3 botones botones de pprimera de registro estan todos listos
         */
        //tercero
        if (e.getSource() == this.principal.registrarse) {
            registro.setVisible(true);
            principal.dispose();

        }
        //segundo
        //boton que filtra el ingreso
        if (e.getSource() == this.principal.ingresar) {
            usuario = this.principal.usuario.getText();
            contraseña = this.principal.contraseña.getText();
            boolean respuesta = login.acceder_usuario(usuario, contraseña);
            if (respuesta == true) {
                cliente.setVisible(true);
                principal.dispose();
            } else {
                JOptionPane.showMessageDialog(null, "no esta registrado");
            }
            //tercero
            //boton que muestra los usuarios registrados
        } else if (principal.mostrar == e.getSource()) {
            boolean respuestas = login.mostrar_usuario(idusuario, correo, usuario, contraseña);
            if (respuestas == true) {
                JOptionPane.showMessageDialog(null, "si");
            } else {
                JOptionPane.showMessageDialog(null, "no");
            }
        }
        /**
         * son 2 botones botones de segunda de registro estan todos listo
         */
        // primero
        if (e.getSource() == this.registro.ya_tengo) {
            principal.setVisible(true);
            registro.dispose();

            //segundo boton
        } else if (e.getSource() == this.registro.registrarse) {
            idusuario = Integer.parseInt(this.registro.identificacion.getText());
            correo = this.registro.correo.getText();
            usuario = this.registro.usuario.getText();
            contraseña = this.registro.contrasena.getText();
            boolean regis = login.registrar_usuario(idusuario, correo, usuario, contraseña);
            if (regis) {
                JOptionPane.showMessageDialog(null, "registrado con exito");
            } else {
                JOptionPane.showMessageDialog(null, "se ha registrado");
            }
        }

        /**
         * SON 5 BOTONES Y ESTA INCOMPLETOS BOTONES DE CLIENTE1
         */
        // PRIMERO
        if (e.getSource() == this.cliente.agendarcitas) {
            this.cliente.tablacliente.add(agendas);
            agendas.show();
            // SEGUNDO
        } else if (this.cliente.consultarcitas == e.getSource()) {
            this.cliente.tablacliente.add(consulta_cita);
            consulta_cita.show();
            //TERCERO
        } else if (e.getSource() == this.cliente.medicamentos) {
            this.cliente.tablacliente.add(medicamento);
            medicamento.show();

          
        }if (e.getSource() == this.cliente.volvercliente) {
            principal.setVisible(true);
            cliente.dispose();
        }
          /**
             * *
             * 1 BOTON BOTON DE AGENDAR1 esta todo
             */
        if (e.getSource() == this.agendas.guardarcitas) {
            idcitas = Integer.parseInt(this.agendas.idcitas.getText());
            nombrem = this.agendas.nombreanimal.getText();
            especie = this.agendas.razaanimal.getText();
            fecha = this.agendas.fechadecita.getText();
            enfermedad = this.agendas.enfermadad.getText();
            login.Agendar_cita(idcitas, nombrem, especie, fecha, enfermedad);

        }
        /**
         * *
         * todos los botones listos 3 botones botones consultar citas
         */
        //primero
        if (e.getSource() == this.consulta_cita.mostracita) {
            idconsul = Integer.parseInt(this.consulta_cita.jTidcita.getText());
            boolean buscar = login.buscar_cita(idconsul);
            if (buscar == true) {
                JOptionPane.showMessageDialog(null, "si");
            } else {
                JOptionPane.showMessageDialog(null, "no");
            }

        }
        //segundo
        //eliminar citas
        if (e.getSource() == this.consulta_cita.cancelarcita) {
            ideliminar = Integer.parseInt(this.consulta_cita.jTidcita.getText());
            boolean eli = login.eliminar_citas(ideliminar);
            if (eli == true) {
                JOptionPane.showMessageDialog(null, "su cita a sido elimida");
            } else {
                JOptionPane.showMessageDialog(null, "no se pudo eliminar la cita");
            }

            /**
             * *
             *
             */
        }
        
        if (e.getSource()==this.medicamento.mostra) {
            
            
        }

    }

}
