package controller;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class UpdateEarController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	Window modalDialog;
	@Wire
	Label msg;  

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
 		Integer earId = (Integer) Executions.getCurrent().getArg().get("earId");
 		msg.setValue(earId.toString());

	}

 	@Listen("onClick = #updateEarBtn")
	public void updateEar() {
 
		Messagebox.show("Update successfull!");
 		modalDialog.detach();
 		Executions.sendRedirect("searchMvc.zul");
	}
}
