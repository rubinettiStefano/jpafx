package com.generation.jpafx.model;

import com.generation.jpafx.helpers.HibernateHelper;
import jakarta.persistence.EntityManager;

import java.util.List;

public class FileRepository
{
	//SINGLETON
	private static FileRepository instance;
	public static FileRepository getInstance()
	{
		if(instance == null)
			instance = new FileRepository();
		return instance;
	}
	private FileRepository(){}
	//FINE SINGLETON

	private EntityManager em  = HibernateHelper.getEntityManager();

	public List<File> findAllFiles()
	{
		return em.createQuery("select u from File u", File.class).getResultList();
	}

	public File findFile(int id)
	{
		return em.find(File.class, id);
	}

	public void insertFile(File file)
	{
		em.getTransaction().begin();//qui iniziamo la transazione
		em.persist(file);//persist=save
		em.getTransaction().commit();//qui committiamo, la rendiamo permanente
	}

	public void modifyFile(File file)
	{
		em.getTransaction().begin();//qui iniziamo la transazione
		em.merge(file);//merge=update
		em.getTransaction().commit();//qui committiamo, la rendiamo permanente
	}

	public void delete(int id)
	{

		File toDelete = findFile(id);//prima prendiamo lo file con quell'id
		if(toDelete == null)//se non l'abbiamo trovato
			throw new RuntimeException("File not found");

		em.getTransaction().begin();
		em.remove(toDelete);
		em.getTransaction().commit();
	}
	//FINE METODI CRUD DI BASE

	
}
