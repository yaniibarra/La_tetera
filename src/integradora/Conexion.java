package integradora;
import java.sql.*;

import javax.swing.JOptionPane;
public class Conexion 
{
	public String bd="bistro_punto_venta";
	public String user="root";
	public String pasword="";
	public String url="jdbc:mysql://localhost/"+bd;
	Connection con=null;
	public Connection conectar()
	{
		Connection con=null;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection(url,user,pasword);
		} catch (Exception e)
		{
			JOptionPane.showMessageDialog(null,"ERROR DE CONEXIÓN REVISE PORFAVOR: ");
		}
		return con;
	}//aqui termina y cierra la conexion
}
	

