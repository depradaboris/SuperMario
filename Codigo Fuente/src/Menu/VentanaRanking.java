package Menu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Datos.Ranking;

public class VentanaRanking extends JFrame{
	
	protected Ranking ranking;

	public VentanaRanking() {
		
		 	setTitle("Ranking");
	        setSize(400, 400);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	        setLayout(new GridLayout(7, 1, 0, 0));
	        
	        //Llama al ranking para mostrar el TOP 5
	        ranking = new Ranking();
	        mostrarTopFive();
	        
	        
	        JButton botonCerrar = new JButton("Cerrar");
	        //botonCerrar.setBounds(20, 20, 20, 30);
	        add(botonCerrar);
	        
	        // Añadir un ActionListener para cerrar la ventana
	        botonCerrar.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();  // Cierra la ventana actual
				}
	        });
	}

	private void mostrarTopFive() {
		String[] arr_ranking = ranking.getTopFive();
		
		JLabel ranking_text =new JLabel("Ranking");
		ranking_text.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(ranking_text);
		
		for(int i = 0; i < 5; i++) {
			JLabel tmp_label =new JLabel(arr_ranking[i]);
			tmp_label.setBounds(200  ,32 * (i+1),300,30);
			tmp_label.setHorizontalAlignment(SwingConstants.CENTER);
			this.add(tmp_label);
		}
	}
	
}
