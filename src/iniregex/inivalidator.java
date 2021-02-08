package iniregex;

import java.util.HashMap;

public class inivalidator {
	
	private static final String commentregex = ";.*";
	private static final String sectionheaderregex = "\\[.*\\]";
	private static final String propertyvalueregex = ".*=[ \\_0-9_\\w.//()]*";
	
	// checks based on rough ini standard
	// [section]
	// property=value
	// ; comment
	
	private String[] filelines;
	private HashMap<String, HashMap<String, String>> inihm = new HashMap<String, HashMap<String, String>>();
	
	public inivalidator(String[] file) {
		this.filelines = file;
	}
	
	public inivalidator(String file) {
		this.filelines = file.split("\n");
	}
	
	public boolean isValid() {
		boolean out = true;
		for(int i = 0; i < filelines.length; i++) {
			if(!isLineValid(filelines[i])) {
				out = false;
			}
		}
		
		if(out)
			loadData();
		
		return out;
	}
	
	private void loadData() {
		String curSection = "";
		boolean readingSection = false;
		HashMap<String, String> properties = new HashMap<String, String>();
		
		for(int i = 0; i < filelines.length; i++) {
			String line = filelines[i];
			if(!readingSection && line.matches(sectionheaderregex)) {
				// section header detected, starting reading properties
				readingSection = true;
				curSection = line.replaceAll("\\[|\\]", "");
				properties = new HashMap<String, String>();
			}
			
			if(readingSection && line.matches(propertyvalueregex)) {
				String[] propval = line.split("=");
				properties.put(propval[0], propval[1]);
			}
			
			if(readingSection && line.equals("")) {
				// empty line is section reading terminator
				readingSection = false;
				inihm.put(curSection, properties);
			}
			
			if(readingSection && line.matches(sectionheaderregex)) {
				// start reading section directly after property
				inihm.put(curSection, properties);
				curSection = line.replaceAll("\\[|\\]", "");
				properties = new HashMap<String, String>();
			}
		}
		
		if(readingSection) {
			inihm.put(curSection, properties);
		}
	}
	
	private boolean isLineValid(String line) {
		if(line.matches(sectionheaderregex)) {
			// line is a section header
			return true;
		} else if(line.matches(propertyvalueregex)) {
			// line is property=value 
			return true;
		} else if(line.matches(commentregex)) {
			// line is a comment
			return true;
		} else if(line.equals("")) {
			// line is empty
			return true;
		} else {
			System.out.println("line '" + line + "' is not valid");
			return false;
		}
	}
	
	public String[] listSections() {
		String[] keyarr = new String[inihm.keySet().size()];
		for(int i = 0; i < keyarr.length; i++) {
			keyarr[i] = inihm.keySet().toArray()[i].toString();
		}
		return keyarr;
	}
	
	public String[] listSectionKeys(String section) {
		String[] keyarr = new String[inihm.get(section).keySet().size()];
		for(int i = 0; i < keyarr.length; i++) {
			keyarr[i] = inihm.get(section).keySet().toArray()[i].toString();
		}
		return keyarr;
	}
	
	public String getSectionKey(String section, String key) {
		return inihm.get(section).get(key);
	}
	
	public String toString() {
		String out = "";
		String[] sections = listSections();
		for(String i : sections) {
			String[] keys = listSectionKeys(i);
			out += i + "\n";
			for(int j = 0; j < keys.length; j++) {
				if(j < keys.length - 1) {
					out += "┣" + keys[j] + ":" + getSectionKey(i, keys[j]) + "\n";
				} else {
					out += "┗" + keys[j] + ":" + getSectionKey(i, keys[j]) + "\n";
				}
			}
		}
		return out;
	}
}
