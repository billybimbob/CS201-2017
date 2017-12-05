package finalProject;

public enum Location { //location enum with set string values
	name1("at-grade"),
	name2("elevated"),
	name3("elevated/subway"),
	name4("embankment"),
	name5("street level"),
	name6("subway"),
	name7("surface"),
	name8("underground");
	
	private final String name;
	
	private Location(String name) { //constructor
		this.name = name;
	}
	
	public String toString() {
		return name;
	}
}
