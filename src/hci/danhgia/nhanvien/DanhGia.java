package hci.danhgia.nhanvien;

public class DanhGia {
	private String name, date, note;

	public DanhGia(String a, String b, String c) {
		name = a;
		date = b;
		note = c;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

}
