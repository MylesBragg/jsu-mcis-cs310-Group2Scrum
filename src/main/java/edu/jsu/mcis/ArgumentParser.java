package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class ArgumentParser {
	
	private LinkedHashMap<String, PositionalArgument> positionalArgumentHolder;
	private LinkedHashMap<String, NamedArgument> namedArgumentHolder;
	private String program;
	private String programDescription;
	private int currentPositionalArgumentCount;
	
	public ArgumentParser(String progName){
		
		program = progName;
		
		positionalArgumentHolder = new LinkedHashMap<String, PositionalArgument>();
		namedArgumentHolder = new LinkedHashMap<String, NamedArgument>();
		
		currentPositionalArgumentCount = 0;
	}
	
	
	public void setProgramDescription(String programDescription){
		this.programDescription = programDescription;
	}
	
	
	public String getProgramDescription(){
		return programDescription;
	}
	
	
	
	
	public void addPositionalArgument(String name, Argument.Type dataType){
		positionalArgumentHolder.put(name, new PositionalArgument(name, dataType, incrementCurrentPositionalArgumentCount()));
		setInvalidExceptionProgramName(name);
	}
	
	
	private int incrementCurrentPositionalArgumentCount(){
		currentPositionalArgumentCount++;
		return currentPositionalArgumentCount;
	}
	
	
	public int getPositionId(String name){
		return positionalArgumentHolder.get(name).getPositionId();
	}
	
	
	
	
	public void setArgumentDescription(String name, String description){
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) {
			if (namedArgumentHolder.containsKey(name)) {
				namedArgumentHolder.get(name).setDescription(description);
			}
			else if (positionalArgumentHolder.containsKey(name)) {
				positionalArgumentHolder.get(name).setDescription(description);
			}
			else {
				System.out.println("I need an exception handler here");
			}
		}
		else {
			namedArgumentHolder.get(namedArgumentFullName).setDescription(description);
		}
	}
	
	
	public String getArgumentDescription(String name){
		String namedArgumentFullName = getNamedArgumentFullName(name);
		if (namedArgumentFullName.equals("")) {
			if (namedArgumentHolder.containsKey(name)) {
				return namedArgumentHolder.get(name).getDescription();
			}
			else if (positionalArgumentHolder.containsKey(name)) {
				return positionalArgumentHolder.get(name).getDescription();
			}
		}
		else {
			return namedArgumentHolder.get(namedArgumentFullName).getDescription();
		}
		return "";
	}
	
	
	private void setInvalidExceptionProgramName(String name){
		if (namedArgumentHolder.containsKey(name)) {
			namedArgumentHolder.get(name).setInvalidValueExceptionProgramName(program);
		}
		if (positionalArgumentHolder.containsKey(name)) {
			positionalArgumentHolder.get(name).setInvalidValueExceptionProgramName(program);
		}
		
	}
	
	
	
	
	public void addNamedArgument(String name, Argument.Type dataType){
		namedArgumentHolder.put(name, new NamedArgument(name, dataType));
		setInvalidExceptionProgramName(name);
	}
	
	
	public void setNamedArgumentAlternateName(String name, String alternateName){
		namedArgumentHolder.get(name).setAlternateName(alternateName);
	}
	
	
	public String getNamedArgumentAlternateName(String name){
		return namedArgumentHolder.get(name).getAlternateName();
	}
	
	
	public void setNamedArgumentDefaultValue(String name, Object defaultValue){
		String fullName = getNamedArgumentFullName(name);
		
		if (fullName.equals("")) {
			namedArgumentHolder.get(name).setDefaultValue(defaultValue);
		}
		else {
			namedArgumentHolder.get(fullName).setDefaultValue(defaultValue);
		}
	}
	
	
	public <T> T getNamedArgumentDefaultValue(String name){
		String fullName = getNamedArgumentFullName(name);
		
		if (fullName.equals("")) {
			return namedArgumentHolder.get(name).getDefaultValue();
		}
		else {
			return namedArgumentHolder.get(fullName).getDefaultValue();
		}
	}
	
	
	public void setNamedArgumentRequired(String name){
		String fullName = getNamedArgumentFullName(name);
		if (fullName.equals("")) {
			namedArgumentHolder.get(name).setRequired();
		}
		else {
			namedArgumentHolder.get(fullName).setRequired();
		}
	}
	
	
	public boolean getNamedArgumentRequired(String name){
		String fullName = getNamedArgumentFullName(name);
		if (fullName.equals("")) {
			return namedArgumentHolder.get(name).getRequired();
		}
		else {
			return namedArgumentHolder.get(fullName).getRequired();
		}
	}
	
	
	private String getNamedArgumentFullName(String alternateName){
		Iterator<String> hashKeys = namedArgumentHolder.keySet().iterator();
		String name;
		while(hashKeys.hasNext())
		{
			name = hashKeys.next();
			String currentAlternateName = namedArgumentHolder.get(name).getAlternateName();
			if(currentAlternateName.equals(alternateName))
			{
				return name;
			}
		}
		return "";
	}
	
	
	
	
	public void parse(String[] argumentsToParse) {
		String myString = "";
		for (int i = 0; i < argumentsToParse.length; i++)
		{
			myString += argumentsToParse[i] + " ";
		}
		parseFormattedString(myString);
	}
	
	
	public void parse(String argumentsToParse) {
		if (argumentsToParse.contains(".xml")) {
			parseXML(argumentsToParse);
		}
		else {
			parseFormattedString(argumentsToParse);
		}
		
	}
	
	
	private void parseXML(String xmlFile) {
		try {
			
			DocumentBuilderFactory initializeXMLDocument = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder buildXMLDocument = initializeXMLDocument.newDocumentBuilder();
			
			Document currentXMLDocument = buildXMLDocument.parse(ClassLoader.getSystemResourceAsStream(xmlFile));

			NodeList nodeList = currentXMLDocument.getDocumentElement().getChildNodes();
			
			for (int i = 0; i < nodeList.getLength(); i++) {
 
			  Node node = nodeList.item(i);
			  
			  if (node instanceof Element) {
				setXMLArgumentValue(node.getChildNodes());
			  }
			}
		}catch(ParserConfigurationException e){
		
		}catch(SAXException e) {
		
		}catch(IOException e) {
		
		}
	}
	
	
	private void setXMLArgumentValue(NodeList childNodes) {
		for (int j = 0; j < childNodes.getLength(); j++) {
			Node currentNode = childNodes.item(j);
			if (currentNode instanceof Element) {
				String content = currentNode.getLastChild().getTextContent().trim();
				
				if (positionalArgumentHolder.containsKey(currentNode.getNodeName())) {
					positionalArgumentHolder.get(currentNode.getNodeName()).setValue(content);
				}
				else {
					String fullName = getNamedArgumentFullName(currentNode.getNodeName());
					if (fullName.equals("")) {
						if (namedArgumentHolder.containsKey(currentNode.getNodeName())) {
							namedArgumentHolder.get(currentNode.getNodeName()).setValue(content);
						}
						else {
							System.out.println("I need an exception here");
						}
					}
					else {
						namedArgumentHolder.get(fullName).setValue(content);
					}
				}
			}
		}

	}
	
	
	private void parseFormattedString(String argumentsToParse){
		String nextValue = "";
		String previousValue = "";
		Scanner argumentScanner = new Scanner(argumentsToParse);
		int currentPosArgIndex = 1;
		
		setInvalidValueExceptionUsageLine();
		
		while (argumentScanner.hasNext()) {
		
			nextValue = argumentScanner.next();
			if (nextValue.equals("-h") || nextValue.equals("--help")) {
				System.out.println(getHelpInfo());
			}
			else {	
				if ((nextValue.charAt(0) == '-' && nextValue.charAt(1) == '-') || nextValue.charAt(0) == '-') {
					
					String namedArgument = checkCommandLineNamedArgument(nextValue);
					
					if (namedArgument.equals("")) {
						System.out.println("I need an exception handler here");
					}
					else {
						if (checkNamedArgumentTypeBoolean(namedArgument)) {
							setArgumentValue(namedArgument, "true");
						}
						else {
							String namedValue = argumentScanner.next();
							
							setArgumentValue(namedArgument, namedValue);
						}
					}
					
					
				}
				else {
				
					String positionalArgName = getPositionalArgumentName(currentPosArgIndex);
					
					if (positionalArgumentHolder.size() >= currentPosArgIndex) {
						setArgumentValue(getPositionalArgumentName(currentPosArgIndex), nextValue);
						currentPosArgIndex++;
					}
					else {
						String usageLine = getUsageLine();
						throw new TooManyArgumentsException(usageLine, program, nextValue, argumentScanner);
					}
					
				}
			}
		}
		if (!nextValue.equals("-h") && !nextValue.equals("--help") && currentPosArgIndex <= positionalArgumentHolder.size())
		{	
			String usageLine = getUsageLine();
			throw new NotEnoughArgumentsException(usageLine, program, positionalArgumentHolder, positionalArgumentHolder.size());
		}
	}
	
	
	private void setInvalidValueExceptionUsageLine(){
	
		Iterator<String> positionalKeys = positionalArgumentHolder.keySet().iterator();
		Iterator<String> namedKeys = namedArgumentHolder.keySet().iterator();
		String currentNamedKey;
		
		while(positionalKeys.hasNext()) {
			positionalArgumentHolder.get(positionalKeys.next()).setInvalidValueExceptionUsageLine(getUsageLine());
		}
		while(namedKeys.hasNext()) {
			currentNamedKey = namedKeys.next();
			if(namedArgumentHolder.get(currentNamedKey).getRequired()) {
				namedArgumentHolder.get(currentNamedKey).setInvalidValueExceptionUsageLine(getUsageLine());
			}
		}
	}
	
	
	private String getPositionalArgumentName(int position){
		Iterator<String> keyIterator = positionalArgumentHolder.keySet().iterator();
		String currentKey;
		
		while(keyIterator.hasNext()) {
		
			currentKey = keyIterator.next();
			
			if (positionalArgumentHolder.get(currentKey).getPositionId() == position) {
				return currentKey;
			}
		}
		return "Positional Argument Not Found Error";
	}
	
	
	private String checkCommandLineNamedArgument(String name) {
		String fullName = getNamedArgumentFullName(name.substring(1));
		
		if (fullName.equals("")) {
			if (namedArgumentHolder.containsKey(name.substring(2))) {
				return name.substring(2);
			}
			else {
				return "";
			}
		}
		else {
			return fullName;
		}
	}
	
	
	private boolean checkNamedArgumentTypeBoolean(String name) {
		if (namedArgumentHolder.get(name).getType().equals(Argument.Type.BOOLEAN)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	
	private void setArgumentValue(String argumentName, String argumentValue){
	
		
		if (namedArgumentHolder.containsKey(argumentName)) {
			namedArgumentHolder.get(argumentName).setValue(argumentValue);
		}
		if (positionalArgumentHolder.containsKey(argumentName)) {
			positionalArgumentHolder.get(argumentName).setValue(argumentValue);
		}
		
	}
	
	
	public <T> T getArgumentValue(String name){
		if (positionalArgumentHolder.containsKey(name)) {
			return positionalArgumentHolder.get(name).getValue();
		}
		else if (namedArgumentHolder.containsKey(name)) {
			return (T)namedArgumentHolder.get(name).getValue();
		}
		else {
			String alternateFullName = getNamedArgumentFullName(name);
			if (alternateFullName.equals("")) {
				return (T)"Error key not found";
			}
			else {
				return (T)namedArgumentHolder.get(alternateFullName).getValue();
			}
		}
	}
	
	
	
	
	public String getHelpString(){
		return getHelpInfo();
	}
	
	
	private String getHelpInfo(){
		String helpMessage = this.getUsageLine() + "\n\n";
		helpMessage = helpMessage + programDescription + "\n\n";
		helpMessage = helpMessage + getPositionalArgumentsInfo();
		return helpMessage;
	}
	
	private String getUsageLine(){
		String usageString = "usage: java " + program;
		Iterator<String> hashKeys = positionalArgumentHolder.keySet().iterator();
		String argumentList = "";
		while (hashKeys.hasNext())
		{
			argumentList = argumentList + " " + hashKeys.next();
		}
		hashKeys = namedArgumentHolder.keySet().iterator();
		String currentKey = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			if(namedArgumentHolder.get(currentKey).getRequired())
			{
				argumentList = argumentList + " [--" + currentKey + "]";
			}
		}
		argumentList = argumentList.trim();
		usageString = usageString + " " + argumentList;
		usageString = usageString.trim();
		return usageString;
	}
	
	
	private String getPositionalArgumentsInfo(){
		String positionalArgumentsDescription = "positional arguments: ";
		Iterator<String> hashKeys = positionalArgumentHolder.keySet().iterator();
		String currentKey = "";
		String argumentsDescription = "";
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			argumentsDescription = argumentsDescription + currentKey + " " + positionalArgumentHolder.get(currentKey).getDescription() + "\n";
		}
		hashKeys = namedArgumentHolder.keySet().iterator();
		while (hashKeys.hasNext())
		{
			currentKey = hashKeys.next();
			if(namedArgumentHolder.get(currentKey).getRequired())
			{
				argumentsDescription = argumentsDescription + "[--" + currentKey + "] " + namedArgumentHolder.get(currentKey).getDescription() + "\n";
			}
		}
		argumentsDescription = argumentsDescription.trim();
		positionalArgumentsDescription = positionalArgumentsDescription + argumentsDescription;
		positionalArgumentsDescription = positionalArgumentsDescription.trim();
		return positionalArgumentsDescription;
	}	
}