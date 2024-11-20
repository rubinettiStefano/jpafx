package com.generation.jpafx.model;

import jakarta.persistence.*;

@Entity
@Table(name = "file", schema = "jpafx")
public class File
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;

	@Column(name = "filename", length = 50)
	private String filename;

	@Lob
	@Column(name = "content")
	private String content;

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getFilename()
	{
		return filename;
	}

	public void setFilename(String filename)
	{
		this.filename = filename;
	}

	public String getContent()
	{
		return content;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

}