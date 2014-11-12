package edu.jsu.mcis;
public class PositionalArgument extends Argument{
	private int positionId;
	
	public PositionalArgument(String name, Type type, int currentPositionCount) {
		super(name, type);
		positionId = currentPositionCount;
	}
	
	public int getPositionId() {
		return positionId;
	}
	
}