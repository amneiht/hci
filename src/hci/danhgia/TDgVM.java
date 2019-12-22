package hci.danhgia;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Textbox;

import hci.emp.login.User;

public class TDgVM {
	private String name, id;

	public String getName() {
		return name;
	}

	public TDgVM() {
		Session sess = Executions.getCurrent().getSession();
		id = (String) sess.removeAttribute("nhanvienid");
		name ="Nh\u00e2n vi\u00ean" + (String) sess.removeAttribute("nhanvien");
	}

	@Wire
	Textbox text;

	@Command
	public void set_dg() {
		Execution e = Executions.getCurrent();
		User ur = getU();
		String ts = text.getValue();
		if (ts != null) {
			DanhGiaDAO.addDanhGia(ur, ts, id);
		}
		e.sendRedirect("/pages/danh_gia/viewNv.zul");
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		return ur;
	}
}
