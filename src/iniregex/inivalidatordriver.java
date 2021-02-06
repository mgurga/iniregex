package iniregex;

public class inivalidatordriver {
	public static void main(String[] args) {
		String[] correctini = new String[]{
			"; TEST REGEX",
			"",
			"[demosection]",
			"prop1=he llo/asd",
			"prop2=worl d/qwe",
			"foo=bar",
			""
		};
		
		inivalidator ir = new inivalidator(correctini);
		
		System.out.println(ir.isValid()); // validate
		
		ir.loadData(); // loads all sections and properties
		
		// print all sections
		String[] sections = ir.listSections();
		for(String i : sections) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		// print all keys in section
		String[] keys = ir.listSectionKeys("demosection");
		for(String i : keys) {
			System.out.print(i + " ");
		}
		System.out.println();
		
		// get value from section key
		System.out.println(ir.getSectionKey("demosection", "prop1"));
	}
}
