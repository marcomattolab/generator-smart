package it.eng.generate;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlListItem;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ScrapingAuthenticator {

	/**
	 * Test Web scraping Java 
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		testScrapingExprivia();
		//testScrapingGithub();
	}

	/** 
	 * See ==> https://www.scrapingbee.com/blog/introduction-to-web-scraping-with-java/
	 **/
	private static void testScrapingExprivia() {
		String baseUrl = "https://intranet.exprivia.it/"; 
		String homeUrl = "https://intranet.exprivia.it/web/exprivia/home"; 
		String cedoliniUrl = "https://intranet.exprivia.it/web/exprivia/cedolini"; 
		String loginUrl = "https://cas.exprivia.it/cas/login"; 
		String destinationPath = "/Users/marco/eclipse-workspace/exprivia-cedolini/";
		
		String username = "mmartorana";
		String password = "Settembre.2020";
		
		try {
			System.out.println("Input reads from " + cedoliniUrl);    
			System.out.println("Logged as username: " + username + "  password: " + password);    

            WebClient webClient = new WebClient();
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setJavaScriptEnabled(false);
            
            //Login
            HtmlPage page = (HtmlPage) webClient.getPage(loginUrl);
        		HtmlForm form = page.getForms().get(1);
        		form.getInputByName("username").setValueAttribute(username);
            form.getInputByName("password").setValueAttribute(password);
            HtmlPage homepage = form.getInputByValue("LOGIN").click();
            String loggedPage = homepage.getWebResponse().getContentAsString();
            
            //Homepage
            HtmlPage pageHome = (HtmlPage) webClient.getPage(homeUrl);
            String homePageLogged = pageHome.getWebResponse().getContentAsString();

            	//Cedolini
            HtmlPage pageCedolini = (HtmlPage) webClient.getPage(cedoliniUrl);
            String cedoliniPageLogged = pageCedolini.getWebResponse().getContentAsString();
            
            System.out.println("amount;month;allegato;allegato_content_type;year;name;link;id;created_by;created_date");
            int count = 1;
            for(Object obj: (List<Object>) pageCedolini.getByXPath("//li[@class='pft-file ext-pdf']")) {
            		HtmlListItem listItem = ((HtmlListItem) obj);	
            		HtmlAnchor anchor = (HtmlAnchor) listItem.getFirstChild();
            		String hrefLink = anchor.getHrefAttribute();
            		String anchorFullLink = baseUrl + hrefLink;
            		String nameFile = anchor.getFirstChild().asXml().replace("\n", "").replace("\r", "");
            		String year = nameFile.substring(4, 8);
            		String month = nameFile.substring(9, 11);
            		boolean isTredicesima = nameFile.toUpperCase().contains("TREDICESIMA");
            		//System.out.println(nameFile + " " + anchorFullLink);
            		//System.out.println(year + " " + month + (isTredicesima?" " + "TREDICESIMA" : "") );

            		//amount;month;allegato;allegato_content_type;year;name;link;id;created_by;created_date
            		System.out.println(
            				year+"-"+month+";"+ //amount
    						(isTredicesima?"13":month)+";"+ //month
    						"../data/blob/mmartorana/"+nameFile+";"+ // allegato
    						"application/pdf;"+ //allegato_content_type
    						year+";"+
    						nameFile+";"+
    						anchorFullLink+";"+
    						(count++)+";"+
    						"admin"+";"+
    						year+"-"+month+"-"+"01"+";"
    				);
            		
            		//Download as Anchor
            	    anchor.click();                                                                                    
            	    webClient.waitForBackgroundJavaScript(2000);                                                               
            	    Page downloadPage = webClient.getCurrentWindow().getEnclosedPage();                                        
            	    File destFile = new File(destinationPath, nameFile);                                                            
            	    try (InputStream contentAsStream = downloadPage.getWebResponse().getContentAsStream()) {                   
            	        try (OutputStream out = new FileOutputStream(destFile)) {                                              
            	            IOUtils.copy(contentAsStream, out);                                                                
            	        }                                                                                                      
            	    }      
            	    
            	    //System.out.println("Output written to " + destFile.getAbsolutePath());   
            	    
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
	
	
	/**
	 * See ==> https://www.baeldung.com/htmlunit 
	 */
	private static void testScrapingGithub() {
		String loginUrl = "https://github.com/login" ; 
		String login = "marcomatto@libero.it";
		String password = "xxxxx" ;
		try {
            WebClient webClient = new WebClient();
            webClient.getOptions().setThrowExceptionOnScriptError(false);
            webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
            webClient.getOptions().setJavaScriptEnabled(false);

            //Setup your site url
            HtmlPage page = (HtmlPage) webClient.getPage(loginUrl);
            HtmlForm form = page.getForms().get(0);
            form.getInputByName("login").setValueAttribute(login);
            form.getInputByName("password").setValueAttribute(password);

            HtmlPage homepage = form.getInputByValue("Sign in").click();
            String loggedPage = homepage.getWebResponse().getContentAsString();

            System.out.println("## loggedPage: " + loggedPage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}
	
// //Processo Asyncrono di caricamento su DB	
//	 public List<PayslipDTO> testScrapingExprivia(){
// 		List<PayslipDTO> result = new ArrayList<PayslipDTO>();
//		String cedoliniUrl = "https://intranet.exprivia.it/web/exprivia/cedolini"; 
//		String loginUrl = "https://cas.exprivia.it/cas/login"; 
//		String destinationPath = "/Users/marco/eclipse-workspace/exprivia-cedolini/";
//		
//		String username = "mmartorana";
//		String password = "xxx";
//		
//		try {
//			System.out.println("Input reads from " + cedoliniUrl);    
//			System.out.println("Logged as username: " + username + "  password: " + password);    
//
//         WebClient webClient = new WebClient();
//         webClient.getOptions().setThrowExceptionOnScriptError(false);
//         webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
//         webClient.getOptions().setJavaScriptEnabled(false);
//         
//         //Login
//         HtmlPage page = (HtmlPage) webClient.getPage(loginUrl);
//     		HtmlForm form = page.getForms().get(1);
//     		form.getInputByName("username").setValueAttribute(username);
//         form.getInputByName("password").setValueAttribute(password);
//         HtmlPage homepage = form.getInputByValue("LOGIN").click();
//         String loggedPage = homepage.getWebResponse().getContentAsString();
//         
//         	//Cedolini
//         HtmlPage pageCedolini = (HtmlPage) webClient.getPage(cedoliniUrl);
//         String cedoliniPageLogged = pageCedolini.getWebResponse().getContentAsString();
//         
//         for(Object obj: (List<Object>) pageCedolini.getByXPath("//li[@class='pft-file ext-pdf']")) {
//         		HtmlListItem listItem = ((HtmlListItem) obj);	
//         		HtmlAnchor anchor = (HtmlAnchor) listItem.getFirstChild();
//         		String hrefLink = anchor.getHrefAttribute();
//         		String nameFile = anchor.getFirstChild().asXml().replace("\n", "").replace("\r", "");
//         		String year = nameFile.substring(4, 8);
//         		String month = nameFile.substring(9, 11);
//         		boolean isTredicesima = nameFile.toUpperCase().contains("TREDICESIMA");
//         		System.out.println(nameFile + " " + year + " " + month + (isTredicesima?" " + "TREDICESIMA" : "") );
//         		
//         		//Download as Anchor
//         	    anchor.click();                                                                                    
//         	    webClient.waitForBackgroundJavaScript(2000);                                                               
//         	    com.gargoylesoftware.htmlunit.Page downloadPage = webClient.getCurrentWindow().getEnclosedPage();                                        
//         	    File destFile = new File(destinationPath, nameFile);                                                            
//         	    try (InputStream contentAsStream = downloadPage.getWebResponse().getContentAsStream()) {                   
//         	        try (OutputStream out = new FileOutputStream(destFile)) {                                              
//         	            IOUtils.copy(contentAsStream, out);                                                                
//         	        }                                                                                                      
//         	    }    
//         	    
//         	    PayslipDTO cur = new PayslipDTO();
//         	    cur.setMonth(month);
//         	    cur.setName(nameFile);
//         	    cur.setYear(new Integer(year));
//         	    cur.setLink(hrefLink);
//         	    cur.setCompanyName("Exprivia");
//				result.add(cur);
//         	    System.out.println("Output written to " + destFile.getAbsolutePath());   
//         	    
//         }
//     } catch (Exception ex) {
//         ex.printStackTrace();
//     }
//		return result;
//	}
}

