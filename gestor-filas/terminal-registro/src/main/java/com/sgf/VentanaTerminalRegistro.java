package com.sgf;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaTerminalRegistro extends JFrame {

    private JPanel contentPane;
    private JTextField textFieldDNI;

    public VentanaTerminalRegistro() {
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
        
        JTextArea txtrIngresesuDni = new JTextArea();
        txtrIngresesuDni.setText("Ingrese su DNI");
        txtrIngresesuDni.setBounds(141, 24, 117, 22);
        contentPane.add(txtrIngresesuDni);
        
        textFieldDNI = new JTextField();
        textFieldDNI.setBounds(148, 64, 96, 20);
        contentPane.add(textFieldDNI);
        textFieldDNI.setColumns(10);
        
        JButton btnIngresar = new JButton("Ingresar");
        btnIngresar.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        	}
        });
        btnIngresar.setBounds(154, 102, 88, 22);
        contentPane.add(btnIngresar);
    }
}