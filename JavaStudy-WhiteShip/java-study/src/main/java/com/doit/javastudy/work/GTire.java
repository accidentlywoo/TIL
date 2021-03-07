package com.doit.javastudy.work;

public class GTire implements Tire{
	@Override
	public String wheel() {
		return "g_brand_wheel";
	}

	@Override
	public String frame() {
		return "special_alloy";
	}
}
