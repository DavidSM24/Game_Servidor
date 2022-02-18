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

public class UserDAO {

	private static final String GETALL = "SELECT id,username,password,money,admin FROM user ORDER BY username ASC;";
	private static final String GETBYID = "SELECT id,username,password,money,admin FROM user WHERE id=?;";
	private static final String INSERT = "INSERT INTO user (username,password,money,admin) "
			+ "VALUES (?,?,?,?);";
	private static final String UPDATE = "UPDATE user SET username=?, password=?, money=?, admin=? WHERE id=?;";
	private static final String DELETE = "DELETE FROM user WHERE id=?;";

	private static Connection con = null;

	public static List<User> getAll() {
		// TODO Auto-generated method stub
		List<User> users = new ArrayList<User>();

		con = MDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETALL);
				rs = ps.executeQuery();
				while (rs.next()) {
					User aux = new User();
					aux.setId(rs.getInt("id"));
					aux.setUsername(rs.getString("username"));
					aux.setPassword(rs.getString("password"));
					aux.setAdmin(rs.getBoolean("admin"));
					aux.setMoney(rs.getDouble("money"));

					List<Game> games=GameDAO.getByUser(aux);
					aux.setGames(games);
					
					users.add(aux);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					ps.close();
					rs.close();
				} catch (SQLException e) {
					// TODO: handle exception
				}
			}
		}
		return users;
	}

	public static User getById(int id) {
		User result = new User();

		con = MDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETBYID);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				if (rs.next()) {

					result.setId(rs.getInt("id"));
					result.setUsername(rs.getString("username"));
					result.setPassword(rs.getString("password"));
					result.setAdmin(rs.getBoolean("admin"));
					result.setMoney(rs.getDouble("money"));
					
					List<Game> games=GameDAO.getByUser(result);
					result.setGames(games);
;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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
	
	public static User getUsername(String username) {
		User result = null;

		con = MDBConexion.getConexion();
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				ps = con.prepareStatement(GETBYID);
				ps.setString(1, username);
				rs = ps.executeQuery();
				if (rs.next()) {

					result.setId(rs.getInt("id"));
					result.setUsername(rs.getString("username"));
					result.setPassword(rs.getString("password"));
					result.setAdmin(rs.getBoolean("admin"));
					result.setMoney(rs.getDouble("money"));
					
					List<Game> games=GameDAO.getByUser(result);
					result.setGames(games);
;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
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

	public static void insert(User u) {
		int result = -1;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection con = MDBConexion.getConexion();

		if (con != null) {
			try {
				ps = con.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);
				
				ps.setString(1, u.getUsername());
				ps.setString(2, u.getPassword());
				ps.setDouble(3, u.getMoney());
				ps.setBoolean(4, u.isAdmin());

				ps.executeUpdate();

				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					u.setId(rs.getInt(1));
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
					e.printStackTrace();
				}
			}

		}
	}
	
	public static void update(User u) {
		
		int rs = 0;
		PreparedStatement ps = null;
		Connection con = MDBConexion.getConexion();

		if (con != null) {
			try {
				ps = con.prepareStatement(UPDATE);
				ps.setString(1, u.getUsername());
				ps.setString(2, u.getPassword());
				ps.setDouble(3, u.getMoney());
				ps.setBoolean(4, u.isAdmin());

				ps.setInt(5, u.getId());
				rs = ps.executeUpdate();

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
	
	public static void delete(User u) {
		int rs=0;
		Connection con = MDBConexion.getConexion();
		
		if (con != null) {
			try {
				
				PreparedStatement q=con.prepareStatement(DELETE);
				q.setInt(1,u.getId());
				rs =q.executeUpdate();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void buyGame(User u, Game g) {
		
		//ELIMINAR TODOS LOS REGISTROS EN USER_GAME DE ESTE USUARIO EN EL DELETE DE USER
		//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	}

}

