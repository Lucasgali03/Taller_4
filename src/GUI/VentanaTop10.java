package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;

import uniandes.dpoo.taller4.modelo.RegistroTop10;
import uniandes.dpoo.taller4.modelo.Top10;

public class VentanaTop10 extends JDialog{
	
	private Vista ventana;
	
	public VentanaTop10(Top10 top, Vista ventana) {
		
		this.ventana = ventana;
		
		setLayout(new BorderLayout());
		
		setTitle("Top 10");
		
		DefaultListModel<RegistroTop10>  listaM = new DefaultListModel<>();
		
		for(RegistroTop10 i: top.darRegistros()) {
			listaM.addElement(i);
		}

		JList<RegistroTop10> lista = new JList<>(listaM);

		lista.setCellRenderer(new ListCellRenderer<>() {

			@Override
			public Component getListCellRendererComponent(JList<? extends RegistroTop10> list, RegistroTop10 value, int index, boolean isSelected, boolean cellHasFocus) {

				index += 1;
				
				JPanel panel = new JPanel();

				JLabel label = new JLabel("Posicion  Nombre   Puntaje");

				panel.add(label);
				
				panel.setBackground(new Color(255,255,255));

				String txt = null;

				txt = Integer.toString(index) + "    " + value.darNombre() + "    " + Integer.toString(value.darPuntos());

				label.setText(txt);

				if(index == 1) {
					label.setForeground(new Color(255, 215, 0));
				}
				else if(index == 2) {
					label.setForeground(new Color(192, 192, 192));
				}
				else if(index == 3) {
					label.setForeground(new Color(205, 120, 50));
				}

				panel.add(label);

				return panel;
			}

		});
		
		JScrollPane scroll = new JScrollPane(lista);
		
		JPanel panel2 = new JPanel();
		
		panel2.add(new JLabel("#  Nombre  Puntaje"));
		panel2.setBackground(new Color(51, 153, 255));
		
		add(panel2, BorderLayout.NORTH);
		add(scroll, BorderLayout.CENTER);
		
		setModal(true);
		setSize(250, 300);
		setLocationRelativeTo(this.ventana);
		setVisible(true);
	}
}
