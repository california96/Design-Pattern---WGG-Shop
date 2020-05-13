package controller;
import model.Expense;
import model.Wishlist;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class WishlistOperations {
	public boolean insert(Connection connection, Wishlist wishlist) {
		boolean isSuccessful = false;
		String sql = "INSERT into wishlist(userID, categoryID, incomeSourceID, statusID, amount, date, comment) VALUES (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, wishlist.getUserID());
			ps.setInt(2, wishlist.getCategoryID());
			ps.setInt(3, wishlist.getIncomeSourceID());
			ps.setInt(4, wishlist.getStatusID());
			ps.setDouble(5, wishlist.getAmount());
			ps.setString(6, wishlist.getDate().toString());
			ps.setString(7, wishlist.getComment());
			ps.executeUpdate();
			isSuccessful = true;
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		return isSuccessful;
	}
	public ArrayList<Wishlist> getFullWishlist(Connection connection, int userID){
		ArrayList<Wishlist> wishlist = new ArrayList<Wishlist>();
		//public Expense(int expenseID, int categoryID, String category, int userID, double cost, Date date, String comment)
		//Wishlist wishlist = new Wishlist(id, userID, categoryID, category, incomeSourceID, incomeSource, statusID, status, amount, date, comment)
		String sql = "SELECT wishID, users.userID, wishlist.categoryID, categories.name, wishlist.statusID, status.status, wishlist.amount, wishlist.date, wishlist.comment\n" + 
				"FROM wishlist\n" + 
				"INNER JOIN users on users.userID = wishlist.userID\n" + 
				"INNER JOIN categories on categories.id = wishlist.categoryID\n" + 
				"INNER JOIN status on status.statusID = wishlist.statusID\n" + 
				"WHERE users.userID = ?";
		try {
			PreparedStatement prep = connection.prepareStatement(sql);
			prep.setInt(1, userID);
			ResultSet rs = prep.executeQuery();
			while(rs.next()) {
				wishlist.add(new Wishlist(rs.getInt("wishID"), rs.getInt("userID"), rs.getInt("categoryID"), rs.getString("name"), rs.getInt("statusID"), rs.getString("status"), rs.getDouble("amount"), rs.getString("date"), rs.getString("comment")));
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
		return wishlist;
	}
	public void editWishlist(Connection connection, int categoryID, int statusID, double amount, String date, String comment, int wishID) {
		String sql = "UPDATE wishlist SET categoryID = ?, statusID = ?, amount = ?, date = ?, comment = ? WHERE wishID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, categoryID);
			ps.setInt(2, statusID);
			ps.setDouble(3, amount);
			ps.setString(4, date);
			ps.setString(5, comment);
			ps.setInt(6, wishID);
			ps.executeUpdate();
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public void deleteWishlist(Connection connection, int wishID) {
		String sql = "DELETE FROM wishlist WHERE wishlist.wishID = ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, wishID);
			ps.executeUpdate();
			
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}catch(Exception e) {
			System.err.println(e.getMessage());
		}
	}
	public Wishlist retrieveWish(Connection connection, int wishID) {
		Wishlist wish = null;
		String sql = "SELECT a.wishID, a.userID, a.categoryID, b.name as 'category', a.incomeSourceID, c.name as 'incomesource', a.statusID, d.status, a.amount, a.date, a.comment\n" + 
				"FROM wishlist a\n" + 
				"INNER JOIN categories b on b.id = a.categoryID\n" + 
				"INNER JOIN categories c on a.incomeSourceID = c.id\n" + 
				"INNER JOIN users on a.userID = users.userID\n" + 
				"INNER JOIN status d on d.statusID = a.statusID\n" + 
				"WHERE a.wishID= ?";
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, wishID);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				wish = new Wishlist(rs.getInt("wishID"), rs.getInt("wishID"), rs.getInt("categoryID"), rs.getString("category"), rs.getInt("incomeSourceID"), rs.getString("incomesource"), rs.getInt("statusID"),
						rs.getString("status"), rs.getDouble("amount"), rs.getString("date"), rs.getString("comment"));
			}
		}catch(SQLException sqle) {
			System.err.println(sqle.getMessage());
		}
		return wish;
	}
}
