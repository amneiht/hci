package hci.emp.edit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import hci.sql.Con;

public class DetailDAO {

	protected static void getInfo(DetailTaskVm dm, String id) {
		try {
			String sql = "select ten_cv,noi_dung,bat_dau,ket_thuc,ghi_chu,trang_thai from task where id = ?";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			rs.first();
			dm.setBat_dau("Ng\u00e0y b\u1eaft \u0111\u1ea7u :" + rs.getString("bat_dau"));
			dm.setKet_thuc("Ng\u00e0y k\u1ebft th\u00fac: " + rs.getString("ket_thuc"));
			dm.setTieu_de(rs.getString("ten_cv"));
			dm.setNoi_dung(rs.getString("noi_dung"));
			dm.setTrang_thai(rs.getInt("trang_thai"));
			dm.setNote(rs.getString("ghi_chu"));
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static boolean update(String id, String k, int d) {
		try {
			String sql;
			Connection con = Con.getConn();
			PreparedStatement ps = null;
			if (d == -1) {
				sql = " update task set ghi_chu = ? where id = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, k);
				ps.setString(2, id);
			} else {
				sql = " update task set ghi_chu = ? , trang_thai = ? where id = ?";
				ps = con.prepareStatement(sql);
				ps.setString(1, k);
				ps.setInt(2, d);
				ps.setString(3, id);
			}
			ps.executeUpdate();
			ps.close();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

}
