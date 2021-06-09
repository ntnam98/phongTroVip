package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BEAN.StoredPost;
import DB.DBConnection;

public class StoredPostDAO {
	static Connection conn = DBConnection.CreateConnect();
	public static boolean newSP(int accId, int postId){
		String sql = "INSERT INTO StoredPost(accId, postId)\r\n" + 
					"values ('"+accId+"', '"+postId+"')";
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
	
	public static boolean deleteSP(int accId, int postId){
		String sql = "DELETE FROM StoredPost where accId='"+accId+"' and postId='"+postId+"'";
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
	
	public static boolean deleteSP1(int postId){
		String sql = "DELETE FROM StoredPost where postId='"+postId+"'";
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
	
	public static boolean deleteSP2(int accId){
		String sql = "DELETE FROM StoredPost where accId='"+accId+"'";
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
	
	public static ArrayList<StoredPost> getAllSP(int accId, int offset, int noOfRecords){
		noOfRecords += offset;
		offset++;
		String sql = "WITH sp AS\r\n" + 
				"(\r\n" + 
				"    SELECT *,\r\n" + 
				"    ROW_NUMBER() OVER (ORDER BY postId desc) AS 'RowNumber'\r\n" + 
				"    FROM StoredPost where accId='"+accId+"' and postId not in (select postId from Post where isActive = '0')" + 
				") \r\n" + 
				"SELECT * \r\n" + 
				"FROM sp\r\n" + 
				"WHERE RowNumber BETWEEN "+offset+" AND "+noOfRecords+";";
		ArrayList<StoredPost> list = new ArrayList<StoredPost>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				StoredPost fb = new StoredPost();
				fb.setAccId(rs.getInt("accId"));
				fb.setPostId(rs.getInt("postId"));
				list.add(fb);
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getTotalSP(int accId){
		String sql = "select count(*) as total from StoredPost where accId='"+accId+"' and postId not in (select postId from Post where isActive = '0')";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				return rs.getInt("total");
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return 0;
	}
}
