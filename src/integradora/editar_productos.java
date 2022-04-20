package integradora;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.mysql.jdbc.PreparedStatement;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.ResultSet;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.sql.*;
import javax.swing.JTextArea;

public class editar_productos extends JDialog {
	
	Conexion con = new Conexion();

	private final JPanel contentPanel = new JPanel();
	private JTextField txtid;
	private JTextField txtnombre;
	private JTextField txtprecio;
	private JTextField txtcantidad;
	private JLabel lblid,
    lblagregarproducto,
    lblnombre,
    lbldescripcion,
    lblprecio,
    lblcantidad,
    lblModificarProducto;
	private JButton btnaceptar, btncancelar;
	public ResultSet ra;
	public static Productos prod;
	private JTextField textField;
	private JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			editar_productos dialog = new editar_productos(prod, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			

			dialog.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	public editar_productos(Productos productos, boolean modal) {
		
		this.prod = productos;
		this.setModal(modal);
		setLocationRelativeTo(this);
		
		setBounds(100, 100, 700, 645);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
	
		
		    lblid = new JLabel("ID");
			lblid.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
			lblid.setBounds(101, 124, 138, 14);
			contentPanel.add(lblid);
		
			lblModificarProducto = new JLabel("Modificar Producto");
			lblModificarProducto.setFont(new Font("Lucida Calligraphy", Font.BOLD, 30));
			lblModificarProducto.setBounds(152, 36, 389, 34);
			contentPanel.add(lblModificarProducto);
		
			
			//.setEditable(false);
			
			lblnombre = new JLabel("Nombre");
			lblnombre.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
			lblnombre.setBounds(101, 180, 138, 14);
			contentPanel.add(lblnombre);
			
			txtnombre = new JTextField();
			txtnombre.setColumns(10);
			txtnombre.setBounds(249, 166, 191, 46);
			contentPanel.add(txtnombre);
		
			lbldescripcion = new JLabel("Descripcion");
			lbldescripcion.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
			lbldescripcion.setBounds(101, 384, 138, 14);
			contentPanel.add(lbldescripcion);
	
			lblprecio = new JLabel("Precio");
			lblprecio.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
			lblprecio.setBounds(101, 237, 138, 14);
			contentPanel.add(lblprecio);
		
			txtprecio = new JTextField();
			txtprecio.setColumns(10);
			txtprecio.setBounds(249, 223, 191, 47);
			contentPanel.add(txtprecio);
		
			 lblcantidad = new JLabel("Cantidad");
			lblcantidad.setFont(new Font("Lucida Calligraphy", Font.PLAIN, 20));
			lblcantidad.setBounds(101, 303, 138, 14);
			contentPanel.add(lblcantidad);
		
			txtcantidad = new JTextField();
			txtcantidad.setColumns(10);
			txtcantidad.setBounds(249, 289, 191, 47);
			contentPanel.add(txtcantidad);
		
			btncancelar = new JButton("");
			btncancelar.setIcon(new ImageIcon(editar_productos.class.getResource("/integradora/icono.png")));
			btncancelar.setToolTipText("CANCELAR");
			btncancelar.setBounds(385, 536, 56, 59);
			contentPanel.add(btncancelar);		

			btnaceptar = new JButton("");
			btnaceptar.setIcon(new ImageIcon(editar_productos.class.getResource("/integradora/icon.png")));
			btnaceptar.setToolTipText("Modificar");
			btnaceptar.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					alta();
					editar_productos.this.dispose();
				}
			});
			btnaceptar.setBounds(294, 536, 56, 59);
			contentPanel.add(btnaceptar);
			
			txtid = new JTextField();
			txtid.setEditable(false);
			txtid.setColumns(10);
			txtid.setBounds(249, 110, 191, 46);
			contentPanel.add(txtid);
			
			textArea = new JTextArea();
			textArea.setBounds(249, 360, 279, 150);
			contentPanel.add(textArea);
	
			
			buscarproducto();
	}//cierra constructor
	
	
	
	
	public void alta()
	{
		try
		{	

			String id_producto,nombre,descripcion,cantidad,precio;
		
			id_producto=txtid.getText();
			nombre=txtnombre.getText();
			descripcion=textArea.getText();
			cantidad=txtcantidad.getText();
			precio=txtprecio.getText();
			
			
						String consulta ="update tbl_productos SET nombre='"+txtnombre.getText()+"',descripcion='"+textArea.getText()+"',cantidad='"+txtcantidad.getText()+"',precio='"+txtprecio.getText()+"'WHERE id_producto='"+txtid.getText()+"'";
								
						PreparedStatement pstm = (PreparedStatement) con.conectar().prepareStatement(consulta);
						//ra=cn.Insertar(sql);
						int i = pstm.executeUpdate();
						
						if(i==0)
							{
								//JOptionPane.showMessageDialog(null, "El contacto no fue guardado");
							}else
							{
								JOptionPane.showMessageDialog(null, "El producto fue guardado exitosamente");
								
								
							}
					
							
		}catch(Exception e1)
		{
		JOptionPane.showMessageDialog(null,"Error: "+e1.getMessage());
		}
		return;
		
	}
	
	
	
   
 
 	public void buscarproducto(){
 		String consulta ="";
 		String id_prod = Productos.txt_idprod.getText();

 	try 
		{
			consulta= "SELECT *FROM tbl_productos WHERE id_producto='"+id_prod+"'";
			PreparedStatement pstm=(PreparedStatement) con.conectar().prepareStatement(consulta);
			ResultSet rs=pstm.executeQuery();
			while(rs.next())
				{
						txtid.setText(rs.getString("id_producto"));
						txtnombre.setText(rs.getString("nombre"));
						txtcantidad.setText(rs.getString("cantidad"));
						txtprecio.setText(rs.getString("precio"));
						textArea.setText(rs.getString("descripcion"));
				}
			
	} catch (Exception e2)
			{
				JOptionPane.showMessageDialog(null,"Erro: "+e2.getMessage());
			} 
 	}
}
