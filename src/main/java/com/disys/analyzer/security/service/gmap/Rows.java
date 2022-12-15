package com.disys.analyzer.security.service.gmap;

import java.util.Arrays;

public class Rows
{
	private Elements[] elements;
	
	public Elements[] getElements()
	{
		return elements;
	}
	
	public void setElements(Elements[] elements)
	{
		this.elements = elements;
	}
	
	@Override
	public String toString()
	{
		return "Rows [elements=" + Arrays.toString(elements) + "]";
	}
	
}
