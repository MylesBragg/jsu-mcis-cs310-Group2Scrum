package edu.jsu.mcis;

public class OptionalArgument extends Argument {
	private String shortName;
	private boolean required, flagged;
	
	public OptionalArgument(String name, String description, Type dataType) {
		super(name, description, dataType);
		this.name = name;
		shortName = "";
		this.description = description;
		type = dataType;
		required = false;		
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String description, Type dataType, String defaultVal) {
		super(name, description, dataType);
		this.name = name;
		shortName = "";
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String description, Type dataType, int defaultVal) {
		super(name, description, dataType);
		this.name = name;
		shortName = "";
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String description, Type dataType, boolean defaultVal) {
		super(name, description, dataType);
		this.name = name;
		shortName = "";
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String description, Type dataType, float defaultVal) {
		super(name, description, dataType);
		this.name = name;
		shortName = "";
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String shortName, String description, Type dataType) {
		super(name, description, dataType);
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		type = dataType;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String shortName, String description, Type dataType, String defaultVal) {
		super(name, description, dataType);
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String shortName, String description, Type dataType, int defaultVal) {
		super(name, description, dataType);
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String shortName, String description, Type dataType, float defaultVal) {
		super(name, description, dataType);
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		type = dataType;
		value = defaultVal;
		required = false;
		setFlagged(dataType);
	}
	public OptionalArgument(String name, String shortName, String description, Type dataType, boolean defaultVal) {
		super(name, description, dataType);
		this.name = name;
		this.shortName = shortName;
		this.description = description;
		type = dataType;
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
	public Type getType(){
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
		case BOOLEAN: case FLAG:
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