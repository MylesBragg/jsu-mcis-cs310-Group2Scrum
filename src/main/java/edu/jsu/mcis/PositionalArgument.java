package edu.jsu.mcis;

 /**
  * Class that identifies each position of an argument based on its ID while also
  * being able to track its location. 
  */

public class PositionalArgument extends Argument{

	private int positionId;
	 /**
     * Extension of the Argument Constructor with the ability to set the 
     * current position count.
     * @param name Sets the name field.
     * @param type Sets the type field.
     * @param currentPositionCount Sets the index of the positionID field.
     */
	public PositionalArgument(String name, Type type, int currentPositionCount) 
	{
		super(name, type);
		positionId = currentPositionCount;
	}
	/**
     * Returns the position ID of the current argument.
     * @return Gives back an integer position.
     */
	public int getPositionId() 
	{
		return positionId;
	}
}