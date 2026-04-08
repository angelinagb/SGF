package com.sgf;

import com.sgf.excepciones.DNIRepetidoException;
import com.sgf.excepciones.FilaVaciaException;
import javax.swing.SwingUtilities;

public class ControladorOperador {

    private VentanaPanelOperador vista;
    private ClienteOperador cliente;
    private int idPuesto;



    public ControladorOperador(VentanaPanelOperador vista, ClienteOperador cliente,int idPuesto) {
        this.vista = vista;
        this.cliente = cliente;
        this.idPuesto = idPuesto;
    } 

    public void accionarLlamado() {
        try {
            // Intentamos obtener el siguiente (Puede lanzar FilaVaciaException)
            Turno siguiente = cliente.llamarSiguiente(idPuesto);


            // actualizamos la vista 
            SwingUtilities.invokeLater(() -> {
            vista.actualizarVista(siguiente, cliente.getCola()); 
        });

    
        } catch (Exception e) {
            vista.mostrarMensaje("Error al procesar el llamado: " + e.getMessage());
        }
    }


    public void accionarReintento() {
        try {
            Turno reIntento = cliente.reintentarLlamado(idPuesto);
            SwingUtilities.invokeLater(() -> {
                vista.actualizarVista(reIntento, cliente.getCola());
            });
        } catch (Exception e) {
            vista.mostrarMensaje("Error al procesar el reintento: " + e.getMessage());
        }
    }
 

}
