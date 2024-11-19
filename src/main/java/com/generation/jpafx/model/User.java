package com.generation.jpafx.model;

import jakarta.persistence.*;

import java.util.StringJoiner;

@Entity
@Table(name = "user")
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//equivalente all'auto_increment di mysql
	@Column(name = "id")
	private Integer id;//usiamo Integer invece di int perchè ci viene comodo poter avere il valore null
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;

	public User(){}

	public User(String username, String password)
	{
		this.username = username;
		this.password = password;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	@Override
	public String toString()
	{
		return new StringJoiner(", ", User.class.getSimpleName() + "[", "]")
				.add("id=" + id)
				.add("username='" + username + "'")
				.add("password='" + password + "'")
				.toString();
	}
}
