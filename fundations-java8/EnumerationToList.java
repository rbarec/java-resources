package com.w3spoint;
 
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Vector;

// https://www.w3schools.blog/how-to-convert-enumeration-to-list-in-java 
public class Test {
	public static void main(String a[]){		
		Vector<String> vector = new Vector<String>();
		vector.add("vikas");
		vector.add("amit");
		vector.add("ajay");
		vector.add("anil");
		vector.add("mahesh");
		Enumeration<String> enumeration = vector.elements();
		List<String> list = Collections.list(enumeration);
		System.out.println("List elements: "+list);
	}
}