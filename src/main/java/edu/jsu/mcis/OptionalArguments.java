package edu.jsu.mcis;

public class OptionalArguments extends Arguments {
	private String name;
	private String shortName;
	private String description;
	private boolean required, flagged;
	private Type type;
	private Object value;
	
	public OptArgValues(String name, String help, Type dataType) {
		name = name;
		description = help;
		type = dataType;
		required = false;		
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, Type dataType, String defaultVal) {
		name = name;
		description = help;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, Type dataType, int defaultVal) {
		name = name;
		description = help;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, Type dataType, boolean defaultVal) {
		name = name;
		description = help;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String help, Type dataType, float defaultVal) {
		name = name;
		description = help;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, Type dataType) {
		name = name;
		description = help;
		type = dataType;
		shortName = shortName;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, Type dataType, String defaultVal) {
		name = name;
		description = help;
		type = dataType;
		shortName = shortName;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, Type dataType, int defaultVal) {
		name = name;
		description = help;
		type = dataType;
		shortName = shortName;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, Type dataType, float defaultVal) {
		name = name;
		description = help;
		type = dataType;
		shortName = shortName;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptArgValues(String name, String shortName, String help, Type dataType, boolean defaultVal) {
		name = name;
		description = help;
		type = dataType;
		shortName = shortName;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public void setFlagged(Type dataType) {
		switch (dataType) {
			case FLAG:
			flagged = true;
			break;
			default:
			flagged = false;
			break;
		}
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
	public String getShortName(){
		return shortName;
	}
	public <T> T getValue(){
		return (T) value;
	}
	public Type getType){
		return type;
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
	
	// don't let the parser handle it if it breaks 
	public void setValue(String v) throws NumberFormatException {
		switch(type){
		case INT:
			value = Integer.parseInt(v);
			break;
		case FLOAT:
			value = Float.parseFloat(v);
			break;
		case BOOLEAN:FLAG:
			if(v.equals("true") || v.equals("false")){
				value = Boolean.parseBoolean(v);
			}
			else throw new NumberFormatException("Invalid Argument");
			break;
		default:
			value = v;
		}
	}

}