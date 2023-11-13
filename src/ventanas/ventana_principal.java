package ventanas;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clases.FuncionesGlobales;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class ventana_principal extends JFrame {

	private JPanel contentPane;
	public static JLabel lblusuario;
	private FuncionesGlobales fGlobal = new FuncionesGlobales();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ventana_principal frame = new ventana_principal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ventana_principal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 796, 412);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(new Color(245, 245, 251));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		setContentPane(contentPane);
		setResizable(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);

		addWindowListener(new java.awt.event.WindowAdapter() {
			@Override
			public void windowClosing(java.awt.event.WindowEvent evt) {
				fGlobal.salirSistema(rootPane);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Cooperativa De Colaboradores San Pio");
		lblNewLabel.setForeground(new Color(17,16,38));
		lblNewLabel.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblNewLabel.setBounds(130, 39, 404, 27);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("09/03/2021");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setForeground(new Color(17,16,38));
		lblNewLabel_1.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblNewLabel_1.setBounds(613, 39, 138, 27);
		contentPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(247, 229, 233));
		panel.setLayout(null);
		panel.setBounds(34, 93, 717, 112);
		contentPane.add(panel);
		
		JLabel lblUsuarios = new JLabel("");
		lblUsuarios.setBounds(534, 4, 112, 103);
		panel.add(lblUsuarios);
		final ImageIcon logo1 = new ImageIcon(getClass().getResource("/recursos/user.png"));
		final ImageIcon icono1 = new ImageIcon(logo1.getImage().getScaledInstance(lblUsuarios.getWidth(),
				lblUsuarios.getHeight(), Image.SCALE_DEFAULT));
		lblUsuarios.setIcon(icono1);
		
		JLabel lblBienvenido = new JLabel("Bienvenido:");
		lblBienvenido.setForeground(new Color(254, 97, 111));
		lblBienvenido.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblBienvenido.setBounds(10, 26, 130, 27);
		panel.add(lblBienvenido);
		
		lblusuario = new JLabel("Mercy Montoya");
		lblusuario.setForeground(new Color(254, 97, 111));
		lblusuario.setFont(new Font("Open Sans", Font.PLAIN, 22));
		lblusuario.setBounds(150, 26, 374, 27);
		panel.add(lblusuario);
		
		JLabel lblUsuarios_1 = new JLabel("");
		lblUsuarios_1.setBounds(534, 4, 112, 103);
		panel.add(lblUsuarios_1);
		
		JButton btnRegistro = new JButton("Afiliados");
		btnRegistro.setForeground(Color.WHITE);
		btnRegistro.setBackground(new Color(77, 76, 142));
		btnRegistro.setFont(new Font("Open Sans", Font.PLAIN, 22));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_registro registro = new ventana_registro();
				registro.setVisible(true);
				dispose();
			}
		});
		btnRegistro.setBounds(34, 256, 220, 68);
		contentPane.add(btnRegistro);
		
		JButton btnContabilidad = new JButton("Contabilidad");
		btnContabilidad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_contabilidad contabilidad = new ventana_contabilidad();
				contabilidad.setVisible(true);
				dispose();
			}
		});
		btnContabilidad.setForeground(Color.WHITE);
		btnContabilidad.setBackground(new Color(77, 76, 142));
		btnContabilidad.setFont(new Font("Open Sans", Font.PLAIN, 22));
		btnContabilidad.setBounds(282, 256, 220, 68);
		contentPane.add(btnContabilidad);
		
		JLabel lblLogoPio = new JLabel("");
		lblLogoPio.setBounds(34, 7, 86, 76);
		final ImageIcon logoPio = new ImageIcon(getClass().getResource("/recursos/pio.png"));
		final ImageIcon iconoPio = new ImageIcon(logoPio.getImage().getScaledInstance(lblLogoPio.getWidth(),
				lblLogoPio.getHeight(), Image.SCALE_DEFAULT));
		lblLogoPio.setIcon(iconoPio);
		contentPane.add(lblLogoPio);
		
		JButton btnPrestamos = new JButton("Prestamos");
		btnPrestamos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ventana_prestamo prestamo = new ventana_prestamo();
				prestamo.setVisible(true);
				dispose();
			}
		});
		btnPrestamos.setForeground(Color.WHITE);
		btnPrestamos.setFont(new Font("Open Sans", Font.PLAIN, 22));
		btnPrestamos.setBackground(new Color(77, 76, 142));
		btnPrestamos.setBounds(530, 256, 220, 68);
		contentPane.add(btnPrestamos);
	}
}
