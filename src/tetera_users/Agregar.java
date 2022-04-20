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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Agregar extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField nom_emp;
	private JTextField txttel;
	private JTextField txtemail;
	public Connection conexion;
	 public Statement sentencia= null;

	private JTextField txtid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Agregar dialog = new Agregar();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Agregar() {
		setTitle("Agregar Empleados - Tetera ");
		getContentPane().setLayout(null);
		setBounds(100, 100, 450, 500);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(238, 232, 170));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			nom_emp = new JTextField();
			nom_emp.setBounds(125, 150, 163, 32);
			contentPanel.add(nom_emp);
			nom_emp.setColumns(10);
		}
		{
			txttel = new JTextField();
			txttel.setBounds(123, 213, 165, 32);
			contentPanel.add(txttel);
			txttel.setColumns(10);
		}
		
		{
			txtemail = new JTextField();
			txtemail.setBounds(122, 268, 164, 32);
			contentPanel.add(txtemail);
			txtemail.setColumns(10);
		}
		JComboBox combonivel = new JComboBox();
		combonivel.setFont(new Font("Verdana", Font.PLAIN, 14));
		combonivel.setModel(new DefaultComboBoxModel(new String[] {"Seleccionar", "Usuario\t", "Admin"}));
		combonivel.setBounds(63, 365, 163, 32);
		contentPanel.add(combonivel);
		{
			JLabel label = new JLabel("");
			label.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\empo.png"));
			label.setBounds(308, 79, 103, 103);
			contentPanel.add(label);
		}
		{
			JButton btnListo = new JButton("");
			btnListo.setToolTipText("LISTO");
			btnListo.setBackground(Color.GREEN);
			btnListo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
String id, nombre, telefono, email, nivel;
					
					id= txtid.getText();
					nombre= nom_emp.getText();
					telefono= txttel.getText();
					email = txtemail.getText();
					nivel = (String) combonivel.getSelectedItem();
					try {
					
					String url="jdbc:mysql://localhost:3306/utd";
					String user= "root";
				    String pass= "";
				 
				    conexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/utd","root","");
					
					
					String SQL = "INSERT INTO tbl_empleados (id_empleado, nombre, telefono, email, nivel) VALUES "
			    		+ "('"+id+"','"+nombre+"','"+telefono+"','"+email+"'"+nivel+"')";
					 sentencia = conexion.createStatement();
 					;
 					 int n = sentencia.executeUpdate(SQL);
 					
 					if(n>0){
 						JOptionPane.showMessageDialog(null, "error no se pudo agregar el empleado");
 					}
						
					} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
					}
					
				}
			});
			btnListo.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\palomita.png"));
			btnListo.setBounds(290, 355, 72, 63);
			contentPanel.add(btnListo);
		}
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblEmail.setBounds(31, 275, 54, 14);
		contentPanel.add(lblEmail);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNombre.setBounds(31, 157, 77, 14);
		contentPanel.add(lblNombre);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTelefono.setBounds(25, 220, 90, 14);
		contentPanel.add(lblTelefono);
		
		
		JLabel lblNewLabel = new JLabel("NUEVO EMPLEADO");
			lblNewLabel.setFont(new Font("Script MT Bold", Font.PLAIN, 22));
			lblNewLabel.setBounds(100, 29, 234, 25);
			contentPanel.add(lblNewLabel);
		
		
		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblId.setBounds(31, 95, 46, 14);
		contentPanel.add(lblId);
		
		txtid = new JTextField();
		txtid.setBounds(125, 88, 163, 32);
		contentPanel.add(txtid);
		txtid.setColumns(10);
	}
}
