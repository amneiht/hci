package hci.control;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Tabbox;


public class Test {
	private List<String> header = new ArrayList<String>();
	private List<String> src = new ArrayList<String>();
	Tabbox tbx ;
	public Test(){
		header.add("tap");
		header.add("ffaf");
		src.add("lolol");
		src.add("ssss");
	}
	@Command
	public void add_box()
	{
		header.add("ss");
		src.add("Sssas");
	}
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	public ListModel<String> getHeader() {
		return new ListModelList<String>(header, true);
	}

	public ListModel<String> getSrc() {
		return new ListModelList<String>(src, true);
	}

}
