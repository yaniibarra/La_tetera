package integradora;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class agre_prod extends JDialog {
	
	Conexion con=new Conexion();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtnombre;
	private JTextField txtprecio;
	private JTextField txtcantidad;
	private JLabel lblnombre;
	private JLabel lblprecio;
	private JLabel lblcantidad;
	private JLabel lblagregarprod;
	private JLabel lbldescripcion ;
	private JTextArea textArea ;
	private JButton btnaceptar ;
	private JButton btncancelar ;
	private AbstractButton jTextFieldName;
	public static Productos produ;
	public static Menu_Cafe menu;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			agre_prod dialog = new agre_prod(produ, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public agre_prod(Productos parent, boolean modal) {
		
		this.produ = parent;
		this.setModal(modal);
		setLocationRelativeTo(this);
		
		setBounds(100, 100, 700, 600);
		setLocationRelativeTo(this);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
	    lblnombre = new JLabel("Nombre");
		lblnombre.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lblnombre.setBounds(39, 131, 106, 14);
		contentPanel.add(lblnombre);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(171, 117, 191, 46);
		contentPanel.add(txtnombre);
		
		lblprecio = new JLabel("Precio");
		lblprecio.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lblprecio.setBounds(39, 202, 122, 14);
		contentPanel.add(lblprecio);
		
		txtprecio = new JTextField();
		txtprecio.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(!Character.isDigit(e.getKeyChar()) && e.getKeyChar()!= '.' ){
					e.consume();
				}
				if(e.getKeyChar() == '.' && txtprecio.getText().contains(".")){
					e.consume();
				}
				
				String precio = txtprecio.getText();
				char punto = '.';
				for (int i=0; i<precio.length(); i++) {
					if(precio.charAt(i)== punto){
						if(precio.length() > (i+2)){
							e.consume();
						}
					}
				}
			}
		});
		txtprecio.setBounds(171, 188, 191, 47);
		contentPanel.add(txtprecio);
		
		lblcantidad = new JLabel("Cantidad");
		lblcantidad.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lblcantidad.setBounds(39, 267, 98, 14);
		contentPanel.add(lblcantidad);
		
		lbldescripcion = new JLabel("Descripcion");
		lbldescripcion.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
		lbldescripcion.setBounds(23, 326, 138, 14);
		contentPanel.add(lbldescripcion);
		
		btnaceptar = new JButton("");
		btnaceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String nombre = txtnombre.getText();
				String cantidad = txtcantidad.getText();
				String precio = txtprecio.getText();
				String descripcion = textArea.getText();
				
				if(nombre.length()==0 || nombre.length() < 5){
					JOptionPane.showMessageDialog(null, "Nombre demasiado corto");
					txtnombre.requestFocus();
				}else{
				if(precio.length()==0){
					JOptionPane.showMessageDialog(null, "Ingresa un precio");
					txtprecio.requestFocus();
				}else{
				if(cantidad.length()==0){
					JOptionPane.showMessageDialog(null, "Ingresa una Cantidad");
					txtcantidad.requestFocus();					
				}else{
				if(descripcion.length()==0 || descripcion.length()<= 10){
						JOptionPane.showMessageDialog(null, "Ingresa una descripcion mas larga");
						textArea.requestFocus();						
				}else{
					guardar();
					agre_prod.this.dispose();
				}
				}	
				}	
				}
			}
		});
		btnaceptar.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\src\\integradora\\icon.png"));
		btnaceptar.setToolTipText("ACEPTAR");
		btnaceptar.setBounds(222, 484, 58, 58);
		contentPanel.add(btnaceptar);
		
		btncancelar = new JButton("");
		btncancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btncancelar.setToolTipText("CANCELAR");
		btncancelar.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\src\\integradora\\icono.png"));
		btncancelar.setBounds(305, 484, 58, 58);
		contentPanel.add(btncancelar);
		
		txtcantidad = new JTextField();
		txtcantidad.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				if(Character.isDigit(e.getKeyChar())){					
				}else{
					e.consume();
				}
			}
		});
		txtcantidad.setColumns(10);
		txtcantidad.setBounds(171, 254, 191, 47);
		contentPanel.add(txtcantidad);
		
		lblagregarprod = new JLabel("Agregar Productos");
		lblagregarprod.setForeground(Color.DARK_GRAY);
		lblagregarprod.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblagregarprod.setBounds(205, 26, 267, 40);
		contentPanel.add(lblagregarprod);
		
		textArea = new JTextArea();
		textArea.addKeyListener(new KeyAdapter() {
		int limite  = 50;

			@Override
			public void keyTyped(KeyEvent e) {
				
				{if (textArea.getText().length()== limite)
					 
				     e.consume();
				}
			}
		});
		textArea.setBounds(171, 323, 279, 150);
		contentPanel.add(textArea);
	}
	public Statement guardar(){		
		try{
			String consulta ="INSERT INTO tbl_productos (nombre,descripcion,cantidad,precio) VALUES(?,?,?,?)"; 
			PreparedStatement pstm = con.conectar().prepareStatement(consulta);
			pstm.setString(1, txtnombre.getText());
			pstm.setString(2, textArea.getText());
			pstm.setString(3, txtcantidad.getText());
			pstm.setString(4, txtprecio.getText());
			
			int i = pstm.executeUpdate();
			
			if(i==0){
				JOptionPane.showMessageDialog(null, "El Producto no fue guardado");
			}else{
				JOptionPane.showMessageDialog(null, "El Producto fue guardado exitosamente");
				;
			}			
		}catch(SQLException e){
			JOptionPane.showMessageDialog(null, "Error: " + e );
		}
		
		return null;
	}
}

		

