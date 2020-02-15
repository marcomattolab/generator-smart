package it.eng.generate;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlForm;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class ScrapingAuthenticator {
	
	public static void main(String[] args) {
		/** 
		 * Web scraping Java 
		 * See ==> https://www.baeldung.com/htmlunit 
		 * 
		 **/
		
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

            System.out.println("loggedPage: "+loggedPage);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
	}


}

