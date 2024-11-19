package com.generation.jpafx.model;

import com.generation.jpafx.helpers.HibernateHelper;
import jakarta.persistence.EntityManager;

import java.util.List;

public class UserRepository
{
	//SINGLETON
	private static UserRepository instance;
	public static UserRepository getInstance()
	{
		if(instance == null)
			instance = new UserRepository();
		return instance;
	}
	private UserRepository(){}
	//FINE SINGLETON

	private EntityManager em  = HibernateHelper.getEntityManager();//chiediamo all'helper
	//l'entityManager, l'oggetto di Hibernate che parla con il DB

	//Impostiamo i nostri metodi di CRUD base (lettura totale,lettura singola, inserimento,modifica,cancellazione)
	public List<User> findAllUsers()
	{
		//qui eseguiamo sul database una query in linguaggio HQL (molto simile a SQL ma usa i nomi definiti
		//dentro Java), in automatico lui converte le righe lette in una lista di User, grazie
		//alla mappatura ORM fatta nella entità
		return em.createQuery("select u from User u", User.class).getResultList();
	}

	public User findUser(int id)
	{
		return em.find(User.class, id);
		//qui invece legge solo lo user con l'id passato
		//se non lo trova restituisce null
	}

	public void insertUser(User user)
	{
		//Qui facciamo una TRANSAZIONE
		//una transazione è un insieme di operazioni sul db che vengono eseguite o
		//tutte o nessuna
		//obbligatoria per fare DML, anche se qui ne eseguiamo una sola
		em.getTransaction().begin();//qui iniziamo la transazione
		em.persist(user);//persist=save
		em.getTransaction().commit();//qui committiamo, la rendiamo permanente
	}

	public void modifyUser(User user)
	{
		em.getTransaction().begin();//qui iniziamo la transazione
		em.merge(user);//merge=update
		em.getTransaction().commit();//qui committiamo, la rendiamo permanente
	}

	public void delete(int id)
	{

		User toDelete = findUser(id);//prima prendiamo lo user con quell'id
		if(toDelete == null)//se non l'abbiamo trovato
			throw new RuntimeException("User not found");

		//se invece l'abbiamo trovato lo cancelliamo
		em.getTransaction().begin();
		em.remove(toDelete);
		em.getTransaction().commit();
	}
	//FINE METODI CRUD DI BASE

	public User findUserByUsernameAndPassword(String username, String password)
	{
		List<User> users = 	em.createQuery("select u from User u WHERE username=:usn AND password=:pwd", User.class)
							.setParameter("usn", username) //con :usn mettiamo un valore che verrà sostituito da questo parametro
							.setParameter("pwd", password)
							.getResultList();
		//possiamo aver trovato 1 utente, nessun utente o, speriamo di no, più utenti

		//se ne abbiamo trovato più di 1 il nostro database è in cattive acque, poichè non abbiamo utenti univoci
		if(users.size()>1)
			throw new RuntimeException("Multiple users found");
		//se abbiamo trovato 1 utente siamo contenti e lo restituiamo (sarà unico elemento della lista)
		if(users.size()==1)
			return users.getFirst();
		//se non ne abbiamo trovato nessuno no problem, diamo null
		return null;
	}

	public User findUserByUsernameOnly(String username)
	{
		//uguale al metodo sopra ma senza password
		List<User> users = 	em.createQuery("select u from User u WHERE username=:usn", User.class)
							.setParameter("usn", username)
							.getResultList();
		if(users.size()>1)
			throw new RuntimeException("Multiple users found");
		if(users.size()==1)
			return users.getFirst();
		return null;
	}
}
