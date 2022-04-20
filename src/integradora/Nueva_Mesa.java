package integradora;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;



import javax.swing.JComboBox;
import javax.swing.ImageIcon;
import java.awt.event.ItemListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

public class Nueva_Mesa extends JDialog {

	private final JPanel contentPanel = new JPanel();
	Conexion con=new Conexion();
	public static Menu_Cafe menu;
	public JComboBox comboMesa;

	
	public static void main(String[] args) {
		try {
			Nueva_Mesa dialog = new Nueva_Mesa (menu, true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Nueva_Mesa(Menu_Cafe parent,boolean modal1) 
	{	
		
		   this.menu=parent;
		   this.setModal(modal1);
		   this.setTitle("Nueva Mesa");
		   setLocationRelativeTo(this);
			
		   setBounds(100, 100, 386, 218);
			setLocationRelativeTo(null);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			contentPanel.setLayout(null);
			getContentPane().add(contentPanel, BorderLayout.CENTER);
		
			
			JButton btnAceptar = new JButton("");
			btnAceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0)
				{	
					if(comboMesa.getSelectedIndex()==0){
						JOptionPane.showMessageDialog(null, "Seleccione una mesa");
					}else{
						actualizar();
						menu.obtenermesas();
						dispose();
					}
					
					
					
				}
			});
			btnAceptar.setIcon(new ImageIcon("C:\\Users\\FUT54\\workspace\\Estudio_pro\\img\\tick.png"));
			btnAceptar.setBounds(267, 61, 81, 52);
			contentPanel.add(btnAceptar);
			
			comboMesa = new JComboBox();
			comboMesa.setBounds(47, 61, 150, 35);
			contentPanel.add(comboMesa);
			{
				
			}
			
			obtenermesas1();
	}
	
	//Aqui estan los metodos para las consultas a base de Datos
	
	public void obtenermesas1()
	{
		String sql2 = "";
		try {
			sql2="SELECT *FROM tbl_mesas WHERE estado='0'";
			PreparedStatement pst = con.conectar().prepareStatement(sql2);
			ResultSet rs = pst.executeQuery();
			comboMesa.addItem("Seleccionar mesa");
			while(rs.next())
			{
				comboMesa.addItem(rs.getString("numero"));
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
		}
			
	}

	
	
	public void actualizar()
	{
		try 
		{
			
				
			String sql="UPDATE tbl_mesas SET estado='1' WHERE numero='"+comboMesa.getSelectedItem()+"'" ;
			PreparedStatement pstm=con.conectar().prepareStatement(sql);
			int re=pstm.executeUpdate();
			
			
			if(re>0)
			{
				
				JOptionPane.showMessageDialog(null,"MESA:  "+comboMesa.getSelectedItem()+"  ABIERTA");
				//combomesas.getEditor().setItem("ya no esta");
				//menu.seleccionarmesa();
				
				
				
			}
		} catch (Exception e) 
			{
				JOptionPane.showMessageDialog(null,"Error: try de actualizar"+e.getMessage());
			}
		
	}
}

