package Menu;
import java.awt.*;
import javax.swing.*;

import Grafica.Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class GameMenu extends JFrame{

	private ImageIcon imagenOriginal;
	private ImageIcon imagenGris;
	private Image fondo;
	private String nombre_skin_actual;
	private String[] skin_col;
	
	public GameMenu() {
		// Configuración de la ventana principal
        setTitle("Menú de Juego");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);

        // Creación de los botones
        skin_col = new String[2];
        skin_col[0] = "Default";
        skin_col[1] = "Papercut-4";
        nombre_skin_actual = skin_col[0];
        imagenOriginal = new ImageIcon("src/Assets/SkinOriginal/Menu/SuperMarioMenu.png");
        imagenGris = new ImageIcon("src/Assets/SkinGris/Menu/SuperMarioMenu.png");
        fondo = imagenOriginal.getImage();
        getContentPane().setLayout(null);

        
     // Crear el panel de fondo
        JPanel panelFondo = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(fondo, 0, 0, getWidth(), getHeight(), this);
            }
        };
        
        panelFondo.setLayout(null);  // Posicionar manualmente los componentes
        setContentPane(panelFondo);  // Establece el panel de fondo como contenido principal
        
        JButton startButton = new JButton("Iniciar Juego");
        //startButton.setBackground(new Color(87, 173, 215));
        startButton.setBounds(197, 264, 160, 40);
        getContentPane().add(startButton);
        //startButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //startButton.setContentAreaFilled(false);
        
        JButton skinButton = new JButton("Skin actual: " + nombre_skin_actual);
        //skinButton.setBackground(new Color(87, 173, 215));
        skinButton.setBounds(197, 315, 160, 40);
        getContentPane().add(skinButton);
        //skinButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //skinButton .setContentAreaFilled(false);
        
        JButton rankingButton = new JButton("Ranking");
        //rankingButton.setBackground(new Color(87, 173, 215));
        rankingButton.setBounds(197, 366, 160, 40);
        getContentPane().add(rankingButton);
        //rankingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //rankingButton.setContentAreaFilled(false); 
        
        JButton exitButton = new JButton("Salir");
        //exitButton.setBackground(new Color(87, 173, 215));
        exitButton.setBounds(197, 417, 160, 40);
        getContentPane().add(exitButton);
        //exitButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        //exitButton.setContentAreaFilled(false);   
        
                exitButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //JOptionPane.showMessageDialog(null, "Saliendo del juego. ¡Hasta luego!");
                        System.exit(0); // Cierra la aplicación
                    }
                });
        
                rankingButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //JOptionPane.showMessageDialog(null, "Instrucciones:\nEl objetivo del juego es...");
                    	VentanaRanking ventana_ranking = new VentanaRanking();
                    	ventana_ranking.setVisible(true);
                        // Añade las instrucciones de tu juego aquí
                    }
                });
        
                skinButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                    	switch(nombre_skin_actual) {
                    		case("Default"):
                    			nombre_skin_actual = "Papercut-4";
                    			skinButton.setText("Skin actual: " + nombre_skin_actual);
                    			fondo = imagenGris.getImage();
                    			repaint();
                    			break;
                    		case("Papercut-4"):
                    			nombre_skin_actual = "Default";
                    			skinButton.setText("Skin actual: " + nombre_skin_actual);
                    			fondo = imagenOriginal.getImage();
                    			repaint();
                    			break;
                    	}
                    	
                    }
                });
        
                // Agregar listeners a los botones
                startButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //JOptionPane.showMessageDialog(null, "Iniciando el juego...");
                        Juego juego = new Juego(nombre_skin_actual);
                        juego.iniciar();
                        dispose();
                    }
                });
                
	}
}
