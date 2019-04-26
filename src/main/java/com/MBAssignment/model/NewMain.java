package com.MBAssignment.model;

import java.sql.Timestamp;

public class NewMain {

	public static void main(String args[]) {
		
		Timestamp currentDate = new Timestamp(System.currentTimeMillis());
		Timestamp endDate = Timestamp.valueOf("2019-02-23 16:34:53"); 
		if(currentDate.after(endDate)) {
			System.out.println("after");
		}else {
			System.out.println("before");
		}
	}
}
