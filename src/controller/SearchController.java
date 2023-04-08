package controller;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.*;
import org.zkoss.zul.*;
import org.zkoss.zul.ext.Selectable;
import bean.Ear;
import model.EarServiceImpl;
import service.EarService;

public class SearchController extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox keywordBox;
	@Wire
	private Listbox earListbox;
	@Wire
	private Hlayout earDetail;
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
	private Label utilitiesLabel;

	private EarService earService = new EarServiceImpl(
			"jdbc:ucanaccess://C:/Users/thanh/eclipse-workspace/637764_NguyenVanThanh/WebContent/earphoneDB.accdb", "", "");

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
 		List<Ear> result = earService.search(null);
		earListbox.setModel(new ListModelList<Ear>(result));
	}

 	@Listen("onClick = #addButton")
	public void showAddModal() {
		Window window = (Window) Executions.createComponents("addEar.zul", null, null);
		window.doModal();
	}

 	@Listen("onEdit=#earListbox")
	public void showUpdateModalDialog(ForwardEvent evt) {
 		
 		Button editBtn = (Button) evt.getOrigin().getTarget();
 		
 		String editBtnId = editBtn.getId();
 		
 		Integer earId = Integer.valueOf(editBtnId.substring(4));
 		
 		final HashMap<String, Object> map = new HashMap<String, Object>();
		
 		map.put("earId", earId);
		
 		Window window = (Window) Executions.createComponents("editEar.zul", null, map);
		window.doModal();
	}

 	@Listen("onDelete=#earListbox")
	public void doDelete(ForwardEvent evt) {
		Messagebox.show("Are you sure to delete?", "Delete?", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
				new EventListener<Event>() {
					@Override
					public void onEvent(final Event confirmEvt) throws InterruptedException {
 						if (Messagebox.ON_YES.equals(confirmEvt.getName())) {
 							
 							Button deleteBtn = (Button) evt.getOrigin().getTarget();
 							
							String deleteBtnId = deleteBtn.getId();
							
							Integer earId = Integer.valueOf(deleteBtnId.substring(6));
							
 							Messagebox.show("Ear Id: " + earId);
 
						} else {  
							return;
						}
					}
				});
	}

 	@Listen("onClick = #searchButton")
	public void search() {
		String keyword = keywordBox.getValue();
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
			typeLabel.setValue(selected.getType());
			priceLabel.setValue(selected.getPrice().toString());
			connectLabel.setValue(selected.getConnect());
		}
	}
}