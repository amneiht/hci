package hci.control;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;

import hci.emp.login.User;

public class Menu {
	private boolean themtask = false;

	public Menu() {
		User ap = getU();
		if (ap != null) {
			if (ap.getStatus() > 0)
				themtask = true;
		}
	}

	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		return ur;
	}
	@Command
	public void xem_danh_gia()
	{
		Execution e = Executions.getCurrent();
		e.sendRedirect("/pages/baocao/tong_quan.zul");
	}
	@Command
	public void tk_view(@BindingParam("id") int id) {
		Execution e = Executions.getCurrent();
		e.getSession().setAttribute("tk_id", id);
		e.sendRedirect("/pages/danh_gia/Tong_hop/hoan_thanh.zul");
	}

	@Command
	public void chua_xong() {

		Execution e = Executions.getCurrent();
		e.getSession().setAttribute("view", "x");
		e.sendRedirect("/pages/work/viewtask.zul");
	}

	@Command
	public void danh_gia() {
		Execution e = Executions.getCurrent();
		e.sendRedirect("/pages/danh_gia/viewNv.zul");
	}

	@Command
	public void da_xong() {

		Execution e = Executions.getCurrent();
		e.getSession().setAttribute("view", "o");
		e.sendRedirect("/pages/work/viewtask.zul");
	}

	@Command
	public void task_cham() {
		System.out.println("chuyen trang");
		Execution e = Executions.getCurrent();
		e.getSession().setAttribute("view", "c");
		e.sendRedirect("/pages/work/viewtask.zul");
	}

	public boolean isThemtask() {
		return themtask;
	}

	@Command
	public void logout() {
		Execution e = Executions.getCurrent();
		e.getSession().removeAttribute("user");
		e.sendRedirect("/");
	}

	@Command
	public void them_task() {
		Execution e = Executions.getCurrent();
		e.sendRedirect("/pages/work/addtask.zul");
	}
}
