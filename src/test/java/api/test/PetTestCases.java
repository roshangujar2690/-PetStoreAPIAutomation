package api.test;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.playwright.APIResponse;

import api.endpoints.PetEndPoints;

public class PetTestCases extends BaseClass{
	
	@Test(priority=1)
	public void testPostPet() throws IOException
	{
		logger.info("************************Creating a pet*********************************");
		APIResponse response=PetEndPoints.createPet(petPayload);
		System.out.println("POST response status is : "+response.status());
		System.out.println("POST response body is : "+response.text());
		
		Assert.assertEquals(response.status(), 200);
		Assert.assertEquals(response.statusText(), "OK");
		Assert.assertTrue(response.text().contains(this.petPayload.getName()));
		
		ObjectMapper mapObject = new ObjectMapper();
		JsonNode jsonresponse=mapObject.readTree(response.body());
		
//		String prettyResponse=jsonresponse.toPrettyString();
//		System.out.println("POST Pretty response body is : "+prettyResponse);
		
		String petId=jsonresponse.get("id").asText();
		System.out.println("Id of a pet animal is :" +petId);	
		logger.info("************************A pet is created*********************************");
	}
	
	@Test(priority=2)
	public void testGetPetById()
	{
		logger.info("************************Retrieving pet info*********************************");
		APIResponse response=PetEndPoints.readPet(this.petPayload.getId());
		System.out.println("GET response status is : "+response.status());
		System.out.println("GET response body is : "+response.text());
		
		Assert.assertEquals(response.status(), 200);
		Assert.assertEquals(response.statusText(), "OK");
		logger.info("************************Pet info is retrieved*********************************");
	}
	
	@Test(priority=3)
	public void testUpdatePetByName()
	{
		logger.info("************************Updating a pet*********************************");
		petPayload.setName("Kitty");
		APIResponse response=PetEndPoints.updatePet(petPayload);
		System.out.println("PUT response status is : "+response.status());
		System.out.println("PUT response body is : "+response.text());
		
		Assert.assertEquals(response.status(), 200);
		Assert.assertEquals(response.statusText(), "OK");
		Assert.assertTrue(response.text().contains("Kitty"));
		logger.info("************************Pet is updated*********************************");
	}
	@Test(priority=4)
	public void testDeletePetById()
	{
		logger.info("************************Deleting pet*********************************");
		APIResponse response=PetEndPoints.deletePet(this.petPayload.getId());
		System.out.println("Delete response status is : "+response.status());
		System.out.println("Delete response body is : "+response.text());
		
		Assert.assertTrue(response.text().contains("Pet deleted"));
		Assert.assertEquals(response.status(), 200);
		Assert.assertEquals(response.statusText(), "OK");
		logger.info("************************Pet is deleted*********************************");
		
		logger.info("************************Retrieving deleted pet*********************************");
		APIResponse getResponse=PetEndPoints.readPet(this.petPayload.getId());
		System.out.println("GET response body is : "+getResponse.text());
		Assert.assertTrue(getResponse.text().contains("Pet not found"));
		logger.info("************************Pet info not found, pet deleted successfully*********************************");
		
	}

}
