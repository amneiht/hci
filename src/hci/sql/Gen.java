package hci.sql;

public class Gen {
	public static long genid() {
		return (long) (Math.random() * 900000000 + 100000000);
	}
}
