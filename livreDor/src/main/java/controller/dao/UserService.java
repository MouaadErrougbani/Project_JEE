package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserService {
	
	private String table = "user";
	
	
	public User ajouterUser(User user) {
	    String rq = "INSERT INTO " + table + " (name, pwd) VALUES (?, ?)";
	    try (Connection connection = BDConnection.getConnection();
	         PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS)) {

	        pstm.setString(1, user.getName());
	        pstm.setString(2, user.getPwd());

	        int isAdd = pstm.executeUpdate();
	        if (isAdd > 0) {
	            return getUser(user.getName());
	        }
	    } catch (SQLException e) {
	        e.printStackTrace(); // Log the exception for debugging
	        
	    }
	    return null;
	}

	
	public User getUser(String name) {
		String rq = "select * from " + table + " where name = ?" ;
		User user=null;
		try(Connection connection = BDConnection.getConnection();
			PreparedStatement pstm = connection.prepareStatement(rq);
				){
			pstm.setString(1, name);
			try(ResultSet rs = pstm.executeQuery()){
				if(rs.next())
					user = new User( rs.getString("name"), rs.getString("pwd"), rs.getDate("date_creation"));		
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	

}
