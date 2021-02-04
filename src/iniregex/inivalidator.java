package iniregex;

public class inivalidator {
	
	// checks based on rough ini standard
	// [section]
	// property=value
	// ; comment
	
	private String[] filelines;
	
	public inivalidator(String[] file) {
		this.filelines = file;
	}
	
	public boolean isValid() {
		
		for(int i = 0; i < filelines.length; i++) {
			
		}
		
	}
	
	public String getProperty() {
		
	}
}
