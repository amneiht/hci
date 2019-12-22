package hci.danhgia.nhanvien;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;

import hci.emp.login.User;

public class DanhGiaVM {
	public String tld="T\\u00ean l\\u00e3nh \\u0111\\u1ea1o";
	private List<DanhGia> map = new ArrayList<DanhGia>();
	public  ListModel<DanhGia> getMap()
	{
		return new ListModelList<DanhGia>(map,true);
	}
	public DanhGiaVM()
	{
		User p=getU();
		DanhGiaDao.getInfo(p,map);
	}
	public User getU() {
		User ur = (User) Executions.getCurrent().getSession().getAttribute("user");
		return ur;
	}
}
