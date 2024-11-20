package com.generation.jpafx;

import com.generation.jpafx.model.File;
import com.generation.jpafx.model.FileRepository;
import com.generation.jpafx.model.User;
import com.generation.jpafx.model.UserRepository;

public class Prove
{
	public static void main(String[] args)
	{
//		UserRepository repo = UserRepository.getInstance();
//
//		User u = repo.findUser(2);

//		for(File f : u.getFiles())
//			System.out.println(f.getContent());

		FileRepository repo = FileRepository.getInstance();
		File f = repo.findFile(2);

		System.out.println("QUesto file è stato scritto da un utente di nome "+f.getUser().getUsername());
	}
}
