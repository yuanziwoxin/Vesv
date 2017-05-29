package com.example.test;

import org.junit.Test;

public class StringTest {

	@Test
	public void Stringtest()
	{
		String str="abcdef";
		int index=str.lastIndexOf("d");
		String str1=str.substring(1,3);
		System.out.println(index+":"+str1);
	}
}
