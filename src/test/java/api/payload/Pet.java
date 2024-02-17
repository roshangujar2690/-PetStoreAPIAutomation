package api.payload;

import java.util.List;

public class Pet {
	
	private int id;
	private String name;
	private Category category;
	private String[] photoUrls;
	private List<Tags> tags;
	private String status;
	static int petId;
	
	public Pet(){
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String[] getPhotoUrls() {
		return photoUrls;
	}

	public void setPhotoUrls(String[] photoUrls) {
		this.photoUrls = photoUrls;
	}

	public List<Tags> getTags() {
		return tags;
	}

	public void setTags(List<Tags> tags) {
		this.tags = tags;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public int generateRandomId(int petId)
	{
		int max=10000;
		int min=1000;
		petId= (int)(Math.random()*(max-min+1)+ min);
		return petId;
	}

}
