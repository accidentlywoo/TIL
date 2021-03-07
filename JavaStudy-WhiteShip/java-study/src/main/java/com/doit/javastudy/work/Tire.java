package com.doit.javastudy.work;

public interface Tire {
	default String figure(){return "round";}
	String wheel();
	String frame();
}
