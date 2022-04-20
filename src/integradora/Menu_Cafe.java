package integradora;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;



import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.SystemColor;
import java.awt.ComponentOrientation;
import javax.swing.JDesktopPane;

public class Menu_Cafe extends JFrame
{
	private JTextField txtCambio;
	private JScrollPane scroll;
	private static DefaultTableModel  model;
	private static JTable tabla;
	public static Nueva_Mesa nueva_mesa;
	public static Agregar_productos agregar;
	public static Productos prod;
	static Conexion con= new Conexion();
	public static JComboBox comboMesas;
	private JTextArea textTotal;
	private JLabel lblPago;
	private JTextArea txtPago;
 
	private JTextField txtGuarda_id;
	
	
	public Menu_Cafe()
	
	{
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\tetera2.png"));
	
		

		
		
		this.setTitle("Men\u00FA Caf\u00E9");
		
		this.setBounds(400,200,1450,900);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		String columnas []={"ID","Nombre","Cantidad","Precio","IMPORTE"};
		{
			
			
			scroll= new JScrollPane();
			scroll.setBounds(23, 111, 668, 261);
			
			model= new DefaultTableModel(null,columnas);
			getContentPane().setLayout(null);
			tabla =new JTable(model)
			{
				public boolean isCellEditable(int row, int column)
				{
					if(column==1)
					{
						return true;
						
					}else
					{
						return false;
					}
				}
			};
			scroll.setViewportView(tabla);
			getContentPane().add(scroll);
			
			tabla.getColumnModel().getColumn(0).setMinWidth(50);
			tabla.getColumnModel().getColumn(0).setMaxWidth(50);
			
			tabla.getColumnModel().getColumn(1).setMinWidth(150);
			tabla.getColumnModel().getColumn(1).setMinWidth(150);
			
			
			tabla.getColumnModel().getColumn(2).setMinWidth(150);
			tabla.getColumnModel().getColumn(2).setMinWidth(150);
			
			
			tabla.getColumnModel().getColumn(3).setMinWidth(200);
			tabla.getColumnModel().getColumn(3).setMinWidth(200);
			
			
			tabla.getTableHeader().setFont(new Font("SansSerif",Font.BOLD,16));
			tabla.getTableHeader().setBackground(new Color(0,0,180));
			tabla.getTableHeader().setForeground(new Color(255,255,255));
			tabla.setRowHeight(30);
			
			tabla.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					int fila=tabla.getSelectedRow();
					String id1=tabla.getValueAt(fila,0).toString();
					JOptionPane.showMessageDialog(null,""+id1);
					txtGuarda_id.setText(""+id1);
					
					
				}
			});
			
			txtGuarda_id = new JTextField();
			txtGuarda_id.setBounds(605, 78, 86, 20);
			getContentPane().add(txtGuarda_id);
			txtGuarda_id.setColumns(10);
			
			
		}
		
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setBounds(0, 0, 0, 0);
		getContentPane().add(btnNewButton);
		
		
		
		JLabel lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(30, 455, 195, 42);
		lblTotal.setForeground(new Color(0, 0, 255));
		lblTotal.setFont(new Font("Tahoma", Font.PLAIN, 45));
		getContentPane().add(lblTotal);
		
		 textTotal = new JTextArea();
		 textTotal.setBounds(219, 455, 188, 42);
		textTotal.setBackground(Color.GRAY);
		textTotal.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		textTotal.setText("$ 0.00");
		textTotal.setForeground(new Color(0, 0, 255));
		getContentPane().add(textTotal);
		
		
		JLabel lblLaTeteraBistro = new JLabel("LA TETERA BISTRO CAF\u00C9");
		lblLaTeteraBistro.setBounds(170, 64, 379, 36);
		lblLaTeteraBistro.setBackground(new Color(205, 133, 63));
		lblLaTeteraBistro.setFont(new Font("Times New Roman", Font.PLAIN, 30));
		getContentPane().add(lblLaTeteraBistro);
		
		lblPago= new JLabel("PAG\u00D3");
		lblPago.setBounds(459, 459, 144, 42);
		lblPago.setForeground(Color.BLUE);
		lblPago.setFont(new Font("Tahoma", Font.PLAIN, 35));
		getContentPane().add(lblPago);
		
		 txtPago = new JTextArea();
		txtPago.setBounds(647, 455, 188, 42);
		txtPago.setBackground(Color.GRAY);
		txtPago.setForeground(Color.BLUE);
		txtPago.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		txtPago.setText("$ 0.00 ");
		getContentPane().add(txtPago);
		
		JLabel lblCambio = new JLabel("CAMBIO");
		lblCambio.setBounds(859, 462, 154, 36);
		lblCambio.setForeground(Color.BLUE);
		lblCambio.setFont(new Font("Tahoma", Font.PLAIN, 35));
		getContentPane().add(lblCambio);
		
		txtCambio = new JTextField();
		txtCambio.setBounds(1082, 455, 188, 45);
		txtCambio.setBackground(Color.GRAY);
		txtCambio.setForeground(Color.BLUE);
		txtCambio.setFont(new Font("Times New Roman", Font.PLAIN, 40));
		txtCambio.setText("$ 0.00");
		getContentPane().add(txtCambio);
		txtCambio.setColumns(10);
		
		
		
		JButton btnMesas = new JButton("");
		btnMesas.setBounds(720, 56, 82, 79);
		btnMesas.setToolTipText("Agregar Mesa");
		btnMesas.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\mesa5.jpg"));
		btnMesas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				addNueva_Mesa();
				
			}
		});
		getContentPane().add(btnMesas);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnAdministracinDeProductos = new JMenu("Administraci\u00F3n de Productos");
		menuBar.add(mnAdministracinDeProductos);
		
		//JPanel pnlProductos = new JPanel();
		
		JLabel lblMesa_1 = new JLabel("MESA :  ");
		lblMesa_1.setBounds(1085, 189, 104, 28);
		lblMesa_1.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblMesa_1.setForeground(new Color(205, 133, 63));
		getContentPane().add(lblMesa_1);
		
		comboMesas = new JComboBox();
		comboMesas.setBounds(962, 116, 237, 45);
		comboMesas.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) 
			{
				
				lblMesa_1.setText("MESA: "+comboMesas.getSelectedItem());
				
			}
		});
		comboMesas.setFont(new Font("Verdana", Font.PLAIN, 26));
		getContentPane().add(comboMesas);
		
		JLabel lblMesa = new JLabel("+ MESA");
		lblMesa.setBounds(720, 130, 82, 42);
		lblMesa.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblMesa.setForeground(new Color(255, 69, 0));
		getContentPane().add(lblMesa);
		
		
		
		
		JButton btnproductos = new JButton("");
		btnproductos.setBounds(720, 275, 82, 79);
		btnproductos.setToolTipText("Agregar Productos");
		btnproductos.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\plato2.png"));
		btnproductos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{	
				int i=comboMesas.getSelectedIndex();
				if(comboMesas.getSelectedIndex()==0)
				{
					JOptionPane.showMessageDialog(null, "Seleccione Una Mesa Porfavor");
				}else
				{
					agrega();
				}
		
				
			}
		});
		getContentPane().add(btnproductos);
		
		
		JButton btnEliminarMesa = new JButton("");
		btnEliminarMesa.setBounds(986, 172, 54, 45);
		btnEliminarMesa.setToolTipText("Eliminar Mesa");
		btnEliminarMesa.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\remove.png"));
		btnEliminarMesa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboMesas.getSelectedIndex()==0)
				{
					JOptionPane.showMessageDialog(null,"Seleccione una Mesa");
				}else
				{
					
					eliminamesa();
					llenatablamesa();
				}
				
				
			}
		});
		getContentPane().add(btnEliminarMesa);
		
		JLabel lblAgregarProductos = new JLabel("+ Productos");
		lblAgregarProductos.setBounds(720, 365, 135, 25);
		lblAgregarProductos.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAgregarProductos.setForeground(new Color(255, 69, 0));
		getContentPane().add(lblAgregarProductos);
		
		JLabel label = new JLabel("");
		label.setBounds(1209, 0, 153, 155);
		label.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\tetera.jpg"));
		getContentPane().add(label);
		
		JButton btbElimina_pro = new JButton("");
		btbElimina_pro.setBounds(730, 181, 54, 51);
		btbElimina_pro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				elima_producto();
				
			}
		});
		btbElimina_pro.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\remove.png"));
		getContentPane().add(btbElimina_pro);
		
		JLabel lblProducto = new JLabel("- Eliminar");
		lblProducto.setBounds(701, 239, 135, 25);
		lblProducto.setToolTipText("Eliminar Producto");
		lblProducto.setForeground(new Color(255, 69, 0));
		lblProducto.setFont(new Font("Tahoma", Font.PLAIN, 22));
		getContentPane().add(lblProducto);
		


		JButton Productos = new JButton("");
		Productos.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\src\\integradora\\add.png"));
		Productos.setBounds(46, 11, 39, 42);
		Productos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				Admin_Prod();
			}
		});
		getContentPane().add(Productos);
		
		
		obtenermesas();
		
		JLabel lblAdministracion = new JLabel("Administracion");
		lblAdministracion.setForeground(new Color(255, 140, 0));
		lblAdministracion.setBounds(39, 64, 69, 14);
		getContentPane().add(lblAdministracion);
	
		//Fondo  del menú
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\FUT54\\Downloads\\t.jpg"));
		lblNewLabel.setBounds(0, 0, 1372, 762);
		getContentPane().add(lblNewLabel);
		
		
	}
	

	public static void main(String[]args)
	{
		Menu_Cafe form2=new Menu_Cafe();
		form2.setVisible(true);
		
			
	}
	
	
	public void obtenermesas(){
		comboMesas.removeAllItems();
		String sql = "";
		try {
			sql= "SELECT *FROM tbl_mesas WHERE estado ='1'";
			PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			comboMesas.addItem("Seleccionar mesa");
			while(rs.next()){
				comboMesas.addItem(rs.getString("numero"));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}	
	}
	public void Productos()
	{
		String productos;
		 try 
		 {
			 String sql="SELECT *FROM tbl_productos";
				PreparedStatement pstm=(PreparedStatement) con.conectar().prepareStatement(sql);
				ResultSet respuesta=pstm.executeQuery(sql);
				
				while(respuesta.next())
				{
					model.addRow(new Object[]{respuesta.getString("id_producto"),respuesta.getString("nombre"),respuesta.getString("cantidad"),respuesta.getString("precio")});
				}
				
		} 
		 catch (Exception e) 
		 {
			JOptionPane.showMessageDialog(null,"Error: "+e.getMessage());
		 }
	}
	public void borrartabla(){
		model.setRowCount(0);
	}
		
	public void llenatablamesa(){
		
		String sql = "";
		
		try {
			String id_mesa = String.valueOf(comboMesas.getSelectedItem());
			
			
			sql= "SELECT tbl_productos.id_producto,tbl_productos.precio, tbl_productos.nombre, tbl_temporal.id_producto,tbl_temporal.id_mesa, tbl_temporal.cantidad FROM tbl_productos INNER JOIN tbl_temporal ON tbl_productos.id_producto = tbl_temporal.id_producto AND  tbl_temporal.id_mesa='"+comboMesas.getSelectedItem()+"'";
			PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			String []datos = new String[5]; 
			double total=0;
			
			while(rs.next())
			{
				
				double cant = Double.parseDouble(rs.getString("cantidad"));
				double prec = Double.parseDouble(rs.getString("precio"));
				double importe =  cant*prec;
			
				
				datos[0] = rs.getString("id_producto");
				datos[1] = rs.getString("nombre");
				datos[2] = rs.getString("cantidad");
				datos[3] = rs.getString("nombre");
				datos[4] = String.valueOf(importe);
				model.addRow(datos);
				
				total=importe+total;	
				
				
				
			}
				
			textTotal.setText(String.valueOf("$ "+total));
			double pago,cambio,cambio_total;
			pago=Double.parseDouble(txtPago.getText());
			cambio=Double.parseDouble(txtCambio.getText());
			cambio_total=total-pago;
			txtCambio.setText("$ "+cambio_total);
			
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}	
		
	}
	
	public void addNueva_Mesa(){
		nueva_mesa = new Nueva_Mesa(this,true);
		nueva_mesa.setVisible(true);
	}
	public void agrega(){
		agregar = new Agregar_productos(this,true);
		agregar.setVisible(true);
	}
	public void Admin_Prod()
	{
		prod = new Productos(this,true);
		prod.setVisible(true);
	}
	
	
	public void eliminamesa()

	{
		
			
		int res=JOptionPane.showConfirmDialog(null, "¿Estas seguro de Eliminar la Mesa?"+comboMesas.getSelectedItem());
			 if(res== JOptionPane.YES_NO_OPTION)
			{ try 
			  {				
				
				String sql="UPDATE tbl_mesas SET estado='0' WHERE numero='"+comboMesas.getSelectedItem()+"'" ;
				PreparedStatement pstm=con.conectar().prepareStatement(sql);
				int re=pstm.executeUpdate();
				
			  } catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null,"Error: try de actualizar"+e.getMessage());
				
			}
				
			}else
			{
			
			}
			obtenermesas();
	}
	
	public void elima_producto()
	{
		String sql="";
		try 
		{
			sql="DELETE FROM tbl_temporal WHERE id_producto='"+txtGuarda_id.getText()+"'";
			PreparedStatement pstm=con.conectar().prepareStatement(sql);
			int rs=pstm.executeUpdate();
			if(rs==0)
			{
				JOptionPane.showMessageDialog(null, "Nos se borro");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "Si se borro");

			}
		} catch (Exception e) 
			{
			JOptionPane.showMessageDialog(null,"Error "+e.getMessage());
			}
	}
	
	public void llena_la_tabla_principal(){
		
		String sql = "";
		try {
			sql= "SELECT *FROM tbl_temporal WHERE id_mesa='"+comboMesas.getSelectedItem()+"'";
			PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			String []datos = new String[5]; 
			double total=0;
			while(rs.next())
			{
				double cant = Double.parseDouble(rs.getString("cantidad"));
				double prec = Double.parseDouble(rs.getString("precio"));
				double importe =  cant*prec;
				datos[0] = rs.getString("id_producto");
				datos[1] = rs.getString("descripcion");
				datos[2] = rs.getString("cantidad");
				datos[3] = rs.getString(00);
				datos[4] = String.valueOf(importe);
				model.addRow(datos);
				//total=importe+total;
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e);
		}	
	}
}

