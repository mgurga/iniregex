package iniregex;

public class inivalidatordriver {
	public static void main(String[] args) {
		// Test #1
		String[] correctini = new String[] {
			"; CORRECT REGEX",
			"",
			"[demosection]",
			"prop1=he llo/asd",
			"prop2=worl d/qwe",
			"foo=bar",
			"",
			"[infosection]",
			"filelength=20",
			"totalfiles=100"
		};
		
		inivalidator correctinivalidator = new inivalidator(correctini);
		
		System.out.println("CORRECTINI TEST");
		
		System.out.println("valid: " + correctinivalidator.isValid()); // validate
		
		// print all sections
		String[] sections = correctinivalidator.listSections();
		System.out.print("section list: ");
		for(String i : sections) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		// print all keys in section
		String[] keys = correctinivalidator.listSectionKeys("demosection");
		System.out.print("key list in [demosection]: ");
		for(String i : keys) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		// get value from section key
		System.out.print("prop1 key value: ");
		System.out.println(correctinivalidator.getSectionKey("demosection", "prop1"));
		
		// print object with toString
		System.out.println("object toString:");
		System.out.println(correctinivalidator.toString());
		
		// Test #2
		String longstringwrongini = ""
				+ "; ONE STRING WRONG REGEX (comment on same line as property)\n"
				+ "[database]\n"
				+ "type=sql\n"
				+ "totalusers=253\n"
				+ "owner=root ;will change later";
		
		System.out.println("LONGWRONGINI TEST");
		inivalidator longwronginivalidator = new inivalidator(longstringwrongini);
		System.out.println("valid: " + longwronginivalidator.isValid());
		
		// Test #3
		String[] multiplewrongini = new String[] {
			"; MULTIPLE ERRORS REGEX (section and property on same line)",
			"[serversetup]	port=83",
			"hostname=google.com",
			"ip=192.168.3.235",
			"ping=pong",
			"",
			"command=help [permissions]",
			"owner=root",
			"moderator=alice,bob"
		};
		
		System.out.println();
		
		System.out.println("MULTIPLEWRONGINI TEST");
		inivalidator multipleerrorini = new inivalidator(multiplewrongini);
		System.out.println("valid: " + multipleerrorini.isValid());
		
		System.out.println();
		
		// Test #4
		String[] compressedini = new String[] {
			"; COMPRESSED INI (ini with least amount of empty lines)",
			"[github]",
			"owner=Microsoft",
			"branch=master",
			"forks=20",
			"[gitlab]",
			"type=opensource",
			"setupsteps=8"
		};
		
		System.out.println("COMPRESSEDINI TEST");
		inivalidator compressedinivalidator = new inivalidator(compressedini);
		System.out.println("valid: " + compressedinivalidator.isValid());
		
		sections = compressedinivalidator.listSections();
		System.out.print("section list: ");
		for(String i : sections) {
			System.out.print(i + ", ");
		}
		System.out.println();
		keys = compressedinivalidator.listSectionKeys("gitlab");
		System.out.print("key list in [gitlab]: ");
		for(String i : keys) {
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println("type key value: " + compressedinivalidator.getSectionKey("gitlab", "type"));
		System.out.println("object toString:");
		System.out.println(compressedinivalidator.toString());
		
		// Test #5
		String[] windowsini = new String[] {
			"; WINDOWS INI (ini from windows)",
			"[boot loader]",
			"timeout=30",
			"default=multi(0)disk(0)rdisk(0)partition(1)WINDOWS",
			"[operating systems]",
			"edition=Microsoft Windows XP Professional"
		};
		
		System.out.println("WINDOWSINI TEST");
		
		inivalidator wininivalidator = new inivalidator(windowsini);
		System.out.println("valid: " + wininivalidator.isValid());
		sections = wininivalidator.listSections();
		System.out.print("section list: ");
		for(String i : sections) {
			System.out.print(i + ", ");
		}
		System.out.println();
		
		keys = wininivalidator.listSectionKeys("operating systems");
		System.out.print("key list in [operating systems]: ");
		for(String i : keys) {
			System.out.print(i + ", ");
		}
		System.out.println();
		System.out.println("edition key value: " + wininivalidator.getSectionKey("operating systems", "edition"));
		
		System.out.println("object toString:");
		System.out.println(wininivalidator.toString());
	}
}
