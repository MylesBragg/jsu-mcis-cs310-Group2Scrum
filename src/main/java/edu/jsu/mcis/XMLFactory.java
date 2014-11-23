package edu.jsu.mcis;

import java.io.*;
import java.util.*;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLFactory 
{
	private static ArgumentParser p;
	private static String name, description, alternateName, filePath, fileName;
	private static Argument.Type type;
	private static Object defaultValue;
	private static boolean required;
	private static Scanner myScanner;
	
	public static ArgumentParser loadArgumentParser(String programName, String xmlFile) 
	{
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
	
	private static void instantiateContentHolders() 
	{
		name = "";
		description = "";
		alternateName = "";
		defaultValue = null;
		required = false;
	}
	
	private static void getDataFromXML(NodeList currentArguments) 
	{
		for (int i = 0; i < currentArguments.getLength(); i++) 
		{
			Node node = currentArguments.item(i);
			
			if (node instanceof Element) 
			{
				String argumentType = node.getAttributes().getNamedItem("type").getNodeValue();

				NodeList childNodes = node.getChildNodes();
				
				getContentFromArgumentNode(childNodes, argumentType.toLowerCase());
			}
		}	
	}
	
	private static void getContentFromArgumentNode(NodeList currentArgument, String argumentType) 
	{
		for (int j = 0; j < currentArgument.getLength(); j++) 
		{
			Node cNode = currentArgument.item(j);

			if (cNode instanceof Element) 
			{
				String nodeName = cNode.getNodeName().toLowerCase();
				String content = cNode.getLastChild().getTextContent().trim();
	
				assignContent(nodeName, content);
			}
		}
		if (argumentType.equals("positional")) 
		{
			addPositionalArgument();
		}
		else if (argumentType.equals("named")) 
		{
			addNamedArgument();
		}
		else 
		{
		}
		resetContentHolders();
	}
	
	private static void assignContent(String nodeName, String content) 
	{
		switch(nodeName) 
		{
			case "name":
				name = content;
				break;
			case "type":
				setType(content);
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
	private static void setType(String content)
	{
		switch(content.toLowerCase()) 
		{
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
	}
	private static void addPositionalArgument() 
	{
		p.addPositionalArgument(name, type);
		p.setArgumentDescription(name, description);
	}
	
	private static void addNamedArgument() 
	{
		p.addNamedArgument(name, type);
		p.setArgumentDescription(name, description);
		p.setNamedArgumentAlternateName(name, alternateName);
		
		if (defaultValue != null) 
		{
			p.setNamedArgumentDefaultValue(name, defaultValue);
		}
		if (required) 
		{
			p.setNamedArgumentRequired(name);
		}
	}
	
	private static void resetContentHolders() 
	{
		instantiateContentHolders();
	}
	
	private static Node writePositionalArgument(Document currentDocument, String name) 
	{
		String type;
		Element argument = currentDocument.createElement("argument");
		
		switch(getArgumentType(name)) 
		{
			case INT:
				type = "integer";
				break;
			case BOOLEAN:
				type = "boolean";
				break;
			case FLOAT:
				type = "float";
				break;
			default:
				type = "string";
				break;
		}
		argument.setAttribute("type", "positional");
		argument.appendChild(setChildNodeValue(currentDocument, argument, "name", name));
		argument.appendChild(setChildNodeValue(currentDocument, argument, "type", type));
		if (!getArgumentDescription(name).equals("")) {
			argument.appendChild(setChildNodeValue(currentDocument, argument, "description", getArgumentDescription(name)));
		}
		
		
		return argument;
	}
	
	private static Node writeNamedArgument(Document currentDocument, String name) 
	{
		String type;
		Element argument = currentDocument.createElement("argument");
		
		switch(getArgumentType(name)) {
			case INT:
				type = "integer";
				break;
			case BOOLEAN:
				type = "boolean";
				break;
			case FLOAT:
				type = "float";
				break;
			default:
				type = "string";
				break;
		}
		argument.setAttribute("type", "named");
		argument.appendChild(setChildNodeValue(currentDocument, argument, "name", name));
		argument.appendChild(setChildNodeValue(currentDocument, argument, "type", type));
		if (!getArgumentDescription(name).equals("")) {
			argument.appendChild(setChildNodeValue(currentDocument, argument, "description", getArgumentDescription(name)));
		}
		if (!getNamedArgumentAlternateName(name).equals("")) 
		{
			argument.appendChild(setChildNodeValue(currentDocument, argument, "alternatename", getNamedArgumentAlternateName(name)));
		}
		if (getNamedArgumentDefaultValue(name) != null) 
		{
			argument.appendChild(setChildNodeValue(currentDocument, argument, "defaultvalue", getNamedArgumentDefaultValue(name).toString()));
		}
		if (getNamedArgumentRequired(name) == true) 
		{
			argument.appendChild(setChildNodeValue(currentDocument, argument, "required", "true"));
		}
		return argument;
	}
	
	private static Node setChildNodeValue(Document currentDocument, Element currentElement, String name, String value) 
	{
		Element node = currentDocument.createElement(name);
        node.appendChild(currentDocument.createTextNode(value));
        return node;
	}
	
	public static void saveArgumentParser(ArgumentParser currentParser)
	{
		p = currentParser;
		String names = getArgumentNames();
		myScanner = new Scanner(names);
		int positionalSize = getPositionalArgumentHolderSize();
		
		DocumentBuilderFactory icFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder icBuilder;
        try 
		{
            icBuilder = icFactory.newDocumentBuilder();
            Document currentDocument = icBuilder.newDocument();
            Element mainRootElement = currentDocument.createElement("arguments");
            currentDocument.appendChild(mainRootElement);
			int i = 1;
            while(myScanner.hasNext()) 
			{
				if (i <= positionalSize)
				{
					mainRootElement.appendChild(writePositionalArgument(currentDocument, myScanner.next()));
					i++;
				}
				else 
				{
				mainRootElement.appendChild(writeNamedArgument(currentDocument, myScanner.next()));
				}
			}   
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(currentDocument);
			int fileCount = 0;
			File f = new File(p.getProgramName() +".xml");
			
			while(f.exists()) 
			{
				fileCount++;
				f = new File(p.getProgramName() + fileCount +".xml");
			}
            StreamResult console = new StreamResult(f);
            transformer.transform(source, console);
 
            System.out.println("\nXML DOM Created Successfully..");
			
			System.out.println("\nYour new file name is: " + f.getName());
			
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	public static void setFileLocation(String newFilePath) {
		filePath = newFilePath + "\\";
	}
	
	public static void setFileName(String newFileName) {
		fileName = newFileName;
	}
	private static String getArgumentNames()
	{
		return p.getArgumentNames();
	}
	
	private static Argument.Type getArgumentType(String name)
	{
		return p.getArgumentType(name);
	}
	
	private static String getArgumentDescription(String name)
	{
		return p.getArgumentDescription(name);
	}
	
	private static String getNamedArgumentAlternateName(String name)
	{
		return p.getNamedArgumentAlternateName(name);
	}
	
	private static boolean getNamedArgumentRequired(String name)
	{
		return p.getNamedArgumentRequired(name);
	}
	
	private static <T> T getNamedArgumentDefaultValue(String name)
	{
		return p.getNamedArgumentDefaultValue(name);
	}
	
	private static int getPositionalArgumentHolderSize()
	{
		return p.getPositionalArgumentSize();
	}
}