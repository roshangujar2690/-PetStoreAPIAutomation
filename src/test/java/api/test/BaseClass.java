package api.test;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.github.javafaker.Faker;
import com.microsoft.playwright.APIRequestContext;

import api.endpoints.ApiFactory;
import api.endpoints.PetEndPoints;
import api.payload.Category;
import api.payload.Pet;
import api.payload.Tags;

public class BaseClass {
	
	ApiFactory apifactory;
	APIRequestContext request;
	protected PetEndPoints petEndPoints;
	
	Pet petPayload;
	Faker faker;
	public Logger logger;
	
	@BeforeTest
	public void setup()
	{
		apifactory = new ApiFactory();
		request = apifactory.init();
		petEndPoints = new PetEndPoints();
		
		petPayload= new Pet();
		faker = new Faker();
		
		petPayload.setId(faker.idNumber().hashCode());
		
		petPayload.setName(faker.animal().name());
		
		Category cat = new Category(faker.bool().hashCode(), faker.cat().breed());
		petPayload.setCategory(cat);
		
		petPayload.setPhotoUrls(new String [] {faker.internet().url()});
		
		Tags tags = new Tags(faker.bool().hashCode(), faker.cat().name());
		List<Tags> tag = new ArrayList<>();
		tag.add(tags);
		petPayload.setTags(tag);
		
		petPayload.setStatus("Available");
		
		logger=LogManager.getLogger(this.getClass());
		
	}
	@AfterTest
	public void tearDown()
	{
//		pg.pause();
		apifactory.close();
		logger.info("**********Playwright closed*********");
	}

}
