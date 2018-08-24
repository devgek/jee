package com.gek.jee;

import java.util.Arrays;
import java.util.List;

import org.joda.time.LocalTime;

/**
 * 
 * @author moo
 *
 */
public class Hello8 {
	public static void main(String[] args) {
		LocalTime currentTime = new LocalTime();
		System.out.println("The current local time is: " + currentTime);
		
		List<String> lst = 
			      Arrays.asList("Hello ", 
			                    "Lambda-", 
			                    "World\n");
			    lst.forEach( s->System.out.print(s) );
	}
}
