package model;

import java.sql.*;
import java.util.Vector;

public class RentalModel {
    private Connection conn;

    public RentalModel() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:gocarindo.db");
            Statement stmt = conn.createStatement();
            stmt.execute("CREATE TABLE IF NOT EXISTS penyewaan ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nama TEXT,"
                    + "kontak TEXT,"
                    + "jenis_mobil TEXT,"
                    + "durasi INTEGER,"
                    + "total_biaya INTEGER,"
                    + "status_pembayaran TEXT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void tambahData(String nama, String kontak, String jenis, int durasi, String status) {
        int total = durasi * 300000;
        try (PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO penyewaan (nama, kontak, jenis_mobil, durasi, total_biaya, status_pembayaran) VALUES (?, ?, ?, ?, ?, ?)")) {
            ps.setString(1, nama);
            ps.setString(2, kontak);
            ps.setString(3, jenis);
            ps.setInt(4, durasi);
            ps.setInt(5, total);
            ps.setString(6, status);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateData(int id, String nama, String kontak, String jenis, int durasi, String status) {
        int total = durasi * 300000;
        try (PreparedStatement ps = conn.prepareStatement(
                "UPDATE penyewaan SET nama=?, kontak=?, jenis_mobil=?, durasi=?, total_biaya=?, status_pembayaran=? WHERE id=?")) {
            ps.setString(1, nama);
            ps.setString(2, kontak);
            ps.setString(3, jenis);
            ps.setInt(4, durasi);
            ps.setInt(5, total);
            ps.setString(6, status);
            ps.setInt(7, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteData(int id) {
        try (PreparedStatement ps = conn.prepareStatement("DELETE FROM penyewaan WHERE id=?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Vector<Vector<Object>> getAllData() {
        Vector<Vector<Object>> data = new Vector<>();
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery("SELECT * FROM penyewaan")) {
            while (rs.next()) {
                Vector<Object> row = new Vector<>();
                for (int i = 1; i <= 7; i++) {
                    row.add(rs.getObject(i));
                }
                data.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return data;
    }
}
