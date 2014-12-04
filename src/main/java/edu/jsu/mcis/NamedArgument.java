package edu.jsu.mcis;

/**
  * Class that sets the requirement for named arguments based on whether the 
  * user decides to add them. It should be able to define any alternate names 
  * or default values with the information provided. 
  */
  
public class NamedArgument extends Argument 
{
	private String alternateName;
	private boolean required;
	private Object defaultValue;
	
	
	 /**
     * Extension of the Argument Constructor with the ability to 
     * specify names.
     * @param name Sets the name field. 
     * @param type Sets the type field.
     */
	public NamedArgument(String name, Type type) 
	{
		super(name, type);
		alternateName = "";
		required = false;
		defaultValue = null;
	}
	    /**
     * Sets the alternative name when specified.
     * @param alternateName Sets the alternate name field.
     */
	public void setAlternateName(String alternateName) 
	{
		this.alternateName = alternateName;
	}
	    /**
     * Returns the alternative name.
     * @return Gives a different name when specified.
     */
	public String getAlternateName() 
	{
		return alternateName;
	}
	    /**
     * Sets the default value depending on its current primitive type.
     * @param defaultValue Sets the default value field.
     * @throws NumberFormatException - Throws an exception if the default value 
     * is neither true or false.
     */
	public void setDefaultValue(Object defaultValue)
	{
		String newDefaultValue = defaultValue.toString();
	
		switch(super.type)
		{
			case INT:
				this.defaultValue = Integer.parseInt(newDefaultValue);
				break;
			case FLOAT:
				this.defaultValue = Float.parseFloat(newDefaultValue);
				break;
			case BOOLEAN:
				setBooleanDefaultValue(newDefaultValue);
				break;
			default:
				this.defaultValue = newDefaultValue;
		}
	}
	private void setBooleanDefaultValue(String newDefaultValue)
	{
		if (newDefaultValue.toLowerCase().equals("true") || newDefaultValue.toLowerCase().equals("false")) 
		{
			this.defaultValue = Boolean.parseBoolean(newDefaultValue);
		}
		else throw new NumberFormatException(newDefaultValue + " is not true or false.");
	}
	    /**
     * Returns the default value unless the current value is null. 
     * @param <T> Gives any data type.
     * @return Either responds with value or default value.
     */
	@Override
	public <T> T getValue() 
	{
		if (super.value == null) 
		{
			return (T)defaultValue;
		}
		else 
		{
			return (T)super.value;
		}
	}
	   /**
     * Returns the current default values type.
     * @param <T> Gives any data type.
     * @return String,Int,Float,Boolean.
     */
	public <T> T getDefaultValue() 
	{
		return (T)defaultValue;
	}
	    /**
     * Sets the requirement to true.
     */
	public void setRequired() 
	{
		required = true;
	}
	    /**
     * Returns the current required boolean as either true or false.
     * @return Gives either true or false.
     */
	public boolean getRequired() 
	{
		return required;
	}
}