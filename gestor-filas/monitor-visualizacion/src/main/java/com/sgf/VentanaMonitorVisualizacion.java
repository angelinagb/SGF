package com.sgf;

import java.awt.Font;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

public class VentanaMonitorVisualizacion extends JFrame {

    private JPanel contentPane;
    private JLabel lblTurnoActual;
    private DefaultListModel<String> proximosModel;

    public VentanaMonitorVisualizacion() {
        setTitle("Monitor de Sala");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 600, 450);
        
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblTitulo = new JLabel("LLAMANDO A:");
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 24));
        lblTitulo.setBounds(10, 20, 564, 30);
        contentPane.add(lblTitulo);

        lblTurnoActual = new JLabel("---");
        lblTurnoActual.setHorizontalAlignment(SwingConstants.CENTER);
        lblTurnoActual.setFont(new Font("Tahoma", Font.BOLD, 60));
        lblTurnoActual.setBounds(10, 60, 564, 80);
        contentPane.add(lblTurnoActual);

        // Cambiamos el texto para que diga Próximos
        JLabel lblUltimos = new JLabel("Próximos a ser atendidos:");
        lblUltimos.setFont(new Font("Tahoma", Font.PLAIN, 18));
        lblUltimos.setBounds(20, 180, 250, 25);
        contentPane.add(lblUltimos);

        proximosModel = new DefaultListModel<>();
        JList<String> listProximos = new JList<>(proximosModel);
        listProximos.setFont(new Font("Tahoma", Font.PLAIN, 24));
        listProximos.setBounds(20, 210, 540, 150);
        listProximos.setEnabled(false);
        contentPane.add(listProximos);

        iniciarServidorMonitor();
    }

    private void iniciarServidorMonitor() {
        new Thread(new Runnable() {
            public void run() {
                try (ServerSocket serverSocket = new ServerSocket(5001)) {
                    while (true) {
                        Socket socket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        
                        // Recibe el texto con comas, ej: "1234,111,222,333"
                        String datosRecibidos = in.readLine(); 
                        
                        if (datosRecibidos != null) {
                            actualizarPantalla(datosRecibidos);
                        }
                        socket.close();
                    }
                } catch (Exception e) {
                    System.out.println("Error en el monitor: " + e.getMessage());
                }
            }
        }).start();
    }

    private void actualizarPantalla(String datosRecibidos) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Separamos el texto por las comas
                String[] DNIs = datosRecibidos.split(",");
                
                // El primer DNI del array es el que se está llamando ahora
                lblTurnoActual.setText(DNIs[0]);
                
                // Limpiamos la lista y agregamos los que siguen (si es que hay)
                proximosModel.clear();
                for (int i = 1; i < DNIs.length; i++) {
                    proximosModel.addElement("En espera: " + DNIs[i]);
                }
            }
        });
    }

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    VentanaMonitorVisualizacion frame = new VentanaMonitorVisualizacion();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}