package hci.danhgia.nhanvien;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import hci.emp.login.User;
import hci.sql.Con;

public class DanhGiaDao {

	public static void getInfo(User p, List<DanhGia> map) {
		try {
			String sql = "SELECT ten , ngay , nhan_xet FROM danhgia , thongtin WHERE id_boss = ma_nhanvien and id_nv = ?";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getMsnv());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				map.add(new DanhGia(rs.getString("ten"), rs.getString("ngay"), rs.getString("nhan_xet")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	// public static
}
