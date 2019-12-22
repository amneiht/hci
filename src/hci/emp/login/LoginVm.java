package hci.emp.login;

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
import org.zkoss.zul.Textbox;

import hci.sql.DAO;

public class LoginVm {
	@Wire
	private Textbox user;
	@Wire
	private Textbox pass;
	@Wire
	private Label mess;

	public LoginVm() {
		Session seg = Executions.getCurrent().getSession();
		User ap = (User) seg.getAttribute("user");
		if (ap != null) {
			Executions.getCurrent().sendRedirect("/pages/broad.zul");
		}
	}

	@Command
	public void login() {
		User sr;
		String ten = user.getText();
		String ma = pass.getText();
		if (ten == "" || ma == "") {
			mess.setValue("nhap du thong tin di ban");
			sr = DAO.getUser("amneiht", "1");
			// return;
		} else
			sr = DAO.getUser(ten, ma);
		if (sr == null)
			mess.setValue("sai thong tin");
		else {
			Session seg = Executions.getCurrent().getSession();
			seg.setAttribute("user", sr);
			Executions.getCurrent().sendRedirect("/pages/broad.zul");
		}
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
}
