package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import bean.Ear;
import service.EarService;

public class EarServiceImpl implements EarService{

	private String jdbcURL;
	private String jdbcUsername;
	private String jdbcPassword;
	private Connection conn;
	
	public EarServiceImpl(String jdbcURL, String jdbcUsername, String jdbcPassword) {
		this.jdbcURL = jdbcURL;
		this.jdbcUsername = jdbcUsername;
		this.jdbcPassword = jdbcPassword;
	}
	
	public List<Ear> search(String keyword){
		List<Ear> result = new LinkedList<Ear>();
		PreparedStatement pst = null;
		try {
			conn = DBConnection.create(jdbcURL, jdbcUsername, jdbcPassword);
			String sqlCommand ="Select * from ear ";
			
			if ((keyword!=null && !"".equals(keyword))){
				sqlCommand += "WHERE "
								+ "produce LIKE ? "
								+ " OR "
								+ "type LIKE ?";
			}
			
			pst = conn.prepareStatement(sqlCommand);
			if ((keyword!=null && !"".equals(keyword))){
				pst.setString(1, "%" + keyword.toLowerCase() + "%");
				pst.setString(2, "%" + keyword.toLowerCase() + "%");
			}
			
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				Integer id = rs.getInt(1);
				String preview = rs.getString(2);
				String produce = rs.getString(3);
				String connect = rs.getString(4);
				String type = rs.getString(5);
				String price = rs.getString(6);
				String name = rs.getString(7);
			

				Ear ear = new Ear(id, preview, produce, connect, type, price, name);
				result.add(ear);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBConnection.closeConnect(conn);
			DBConnection.closeStatement(pst);
		}
		return result;
	}
	
	public boolean insertEar(Ear ear) {
		return false;
	}
	
	public boolean updateEar(Ear ear) {
		return false;
	}
	
	public boolean deleteEar(int earId) {
		return false;
	}
	
	public Ear selectEar(int earId) {
		return null;
	}
}
