package com.sgf;

import com.sgf.excepciones.DniInvalidoException;

public class ControladorRegistro {
    private VentanaTerminalRegistro vista;
    private ClienteSocket cliente;

    public ControladorRegistro(VentanaTerminalRegistro vista, ClienteSocket cliente) {
        this.vista = vista;
        this.cliente = cliente;
    }

    public void escribirNumero(String numero) {  // funcion que escribe el numero en el text field
        String dniActual = vista.getDNI();

        // limitamos a 8 dígitos para evitar ingresos excesivos
        if (dniActual.length() < 8) {
            vista.setDNI(dniActual + numero);
        }
    }

    public void borrarUltimo() {  // funcion boton borrar
        String texto = vista.getDNI().trim();

        if (texto.length() > 0) {
            vista.setDNI(texto.substring(0, texto.length() - 1));
        }
    }

    private void validarDNI(String dni) throws DniInvalidoException {
        if (dni.length() < 7 || dni.length() > 8) {
            throw new DniInvalidoException(dni);
        }
    }


    public void ingresarDNI() {
        String dni = vista.getDNI();
            try {
            validarDNI(dni);
            
            Turno t = new Turno(dni); //si validó, creo el turno y lo envío al servidor
            cliente.enviarTurno(t); 

            vista.mostrarMensaje("¡Turno Registrado!\nDocumento: " + dni);
            vista.setDNI("");

        } catch (DniInvalidoException e) {
            vista.mostrarMensaje(e.getMessage());
        } catch (Exception e) {
            vista.mostrarMensaje("Error de conexión: No se pudo enviar el turno.");
        }
        
    }

    public void limpiar() {
        vista.setDNI("");
    }
}
