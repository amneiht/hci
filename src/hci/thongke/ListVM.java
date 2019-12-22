package hci.thongke;

import java.util.LinkedList;
import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import hci.emp.login.User;

public class ListVM {
	private List<Ltask> map = new LinkedList<Ltask>();

	public ListModel<Ltask> getMap() {
		return new ListModelList<Ltask>(map, true);
	}

	public ListVM() {
		Session sess = Executions.getCurrent().getSession();
		User ur = (User) sess.getAttribute("user");
		int id = (int) sess.removeAttribute("tk_id");
		switch (id) {
		case 1:
			LtaskDAO.getDoneTask(ur, map);
			break;

		case 2:
			LtaskDAO.getOngoingTask(ur, map);
			break;
		case 3:
			LtaskDAO.getDeadLineList(ur, map);
			break;

		default:
			return ;
		}
	}
}
