package hci.sql;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class Con {
	private static String sql = null, user = null, pass = null;
	static final String path = "/home/anmeiht/app/code/workspace/EclipseWp/hci/resources/config.properties";
	public static void main(String[] args) {
		getConn();
	}

	private static void init() throws Exception {
		InputStream input = new FileInputStream(path);
		Properties pro = new Properties();
		pro.load(input);
		sql = String.format("%s:%s/%s", pro.getProperty("db.host"), pro.getProperty("db.port"),
				pro.getProperty("db.name"));
		user = pro.getProperty("db.user");
		pass = pro.getProperty("db.pass");

	}

	public static Connection getConn() {
		try {

			Class.forName("com.mysql.jdbc.Driver");
			if (sql == null)
				init();
			Connection con = DriverManager.getConnection(sql, user, pass);
			return con;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return null;
	}
}
