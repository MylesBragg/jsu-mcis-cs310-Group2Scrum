package edu.jsu.mcis;

public class OptArgValues {
	private String argName;
	private String argShortName;
	private String argHelp;
	private dataTypeDefinitions argDataType;
	private Object argDefaultVal;
	
	public OptArgValues(String name, String help, dataTypeDefinitions dataType) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
	}
	public OptArgValues(String name, String help, dataTypeDefinitions dataType, String defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String help, dataTypeDefinitions dataType, int defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String help, dataTypeDefinitions dataType, boolean defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String help, dataTypeDefinitions dataType, float defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, dataTypeDefinitions dataType) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
	}
	public OptArgValues(String name, String shortName, String help, dataTypeDefinitions dataType, String defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, dataTypeDefinitions dataType, int defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, dataTypeDefinitions dataType, float defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
	public OptArgValues(String name, String shortName, String help, dataTypeDefinitions dataType, boolean defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
	}
}