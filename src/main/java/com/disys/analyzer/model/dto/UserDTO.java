package com.disys.analyzer.model.dto;

public class UserDTO
{
	private String userId;
	private String name;
	private String apdCode;
	
	public UserDTO()
	{
		
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getApdCode()
	{
		return apdCode;
	}

	public void setApdCode(String apdCode)
	{
		this.apdCode = apdCode;
	}
}
