package controller;

import java.util.List;
import java.util.Set;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.A;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.ext.Selectable;

import bean.Ear;
import bean.User;
import service.EarService;
import model.EarServiceImpl;

public class HomeController extends SelectorComposer<Component>{

	private static final long serialVersionUID = 1L;
	@Wire
	private Label titleLabel;
	@Wire
	private Listbox earListbox;
	@Wire
	private Hlayout earDetail;
	@Wire
	private Hlayout Msg;
	@Wire
	private Image previewImage;
	@Wire
	private Label produceLabel;
	@Wire
	private Label connectLabel;
	@Wire
	private Label typeLabel;
	@Wire
	private Label priceLabel;
	@Wire
	private Label nameLabel;
	@Wire
	private A back;
	
		
	private EarService earService = new EarServiceImpl(
			"jdbc:ucanaccess://C:/Users/thanh/eclipse-workspace/637764_NguyenVanThanh/WebContent/earphoneDB.accdb", "", "");
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		String keyword = (String) Executions.getCurrent().getParameter("type");
		if (keyword != null) {
			titleLabel.setValue(keyword.toUpperCase() + " Ear LIST:");
		} else {
			titleLabel.setValue("Ear LIST:");
		}
		List<Ear> result = earService.search(keyword);
		earListbox.setModel(new ListModelList<Ear>(result));
	}
	
	@Listen("onSelect = #earListbox")
	public void showDetail() {
		Set<Ear> selection = ((Selectable<Ear>) earListbox.getModel()).getSelection();
		if (selection != null && !selection.isEmpty()) {
			Ear selected = selection.iterator().next();
			previewImage.setSrc(selected.getPreview());
			produceLabel.setValue(selected.getProduce());
			connectLabel.setValue(selected.getConnect());
			typeLabel.setValue(selected.getType());
			priceLabel.setValue(selected.getPrice());
			nameLabel.setValue(selected.getName());
		}
		titleLabel.setValue("Ear DETAIL:");
		earListbox.setVisible(false);
		earDetail.setVisible(true);
		Msg.setVisible(false);
	}
	
	@Listen("onClick = #back")
	public void backEarList() {
		earListbox.setVisible(true);
		earDetail.setVisible(false);
	}
	
	@Listen("onClick = #submitButton")
	public void submit() {
		Msg.setVisible(true);
		earDetail.setVisible(false);
		
	}
	
	@Listen("onClick = #go")
	public void go() {
		Msg.setVisible(false);
		earListbox.setVisible(true);
		
	}
	@Listen("onOK = #formGrid")
	public void onOK() {
		submit();
	}
}
