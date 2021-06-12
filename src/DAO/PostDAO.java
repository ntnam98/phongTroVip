package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BEAN.Post;
import DB.DBConnection;

public class PostDAO {
	static Connection conn = DBConnection.CreateConnect();
	public static ArrayList<Post> getPosts(int accId, int offset, int noOfRecords){
		noOfRecords += offset;
		offset++;
		String sql = "WITH postt AS\r\n" + 
				"(\r\n" + 
				"    SELECT *,\r\n" + 
				"    ROW_NUMBER() OVER (ORDER BY createdAt desc) AS 'RowNumber'\r\n" + 
				"    FROM Post\r\n" + 
				"    where accId = "+accId+"\r\n" + 
				") \r\n" + 
				"SELECT * \r\n" + 
				"FROM postt\r\n" + 
				"WHERE RowNumber BETWEEN "+offset+" AND "+noOfRecords+";";
		ArrayList<Post> postList =  new ArrayList<Post>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				Post post = new Post();
				int postId = rs.getInt("postId");
				int categoryId = rs.getInt("categoryId");
				String city = rs.getString("city");
				String district = rs.getString("district");
				String ward = rs.getString("ward");
				String address = rs.getString("address");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int price = rs.getInt("price");
				int area = rs.getInt("area");
				boolean isActive = rs.getBoolean("isActive");
				String createdAt = rs.getString("createdAt");
				String updatedAt = rs.getString("updatedAt");
				post.setPostId(postId);
				post.setCategoryId(categoryId);
				post.setCity(city);
				post.setDistrict(district);
				post.setWard(ward);
				post.setPrice(price);
				post.setArea(area);
				post.setAddress(address);
				post.setContent(content);
				post.setActive(isActive);
				post.setTitle(title);
				post.setAccId(accId);
				post.setCreatedAt(createdAt);
				post.setUpdatedAt(updatedAt);
				postList.add(post);
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return postList;
	}
	
	public static Post getPost(int postId){
		String sql = "select * from Post where postId='"+postId+"'";
		Post post = new Post();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				int categoryId = rs.getInt("categoryId");
				int accId = rs.getInt("accId");
				String city = rs.getString("city");
				String district = rs.getString("district");
				String ward = rs.getString("ward");
				String address = rs.getString("address");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int price = rs.getInt("price");
				int area = rs.getInt("area");
				boolean isActive = rs.getBoolean("isActive");
				String createdAt = rs.getString("createdAt");
				String updatedAt = rs.getString("updatedAt");
				post.setAccId(accId);
				post.setPostId(postId);
				post.setCategoryId(categoryId);
				post.setCity(city);
				post.setDistrict(district);
				post.setWard(ward);
				post.setPrice(price);
				post.setArea(area);
				post.setAddress(address);
				post.setContent(content);
				post.setActive(isActive);
				post.setTitle(title);
				post.setCreatedAt(createdAt);
				post.setUpdatedAt(updatedAt);
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return post;
	}
	
	public static boolean addNewPost(int accId, String city,String district,String ward, String address,
			int categoryInt,String title,String content, int priceInt, int areaInt) {
		String sql = "INSERT INTO Post(accId, categoryId, city, district, ward,address,title,content,price,area,isActive,createdAt,updatedAt)"+
				"values ('"+accId+"', '"+categoryInt+"', N'"+city+"', N'"+district+"',N'"+ward+"',N'"+address+"',N'"+title+"',"+
						"N'"+content+"','"+priceInt+"','"+areaInt+"','1',CURRENT_TIMESTAMP,CURRENT_TIMESTAMP)";
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
	
	public static int getPostId() {
		String sql = "select top 1 postId from Post order by postId desc";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()){
				int postId = rs.getInt("postId");
				return postId;
			}
		
		} 
		catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		return 0;
	}
	
	public static int getTotalPost(int accId) {
		String sql = "select COUNT(*) as sum from Post where accId ='"+accId+"'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()){
				int toltalPost = rs.getInt("sum");
				return toltalPost;
			}
		
		} 
		catch (SQLException e) 
		{	
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean HidePost(int postId) {
		String sql = "update Post set isActive = '0' where postId = '"+postId+"'";
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

	public static boolean ShowPost(int postId) {
		String sql = "update Post set isActive = '1' where postId = '"+postId+"'";
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
	
	public static boolean DeletePost(int postId) {
		String sql = "delete from Image where postId='"+postId+"'"
				+ " delete from Post where postId='"+postId+"'";
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
	
	public static boolean ChangePost(int postId, String address, String title, String content, int price, int area) {
		String sql = "update Post\r\n" + 
				"set address=N'"+address+"',title = N'"+title+"', content=N'"+content+"', price ='"+price+"', updatedAt=CURRENT_TIMESTAMP, area='"+area+"'\r\n" + 
				"where postId = '"+postId+"'";
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
	
	public static ArrayList<Post> getPost1(String isActive,String type, String city, String district, String ward,
			int price1, int price2, int area1, int area2, int offset, int noOfRecords){
		noOfRecords += offset;
		offset++;
		String sql = "with postt as\r\n" + 
				"(\r\n" + 
				"select *,\r\n" + 
				"ROW_NUMBER() OVER (ORDER BY createdAt desc) AS 'RowNumber'\r\n" + 
				"from Post\r\n" + 
				"where isActive like '"+isActive+"' and categoryId like '"+type+"' and city like N'"+city+"' and district like N'"+district+"' and ward like N'"+ward+"'\r\n" + 
				"	and price between "+price1+" and "+price2+" and area between "+area1+" and "+area2+"\r\n" + 
				")\r\n" + 
				"select * from postt where RowNumber BETWEEN "+offset+" AND "+noOfRecords+";";
		ArrayList<Post> postList =  new ArrayList<Post>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				Post post = new Post();
				int accId = rs.getInt("accId");
				int postId = rs.getInt("postId");
				int categoryId = rs.getInt("categoryId");
				city = rs.getString("city");
				district = rs.getString("district");
				ward = rs.getString("ward");
				String address = rs.getString("address");
				String title = rs.getString("title");
				String content = rs.getString("content");
				int priceInt= rs.getInt("price");
				int areaInt = rs.getInt("area");
				boolean Active = rs.getBoolean("isActive");
				String createdAt = rs.getString("createdAt");
				String updatedAt = rs.getString("updatedAt");
				post.setAccId(accId);
				post.setPostId(postId);
				post.setCategoryId(categoryId);
				post.setCity(city);
				post.setDistrict(district);
				post.setWard(ward);
				post.setPrice(priceInt);
				post.setArea(areaInt);
				post.setAddress(address);
				post.setContent(content);
				post.setActive(Active);
				post.setTitle(title);
				post.setCreatedAt(createdAt);
				post.setUpdatedAt(updatedAt);
				postList.add(post);
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return postList;
	}
	
	public static int getTotalPost1(String isActive,String type, String city, String district, String ward, int price1,int price2, int area1, int area2){
		String sql = "select COUNT(*) as total\r\n" + 
				"from Post\r\n" + 
				"where isActive like '"+isActive+"' and categoryId like '"+type+"' and city like N'"+city+"' and district like N'"+district+"' and ward like N'"+ward+"'\r\n" + 
				"	and price between "+price1+" and "+price2+" and area between "+area1+" and "+area2+"";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			if(rs.next()){
				int total = rs.getInt("total");
				return total;
			}
						
		} 
		catch (SQLException e) 
		{			
			e.printStackTrace();
		}
		return 0;
	}
	
}
