package edu.jsu.mcis;

public class OptArgValues {
	private String argName;
	private String argShortName;
	private String argHelp;
	private ArgValues.Type argDataType;
	private Object argDefaultVal;
	
	public OptArgValues(String name, String help, ArgValues.Type dataType) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, String defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, int defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, boolean defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, float defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, String defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, int defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, float defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, boolean defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
}