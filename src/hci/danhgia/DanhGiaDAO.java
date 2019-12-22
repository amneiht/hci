package hci.danhgia;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;

import hci.emp.login.User;
import hci.sql.Con;
import hci.sql.Gen;

public class DanhGiaDAO {

	public static void addDanhGia(User ur, String ts, String id) {
		try {
			String sql = "insert into danhgia (id,ngay,id_nv, id_boss,nhan_xet) values(?,?,?,?,?)";
			Connection con = Con.getConn();
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setLong(1, Gen.genid());
			ps.setDate(2, new Date(System.currentTimeMillis()));
			ps.setString(3, id);
			ps.setString(4, ur.getMsnv());
			ps.setString(5, ts);
			ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
