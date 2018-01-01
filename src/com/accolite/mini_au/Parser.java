package com.accolite.mini_au;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Parser {
public static void main(String [] args) throws Exception {
	File fXmlFile = new File("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Advanced_Java_Assignment\\src\\com\\accolite\\mini_au\\example.xml");
	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
	Document doc = null;
	try {
		doc = dBuilder.parse(fXmlFile);
	}
	catch(Exception e)
	{
		System.out.println("Exception occured when reading input file");
		System.exit(1);
	}
	BufferedWriter merged =null;
	BufferedWriter il = null;
	BufferedWriter ill  = null;
	try {
	 merged = new BufferedWriter(new FileWriter("C:\\\\Users\\\\Hyderabad-Intern\\\\eclipse-workspace\\\\Advanced_Java_Assignment\\\\src\\\\com\\\\accolite\\\\mini_au\\\\merger.csv"));
	 il = new BufferedWriter(new FileWriter("C:\\\\Users\\\\Hyderabad-Intern\\\\eclipse-workspace\\\\Advanced_Java_Assignment\\\\src\\\\com\\\\accolite\\\\mini_au\\\\invalid-licenses.csv"));
	 ill = new BufferedWriter(new FileWriter("C:\\\\Users\\\\Hyderabad-Intern\\\\eclipse-workspace\\\\Advanced_Java_Assignment\\\\src\\\\com\\\\accolite\\\\mini_au\\\\invalid-license-lines.csv"));
	}
	catch(FileNotFoundException e)
	{
		System.out.println("Output File/Files not found");
		System.exit(1);
	}
	doc.getDocumentElement().normalize();
	
	
	merged.write("nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status,License Line,License Line Effective Date,License Line Expiry Date,License Line Status\n");
	il.write("nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status\n");
	ill.write("nipr,License ID,Jurisdiction,Resident,License Class,License Effective Date,License Expiry Date,License Status,License Line,License Line Effective Date,License Line Expiry Date,License Line Status\n");


	NodeList nList = doc.getElementsByTagName("CSR_Producer");
	ArrayList<License> l1 = new ArrayList<License>();

	for (int temp = 0; temp < nList.getLength(); temp++) {
		Node nNode = nList.item(temp);
		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;
			NodeList licenses = eElement.getElementsByTagName("License");
			for(int i =0;i<licenses.getLength();i++)
			{
				License l = new License();
				try {
					l.nipr = Integer.parseInt(eElement.getAttribute("NIPR_Number"));
				}
				catch(NumberFormatException e)
				{
					l.nipr = 0;
				}
				Element lic = (Element)licenses.item(i);
				
				try {
					l.license_id = Integer.parseInt(lic.getAttribute("License_Number"));
				}
				catch(NumberFormatException e)
				{
					l.license_id = 0;
				}
				l.state_code = lic.getAttribute("State_Code");
				try {
					l.effective_date = LocalDate.parse(lic.getAttribute("License_Issue_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				}
				catch(DateTimeParseException e)
				{
					l.effective_date = LocalDate.now();
				}
				try {
					l.exp_date = LocalDate.parse(lic.getAttribute("License_Expiration_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
				}
				catch(DateTimeParseException e)
				{
					l.exp_date = l.effective_date.plusYears(2);
				}
				l.lic_class = lic.getAttribute("License_Class");
				try {
					l.resident = lic.getAttribute("Resident_Indicator").charAt(0);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					l.resident = ' ';
				}
				try {
				l.lic_status = lic.getAttribute("License_Status").charAt(0);
				}
				catch(StringIndexOutOfBoundsException e)
				{
					l.lic_status = ' ';
				}
				l1.add(l);
			
			}
		}
	
	}

	File fXmlFile2 = new File("C:\\Users\\Hyderabad-Intern\\eclipse-workspace\\Advanced_Java_Assignment\\src\\com\\accolite\\mini_au\\example2.xml");
	Document doc2 = null;
	try {
		doc2 = dBuilder.parse(fXmlFile2);
	}
	catch(FileNotFoundException e)
	{
		System.out.println("Input File not found");
	}
	doc2.getDocumentElement().normalize();



	NodeList nList2 = doc2.getElementsByTagName("CSR_Producer");
	ArrayList<License> l2 = new ArrayList<License>();

	for (int temp = 0; temp < nList2.getLength(); temp++) {
		Node nNode = nList2.item(temp);

		if (nNode.getNodeType() == Node.ELEMENT_NODE) {
			Element eElement = (Element) nNode;

			NodeList licenses = eElement.getElementsByTagName("License");
			for(int i =0;i<licenses.getLength();i++)
			{
		
				Element lic = (Element)licenses.item(i);
		
				NodeList loa = lic.getElementsByTagName("LOA");
				for(int j =0;j<loa.getLength();j++)
				{
					License_LOA l = new License_LOA();
					try {
						l.nipr = Integer.parseInt(eElement.getAttribute("NIPR_Number"));
					}
					catch(NumberFormatException e)
					{
						l.nipr = 0;
					}
					
					try {
						l.license_id = Integer.parseInt(lic.getAttribute("License_Number"));
					}
					catch(NumberFormatException e)
					{
						l.license_id = 0;
					}
					l.state_code = lic.getAttribute("State_Code");
					try {
						l.effective_date = LocalDate.parse(lic.getAttribute("License_Issue_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					}
					catch(DateTimeParseException e)
					{
						l.effective_date = LocalDate.now();
					}
					try {
						l.exp_date = LocalDate.parse(lic.getAttribute("License_Expiration_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					}
					catch(DateTimeParseException e)
					{
						l.exp_date = l.effective_date.plusYears(2);
					}
					l.lic_class = lic.getAttribute("License_Class");
					try {
						l.resident = lic.getAttribute("Resident_Indicator").charAt(0);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						l.resident = ' ';
					}
					try {
					l.lic_status = lic.getAttribute("License_Status").charAt(0);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						l.lic_status = ' ';
					}
					Element line = (Element) loa.item(j);
					l.loa = line.getAttribute("LOA_Name");	
					try {
						l.loa_effective_date = LocalDate.parse(line.getAttribute("LOA_Issue_Date"),DateTimeFormatter.ofPattern("MM/dd/yyyy"));
					
					}
					catch(DateTimeParseException e)
					{
						l.loa_effective_date = LocalDate.now();
					}
					l.loa_exp_date = l.loa_effective_date.plusYears(2);
					try {
						l.loa_status = line.getAttribute("LOA_Status").charAt(0);
					}
					catch(StringIndexOutOfBoundsException e)
					{
						l.loa_status = ' ';
					}
					if(l1.contains(l))
					{
						merged.write(l.toString());
					}
					else
					{
						ill.write(l.toString());
					}
					l2.add(l);
				}
		
		
		
			}	
		}

	}


	for(License l:l1)
	{
		if (!l2.contains(l))
		{
			il.write(l.toString());
		}
}

	merged.close();
	il.close();
	ill.close();
	System.out.println("Parsing completed. Check the output files");
	}
}