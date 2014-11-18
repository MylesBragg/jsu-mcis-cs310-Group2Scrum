package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class XMLFactory {
	private static ArgumentParser p;
	private static String name, description, alternateName;
	private static Argument.Type type;
	private static Object defaultValue;
	private static boolean required;
	private static Scanner myScanner;
	
	public static ArgumentParser createArgumentParser(String programName, String xmlFile) {

		p = new ArgumentParser(programName);
		
		instantiateContentHolders();
		try{
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(ClassLoader.getSystemResourceAsStream(xmlFile));
			NodeList nodeList = document.getDocumentElement().getChildNodes();
		
		getDataFromXML(nodeList);
		}catch(ParserConfigurationException e) {
		
		}catch(SAXException e) {
		
		}catch(IOException e) {
		
		}
		return p;
	}
	
	private static void instantiateContentHolders() {
		name = "";
		description = "";
		alternateName = "";
		defaultValue = null;
		required = false;
	}
	private static void getDataFromXML(NodeList currentArguments) {
		for (int i = 0; i < currentArguments.getLength(); i++) {
			
			Node node = currentArguments.item(i);
			
			if (node instanceof Element) {
			
				String argumentType = node.getAttributes().getNamedItem("type").getNodeValue();

				NodeList childNodes = node.getChildNodes();
				
				getContentFromArgumentNode(childNodes, argumentType.toLowerCase());
			}
		}
				
	}
	private static void getContentFromArgumentNode(NodeList currentArgument, String argumentType) {
		for (int j = 0; j < currentArgument.getLength(); j++) {
				
			Node cNode = currentArgument.item(j);


			if (cNode instanceof Element) {
			
				String nodeName = cNode.getNodeName().toLowerCase();
				String content = cNode.getLastChild().getTextContent().trim();
				
				assignContent(nodeName, content);
				
			}
		}
		if (argumentType.equals("positional")) {
			addPositionalArgument();
		}
		else if (argumentType.equals("named")) {
			addNamedArgument();
		}
		else {
		
		}
		resetContentHolders();
	}
	
	private static void assignContent(String nodeName, String content) {
		switch(nodeName) {
			case "name":
				name = content;
				break;
			case "type":
				switch(content.toLowerCase()) {
					case "integer":
						type = Argument.Type.INT;
						break;
					case "boolean":
						type = Argument.Type.BOOLEAN;
						break;
					case "float":
						type = Argument.Type.FLOAT;
						break;
					default:
						type = Argument.Type.STRING;
						break;
				}
				break;
			case "description":
				description = content;
				break;
			case "alternatename":
				alternateName = content;
				break;
			case "required" :
				required = true;
				break;
			case "defaultvalue":
				defaultValue = content;
				break;
		}
	}
	
	private static void addPositionalArgument() {
		p.addPositionalArgument(name, type);
		p.setArgumentDescription(name, description);
	}
	private static void addNamedArgument() {
		p.addNamedArgument(name, type);
		p.setArgumentDescription(name, description);
		p.setNamedArgumentAlternateName(name, alternateName);
		
		if (defaultValue != null) {
			p.setNamedArgumentDefaultValue(name, defaultValue);
		}
		
		if (required) {
			p.setNamedArgumentRequired(name);
		}
	}
	
	private static void resetContentHolders() {
		instantiateContentHolders();
	}
	
	public static void writeArgumentParser(ArgumentParser currentParser){
		p = currentParser;
		String names = getArgumentNames();
		myScanner = new Scanner(names);
		int positionalSize = getPositionalArgumentHolderSize();
		
		
	}
	/*************************************************************/
	// Saving to XML File
	public static String getArgumentNames()
	{
		return p.getArgumentNames();
	}
	
	public static Argument.Type getArgumentType(String name)
	{
		return p.getArgumentType(name);
	}
	
	public static String getArgumentDescription(String name)
	{
		return p.getArgumentDescription(name);
	}
	
	public static String getNamedArgumentAlternateName(String name)
	{
		return p.getNamedArgumentAlternateName(name);
	}
	
	public static boolean getNamedArgumentRequired(String name)
	{
		return p.getNamedArgumentRequired(name);
	}
	
	public static <T> T getNamedArgumentDefaultValue(String name)
	{
		return p.getNamedArgumentDefaultValue(name);
	}
	
	public static int getPositionalArgumentHolderSize()
	{
		return 7;
	}
}