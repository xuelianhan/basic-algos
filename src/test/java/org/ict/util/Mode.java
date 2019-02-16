package org.ict.util;

public enum Mode {
	 RUNNING("SytemRunning"),
	 STOPPED("SystemStopped"),
	 IDLE("tmpIdle");

	 private String key;

	 private Mode(String key){
	     this.key = key;
	 }
	  
	 public String getKey() {
		 return key;
     }
}
