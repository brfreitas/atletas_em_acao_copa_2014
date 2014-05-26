package com.example.tabelacopa2014.Contants;

import com.example.tabelacopa2014.R;



public enum Flags {
	
	BRAZIL(1L,R.string.brazil,R.drawable.brazil);
	
	private Long id;
	private int country;
	private int resourceId;
	
	private Flags(Long id, int country, int resourceId) {
		this.id = id;
		this.country = country;
		this.resourceId = resourceId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getCountry() {
		return country;
	}

	public void setCountry(int country) {
		this.country = country;
	}

	public int getResourceId() {
		return resourceId;
	}

	public void setResourceId(int resourceId) {
		this.resourceId = resourceId;
	}
	
	

}
