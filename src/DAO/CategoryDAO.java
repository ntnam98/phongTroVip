package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import DB.DBConnection;

public class CategoryDAO {
	static Connection conn = DBConnection.CreateConnect();	
	public static String getCategory(int categoryId){
		String sql = "select * from Category where categoryId ='"+categoryId+"'";
		String category =  new String();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				category = rs.getString("name");
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return category;
	}
}
