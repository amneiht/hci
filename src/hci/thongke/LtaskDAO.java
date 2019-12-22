package hci.thongke;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import hci.emp.login.User;
import hci.sql.Con;

public class LtaskDAO {
	protected static void getDeadLineList(User p, List<Ltask> map) {
		try {
			String sql = "select ten , dem from (" + "SELECT nguoi_nhan , count(nguoi_nhan) dem from task "
					+ "WHERE ket_thuc < NOW() and trang_thai =1 and nguoi_nhan in "
					+ "(SELECT ma_nhanvien FROM thongtin where phong_ban= ? and status < ?) GROUP by nguoi_nhan) b2 , thongtin tt "
					+ "WHERE b2.nguoi_nhan = tt.ma_nhanvien";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getPhong());
			ps.setInt(2, p.getStatus());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				map.add(new Ltask(rs.getString("ten"), rs.getString("dem")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void getDoneTask(User p, List<Ltask> map) {
		try {
			String sql = "select ten , dem from (" + "SELECT nguoi_nhan , count(nguoi_nhan) dem from task "
					+ "WHERE MONTH(ket_thuc)>= MONTH(NOW()) and trang_thai =2 and nguoi_nhan in "
					+ "(SELECT ma_nhanvien FROM thongtin where phong_ban= ? and status < ? ) GROUP by nguoi_nhan) b2 , thongtin tt "
					+ "WHERE b2.nguoi_nhan = tt.ma_nhanvien";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getPhong());
			ps.setInt(2, p.getStatus());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				map.add(new Ltask(rs.getString("ten"), rs.getString("dem")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}

	public static void getOngoingTask(User p, List<Ltask> map) {
		try {
			String sql = "select ten , dem from (" + "SELECT nguoi_nhan , count(nguoi_nhan) dem from task "
					+ "WHERE MONTH(ket_thuc)>= MONTH(NOW()) and trang_thai =1 and nguoi_nhan in "
					+ "(SELECT ma_nhanvien FROM thongtin where phong_ban= ? and status < ? ) GROUP by nguoi_nhan) b2 , thongtin tt "
					+ "WHERE b2.nguoi_nhan = tt.ma_nhanvien";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, p.getPhong());
			ps.setInt(2, p.getStatus());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				map.add(new Ltask(rs.getString("ten"), rs.getString("dem")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			System.err.println(e);
		}
	}
}
