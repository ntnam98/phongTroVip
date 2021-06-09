package DB;
import java.sql.*;

public class DBConnection {
		public static Connection CreateConnect(){
		 Connection conn = null;
		 String database = "phongTroVip";
		 String url = "jdbc:jtds:sqlserver://localhost:1433/"
		            + database + ";instance=SQLEXPRESS";
		 String username = "sa";
		 String password = "123456";
		 
		 // load driver
		 try {
			Class.forName("net.sourceforge.jtds.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		 
		 // tao ket noi 
		 try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e){
			e.printStackTrace();
		}

		 return conn;
		 
	 }
	  
}
