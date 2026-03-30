package com.sgf;



public class ControladorRegistro {
    private VentanaTerminalRegistro vista;
    private ClienteSocket cliente;

    public ControladorRegistro(VentanaTerminalRegistro vista, ClienteSocket cliente) {
        this.vista = vista;
        this.cliente = cliente;
    }

    public void escribirNumero(String numero) {  // funcion que escribe el numero en el text field
        String dniActual = vista.getDNI();
        vista.setDNI(dniActual + numero);
    }

    public void borrarUltimo() {  // funcion boton borrar
        String texto = vista.getDNI().trim();

        if (texto.length() > 0) {
            vista.setDNI(texto.substring(0, texto.length() - 1));
        }
    }

    public boolean validarDNI(String dni) {  // se fija si el dni tiene una longitud valida

        if (dni.length() < 7 || dni.length() > 8) {
            return false;
        }

        return true; // no chequeo que sean numeros porque solo se pueden ingresar por pantalla
    }

    public void ingresarDNI() {
        String dni = vista.getDNI();
            if (validarDNI(dni)) {
                
                Turno t = new Turno(dni);
                cliente.enviarTurno(t); // Se envía al servidor

                vista.mostrarMensaje("¡Ingreso válido! DNI: " + vista.getDNI());
                vista.setDNI("");

            } else {
                vista.mostrarMensaje("Ingrese un DNI válido");
            }
        
    }

    public void limpiar() {
        vista.setDNI("");
    }
}
