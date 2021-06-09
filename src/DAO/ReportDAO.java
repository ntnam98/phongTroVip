package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DB.DBConnection;
import BEAN.Report;
public class ReportDAO {
	
	static Connection conn = DBConnection.CreateConnect();
	public static boolean newRp(int postId, String type, String phone, String content){
		String sql = "INSERT INTO Report(postId, type, phone, content, isSolve, createdAt)\r\n" + 
					"values ('"+postId+"',N'"+type+"', '"+phone+"',N'"+content+"','0',CURRENT_TIMESTAMP)";
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
	
	public static ArrayList<Report> getRp(int offset, int noOfRecords){
		noOfRecords += offset;
		offset++;
		String sql = "WITH rp AS\r\n" + 
				"(\r\n" + 
				"    SELECT *,\r\n" + 
				"    ROW_NUMBER() OVER (ORDER BY createdAt desc) AS 'RowNumber'\r\n" + 
				"    FROM Report\r\n" + 
				") \r\n" + 
				"SELECT * \r\n" + 
				"FROM rp\r\n" + 
				"WHERE RowNumber BETWEEN "+offset+" AND "+noOfRecords+";";
		ArrayList<Report> list = new ArrayList<Report>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				Report rp = new Report();
				int rpId = rs.getInt("rpId");
				int postId = rs.getInt("postId");
				String type = rs.getString("type");
				String phone = rs.getString("phone");
				String content = rs.getString("content");
				boolean isSolve = rs.getBoolean("isSolve");
				String createdAt =  rs.getString("createdAt");
				rp.setRpId(rpId);
				rp.setPostId(postId);
				rp.setType(type);
				rp.setPhone(phone);
				rp.setContent(content);
				rp.setSolve(isSolve);
				rp.setCreatedAt(createdAt);
				list.add(rp);
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getTotalRp(){
		String sql = "select count(*) as total from Report";
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
	
	public static boolean deleteRp(int rpId){
		String sql = "delete from Report where rpId = '"+rpId+"'";
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
	
	public static boolean deleteRp1(int postId){
		String sql = "delete from Report where postId = '"+postId+"'";
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
	
	public static boolean solveRp(int rpId){
		String sql = "update Report set isSolve = '1' where rpId = '"+rpId+"'";
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
