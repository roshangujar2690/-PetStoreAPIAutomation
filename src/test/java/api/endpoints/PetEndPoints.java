package api.endpoints;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;

import api.payload.Pet;

public class PetEndPoints {
	
		static Playwright playwright=Playwright.create();
		static APIRequest request= playwright.request();
		static APIRequestContext requestContext= request.newContext();
	
	public static APIResponse createPet(Pet payload)
	{
		APIResponse response=requestContext.post(Routes.post_url, 
				RequestOptions.create()
						.setHeader("Content-Type", "application/json")
						.setHeader("accept", "application/json")
						.setData(payload));
		return(response);
	}
	
	public static APIResponse readPet(int id)
	{
		APIResponse response=requestContext.get(Routes.get_url + id);
		return(response);
	}
	
	public static APIResponse updatePet(Pet payload)
	{
		APIResponse response=requestContext.put(Routes.put_url, 
				RequestOptions.create()
						.setHeader("Content-Type", "application/json")
						.setHeader("accept", "application/json")
						.setData(payload));
		return(response);
	}
	
	public static APIResponse deletePet(int i)
	{
		APIResponse response=requestContext.delete(Routes.delete_url + i);
		return(response);
	}
	
}
