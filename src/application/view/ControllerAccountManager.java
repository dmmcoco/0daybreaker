package application.view;

import application.AccountManager;
import application.Collect_process;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ControllerAccountManager {
	public TextField zoomeye_email;
	public TextField zoomeye_password;
	public Label status;
	public Button apply;
	public Button read;
	public Button decide;
	public Button cancel;
	public void init(AccountManager am){
		zoomeye_email.setText(Collect_process.accounts.zoomeye_email);
		zoomeye_password.setText(Collect_process.accounts.zoomeye_password);
		status.setText("读取配置完毕");
		apply.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				Collect_process.accounts.zoomeye_email=zoomeye_email.getText();
				Collect_process.accounts.zoomeye_password=zoomeye_password.getText();
				Collect_process.accounts.writeConfig();
				status.setText("配置已更新");
			}
		});
		read.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				zoomeye_email.setText(Collect_process.accounts.zoomeye_email);
				zoomeye_password.setText(Collect_process.accounts.zoomeye_password);
				status.setText("配置已读取");
			}
		});
		decide.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				Collect_process.accounts.zoomeye_email=zoomeye_email.getText();
				Collect_process.accounts.zoomeye_password=zoomeye_password.getText();
				Collect_process.accounts.writeConfig();
				am.close();
			}
		});
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO 自动生成的方法存根
				am.close();
			}
		});
		
	}
	
}
