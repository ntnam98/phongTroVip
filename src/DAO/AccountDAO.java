 package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import BEAN.Account;
import DB.DBConnection;

public class AccountDAO {
	static Connection conn = DBConnection.CreateConnect();
	public static boolean validateAcc(String userName, String password) {
		String sql = "SELECT * \r\n" + 
				"FROM Account\r\n" + 
				"WHERE userName = '"+userName+"'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()) {
			String pass = rs.getString("password");
			if(password.equals(pass)) {
				return true;
			}
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isActiveAcc(String userName) {
		String sql = "SELECT * from Account where userName='"+userName+"'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()) {
			boolean isActive = rs.getBoolean("isActive");
			if(isActive) {
				return true;
			}
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public static Account getAcc(String userName){
		String sql = "SELECT * \r\n" + 
				"FROM Account\r\n" + 
				"WHERE userName = '"+userName+"'";
		Account acc =  new Account();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				int accId = rs.getInt("accId");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String createdAt =  rs.getString("createdAt");
				String role = rs.getString("role");
				acc.setAccId(accId);
				acc.setName(name);
				acc.setRole(role);
				acc.setPassword(password);
				acc.setUsername(userName);
				acc.setPhone(phone);
				acc.setEmail(email);
				acc.setCreatedAt(createdAt);
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return acc;
	}

	public static Account getAcc(int accId){
		String sql = "SELECT * \r\n" + 
				"FROM Account\r\n" + 
				"WHERE accId = '"+accId+"'";
		Account acc =  new Account();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				String userName = rs.getString("userName");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String createdAt =  rs.getString("createdAt");
				acc.setAccId(accId);
				acc.setName(name);
				acc.setPassword(password);
				acc.setUsername(userName);
				acc.setPhone(phone);
				acc.setEmail(email);
				acc.setCreatedAt(createdAt);
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return acc;
	}
	
	public static boolean insertAccount(String userName,String name,String phone, String password, String role) {
		String sql = "insert into Account(username, name, password, phone, isActive, role, createdAt) "
				+ "values('"+userName+"', N'"+name+"','"+password+"','"+phone+"','1','"+role+"',CURRENT_TIMESTAMP)";
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
	
	public static boolean deleteAcc(int accId) {
		String sql = "delete from Account where accId = '"+accId+"';";
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
	
	public static boolean kiemTraDangKi(String userName, String phone) {
		String sql = "select * from Account where userName = '"+userName+"'or phone = '"+phone+"'";
		try {
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()){
				return false;
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public static boolean changePassword(String userName, String pw) {
		String sql = "update Account set password='"+pw+"' where userName ='"+userName+"'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean changeInfo(String userName, String name, String email) {
		String sql = "update Account set name=N'"+name+"', email='"+email+"' where userName ='"+userName+"'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return false;
	}
	
	public static ArrayList<Account> getAllAccount(String role, int offset, int noOfRecords){
		noOfRecords += offset;
		offset++;
		String sql = "WITH us as\r\n" + 
				"( select *,\r\n" + 
				"  ROW_NUMBER() OVER (ORDER BY accId) AS 'RowNumber'\r\n" + 
				"from Account\r\n" + 
				"where role like'"+role+"')\r\n" + 
				"select *\r\n" + 
				"from us\r\n" + 
				"where RowNumber between "+offset+" and "+noOfRecords+" ";
		ArrayList<Account> list =  new ArrayList<Account>();
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			while(rs.next()){
				Account acc = new Account();
				int id = rs.getInt("accId");
				String userName = rs.getString("userName");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				boolean isActive = rs.getBoolean("isActive");
				role = rs.getString("role");
				String createdAt =  rs.getString("createdAt");
				acc.setAccId(id);
				acc.setName(name);
				acc.setPassword(password);
				acc.setUsername(userName);
				acc.setPhone(phone);
				acc.setEmail(email);
				acc.setRole(role);
				acc.setActive(isActive);
				acc.setCreatedAt(createdAt);
				acc.setActive(rs.getBoolean("isActive"));
				list.add(acc);
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return list;
	}
	
	public static int getTotalAcc(String role) {
		String sql = "select count(*) as count from Account where role like '"+role+"'";
		try 
		{
			PreparedStatement ptmt = conn.prepareStatement(sql);
			
			ResultSet rs = ptmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("count");
			}
						
		} 
		catch (SQLException e) 
		{
			
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean LockAcc(int accId) {
		String sql = "update Account set isActive = '0' where accId = '"+accId+"'";
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
	
	public static boolean unLockAcc(int accId) {
		String sql = "update Account set isActive = '1' where accId = '"+accId+"'";
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