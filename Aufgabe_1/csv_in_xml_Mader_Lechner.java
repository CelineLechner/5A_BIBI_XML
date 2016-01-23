package csv_in_xml_wo_doBil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class csv_in_xml_Mader_Lechner {
	static ArrayList<String> al = new ArrayList<String>();
	static int x = 0;

	public static void Einlesen() throws IOException {
		BufferedReader br = new BufferedReader(
				new FileReader("C:/Users/Celine/Documents/Schule/5aHWI/Programmieren/BIBI_XML/Daten_Mader_Lechner.csv"));
		String line;

		while ((line = br.readLine()) != null) {
			String[] splitted = line.split(";");

			for (int i = 0; i < splitted.length; i++) {
				al.add(splitted[i]);
			}
			String Tisch = splitted[0];
			String Speise = splitted[1];
			String Getränk = splitted[2];
			String Preis = splitted[3];
		}
	}

	public static void csvToXml() throws FileNotFoundException {
		File f = new File("C:/Users/Celine/Documents/Schule/5aHWI/Programmieren/BIBI_XML/Speise_Mader_Lechner.xml");
		PrintWriter pw = new PrintWriter(f);
		pw.println("<?xml version=\"1.0\"encoding=\"UTF-8\"?>");

		pw.println("<Restaurant>");

		for (int i = 0; i < al.size() / 4; i++) {
			pw.println("\t" + "<Gast>");
			pw.println("\t\t" + "<TischNr>" + al.get(x) + "</TischNr>");
			pw.println("\t\t" + "<Speise>" + al.get(x + 1) + "</Speise>");
			pw.println("\t\t" + "<Getränk>" + al.get(x + 2) + "</Getränk>");
			pw.println("\t\t" + "<Preis>" + al.get(x + 3) + "</Preis>");
			pw.println("\t" + "</Gast>");
			x = x + 4;
		}
		pw.println("</Restaurant>");
		pw.flush();
		pw.close();
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		Einlesen();
		csvToXml();

	}

}
