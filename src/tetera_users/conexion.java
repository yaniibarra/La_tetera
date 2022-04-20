package tetera_users;
import java.sql.Connection;


  
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class conexion {
	
	public String user="root";
	public String pass="";
	public String server="localhost";
	public String bd= "bistro_punto_venta";
	public String url = "jdbc:mysql://"+server+"/"+bd; 
	public static int id_user=0;
	
	public Connection cn =null;
	//estable la conexion 
	public PreparedStatement pst= null;
	public ResultSet rs = null;
	
	public Connection conetar(){
		Connection cn=null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url, user, pass);
			//JOptionPane.showMessageDialog(null, "Conectando a base de datos");
			
		}
		catch(SQLException e){
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		catch(ClassNotFoundException e){
			JOptionPane.showMessageDialog(null,e.getMessage());
		}
		return cn;
	
	}
	public int login(String id, String pass)
	{
		int res=0;
		try {
			String sql= "SELECT * FROM usuarios_sistema where user='"+id+"'";
			PreparedStatement pstm =conetar().prepareStatement(sql);
			pstm.executeQuery();
			ResultSet rs = pstm.executeQuery();
			int acum=0;
			
			if(rs.next())
			{
			String passbd= rs.getString("pass");
			if(passbd.equals(pass)){
				id_user = rs.getInt("id_usuario");
				
				 res=1;		 
			}
		}
	}catch (Exception e) {
			
		}
		return res;
		
	}
}

