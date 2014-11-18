package edu.jsu.mcis;

public class NamedArgument extends Argument 
{
	private String alternateName;
	private boolean required;
	private Object defaultValue;
	
	public NamedArgument(String name, Type type) 
	{
		super(name, type);
		alternateName = "";
		required = false;
		defaultValue = null;
	}
	
	public void setAlternateName(String alternateName) 
	{
		this.alternateName = alternateName;
	}
	
	public String getAlternateName() 
	{
		return alternateName;
	}
	
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
				if (newDefaultValue.toLowerCase().equals("true") || newDefaultValue.toLowerCase().equals("false")) 
				{
					this.defaultValue = Boolean.parseBoolean(newDefaultValue);
				}
				else 
				{
					throw new NumberFormatException(newDefaultValue + " is not true or false.");
				}
				break;
			default:
				this.defaultValue = newDefaultValue;
		}
	}
	
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
	
	public <T> T getDefaultValue() 
	{
		return (T)defaultValue;
	}
	
	public void setRequired() 
	{
		required = true;
	}
	
	public boolean getRequired() 
	{
		return required;
	}
}