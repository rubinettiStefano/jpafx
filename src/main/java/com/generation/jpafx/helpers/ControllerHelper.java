package com.generation.jpafx.helpers;

import com.generation.jpafx.model.User;
import com.generation.jpafx.model.UserRepository;

public class ControllerHelper
{
	//singleton
	private static ControllerHelper instance;
	public static ControllerHelper getInstance()
	{
		if(instance == null)
			instance = new ControllerHelper();
		return instance;
	}
	private ControllerHelper(){}
	//fine singleton
	//prendo la repository
	UserRepository repo = UserRepository.getInstance();


	public String doRegister(String username, String password)
	{
		//controlliamo di non avere già un utente con lo stesso username
		User sameUsername = repo.findUserByUsernameOnly(username);
		if(sameUsername != null)//se abbiamo già un utente con quello username ci fermiamo e diciamo che non possiamo registrare
			return "Utente già presente";

		//altrimenti lo salviamo
		//creiamo prima l'oggetto
		User toSave = new User(username, password);
		//chiediamo alla repository di salvarlo
		repo.insertUser(toSave);
		//diciamo che è andato tutto bene
		return "Utente salvato";
	}

	public String doLogin(String username, String password)
	{
		User nelDb = repo.findUserByUsernameAndPassword(username, password);
		//se l'abbiamo trovato significa che sia username che password sono giusti, un giorno faremo il login vero
		// e proprio, oggi stampiamo solo a schermo il messaggio

		if(nelDb != null)
			return "Bravissimo, ti sei loggato";

		//se non l'abbiamo trovato
		return "Credenziali errate";

	}

}
