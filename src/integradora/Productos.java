package integradora;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;

public class Productos extends JDialog {
	Conexion con = new Conexion();
	private final JPanel contentPanel = new JPanel();
	private JTextField txtbuscar;
	private JScrollPane scroll;
	private static DefaultTableModel  model;
	private JTable tabla;
	private JButton btnagregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JLabel lblMisProductos;
	public static JTextField txt_idprod;
	public static editar_productos editprod;
	public static agre_prod agregarprodu;
	private JTextField nombreprod;
	public static Menu_Cafe menu;
	private JButton button;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			Productos dialog = new Productos(menu,true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Productos (Menu_Cafe parent,boolean modal1)
	{	
		this.menu=parent;
		   this.setModal(modal1);
		   this.setTitle("Administracion de Productos");
		   setLocationRelativeTo(this);
			
		
		setBounds(100, 100, 985, 608);
		//this.setSize(this.getToolkit().getScreenSize()); 
		setResizable(false);
		setLocationRelativeTo(null);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(new Color(255, 255, 204));
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		
		String columnas[]= {"ID","Nombre","cantidad","precio"};
		scroll = new JScrollPane(); 
		model = new DefaultTableModel(null, columnas);//CREA EL MODELO DE LA TABLA ASIGNA LAS COLUMNAS
		tabla= new JTable(model){//AGREGA EL MODELO A NUESTRA TABLA
	      	  public boolean isCellEditable(int row, int column){
	      	    if(column == 1){
	      	    	return true;
	      	    }else{
	      	    	return false;
	      	    }
	      	  }
		};
		scroll.setBounds(142,267,737,200);
		scroll.setViewportView(tabla);
		contentPanel.add(scroll);
		
		tabla.addMouseListener(new MouseAdapter() {
        	public void mouseClicked(MouseEvent arg0) {
        		int  fila = tabla.getSelectedRow();
        		String id= tabla.getValueAt(fila,0 ).toString();
        		String nombre= tabla.getValueAt(fila,1).toString();
        		txt_idprod.setText(id);
        		nombreprod.setText(nombre);
        		
        	}
        });

		tabla.getColumnModel().getColumn(0).setMinWidth(50);
		tabla.getColumnModel().getColumn(0).setMaxWidth(50);
		
		tabla.getColumnModel().getColumn(1).setMinWidth(200);
		tabla.getColumnModel().getColumn(1).setMaxWidth(200);
		/*
		tabla.getColumnModel().getColumn(2).setMinWidth(150);
		tabla.getColumnModel().getColumn(2).setMaxWidth(150);
		*/
		tabla.getColumnModel().getColumn(3).setMinWidth(200);
		tabla.getColumnModel().getColumn(3).setMaxWidth(200);
		
		tabla.setRowHeight(30);
		
		tabla.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
		tabla.getTableHeader().setBackground(new Color(0, 0, 200));
		tabla.getTableHeader().setForeground(new Color(255, 255, 255));
		
		
		

		txtbuscar = new JTextField();
		txtbuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				String dato = txtbuscar.getText();
				buscar(dato);
				
			}
		});
		txtbuscar.setBounds(250, 220, 351, 36);
		contentPanel.add(txtbuscar);
		txtbuscar.setColumns(10);
		
		JLabel lblbusqueda = new JLabel("Busqueda");
		lblbusqueda.setBounds(161, 231, 79, 14);
		contentPanel.add(lblbusqueda);
		
		btnagregar = new JButton("");
		btnagregar.setToolTipText("Agregar Productos");
		btnagregar.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\src\\integradora\\carrito.png"));
		btnagregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				agregarprodu();
				//add.setVisible(true);
			}
		});
		btnagregar.setBounds(320, 131, 57, 59);
		contentPanel.add(btnagregar);
		
		btnEliminar = new JButton("");
		btnEliminar.setIcon(new ImageIcon(Productos.class.getResource("/integradora/borrar.png")));
		btnEliminar.setToolTipText("Eliminar Productos");
		//btnEliminar.setIcon(new ImageIcon(Productos.class.getResource("/integradora/cancelar.png")));
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				if(txt_idprod.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"Seleccione un producto");
			}
			else
			{
				borrar();
			}
				
				
			}
		});
		btnEliminar.setBounds(399, 131, 57, 59);
		contentPanel.add(btnEliminar);
		
		try {
			txt_idprod = new JTextField();
			//txt_idprod.setVisible(false);
			txt_idprod.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					
				}
			});
			txt_idprod.setBounds(611, 220, 86, 34);
			contentPanel.add(txt_idprod);
			txt_idprod.setColumns(10);

		
		btnEditar = new JButton("");
		btnEditar.setIcon(new ImageIcon(Productos.class.getResource("/integradora/edit.png")));
		btnEditar.setToolTipText("Editar Productos");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{	
				ventanaeditarprod();
				
				
			}
		});
		btnEditar.setBounds(482, 131, 57, 59);
		contentPanel.add(btnEditar);
		
		lblMisProductos = new JLabel("Mis Productos ");
		lblMisProductos.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 20));
		lblMisProductos.setBounds(353, 33, 190, 59);
		contentPanel.add(lblMisProductos);
		
		nombreprod = new JTextField();
		nombreprod.setBounds(707, 220, 124, 32);
		contentPanel.add(nombreprod);
		nombreprod.setColumns(10);
		
		button = new JButton("");
		button.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\src\\integradora\\Home.png"));
		button.setBounds(898, 496, 57, 51);
		contentPanel.add(button);
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ok");}
		
		
		llenatabla();
		
	}
	public Statement buscar(String dato){
		try {
			//JOptionPane.showMessageDialog(null, dato);
			String sql = "SELECT * FROM tbl_productos WHERE descripcion LIKE '%" + dato +"%'";
			PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			borrartbl();
			while(rs.next()){
				model.addRow(new Object[]{rs.getInt("id_producto"),rs.getString("nombre"),rs.getInt("cantidad"),rs.getInt("precio")});
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
		
		
		return null;
	}
	
	public static void borrartbl(){		 
		model.setRowCount(0);			     
	 }
	public Statement llenatabla(){
		try {
			String sql = "SELECT * FROM tbl_productos";
			PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				model.addRow(new Object[]{rs.getInt("id_producto"),rs.getString("nombre"),rs.getString("cantidad"),rs.getString("precio")});
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
		
		
		return null;
	}
	
	public void ventanaeditarprod()
	{
			
			if(txt_idprod.getText().length()==0)
			{
				JOptionPane.showMessageDialog(null,"Seleccione un producto");
			}
			else
			{
				editprod = new editar_productos(this, true);
				editprod.setVisible(true);
			}
				
				
			     
			
	}
	public void agregarprodu(){
		
			agregarprodu = new agre_prod(this, true);
			agregarprodu.setVisible(true);
		
		
	}
	public void borrar(){
		String txtproducto = null;
		
		int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro/a de Eliminar " + nombreprod.getText() + " ?");
		if(resp==JOptionPane.YES_OPTION)
		{
			
			
			
			try{ 
				PreparedStatement pstm = con.conectar().prepareStatement("DELETE FROM tbl_productos WHERE id_producto='"+txt_idprod.getText()+"'");
	        	int i=pstm.executeUpdate();
	        	
	        	if(i==1){        		
	        		JOptionPane.showMessageDialog(null, "producto eliminado exitosamente");
	        		
	    
	        	}
			}catch(Exception e1){
				JOptionPane.showMessageDialog(null,(e1.getMessage()));	
			}//llave del segundo catch	
	 		
		}
		else
		{
			
			JOptionPane.showMessageDialog(null,"No se Borro el Producto");
		}
		
 	}


}
