package edu.jsu.mcis;

public class OptArgValues {
	private String argName;
	private String argShortName;
	private String argHelp;
	private boolean required, flagged;

	private ArgValues.Type argDataType;
	private Object argDefaultVal;
	
	public OptArgValues(String name, String help, ArgValues.Type dataType) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		required = false;		
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, String defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, int defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, boolean defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, ArgValues.Type dataType, float defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, String defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, int defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, float defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, ArgValues.Type dataType, boolean defaultVal) {
		argName = name;
		argHelp = help;
		argDataType = dataType;
		argShortName = shortName;
		argDefaultVal = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public void setFlagged(ArgValues.Type dataType) {
		switch (dataType) {
			case FLAG:
			flagged = true;
			break;
			default:
			flagged = false;
			break;
		}
	}
	public String getOptName(){
		return argName;
	}
	public String getArgHelp(){
		return argHelp;
	}
	public String getShortName(){
		return argShortName;
	}
	public <T> T getArgDefault(){
		return (T) argDefaultVal;
	}
	public ArgValues.Type getDataType(){
		return argDataType;
	}
	public void setRequiredBit() {
		required = true;
	}
	public boolean getRequiredBit() {
		return required;
	}
	public boolean getFlagBit() {
		return flagged;
	}
	public void setArgDefault(String v) throws NumberFormatException {
		switch(argDataType){
		case INT:
			argDefaultVal = Integer.parseInt(v);
			break;
		case FLOAT:
			argDefaultVal = Float.parseFloat(v);
			break;
		case BOOLEAN:case FLAG:
			if(v.equals("true") || v.equals("false")){
				argDefaultVal = Boolean.parseBoolean(v);
			}
			else throw new NumberFormatException("Invalid Argument");
			break;
		default:
			argDefaultVal = v;
		}
	}

}