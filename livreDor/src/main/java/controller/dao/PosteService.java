package controller.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Poste;

public class PosteService {
	String table = "paste";

	public Poste addPoste(Poste poste) {
		String rq = "insert into " + table + " (titre_de_livre, contene, auteur) values(?, ?, ?)" ;

		try(Connection connection = BDConnection.getConnection();
			PreparedStatement pstm = connection.prepareStatement(rq, PreparedStatement.RETURN_GENERATED_KEYS);
				){
			
			pstm.setString(1, poste.getTitel());
			pstm.setString(2, poste.getContent());
			pstm.setString(3, poste.getAuteur());
			
			int isAdd = pstm.executeUpdate();
			if(isAdd>0)
				try(ResultSet rs = pstm.getGeneratedKeys()){
					if(rs.next()) {
						poste = getPoste(rs.getInt(1));
					}
					else return null;
					
				}
			
		}catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		return poste;
	}
	
	public Poste getPoste(int id) {
		String fetchQuery = "SELECT * FROM " + table + " WHERE id = ?";
        Poste poste = null;
        
		try (Connection connection = BDConnection.getConnection();
				PreparedStatement fetchPstm = connection.prepareStatement(fetchQuery)) {
            fetchPstm.setInt(1, id);
            try (ResultSet fetchRs = fetchPstm.executeQuery()) {
                if (fetchRs.next()) {
                	poste = new Poste(
                			id, 
                			fetchRs.getString("auteur"),
                			fetchRs.getString("titre_de_livre"),
                			fetchRs.getString("contene"),
                			fetchRs.getDate("date_creation"),
                			fetchRs.getDate("date_modification")
                			);
                    
                }
            }
        }catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return poste;
	}
	
	public Poste updatePoste(Poste poste) {
		String rq = "update " + table + " set "
	            + "titre_de_livre = ?, "
	            + "contene = ?, "
	            + "date_modification = ? "
	            + "where id = ? and auteur = ?"; 	
		Date date = Date.valueOf(LocalDate.now());
		try(Connection connection = BDConnection.getConnection();
			PreparedStatement pstm = connection.prepareStatement(rq);
				){
			
			pstm.setString(1, poste.getTitel());
			pstm.setString(2, poste.getContent());
			pstm.setDate(3,	 date);
			pstm.setInt(4, poste.getId());
			pstm.setString(5, poste.getAuteur());
			int isUpdate = pstm.executeUpdate();
			if(isUpdate > 0)
				poste.setDateModification(date);
			else return null;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		return poste;
	}
	
	public boolean deletePoste(int id) {
	    String rq = "DELETE FROM " + table + " WHERE id = ? ";

	    try (Connection connection = BDConnection.getConnection();
	         PreparedStatement pstm = connection.prepareStatement(rq)) {

	        pstm.setInt(1, id);

	        int rowsAffected = pstm.executeUpdate();

	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        e.printStackTrace(); // Log the exception
	        return false; // Return false if an exception occurs
	    }
	}
	
	public List<Poste> getAllPoste(){
		String rq = "select * from " + table;
		List<Poste> postes = new ArrayList<Poste>();
		try(Connection connection = BDConnection.getConnection();
		         PreparedStatement pstm = connection.prepareStatement(rq);
		         ResultSet fetchRs = pstm.executeQuery()){
			
			while(fetchRs.next()) {
				postes.add(new Poste(
						fetchRs.getInt("id"), 
            			fetchRs.getString("auteur"),
            			fetchRs.getString("titre_de_livre"),
            			fetchRs.getString("contene"),
            			fetchRs.getDate("date_creation"),
            			fetchRs.getDate("date_modification")
						));
			}
			
			
			
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
		return postes;
	}
	
}
