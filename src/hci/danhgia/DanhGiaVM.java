package hci.danhgia;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import hci.emp.login.User;
import hci.sql.DAO;

public class DanhGiaVM {
	List<String> name = new ArrayList<String>();
	List<String> msnv = new ArrayList<String>();

	public ListModel<String> getName() {
		return new ListModelList<String>(name, true);
	}

	@Command
	public void btn_click(@BindingParam("id") int id) {
		Execution e = Executions.getCurrent();
		e.getSession().setAttribute("nhanvien", name.get(id));
		e.getSession().setAttribute("nhanvienid", msnv.get(id));
		e.sendRedirect("/pages/danh_gia/setdg.zul");
	}

	public DanhGiaVM() {
		User ur = getU();
		DAO.getSubEmploy(ur, name, msnv);
	}

	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		return ur;
	}
}
