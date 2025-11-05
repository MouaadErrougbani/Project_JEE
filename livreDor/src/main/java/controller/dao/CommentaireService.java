package controller.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Commentaire;

public class CommentaireService {
	
	private String table = "commentaire";
	
	public Commentaire addCommentaire(Commentaire commentaire) {
		String rq = "insert into " + table + " (id_post, auteur, contene) values (?,?,?)";
		try(Connection connection = BDConnection.getConnection();
			PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS)
				){
			pstm.setInt(1, commentaire.getIdPoste());
			pstm.setString(2, commentaire.getAuteur());
			pstm.setString(3, commentaire.getContene());
			
			int isAdd = pstm.executeUpdate();
			if(isAdd>0) {
				try(ResultSet rs = pstm.getGeneratedKeys()){
					if(rs.next()) {
						int id = rs.getInt(1);
						commentaire = getCommentaire(id);
					}else{
						return null;
					}
					
				}
			}
			else{
				return null;
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return commentaire;
	}
	
	public Commentaire getCommentaire(int id) {
		String rq = "select * from " + table + " where id = ?";
		try(Connection connection = BDConnection.getConnection();
				PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS)
					){
			pstm.setInt(1, id);
			
			try(ResultSet rs = pstm.executeQuery()){
				if(rs.next()) {
					return new Commentaire(id, 
							rs.getInt("id_post"), 
							rs.getString("auteur"), 
							rs.getString("contene"), 
							rs.getDate("date_commentaire"));
				}
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public List<Commentaire> getAllCommentaire(int idPoste){
		
		String rq = "select * from " + table + " where id_post = ?";
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		
		try(Connection connection = BDConnection.getConnection();
				PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS)
					){
			pstm.setInt(1, idPoste);
			
			try(ResultSet rs = pstm.executeQuery()){
				while(rs.next()) {
					commentaires.add(new Commentaire(
							rs.getInt("id"), 
							idPoste, 
							rs.getString("auteur"), 
							rs.getString("contene"), 
							rs.getDate("date_commentaire")
							));
					
				}
			}
		}catch (SQLException e) {
			
			e.printStackTrace();
			return null;
		}
		return commentaires;
	}
	
	public boolean deleteCommentaire(int id) {
		String rq = "delete from " + table + " where id = ?";
		try(Connection connection = BDConnection.getConnection();
				PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS)
					){
			pstm.setInt(1, id);
			int isDeleted = pstm.executeUpdate();
			return isDeleted>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	
	public boolean deleteCommentaireDePoste(int idPoste) {
		
		String rq = "delete from " + table + " where id_post = ?";
		try(Connection connection = BDConnection.getConnection();
				PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS)
					){
			pstm.setInt(1, idPoste);
			int isDeleted = pstm.executeUpdate();
			return isDeleted>0;
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	

}
