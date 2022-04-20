package integradora;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JTextField;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Agregar_productos extends JDialog {

	private final JPanel contentPanel = new JPanel();
	public static Menu_Cafe menu;
	private JScrollPane scroll;
	private static DefaultTableModel model;
	public JTable tablap;
	static Conexion con=new Conexion();
	private JTextField textField;
	private JTextField txtCantidad;
	private String id_Pro;
	private JTextField id_prod;
	public static void main(String[] args) {
		try {
			Agregar_productos dialog = new Agregar_productos(menu, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Agregar_productos(Menu_Cafe parent,boolean modal) 
	{
		this.menu=parent;
		this.setModal(modal);
		this.setTitle("Agregar productos");
		
		setSize(528, 419);
		setLocationRelativeTo(null);	
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(null);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		JLabel lblAgregarPrdouctos = new JLabel("AGREGAR  PRDOUCTOS");
		lblAgregarPrdouctos.setFont(new Font("Times New Roman", Font.BOLD, 20));
		lblAgregarPrdouctos.setBounds(137, 11, 244, 32);
		contentPanel.add(lblAgregarPrdouctos);	
		
		
		final String columnas[]={"ID","NOMBRE","CANTIDAD","PRECIO"};
		model=new DefaultTableModel(null,columnas);
			tablap=new JTable();
			tablap.setModel(model);
			JTable tablap=new JTable(model){
				public boolean isCellEditable(int row, int column)
				{
					if(column==0)
					{
						return false;
					}else{
						return false;
					}
				}			
				};
			scroll=new JScrollPane();
			
			scroll.setBounds(46,100,409,230);
			scroll.setViewportView(tablap);
			contentPanel.add(scroll);
			
			tablap.getColumnModel().getColumn(0).setMinWidth(50);
			tablap.getColumnModel().getColumn(0).setMaxWidth(50);
			
			tablap.getColumnModel().getColumn(1).setMinWidth(50);
			tablap.getColumnModel().getColumn(1).setMinWidth(50);
			
			
			tablap.getColumnModel().getColumn(2).setMinWidth(50);
			tablap.getColumnModel().getColumn(2).setMinWidth(50);
			
			
			tablap.getColumnModel().getColumn(3).setMinWidth(50);
			tablap.getColumnModel().getColumn(3).setMinWidth(50);
			tablap.getRowHeight(50);
			
			tablap.getTableHeader().setFont(new Font("SansSerif",Font.BOLD,16));
			tablap.getTableHeader().setBackground(new Color(0,0,180));
			tablap.getTableHeader().setForeground(new Color(255,255,255));
			
			tablap.addMouseListener(new MouseAdapter()
			{
				@Override
				public void mouseClicked(MouseEvent arg0) 
				{
					int fila=tablap.getSelectedRow();
					
					txtCantidad.setText("1");
					id_prod.setText(tablap.getValueAt(fila,0).toString());
						
				}
			});
			
		
			
			textField = new JTextField();
			textField.addKeyListener(new KeyAdapter() {				
				public void keyTyped(KeyEvent e) {
					String dato=textField.getText();					
					String sql ="";
					borrartabla();
					try
				{
						 sql ="SELECT * FROM tbl_productos  WHERE nombre LIKE '%" + dato +"%' OR id_producto ='"+dato+"'";
						PreparedStatement pstm = con.conectar().prepareStatement(sql);
						ResultSet rs=pstm.executeQuery();
						while(rs.next()){
						 model.addRow(new Object[]{rs.getInt("id_producto"),rs.getString("nombre"),rs.getInt("cantidad"),rs.getFloat("precio")});
						}
				} catch (SQLException e1) 
					{
					JOptionPane.showMessageDialog(null, sql);
					}
				}
			});
			textField.setBounds(65, 54, 390, 35);
			contentPanel.add(textField);
			textField.setColumns(10);
			
			txtCantidad = new JTextField();
			txtCantidad.setBounds(29, 341, 86, 32);
			contentPanel.add(txtCantidad);
			txtCantidad.setColumns(10);
			
			JButton btnAceptar = new JButton("Aceptar");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) 
				{
					guardartbltemporal();
				}
			});
			
			
			btnAceptar.setBounds(379, 346, 79, 23);
			contentPanel.add(btnAceptar);
			
			id_prod = new JTextField();
			id_prod.setBounds(137, 347, 86, 20);
			contentPanel.add(id_prod);
			id_prod.setColumns(10);
			
			//llenar_tabla();
			
	}
	public static void llenar_tabla()
	{
		try 
		{
			String sql= "SELECT *FROM tbl_productos";
			PreparedStatement pstm= (PreparedStatement) con.conectar().prepareStatement(sql);
			ResultSet resul=pstm.executeQuery();
			 while(resul.next())
			 {
				 model.addRow(new Object[]{resul.getInt("id_producto"),resul.getString("descripcion"),resul.getInt("cantidad"),resul.getFloat("precio"),resul.getFloat("importe")});
				
			 }
			
			
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
	//	return null;
	}
	
	
	public void guardartbltemporal()
	{
		String id_mesa = String.valueOf(Menu_Cafe.comboMesas.getSelectedItem());
		//JOptionPane.showMessageDialog(null,id_mesa);
		//JOptionPane.showMessageDialog(null,""+txtCantidad.getText());
		try 
		{
			
			String sql= "INSERT INTO tbl_temporal (id_producto,id_mesa,cantidad,estado) VALUES ('"+id_prod.getText()+"','"+id_mesa+"','"+txtCantidad.getText()+"','"+0+"')";
			PreparedStatement pstm=  con.conectar().prepareStatement(sql);
			int resul = pstm.executeUpdate();
			if(resul==1)
			{
				JOptionPane.showMessageDialog(null,"tabla temporal llenada");
				menu.llenatablamesa();
				dispose();
			}else
			{
				JOptionPane.showMessageDialog(null,"tabla temporal  no llenada");
			}
			
		} catch (SQLException e) 
		{
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
	
	}
	
	public void borrartabla(){
		model.setRowCount(0);
	}
	
	
	
}

