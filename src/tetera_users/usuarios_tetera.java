package tetera_users;

import java.awt.BorderLayout;


import java.awt.FlowLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ComponentOrientation;

public class usuarios_tetera extends JDialog {

	private final JPanel pnl1 = new JPanel();
	private JTextField textBuscar;
	private PreparedStatement pstm = null;
	private JScrollPane scroll;
	private static DefaultTableModel model;
	private JTable tabla;
      //usuarios_tetera con= new usuarios_tetera();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			usuarios_tetera dialog = new usuarios_tetera();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public usuarios_tetera() {
		setTitle("Sistema - La tetera bistro cafe");
		setBounds(100, 100, 755, 614);
		getContentPane().setLayout(new BorderLayout());
		pnl1.setBackground(new Color(240, 230, 140));
		pnl1.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(pnl1, BorderLayout.CENTER);
		pnl1.setLayout(null);
		{
			JButton button = new JButton("");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Agregar dialog= new Agregar();
					dialog.setVisible(true);
				
				}
			});
			button.setToolTipText("Agrege un usuario");
			button.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\agregaruser.jpg"));
			button.setBounds(117, 37, 80, 84);
			pnl1.add(button);
		}
		{
			JButton btnNewButton = new JButton("");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) 
				{
									}
			});
			btnNewButton.setToolTipText("Borre el empleado");
			btnNewButton.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\basura.jpg"));
			btnNewButton.setBounds(306, 37, 80, 84);
			pnl1.add(btnNewButton);
		}
		{
			JButton btneditar = new JButton("");
			btneditar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					modific_tetera dia= new modific_tetera();
					dia.setVisible(true);
					
				}
			});
			btneditar.setToolTipText("EDITAR EMPLEADO");
			btneditar.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Pictures\\editar2.png"));
			btneditar.setBounds(504, 36, 80, 85);
			pnl1.add(btneditar);
		}
		
		textBuscar = new JTextField();
		textBuscar.setToolTipText("ingrese el nombre");
		textBuscar.setBounds(168, 165, 371, 39);
		pnl1.add(textBuscar);
		textBuscar.setColumns(10);
		
		String columnas []={"ID", "NOMBRE", "TELEFONO", "EMAIL"};//array para crear columnas
		scroll =new JScrollPane();
		model = new DefaultTableModel(null, columnas);// crea el modelo de la tabla asigna las columnas
		tabla= new JTable(model);//agrega el modelo a mi tabla
		scroll.setBounds(101, 245, 546, 204);
		scroll.setViewportView(tabla);
		pnl1.add(scroll);
		
	tabla.getColumnModel().getColumn(0).setMinWidth(30);
	tabla.getColumnModel().getColumn(0).setMinWidth(30);
	
	tabla.getColumnModel().getColumn(1).setMinWidth(150);
	tabla.getColumnModel().getColumn(1).setMinWidth(150);
	
	tabla.getColumnModel().getColumn(2).setMinWidth(70);
	tabla.getColumnModel().getColumn(2).setMinWidth(70);
	
	tabla.getColumnModel().getColumn(3).setMinWidth(100);
	tabla.getColumnModel().getColumn(3).setMinWidth(100);
	
	tabla.setRowHeight(30);
	
		
		JLabel lblBuscar = new JLabel("Buscar");
		lblBuscar.setIcon(new ImageIcon("C:\\Users\\yani lizeth\\Downloads\\lupa.png"));
		lblBuscar.setBounds(74, 161, 90, 43);
		pnl1.add(lblBuscar);
	}
	
	public Statement llenatabla(){
		try {
			String sql = "SELECT * FROM tbl_empleados";
		
			//PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			while(rs.next()){
				model.addRow(new Object[]{rs.getInt("id_empleado"), rs.getString("nombre"),rs.getString("telefono"),rs.getString("email")});
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
		
		
		return null;
	}
	public static void borrartbl(){
		model.setRowCount(0);
	}
	
	public Statement buscar(String dato){
		try {
			String sql = "SELECT * FROM tbl_empleados WHERE nombre LIKE '%"+ dato +"%'";
		
			//PreparedStatement pstm = con.conectar().prepareStatement(sql);
			ResultSet rs = pstm.executeQuery();
			
			borrartbl();
			
			while(rs.next()){
				model.addRow(new Object[]{rs.getInt("id_empleado"), rs.getString("nombre"),rs.getString("telefono"),rs.getString("email")});
			}
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error" + e);
		}
		
		
		return null;
	}
}
