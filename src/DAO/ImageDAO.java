package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DBConnection;

public class ImageDAO {
	static Connection conn = DBConnection.CreateConnect();
	public static String getUrl(int postId){
		String sql = "select * from Image where postId ='"+postId+"'";
		String url =  new String();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				url = rs.getString("url");
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return url;
	}
	
	public static boolean addImage(int postId, String url) {
		String sql = "INSERT INTO Image(postId, url)"+
				"values ('"+postId+"', '"+url+"')";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()){
				return true;
			}
		
		} 
		catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<String> getAllUrl(int postId){
		String sql = "select * from Image where postId ='"+postId+"'";
		ArrayList<String> List =  new ArrayList<String>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				String url = rs.getString("url");
				List.add(url);
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return List;
	}
}
