package hci.sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import sercurity.Md5;

public class Insert {
	public static void main(String[] args) {
		adduser("admin", "1", "KT", 10, "game master");
		adduser("amneiht", "1", "KT", 10, "Dang Cong Can");
		adduser("kieund", "1", "KT", 2, "Nguyen Dinh Truong");
		adduser("truongnd", "1", "KT", 1, "Nguyen Dinh kieu");
		adduser("thanhpv", "1", "KT", 0, "Duy thanh");
		adduser("quannd", "1", "KT", 00, "Nguyen Ngoc Quan");
		adduser("ngoctu", "1", "Sale", 3, "Tu beo");
		adduser("minhhnt", "1", "Sale", 2, "minh beo");
	}

	public static void adduser(String use, String pass, String phong, int satus, String ten) {

		try {
			Connection conn = Con.getConn();
			long manv = Gen.genid();
			String sql = "insert into nhanvien(user,pass,ma_nhanvien) values(?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, use);
			ps.setString(2, Md5.hash(pass));
			ps.setLong(3, manv);
			ps.executeUpdate();
			ps.close();
			sql = "insert into thongtin(ma_nhanvien , ten, phong_ban,status) values(?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, manv);
			ps.setString(2, ten);
			ps.setString(3, phong);
			ps.setInt(4, satus);
			ps.executeUpdate();
			ps.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
