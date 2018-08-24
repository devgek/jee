package com.gek.jee.ui.controller;

import org.joda.time.LocalTime;

@UIController
public class SpringController{
	public String getGreeting() {
		LocalTime currentTime = new LocalTime();
		return "Hello you oppossums from SpringController at " + currentTime;
	}
}
