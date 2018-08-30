package com.mss.microservices.limitsservice;

public class LimitsConfiguration {
	
	private int minimum;
	private int maximun;
	
	public LimitsConfiguration() {}
	
	public LimitsConfiguration(int minimum, int maximun) {
		super();
		this.minimum = minimum;
		this.maximun = maximun;
	}

	public int getMinimum() {
		return minimum;
	}

	public int getMaximun() {
		return maximun;
	}

	public void setMinimum(int minimum) {
		this.minimum = minimum;
	}

	public void setMaximun(int maximun) {
		this.maximun = maximun;
	}
	
	
	
	

}
