package hci.emp.edit;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;

import hci.emp.login.User;

public class DetailTaskVm {
	private String id;
	private String tieu_de, noi_dung, bat_dau, ket_thuc, note;
	private int trang_thai;
	private String[] map = { "Ch\u01b0a xong", "Ho\u00e0n Th\u00e0nh" };

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTieu_de() {
		return tieu_de;
	}

	public void setTieu_de(String tieu_de) {
		this.tieu_de = tieu_de;
	}

	public String getNoi_dung() {
		return noi_dung;
	}

	public void setNoi_dung(String noi_dung) {
		this.noi_dung = noi_dung;
	}

	public String getBat_dau() {
		return bat_dau;
	}

	public void setBat_dau(String bat_dau) {
		this.bat_dau = bat_dau;
	}

	public String getKet_thuc() {
		return ket_thuc;
	}

	public void setKet_thuc(String ket_thuc) {
		this.ket_thuc = ket_thuc;
	}

	public int getTrang_thai() {
		return trang_thai;
	}

	public void setTrang_thai(int trang_thai) {
		this.trang_thai = trang_thai;
	}

	public DetailTaskVm() {
		Session sess = Executions.getCurrent().getSession();
		id = (String) sess.getAttribute("taskid");
		if (id != null) {
			DetailDAO.getInfo(this, id);
		}
	}

	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		return ur;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public ListModel<String> getMap() {
		return new ListModelList<String>(map);
	}

	@Wire
	private Textbox txt;
	@Wire
	private Selectbox sbox;
	@Wire
	private Label mess;

	@Command
	public void bn_click() {
		int d = sbox.getSelectedIndex();
		String k = txt.getValue();
		boolean s = DetailDAO.update(id, k, d + 1);
		if (!s) {
			mess.setValue("Loi tai Lop truong");
		} else {
			Executions.getCurrent().sendRedirect("/pages/work/viewtask.zul");
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
}
