package controller;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;

import bean.User;
import model.LoginModel;

@SuppressWarnings("serial")
public class LoginController extends SelectorComposer<Div> {

		@Wire
	private Textbox username;
	@Wire
	private Textbox password;
	@Wire
	private Label errorMsg;
	private LoginModel loginModel = new LoginModel("jdbc:ucanaccess://C:/Users/thanh/eclipse-workspace/637764_NguyenVanThanh/WebContent/earphoneDB.accdb", "", "");
	@Listen("onClick = #resetButton")
	public void reset() {
		username.setRawValue(null);
		password.setRawValue(null);
		errorMsg.setValue(null);
	}

	@Listen("onClick = #submitButton")
	public void submit() {

		User user = new User(username.getValue(), password.getValue());
		if (loginModel.loginModel(user)) {
			Executions.sendRedirect("/home.zul");
		}else {
			errorMsg.setValue("incorrect username or password");
		}
	}

	@Listen("onOK = #formGrid")
	public void onOK() {
		submit();
	}

}