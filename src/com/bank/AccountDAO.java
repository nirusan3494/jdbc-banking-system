package com.bank;

import java.sql.*;

public class AccountDAO {

    public boolean hasSufficientBalance(Connection con, int id, double amount)
            throws SQLException {

        String query = "SELECT balance FROM accounts WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);

        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getDouble("balance") >= amount;
        }
        return false;
    }

    public int debit(Connection con, int id, double amount)
            throws SQLException {

        String query = "UPDATE accounts SET balance = balance - ? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        return ps.executeUpdate();
    }

    public int credit(Connection con, int id, double amount)
            throws SQLException {

        String query = "UPDATE accounts SET balance = balance + ? WHERE id=?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setDouble(1, amount);
        ps.setInt(2, id);
        return ps.executeUpdate();
    }
}
