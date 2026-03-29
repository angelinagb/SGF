package com.sgf;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPanelOperador extends JFrame {

    private JPanel contentPane;

    public VentanaPanelOperador() {
        // Título y configuración básica de la ventana
        setTitle("Terminal de Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 400, 300);
        
        // Panel principal donde vas a arrastrar los botones
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        
        // El layout en 'null' o 'Absolute' es vital para poder arrastrar y soltar libremente
        contentPane.setLayout(null);
        
        JButton btnLlamar = new JButton("Llamar");
        btnLlamar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnLlamar.setBounds(154, 102, 88, 22);
        contentPane.add(btnLlamar);
    }
}