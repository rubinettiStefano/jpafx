package com.generation.jpafx.controller;

import com.generation.jpafx.helpers.ControllerHelper;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class ApplicationController
{

	@FXML
	public TextField username,password;
	@FXML
	public Text messages;

	private ControllerHelper helper = ControllerHelper.getInstance();

	public void register()
	{
		String usn = username.getText();
		String pwd = password.getText();

		String messaggio = helper.doRegister(usn, pwd);
		//stampiamo il messaggio a schermo, mettendolo nella casellina di testo
		messages.setText(messaggio);
	}

	public void login()
	{
		String usn = username.getText();
		String pwd = password.getText();

		String messaggio = helper.doLogin(usn, pwd);
		messages.setText(messaggio);
	}
}
