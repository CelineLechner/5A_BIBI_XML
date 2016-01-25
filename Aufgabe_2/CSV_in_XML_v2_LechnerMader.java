import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class CSV_in_XML_v2_LechnerMader {

	private static String CSVFile = "C:/Users/armin/Documents/Programmieren/Workspace/CSV_in_XML_v2_LechnerMader/Daten_Mader_Lechner.csv";
	private static FileReader fr;
	private static BufferedReader br;
	private static int anzahlGaeste = 0;
	private static String[][] Gaeste;
	private static String[] split;
	private static String zeile;
	
	private static DocumentBuilderFactory dbf;
	private static DocumentBuilder db;
	private static Element rootElement;
	private static Document d;
	private static Element Restaurant;
	private static Attr TischNummerAttribute;
	private static String TischNummer;
	private static Element Speise;
	private static Element Getränk;
	private static Element Preis;
	private static String SpeiseX;
	private static String GetränkX;
	private static String PreisX;
	private static TransformerFactory tf;
	private static Transformer t;
	private static DOMSource source;
	private static StreamResult result;
	private static StreamResult cResult;
	
	
	
	public static void anzahlGaeste() throws IOException
	{
		fr = new FileReader(CSVFile);
		br = new BufferedReader(fr);
		while (br.readLine() != null)
		{
			anzahlGaeste++;
		}
		br.close();
	}
	
	public static void CSVdatenLesen() throws IOException
	{
		anzahlGaeste();
		
		fr = new FileReader(CSVFile);
		br = new BufferedReader(fr);
		
		Gaeste = new String[anzahlGaeste][4];
		
		for(int i = 0; i < anzahlGaeste; i++)
		{
			zeile = br.readLine();
			split = zeile.split(";");
			
			for (int j = 0; j < split.length; j++)
			{
				Gaeste[i][j] = split[j];
			}
		}
	}
	
	public static void auslesen()
	{
		for (int i = 0; i < Gaeste.length; i++)
		{
			System.out.println("Zeile " + (i + 1 + ": "));
			System.out.println(" ");
			for (int j = 0; j < Gaeste[i].length; j++)
			{
				System.out.println(Gaeste[i][j]);
				
			}
			System.out.println("---------------------------");
		}
	}
	
	
	public static void CSVinXML() throws IOException
	{
		try {
			dbf = DocumentBuilderFactory.newInstance();
			db = dbf.newDocumentBuilder();
			d = db.newDocument();
			rootElement = d.createElement("Gaeste");
			d.appendChild(rootElement);
			
			for (int i = 0; i < Gaeste.length; i++)
			{
				Restaurant = d.createElement("Restaurant");
				TischNummerAttribute = d.createAttribute("Tisch");
				TischNummer = Gaeste[i][0];
				
				if (TischNummer.equals("Tisch1"))
				{
					TischNummerAttribute.setValue("Tisch1");
				} else if(TischNummer.equals("Tisch2"))
				{
					TischNummerAttribute.setValue("Tisch2");
				} else if(TischNummer.equals("Tisch3"))
				{
					TischNummerAttribute.setValue("Tisch3");
				} else if(TischNummer.equals("Tisch4"))
				{
					TischNummerAttribute.setValue("Tisch4");
				} else if(TischNummer.equals("Tisch5"))
				{
					TischNummerAttribute.setValue("Tisch5");
				} else if(TischNummer.equals("Tisch6"))
				{
					TischNummerAttribute.setValue("Tisch6");
				}
				
				Restaurant.setAttributeNode(TischNummerAttribute);
				
				Speise = d.createElement("Speise");
				Getränk = d.createElement("Getränk");
				Preis = d.createElement("Preis");
				
				SpeiseX = Gaeste[i][1];
				GetränkX = Gaeste[i][2];
				PreisX = Gaeste[i][3];
				
				Speise.appendChild(d.createTextNode(SpeiseX));
				Getränk.appendChild(d.createTextNode(GetränkX));
				Preis.appendChild(d.createTextNode(PreisX));
				
				Restaurant.appendChild(Speise);
				Restaurant.appendChild(Getränk);
				Restaurant.appendChild(Preis);
				
				rootElement.appendChild(Restaurant);
			}
			
			tf = TransformerFactory.newInstance();
			t = tf.newTransformer();
			source = new DOMSource(d);
			result = new StreamResult(new File("Speisen_LechnerMader.xml"));
			t.transform(source, result);
			cResult = new StreamResult(System.out);
			t.transform(source, cResult);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	
	
	public static void main(String[] args) throws IOException
	{
		CSVdatenLesen();
		auslesen();
		CSVinXML();
	}
}
