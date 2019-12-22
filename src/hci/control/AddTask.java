package hci.control;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Selectbox;
import org.zkoss.zul.Textbox;

import hci.emp.login.User;
import hci.sql.DAO;

public class AddTask {
	private List<String> nv = new ArrayList<String>();

	public ListModel<String> getNv() {
		return new ListModelList<String>(nv, true);
	}

	private List<String> msvn = new ArrayList<String>();
	@Wire
	private Textbox tieude;
	@Wire
	private Textbox noidung;
	@Wire
	private Datebox start;
	@Wire
	private Datebox end;
	@Wire
	private Selectbox sbox;
	@Wire
	Label mess;

	public AddTask() {
		User ur = getU();
		if (ur != null)
			DAO.getSubEmploy(ur, nv, msvn);
	}

	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		
		return ur;
	}

	@Command
	public void add_task() {
		User ur = getU();
		if (ur == null) {
			return;
		}
		if (sbox.getSelectedIndex() == -1) {
			mess.setValue("Ch\u1ecdn nh\u00e2n vi\u00ean");
			return;
		}
		String header, note;
		header = tieude.getValue();
		if (header == null || header == "") {
			mess.setValue("Nh\u1eadp Ti\u00eau \u0111\u1ec1 v\u00e0o");
			return;
		}
		note = noidung.getValue();
		if (note == null || note == "") {
			mess.setValue("Nh\u1eadp n\u1ed9i dung");
			return;
		}
		String st = null, ed = null;
		Date q = start.getValue();
		if (q == null) {
			mess.setValue("Nh\u1eadp ng\u00e0y b\u1eaft \u0111\u1ea7u");
			return;
		}
		Date p = end.getValue();
		if (p != null) {

			ed = new java.sql.Date(p.getTime()).toString();
		} else {
			mess.setValue("Nh\u1eadp ng\u00e0y k\u1ebft th\u00fac");
			return;
		}
		if(p.before(q))
		{
			mess.setValue("Ng\u00e0y k\u1ebft th\u00fac ph\u1ea3i sau ng\u00e0y b\u1eaft \u0111\u1ea7u");
			return;
		}
		st = new java.sql.Date(q.getTime()).toString();
		DAO.setTask(getU(), msvn.get(sbox.getSelectedIndex()), header, note, st, ed);
		resetf();
	}

	private void resetf() {
		sbox.setSelectedIndex(-1);
		tieude.setValue("");
		noidung.setValue("");
		start.setValue(null);
		end.setValue(null);
		mess.setValue("T\u1ea1o th\u00e0nh c\u00f4ng");
		
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
}
