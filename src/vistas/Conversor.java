package vistas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Conversor {

	private JFrame frame;
	private JButton btnConvertir;
	private JComboBox jcb;
	private JTextField txtconvertir;
	private JLabel lblconvertido;
	
	public enum TipoMoneda{
		pesosCo_dolar,
		pesosCo_euro,
		pesosCo_libra,
		dolar_pesosCo,
		euro_pesosCo,
		libra_pesosCo
		
	}
	
	public double dolar = 4087.18;
	public double libra = 5112.51;
	public double euro = 4383.13;
	
	public double valorIngresado = 0.00;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Conversor window = new Conversor();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Conversor() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 299, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(96, 150, 251));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 283, 261);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		txtconvertir = new JTextField();
		txtconvertir.setBounds(61, 67, 144, 26);
		panel.add(txtconvertir);
		txtconvertir.setColumns(10);
		
		jcb = new JComboBox<TipoMoneda>();
		jcb.setModel(new DefaultComboBoxModel<>(TipoMoneda.values()));
		jcb.setBounds(61, 114, 144, 26);
		panel.add(jcb);
		
		JLabel lblNewLabel = new JLabel("CONVERTIDOR DE MONEDA");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("HP Simplified", Font.BOLD, 13));
		lblNewLabel.setBounds(49, 11, 167, 37);
		panel.add(lblNewLabel);
		
		btnConvertir = new JButton("Convertir");
		btnConvertir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Convertir();
			}

			private void Convertir() {
				
				if(Validar(txtconvertir.getText())) {
					
					TipoMoneda moneda = (TipoMoneda) jcb.getSelectedItem();
					
					switch(moneda) {
					case pesosCo_dolar:
						PesoAMoneda(dolar);
						break;
					case pesosCo_euro:
						PesoAMoneda(euro);
						break;
					case pesosCo_libra:
						PesoAMoneda(libra);
						break;
					case dolar_pesosCo:
						MonedaAPeso(dolar);
						break;
					case euro_pesosCo:
						MonedaAPeso(euro);
						break;
					case libra_pesosCo:
						MonedaAPeso(libra);
						break;
						
					}
					
				}
				
				
				
			}
			
			
			public void PesoAMoneda(double moneda) {
				double resultado = valorIngresado/moneda;
				lblconvertido.setText(Redondear(resultado));
			} 
			
			
			public void MonedaAPeso(double moneda) {
				double resultado = valorIngresado*moneda;
				lblconvertido.setText(Redondear(resultado));
				
			}
			
			public String Redondear(double valor) {
				DecimalFormat df = new DecimalFormat("0.00");
				df.setRoundingMode(RoundingMode.HALF_UP);
				return df.format(valor);
			}
			
			public boolean Validar(String txt) {
				try {
					double x = Double.parseDouble(txt);
					if (x > 0) {
						valorIngresado = x ;
						return true;
					}
				}catch(NumberFormatException e) {
					lblconvertido.setText(" Solo se aceptan numeros " );
					return false;
				}
				return false;
				
			}
			
			
		});
		btnConvertir.setFont(new Font("Segoe UI Semibold", Font.BOLD, 11));
		btnConvertir.setForeground(new Color(255, 255, 255));
		btnConvertir.setBackground(Color.BLUE);
		
		
		
		btnConvertir.setBounds(81, 162, 104, 26);
		panel.add(btnConvertir);
		
		lblconvertido = new JLabel("");
		lblconvertido.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblconvertido.setForeground(new Color(255, 255, 255));
		lblconvertido.setBounds(110, 214, 54, 17);
		panel.add(lblconvertido);
	}
}
