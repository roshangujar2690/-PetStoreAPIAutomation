package api.endpoints;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;

public class ApiFactory {

	

	Playwright playwright;
	APIRequest request;
	APIRequestContext requestContext;
	
	public APIRequestContext init()
	{
		playwright=Playwright.create();
		request= playwright.request();
		requestContext= request.newContext();
		return requestContext;
	}
	
	public void close()
	{
		playwright.close();
	}
	
}
