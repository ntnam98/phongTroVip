package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DBConnection;
import BEAN.FeedBack;
public class FeedBackDAO {
	static Connection conn = DBConnection.CreateConnect();
	public static boolean newFb(String name, String phone, String content){
		String sql = "INSERT INTO FeedBack(name, phone, content, isSolve, createdAt)\r\n" + 
					"values (N'"+name+"', '"+phone+"',N'"+content+"','0',CURRENT_TIMESTAMP)";
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
	
	public static ArrayList<FeedBack> getFb(int offset, int noOfRecords){
		noOfRecords += offset;
		offset++;
		String sql = "WITH fb AS\r\n" + 
				"(\r\n" + 
				"    SELECT *,\r\n" + 
				"    ROW_NUMBER() OVER (ORDER BY createdAt desc) AS 'RowNumber'\r\n" + 
				"    FROM FeedBack\r\n" + 
				") \r\n" + 
				"SELECT * \r\n" + 
				"FROM fb\r\n" + 
				"WHERE RowNumber BETWEEN "+offset+" AND "+noOfRecords+";";
		ArrayList<FeedBack> list = new ArrayList<FeedBack>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				FeedBack fb = new FeedBack();
				int fbId = rs.getInt("fbId");
				String name = rs.getString("name");
				String phone = rs.getString("phone");
				String content = rs.getString("content");
				boolean isSolve = rs.getBoolean("isSolve");
				String createdAt =  rs.getString("createdAt");
				fb.setFbId(fbId);
				fb.setName(name);
				fb.setPhone(phone);
				fb.setContent(content);
				fb.setSolve(isSolve);
				fb.setCreatedAt(createdAt);
				list.add(fb);
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getTotalFb(){
		String sql = "select count(*) as total from FeedBack";
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
	
	public static boolean deleteFb(int fbId){
		String sql = "delete from FeedBack where fbId = '"+fbId+"'";
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
	
	public static boolean solveFb(int fbId){
		String sql = "update FeedBack set isSolve = '1' where fbId = '"+fbId+"'";
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
}
