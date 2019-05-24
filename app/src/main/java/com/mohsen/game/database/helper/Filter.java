package com.mohsen.game.database.helper;

import java.io.Serializable;

abstract public class Filter implements Serializable {
	
	public abstract String getFilterString();
	
	public Filter() {
		
	}
}
