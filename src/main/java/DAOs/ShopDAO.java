package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import models.Game;
import models.Shop;

public class ShopDAO {

	private final static String GETSHOP="SELECT id, money FROM shop WHERE id=?;";
	
	private static Connection con = null;
	
	public static Shop getShop() {
		// TODO Auto-generated method stub
		Shop shop=null;

		con = utils.MDBConexion.getConexion();
		
		if (con != null) {
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				
				List<Game>games=GameDAO.getAll();
				
				ps = con.prepareStatement(GETSHOP);
				ps.setInt(1, 1);
				
				rs = ps.executeQuery();
				if (rs.next()) {
					shop = new Shop(rs.getInt("id"),games, rs.getDouble("money"));
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
		return shop;
	}
}
