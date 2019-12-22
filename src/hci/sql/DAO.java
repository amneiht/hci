package hci.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import hci.emp.SubTask;
import hci.emp.login.User;
import sercurity.Md5;

public class DAO {
	public static User getUser(String user, String pass) {
		try {
			String sql = "select nv.ma_nhanvien , status ,ten , phong_ban from nhanvien nv , thongtin tt where tt.ma_nhanvien = nv.ma_nhanvien "
					+ " and user = ? and pass = ? ";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user);
			ps.setString(2, Md5.hash(pass));
			User res = new User();
			ResultSet rs = ps.executeQuery();
			rs.last();
			if (rs.getRow() < 1) {

				return null;
			}
			rs.first();
			res.setMsnv(rs.getString("ma_nhanvien"));
			res.setName(rs.getString("ten"));
			res.setPhong(rs.getString("phong_ban"));
			res.setStatus(rs.getInt("status"));
			rs.close();
			ps.close();
			con.close();
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public static void getSubEmploy(User ur, List<String> nv, List<String> msvn) {
		try {

			String sql = "select ma_nhanvien , ten from thongtin where phong_ban = ? and status < ?";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ur.getPhong());
			ps.setInt(2, ur.getStatus());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				nv.add(rs.getString("ten"));
				msvn.add(rs.getString("ma_nhanvien"));
			}
		} catch (Exception e) {
			System.out.println("loi getSubEmploy");
			e.printStackTrace();
		}

	}

	public static void setTask(User u, String string, String header, String note, String st, String ed) {
		try {
			Connection con = Con.getConn();
			PreparedStatement ps;
			String sql = "insert into task (nguoi_nhan,nguoi_giao ,ten_cv,noi_dung, bat_dau,ket_thuc,trang_thai , id) value(?,?,?,?,?,?,1,?) ";
			ps = con.prepareStatement(sql);
			ps.setString(1, string);
			ps.setString(2, u.getMsnv());
			ps.setString(3, header);
			ps.setString(4, note);
			ps.setString(5, st);
			ps.setString(6, ed);
			ps.setLong(7, Gen.genid());
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void getViewTask(User ur, List<SubTask> map, boolean d) {
		try {
			String sql;

			if (d)
				sql = "SELECT id,ten ,ten_cv ,bat_dau,ket_thuc FROM thongtin , task WHERE nguoi_giao = ma_nhanvien and nguoi_nhan= ? and trang_thai = 1";
			else
				sql = "SELECT id, ten ,ten_cv ,bat_dau,ket_thuc FROM thongtin , task WHERE nguoi_giao = ma_nhanvien and nguoi_nhan= ? and trang_thai = 2";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ur.getMsnv());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				map.add(new SubTask(rs.getString("ten_cv"), rs.getString("ten"), rs.getString("bat_dau"),
						rs.getString("ket_thuc"), rs.getString("id")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void viewLateTask(User ur, List<SubTask> map) {
		try {
			String sql;

			sql = "SELECT id,ten ,ten_cv ,bat_dau,ket_thuc FROM thongtin , task WHERE nguoi_giao = ma_nhanvien and nguoi_nhan= ? and trang_thai = 1 and ket_thuc < NOW()";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, ur.getMsnv());
			ResultSet rs = ps.executeQuery();
			rs.beforeFirst();
			while (rs.next()) {
				map.add(new SubTask(rs.getString("ten_cv"), rs.getString("ten"), rs.getString("bat_dau"),
						rs.getString("ket_thuc"), rs.getString("id")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
