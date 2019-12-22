package hci.emp;

public class SubTask {
	private String name;

	private String date;

	private String enddate;

	private String sep;

	/**
	 * id cua cai task
	 */
	private String id;

	public SubTask(String a, String b, String c, String d, String f) {
		name = a;
		date = c;
		sep = b;
		enddate = d;
		id = f;
	}

	// public
	public String getEnddate() {
		return enddate;
	}

	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSep() {
		return sep;
	}

	public void setSep(String sep) {
		this.sep = sep;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}
}
