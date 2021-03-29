package com.tt.util;

import org.apache.commons.lang3.math.NumberUtils;

public class StrUtil {

	public static String getNumberFromString(String msg, int occ) 
	{
		String output = msg;
		int currl = 0;
		String[] words = msg.split(" ");
		for(int i=0;i<words.length;i++)
		{
			if(NumberUtils.isNumber(words[i]))
			{
				currl++;
				if(currl==occ)
				{
					output = words[i];
					break;
				}
				
			}
		}
		return output;
	}
	
	public static String getstringBetween(String msg, String before, String after)
	{
		String output = "";
		
		int startIndex = msg.indexOf(before) ;
		int endIndex = msg.indexOf(after);
		int temp= 0;
		if(startIndex==-1||endIndex==-1)
		{
			return output = "that match is not found";
		}
		if(startIndex>endIndex)
		{
			endIndex = endIndex + after.length();
			temp = startIndex;
			startIndex = endIndex;
			endIndex = temp;
		}
		else if(startIndex<endIndex)
		{
			startIndex = startIndex + before.length();
		}
		System.out.println("before index:" + startIndex);
		System.out.println("after index:" + endIndex);
		output = msg.substring(startIndex, endIndex).trim();
		
		return output;
	}
	
	public static String getRandomString(int size)
	{
		String output = "";
		
		String randomOF = "abcdefghijklmnopqrstwushihaeukgcukeagkwgjggwjevxyz0123456789"; 
		int len = randomOF.length();
		for(int i=0;i<size;i++)
		{
			int index = (int)(Math.random() * len);
			output = output + randomOF.charAt(index);
			
		}
		
		return output;
	}
	
	public static void main(String[] args)
	{
		System.out.println("Number extracted is :" + StrUtil.getNumberFromString("your order 1234 is created"
	+ "successfully, and has 10 items in total",1));
		System.out.println("String between one and three is :" + StrUtil.getstringBetween("one two three four five", "abcd", "one"));
		
		System.out.println("random string:" + StrUtil.getRandomString(5));
	}
}

