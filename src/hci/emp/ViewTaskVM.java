package hci.emp;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import hci.emp.login.User;
import hci.sql.DAO;

public class ViewTaskVM {
	private List<SubTask> map = new ArrayList<SubTask>();

	public ViewTaskVM() {
		User ur = getU();
		Session sess = Executions.getCurrent().getSession();
		String d = (String) sess.removeAttribute("view");
		if (d == null) {

			// return;
			d = "x";
		}
		if (ur != null) {
			if(d=="c")
			{
				DAO.viewLateTask(ur,map);
			}
			else DAO.getViewTask(ur, map, d == "x");
		}
	}

	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		if (ur == null)
			return DAO.getUser("truongnd", "1");
		return ur;
	}
	
	@Command
	public void btn_click(@BindingParam("id") int id) {
		Executions.getCurrent().getSession().setAttribute("user", getU());
		Executions.getCurrent().getSession().setAttribute("taskid", map.get(id).getId());
		Executions.getCurrent().sendRedirect("/pages/work/Detail_task.zul");
	}

	public ListModel<SubTask> getMap() {
		return new ListModelList<SubTask>(map, true);
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
}
