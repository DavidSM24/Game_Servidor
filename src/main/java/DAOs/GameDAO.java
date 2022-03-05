package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.Game;
import models.User;
import utils.MDBConexion;

public class GameDAO {

	private static final String GETALL = "SELECT id,name,price FROM game ORDER BY name ASC;";
	private static final String GETBYID = "SELECT id,name,price FROM game WHERE id=?;";
	private static final String GETBYUSER = "SELECT g.id, g.name, g.price FROM user_game ug "
			+ "INNER JOIN game g ON g.id=ug.id_game "
			+ "WHERE ug.id_user=?";
	private static final String INSERT = "INSERT INTO game (name,price) "
			+ "VALUES (?,?);";
	private static final String UPDATE = "UPDATE game SET name=?, price=? WHERE id=?;";
	private static final String DELETE = "DELETE FROM game WHERE id=?;";

	private static Connection con = null;

	public static List<Game> getAll() {
		// TODO Auto-generated method stub
		List<Game> games = new ArrayList<Game>();

		con = MDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALL);
				rs = ps.executeQuery();
				while (rs.next()) {
					Game aux = new Game();
					aux.setId(rs.getInt("id"));
					aux.setName(rs.getString("name"));
					aux.setPrice(rs.getDouble("price"));

					games.add(aux);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return games;
	}

	public static Game getById(int id) {
		Game result = new Game();

		con = MDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {
					result = new Game(rs.getInt("id"), rs.getString("name"),
							rs.getDouble("price"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return result;
	}

	public static List<Game> getByUser(User u) {
		List<Game> games = new ArrayList<Game>();

		con = MDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETBYUSER);
				ps.setInt(1, u.getId());
				rs = ps.executeQuery();
				
				while (rs.next()) {
					Game aux = new Game();
					aux.setId(rs.getInt("id"));
					aux.setName(rs.getString("name"));
					aux.setPrice(rs.getDouble("price"));

					
					games.add(aux);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return games;
	}
	
	public static void insert(Game g) {
		int result = -1;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = MDBConexion.getConexion();

		if (con != null) {
			try {
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, g.getName());
				ps.setDouble(2, g.getPrice());

				ps.executeUpdate();

				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					g.setId(rs.getInt(1));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}

		}
	}
	
	public static void update(Game g) {
		
		int rs = 0;
		PreparedStatement ps = null;
		Connection con = MDBConexion.getConexion();

		if (con != null) {
			try {
				ps = con.prepareStatement(UPDATE);
				ps.setString(1, g.getName());
				ps.setDouble(2, g.getPrice());

				ps.setInt(3, g.getId());
				rs = ps.executeUpdate();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}

			finally {
				try {
					ps.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}

		}
	}
	
	public static void delete(Game g) {
		int rs=0;
		Connection con = MDBConexion.getConexion();
		
		if (con != null) {
			try {
				
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setInt(1,g.getId());
				rs =q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
			}
		}
	}
}
