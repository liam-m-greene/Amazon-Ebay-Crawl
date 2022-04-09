import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub

		JFrame frame1 = new JFrame("Introduction");
		frame1.getContentPane().setBackground(Color.WHITE);

		JTextField searchable = new JTextField(30);
		JButton searchB = new JButton("Search");
		JPanel panel = new JPanel();


		JCheckBox amazonDE = new JCheckBox("Amazon.de");
		panel.add(amazonDE);
		JCheckBox amazonFR = new JCheckBox("Amazon.fr");
		panel.add(amazonFR);
		JCheckBox amazonES = new JCheckBox("Amazon.es");
		panel.add(amazonES);
		JCheckBox amazonIT = new JCheckBox("Amazon.it");
		panel.add(amazonIT);
		JCheckBox amazonNL = new JCheckBox("Amazon.nl");
		panel.add(amazonNL);
		JCheckBox ebayDE = new JCheckBox("Ebay.de");
		panel.add(ebayDE);

		panel.add(searchable);
		panel.add(searchB);


		frame1.add(panel);
		frame1.pack(); 
		frame1.setLayout(null);    
		frame1.setVisible(true);    
		frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		searchB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String keyword = searchable.getText();
				boolean amazonDEChecked = amazonDE.isSelected();
				boolean amazonFRChecked = amazonFR.isSelected();
				boolean amazonESChecked = amazonES.isSelected();
				boolean amazonITChecked = amazonIT.isSelected();
				boolean amazonNLChecked = amazonNL.isSelected();
				boolean ebayDEChecked = ebayDE.isSelected();
				try {
					scrape(keyword,amazonDEChecked,amazonFRChecked,amazonESChecked,amazonITChecked,amazonNLChecked,ebayDEChecked);
				} catch (IOException | InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});

		//String dataToScrape = "https://www.walmart.com/ip/NYX-Professional-Makeup-Total-Control-Drop-Foundation-Nutmeg/436719582";
		//System.out.println(dataToScrape);
		//scrape(dataToScrape);

	}





	public static void scrape(String keyword, boolean amazonDEChecked, boolean amazonFRChecked, boolean amazonESChecked,
			boolean amazonITChecked, boolean amazonNLChecked, boolean ebayDEChecked) throws IOException, InterruptedException {

		LinkedList itemTitleAmazonDE = new LinkedList();
		LinkedList itemTitleAmazonFR = new LinkedList();
		LinkedList itemTitleAmazonES = new LinkedList();
		LinkedList itemTitleAmazonIT = new LinkedList();
		LinkedList itemTitleAmazonNL = new LinkedList();
		LinkedList itemTitleEbayDE = new LinkedList();

		if(amazonDEChecked == true) {

			String url = "https://www.amazon.de/s?k=" + keyword;
			URL url2 = new URL (url);
			String url3 = "https://www.amazon.de/s?k=" + keyword + "&page=2";
			URL url4 = new URL (url3);
			HttpURLConnection connection = null;

			for (int i = 0; i<2;i++) {
				if (i == 0) {
					connection = (HttpURLConnection) url2.openConnection();
				}
				else if(i == 1) {
					connection = (HttpURLConnection) url4.openConnection();
				}
				connection.setRequestMethod("GET");
				System.setProperty("http.agent", "");
				connection.connect();

				int code = connection.getResponseCode();

				if (code == 429) {
					System.out.println("Error");
				}

				else {

					Thread.sleep(2000);
					Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").referrer("http://www.amazon.de").followRedirects(true).ignoreHttpErrors(true).get();

					String docForRegex = doc.toString();
					//System.out.println(docForRegex);

					Pattern linkPattern1 = Pattern.compile("(?<=alt=)(.*)(?=srcset=)");
					Matcher aboutMatcher = linkPattern1.matcher(docForRegex);
					//email.add("||||");
					while(aboutMatcher.find()){
						itemTitleAmazonDE.add(aboutMatcher.group());
					}

				}
			} 
		}

		/* for (int i = 0; i < itemTitleAmazonDE.size(); i++) {
			System.out.println(itemTitleAmazonDE.get(i).toString());
		} */

		if(amazonFRChecked == true) {

			String url = "https://www.amazon.fr/s?k=" + keyword;
			URL url2 = new URL (url);
			String url3 = "https://www.amazon.fr/s?k=" + keyword + "&page=2";
			URL url4 = new URL (url3);
			HttpURLConnection connection = null;

			for (int i = 0; i<2;i++) {
				if (i == 0) {
					connection = (HttpURLConnection) url2.openConnection();
				}
				else if(i == 1) {
					connection = (HttpURLConnection) url4.openConnection();
				}
				connection.setRequestMethod("GET");
				System.setProperty("http.agent", "");
				connection.connect();

				int code = connection.getResponseCode();

				if (code == 429) {
					System.out.println("Error");
				}

				else {

					Thread.sleep(2000);
					Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").referrer("http://www.amazon.de").followRedirects(true).ignoreHttpErrors(true).get();

					String docForRegex = doc.toString();
					//System.out.println(docForRegex);

					Pattern linkPattern1 = Pattern.compile("(?<=alt=)(.*)(?=srcset=)");
					Matcher aboutMatcher = linkPattern1.matcher(docForRegex);
					//email.add("||||");
					while(aboutMatcher.find()){
						itemTitleAmazonFR.add(aboutMatcher.group());
					}

				}
			} 

		}

		/* System.out.println("----------- Amazon France ------------");
		for (int i = 0; i < itemTitleAmazonFR.size(); i++) {
			System.out.println(itemTitleAmazonFR.get(i).toString());
		} */

		if(amazonESChecked == true) {

			String url = "https://www.amazon.es/s?k=" + keyword;
			URL url2 = new URL (url);
			String url3 = "https://www.amazon.es/s?k=" + keyword + "&page=2";
			URL url4 = new URL (url3);
			HttpURLConnection connection = null;

			for (int i = 0; i<2;i++) {
				if (i == 0) {
					connection = (HttpURLConnection) url2.openConnection();
				}
				else if(i == 1) {
					connection = (HttpURLConnection) url4.openConnection();
				}
				connection.setRequestMethod("GET");
				System.setProperty("http.agent", "");
				connection.connect();

				int code = connection.getResponseCode();

				if (code == 429) {
					System.out.println("Error");
				}

				else {

					Thread.sleep(2000);
					Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").referrer("http://www.amazon.de").followRedirects(true).ignoreHttpErrors(true).get();

					String docForRegex = doc.toString();
					//System.out.println(docForRegex);

					Pattern linkPattern1 = Pattern.compile("(?<=alt=)(.*)(?=srcset=)");
					Matcher aboutMatcher = linkPattern1.matcher(docForRegex);
					//email.add("||||");
					while(aboutMatcher.find()){
						itemTitleAmazonES.add(aboutMatcher.group());
					}

				}
			} 

		}

		/* System.out.println("----------- Amazon Spain ------------");
		for (int i = 0; i < itemTitleAmazonES.size(); i++) {
			System.out.println(itemTitleAmazonES.get(i).toString());
		} */

		if(amazonITChecked == true) {

			String url = "https://www.amazon.it/s?k=" + keyword;
			URL url2 = new URL (url);
			String url3 = "https://www.amazon.it/s?k=" + keyword + "&page=2";
			URL url4 = new URL (url3);
			HttpURLConnection connection = null;

			for (int i = 0; i<2;i++) {
				if (i == 0) {
					connection = (HttpURLConnection) url2.openConnection();
				}
				else if(i == 1) {
					connection = (HttpURLConnection) url4.openConnection();
				}
				connection.setRequestMethod("GET");
				System.setProperty("http.agent", "");
				connection.connect();

				int code = connection.getResponseCode();

				if (code == 429) {
					System.out.println("Error");
				}

				else {

					Thread.sleep(2000);
					Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").referrer("http://www.amazon.de").followRedirects(true).ignoreHttpErrors(true).get();

					String docForRegex = doc.toString();
					//System.out.println(docForRegex);

					Pattern linkPattern1 = Pattern.compile("(?<=alt=)(.*)(?=srcset=)");
					Matcher aboutMatcher = linkPattern1.matcher(docForRegex);
					//email.add("||||");
					while(aboutMatcher.find()){
						itemTitleAmazonIT.add(aboutMatcher.group());
					}

				}
			} 

		}

		/* System.out.println("----------- Amazon Italy ------------");
		for (int i = 0; i < itemTitleAmazonIT.size(); i++) {
			System.out.println(itemTitleAmazonIT.get(i).toString());
		} */

		if(amazonNLChecked == true) {

			String url = "https://www.amazon.nl/s?k=" + keyword;
			URL url2 = new URL (url);
			String url3 = "https://www.amazon.nl/s?k=" + keyword + "&page=2";
			URL url4 = new URL (url3);
			HttpURLConnection connection = null;

			for (int i = 0; i<2;i++) {
				if (i == 0) {
					connection = (HttpURLConnection) url2.openConnection();
				}
				else if(i == 1) {
					connection = (HttpURLConnection) url4.openConnection();
				}
				connection.setRequestMethod("GET");
				System.setProperty("http.agent", "");
				connection.connect();

				int code = connection.getResponseCode();

				if (code == 429) {
					System.out.println("Error");
				}

				else {

					Thread.sleep(2000);
					Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").referrer("http://www.amazon.de").followRedirects(true).ignoreHttpErrors(true).get();

					String docForRegex = doc.toString();
					//System.out.println(docForRegex);

					Pattern linkPattern1 = Pattern.compile("(?<=alt=)(.*)(?=srcset=)");
					Matcher aboutMatcher = linkPattern1.matcher(docForRegex);
					//email.add("||||");
					while(aboutMatcher.find()){
						itemTitleAmazonNL.add(aboutMatcher.group());
					}

				}
			} 

		}

		/* System.out.println("----------- Amazon Netherlands ------------");
		for (int i = 0; i < itemTitleAmazonNL.size(); i++) {
			System.out.println(itemTitleAmazonNL.get(i).toString());
		} */

		if(ebayDEChecked == true) {

			String url = "https://www.ebay.de/sch/i.html?_nkw=" + keyword;
			URL url2 = new URL (url);
			String url3 = "https://www.ebay.de/sch/i.html?__nkw=" + keyword + "&_sacat=0&_pgn=2";
			URL url4 = new URL (url3);
			HttpURLConnection connection = null;

			for (int i = 0; i<2;i++) {
				if (i == 0) {
					connection = (HttpURLConnection) url2.openConnection();
				}
				else if(i == 1) {
					connection = (HttpURLConnection) url4.openConnection();
				}
				connection.setRequestMethod("GET");
				System.setProperty("http.agent", "");
				connection.connect();

				int code = connection.getResponseCode();
				//System.out.println(code);

				if (code == 429) {
					System.out.println("Error");
				}

				else {

					Thread.sleep(2000);
					Document doc = Jsoup.connect(url.toString()).userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/76.0.3809.132 Safari/537.36").referrer("google.com").followRedirects(true).ignoreHttpErrors(false).get();
					String docForRegex = doc.toString();
					//System.out.println(docForRegex);

					Pattern linkPattern1 = Pattern.compile("(?<=alt=\\\")(.*)(?=\\\" src)");
					Matcher aboutMatcher = linkPattern1.matcher(docForRegex);
					//email.add("||||");
					while(aboutMatcher.find()){
						itemTitleEbayDE.add(aboutMatcher.group());
					}

				}
			} 

		}

		/*System.out.println("----------- Ebay Germanyy ------------");
		for (int i = 0; i < itemTitleEbayDE.size(); i++) {
			System.out.println(itemTitleEbayDE.get(i).toString());
		} */




		if(amazonDEChecked == true) {
			LinkedList bagOfWordsADE = new LinkedList();
			for (int i = 0; i < itemTitleAmazonDE.size(); i++) {
				String[] addToBOW = itemTitleAmazonDE.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = 0; j<addToBOW.length; j++) {
					bagOfWordsADE.add(addToBOW[j]);
				}
			}

			/* for (int i = 0; i < bagOfWordsADE.size(); i++) {
			System.out.println(bagOfWordsADE.get(i).toString());
		} */



			String[] words = new String[bagOfWordsADE.size()];
			int[] counts = new int[bagOfWordsADE.size()];

			for (int i = 0; i < bagOfWordsADE.size(); i++) {
				words[i] = bagOfWordsADE.get(i).toString().toUpperCase();
			}

			for (int i = 0; i < bagOfWordsADE.size(); i++) {
				counts[i] = 0;
			}

			for (int i = 0; i < bagOfWordsADE.size(); i++) {
				for (int j = 0; j < bagOfWordsADE.size(); j++) {
					if (words[i].toUpperCase().equals(words[j].toUpperCase())) {
						counts[i] = counts[i] + 1;
					} 
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("Amazon Germany Search '" + keyword + "'.csv"));

			int lengthAmazonDEForPrinting = 0;
			for (int i = 0; i <  itemTitleAmazonDE.size(); i++) {
				int lengthAmazonDEForPrinting2 = lengthAmazonDEForPrinting;
				writer.write( "\n " + itemTitleAmazonDE.get(i).toString().replace("\"", "").replace("\"", "").replace(",", "-"));
			}
			
			
			LinkedList wordsRemoveDup = new LinkedList();
			for (int i = 0; i < words.length; i++) {
				boolean match = false;
				match = wordsRemoveDup.contains(words[i].toUpperCase().replace(",", "-"));
				if (match == true) {
					 
				}
				else {
					wordsRemoveDup.add(words[i].toUpperCase().replace(",", "-"));
					wordsRemoveDup.add("," + counts[i]);
				}
				
			}
			for (int i = 0; i < wordsRemoveDup.size(); i=i+2) {
				writer.write("\n" + wordsRemoveDup.get(i).toString() + wordsRemoveDup.get(i+1).toString() );
			}
			
			writer.close();
		}

		if(amazonFRChecked == true) {
			LinkedList bagOfWordsAFR = new LinkedList();
			for (int i = 0; i < itemTitleAmazonFR.size(); i++) {
				String[] addToBOW = itemTitleAmazonFR.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = 0; j<addToBOW.length; j++) {
					bagOfWordsAFR.add(addToBOW[j]);
				}
			}

			/* for (int i = 0; i < bagOfWordsAFR.size(); i++) {
			System.out.println(bagOfWordsAFR.get(i).toString());
		} */



			String[] words = new String[bagOfWordsAFR.size()];
			int[] counts = new int[bagOfWordsAFR.size()];

			for (int i = 0; i < bagOfWordsAFR.size(); i++) {
				words[i] = bagOfWordsAFR.get(i).toString().toUpperCase();
			}

			for (int i = 0; i < bagOfWordsAFR.size(); i++) {
				counts[i] = 0;
			}

			for (int i = 0; i < bagOfWordsAFR.size(); i++) {
				for (int j = 0; j < bagOfWordsAFR.size(); j++) {
					if (words[i].toUpperCase().equals(words[j].toUpperCase())) {
						counts[i] = counts[i] + 1;
					} 
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("Amazon France Search '" + keyword + "'.csv"));

			int lengthAmazonFRForPrinting = 0;
			for (int i = 0; i <  itemTitleAmazonFR.size(); i++) {
				int lengthAmazonFRForPrinting2 = lengthAmazonFRForPrinting;
				writer.write( "\n " + itemTitleAmazonFR.get(i).toString().replace("\"", "").replace("\"", "").replace(",", "-"));
			}
			
			
			LinkedList wordsRemoveDup = new LinkedList();
			for (int i = 0; i < words.length; i++) {
				boolean match = false;
				match = wordsRemoveDup.contains(words[i].toUpperCase().replace(",", "-"));
				if (match == true) {
					 
				}
				else {
					wordsRemoveDup.add(words[i].toUpperCase().replace(",", "-"));
					wordsRemoveDup.add("," + counts[i]);
				}
				
			}
			for (int i = 0; i < wordsRemoveDup.size(); i=i+2) {
				writer.write("\n" + wordsRemoveDup.get(i).toString() + wordsRemoveDup.get(i+1).toString() );
			}
			
			writer.close();
		}


		if(amazonESChecked == true) {
			LinkedList bagOfWordsAES = new LinkedList();
			for (int i = 0; i < itemTitleAmazonES.size(); i++) {
				String[] addToBOW = itemTitleAmazonES.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = 0; j<addToBOW.length; j++) {
					bagOfWordsAES.add(addToBOW[j]);
				}
			}

			/* for (int i = 0; i < bagOfWordsAES.size(); i++) {
			System.out.println(bagOfWordsAES.get(i).toString());
		} */



			String[] words = new String[bagOfWordsAES.size()];
			int[] counts = new int[bagOfWordsAES.size()];

			for (int i = 0; i < bagOfWordsAES.size(); i++) {
				words[i] = bagOfWordsAES.get(i).toString().toUpperCase();
			}

			for (int i = 0; i < bagOfWordsAES.size(); i++) {
				counts[i] = 0;
			}

			for (int i = 0; i < bagOfWordsAES.size(); i++) {
				for (int j = 0; j < bagOfWordsAES.size(); j++) {
					if (words[i].toUpperCase().equals(words[j].toUpperCase())) {
						counts[i] = counts[i] + 1;
					} 
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("Amazon Spain Search '" + keyword + "'.csv"));


			int lengthAmazonESForPrinting = 0;
			for (int i = 0; i <  itemTitleAmazonES.size(); i++) {
				int lengthAmazonESForPrinting2 = lengthAmazonESForPrinting;
				writer.write( "\n " + itemTitleAmazonES.get(i).toString().replace("\"", "").replace("\"", "").replace(",", "-"));
			}
			
			
			LinkedList wordsRemoveDup = new LinkedList();
			for (int i = 0; i < words.length; i++) {
				boolean match = false;
				match = wordsRemoveDup.contains(words[i].toUpperCase().replace(",", "-"));
				if (match == true) {
					 
				}
				else {
					wordsRemoveDup.add(words[i].toUpperCase().replace(",", "-"));
					wordsRemoveDup.add("," + counts[i]);
				}
				
			}
			for (int i = 0; i < wordsRemoveDup.size(); i=i+2) {
				writer.write("\n" + wordsRemoveDup.get(i).toString() + wordsRemoveDup.get(i+1).toString() );
			}
			
			writer.close();
		}

		if(amazonITChecked == true) {
			LinkedList bagOfWordsAIT = new LinkedList();
			for (int i = 0; i < itemTitleAmazonIT.size(); i++) {
				String[] addToBOW = itemTitleAmazonIT.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = 0; j<addToBOW.length; j++) {
					bagOfWordsAIT.add(addToBOW[j]);
				}
			}

			/* for (int i = 0; i < bagOfWordsAIT.size(); i++) {
			System.out.println(bagOfWordsAIT.get(i).toString());
		} */



			String[] words = new String[bagOfWordsAIT.size()];
			int[] counts = new int[bagOfWordsAIT.size()];

			for (int i = 0; i < bagOfWordsAIT.size(); i++) {
				words[i] = bagOfWordsAIT.get(i).toString().toUpperCase();
			}

			for (int i = 0; i < bagOfWordsAIT.size(); i++) {
				counts[i] = 0;
			}

			for (int i = 0; i < bagOfWordsAIT.size(); i++) {
				for (int j = 0; j < bagOfWordsAIT.size(); j++) {
					if (words[i].toUpperCase().equals(words[j].toUpperCase())) {
						counts[i] = counts[i] + 1;
					} 
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("Amazon Italy Search '" + keyword + "'.csv"));


			int lengthAmazonITForPrinting = 0;
			for (int i = 0; i <  itemTitleAmazonIT.size(); i++) {
				int lengthAmazonITForPrinting2 = lengthAmazonITForPrinting;
				writer.write( "\n " + itemTitleAmazonIT.get(i).toString().replace("\"", "").replace("\"", "").replace(",", "-"));
			}
			
			
			LinkedList wordsRemoveDup = new LinkedList();
			for (int i = 0; i < words.length; i++) {
				boolean match = false;
				match = wordsRemoveDup.contains(words[i].toUpperCase().replace(",", "-"));
				if (match == true) {
					 
				}
				else {
					wordsRemoveDup.add(words[i].toUpperCase().replace(",", "-"));
					wordsRemoveDup.add("," + counts[i]);
				}
				
			}
			for (int i = 0; i < wordsRemoveDup.size(); i=i+2) {
				writer.write("\n" + wordsRemoveDup.get(i).toString() + wordsRemoveDup.get(i+1).toString() );
			}
			
			writer.close();
		}

		if(amazonNLChecked == true) {
			LinkedList bagOfWordsANL = new LinkedList();
			for (int i = 0; i < itemTitleAmazonNL.size(); i++) {
				String[] addToBOW = itemTitleAmazonNL.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = 0; j<addToBOW.length; j++) {
					bagOfWordsANL.add(addToBOW[j]);
				}
			}

			/* for (int i = 0; i < bagOfWordsANL.size(); i++) {
			System.out.println(bagOfWordsANL.get(i).toString());
		} */



			String[] words = new String[bagOfWordsANL.size()];
			int[] counts = new int[bagOfWordsANL.size()];

			for (int i = 0; i < bagOfWordsANL.size(); i++) {
				words[i] = bagOfWordsANL.get(i).toString().toUpperCase();
			}

			for (int i = 0; i < bagOfWordsANL.size(); i++) {
				counts[i] = 0;
			}

			for (int i = 0; i < bagOfWordsANL.size(); i++) {
				for (int j = 0; j < bagOfWordsANL.size(); j++) {
					if (words[i].toUpperCase().equals(words[j].toUpperCase())) {
						counts[i] = counts[i] + 1;
					} 
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("Amazon Netherlands Search '" + keyword + "'.csv"));


			int lengthAmazonNLForPrinting = 0;
			for (int i = 0; i <  itemTitleAmazonNL.size(); i++) {
				int lengthAmazonNLForPrinting2 = lengthAmazonNLForPrinting;
				writer.write( "\n " + itemTitleAmazonNL.get(i).toString().replace("\"", "").replace("\"", "").replace(",", "-"));
			}
			
			
			LinkedList wordsRemoveDup = new LinkedList();
			for (int i = 0; i < words.length; i++) {
				boolean match = false;
				match = wordsRemoveDup.contains(words[i].toUpperCase().replace(",", "-"));
				if (match == true) {
					 
				}
				else {
					wordsRemoveDup.add(words[i].toUpperCase().replace(",", "-"));
					wordsRemoveDup.add("," + counts[i]);
				}
				
			}
			for (int i = 0; i < wordsRemoveDup.size(); i=i+2) {
				writer.write("\n" + wordsRemoveDup.get(i).toString() + wordsRemoveDup.get(i+1).toString() );
			}
			
			writer.close();
		}

		if(ebayDEChecked == true) {
			LinkedList bagOfWordsAEBAY = new LinkedList();
			for (int i = 0; i < itemTitleEbayDE.size(); i++) {
				String[] addToBOW = itemTitleEbayDE.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = 0; j<addToBOW.length; j++) {
					bagOfWordsAEBAY.add(addToBOW[j]);
				}
			}

			/* for (int i = 0; i < bagOfWordsAEBAY.size(); i++) {
			System.out.println(bagOfWordsAEBAY.get(i).toString());
		} */



			String[] words = new String[bagOfWordsAEBAY.size()];
			int[] counts = new int[bagOfWordsAEBAY.size()];

			for (int i = 0; i < bagOfWordsAEBAY.size(); i++) {
				words[i] = bagOfWordsAEBAY.get(i).toString().toUpperCase();
			}

			for (int i = 0; i < bagOfWordsAEBAY.size(); i++) {
				counts[i] = 0;
			}

			for (int i = 0; i < bagOfWordsAEBAY.size(); i++) {
				for (int j = 0; j < bagOfWordsAEBAY.size(); j++) {
					if (words[i].toUpperCase().equals(words[j].toUpperCase())) {
						counts[i] = counts[i] + 1;
					} 
				}
			}

			BufferedWriter writer = new BufferedWriter(new FileWriter("Ebay Germany Search '" + keyword + "'.csv"));

			int lengthEbayDEForPrinting = 0;
			for (int i = 0; i <  itemTitleEbayDE.size(); i++) {
				int lengthEbayDEForPrinting2 = lengthEbayDEForPrinting;
				writer.write( "\n " + itemTitleEbayDE.get(i).toString().replace("\"", "").replace("\"", "").replace(",", "-"));
			}
			
			
			LinkedList wordsRemoveDup = new LinkedList();
			for (int i = 0; i < words.length; i++) {
				boolean match = false;
				match = wordsRemoveDup.contains(words[i].toUpperCase().replace(",", "-"));
				if (match == true) {
					 
				}
				else {
					wordsRemoveDup.add(words[i].toUpperCase().replace(",", "-"));
					wordsRemoveDup.add("," + counts[i]);
				}
				
			}
			for (int i = 0; i < wordsRemoveDup.size(); i=i+2) {
				writer.write("\n" + wordsRemoveDup.get(i).toString() + wordsRemoveDup.get(i+1).toString() );
			}
			
			writer.close();
			/* int lengthAmazonEBAYForPrinting = 0;
			for (int i = 0; i <  itemTitleEbayDE.size(); i++) {
				int lengthAmazonEBAYForPrinting2 = lengthAmazonEBAYForPrinting;
				writer.write( "\n " + itemTitleEbayDE.get(i).toString().replace("\"", "").replace("\"", ""));
				String[] lengthsplit = itemTitleEbayDE.get(i).toString().replace("\"", "").replace("\"", "").split(" ");
				for (int j = lengthAmazonEBAYForPrinting; j < lengthAmazonEBAYForPrinting2 + lengthsplit.length; j++) {
					lengthAmazonEBAYForPrinting++;
					writer.write("," + words[j] + " [" + counts[j] + "]");
				}
			}
			writer.close(); */
		} 



	}

}


