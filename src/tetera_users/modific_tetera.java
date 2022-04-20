package tetera_users;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class modific_tetera extends JDialog {
	private final JPanel contentPanel = new JPanel();
	private JTextField nom_emp;
	private JTextField txttel;
	private JTextField txtemail;
	conexion con=new conexion();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
		modific_tetera dialog = new modific_tetera();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public modific_tetera() {
		setTitle("Editar Empleados - Tetera ");
		getContentPane().setLayout(null);
		setBounds(100, 100, 450, 427);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(205, 133, 63));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			nom_emp = new JTextField();
			nom_emp.setBounds(123, 89, 163, 32);
			contentPanel.add(nom_emp);
			nom_emp.setColumns(10);
		}
		{
			txttel = new JTextField();
			txttel.setBounds(121, 150, 165, 32);
			contentPanel.add(txttel);
			txttel.setColumns(10);
		}
		{
			txtemail = new JTextField();
			txtemail.setBounds(122, 211, 164, 32);
			contentPanel.add(txtemail);
			txtemail.setColumns(10);
		}
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\ED2.png"));
			label.setBounds(308, 62, 116, 142);
			contentPanel.add(label);
		}
		{
			JButton btnListo = new JButton("");
			btnListo.setToolTipText("LISTO");
			btnListo.setBackground(Color.GREEN);
			btnListo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
					try {
					String id, nombre, telefono, email;
					
					
					nombre= nom_emp.getText();
					telefono= txttel.getText();
					
					
					String consulta = "INSERT INTO empleado (id, nombre, telefono) VALUES (?,?,?)";
 					PreparedStatement pstm = con.conetar().prepareStatement(consulta);
 					
 					
 				
 					pstm.setString(2,nombre);
 					pstm.setString(3,telefono);
						
					} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					
				}
			});
			btnListo.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\palomita.png"));
			btnListo.setBounds(279, 276, 72, 63);
			contentPanel.add(btnListo);
		}
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Verdana", Font.BOLD, 16));
		lblEmail.setBounds(30, 217, 54, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Verdana", Font.BOLD, 16));
		lblNombre.setBounds(30, 98, 77, 23);
		contentPanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Verdana", Font.BOLD, 16));
		lblTelefono.setBounds(30, 159, 85, 14);
		contentPanel.add(lblTelefono);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 15));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Usuario\t", "Admin"}));
		comboBox.setBounds(47, 276, 163, 32);
		contentPanel.add(comboBox);
		
		JLabel lblNewLabel = new JLabel("EDITAR EMPLEADOS");
		lblNewLabel.setFont(new Font("Script MT Bold", Font.BOLD, 24));
		lblNewLabel.setToolTipText("");
		lblNewLabel.setBounds(74, 22, 274, 23);
		contentPanel.add(lblNewLabel);
	}
	}
