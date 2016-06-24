package gsg.gae.core.items.domain;

import com.google.appengine.api.blobstore.BlobKey;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.*;


@Entity
public class Item {

	@Id public Long id;
	@Index
	public String name;
	@Index public Long groupId;
	public String shortDescription;
	public String description;
	public Double price;
	public boolean archive;
	@Index public Date date;

	//public Map<?, ?> fileIds = new HashMap<>();

	//@Stringify(com.googlecode.objectify.stringifier.KeyStringifier.class)

	public Map<String, String> fileIds = new HashMap<>();

	public List<Map<String, String>> comments  = new ArrayList<>();

	public Item() {}

	public Item(Long id) {this.id = id;}

	public Item(Long id, String name, Long groupId, String shortDescription, String description, Double price, boolean archive, Date date, Map<String, String> fileIds, List<Map<String, String>> comments) {
		this.id = id;
		this.name = name;
		this.groupId = groupId;
		this.shortDescription = shortDescription;
		this.description = description;
		this.price = price;
		this.archive = archive;
		this.date = date;
		this.fileIds = fileIds;
		this.comments = comments;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Map<String, String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(Map<String, String> fileIds) {
		this.fileIds = fileIds;
	}

	public List<Map<String, String>> getComments() {
		return comments;
	}

	public void setComments(List<Map<String, String>> comments) {
		this.comments = comments;
	}

	/*@Override
	public String toString() {
		return "Item{" +
				"id=" + id +
				", name='" + name + '\'' +
				", groupId=" + groupId +
				", shortDescription='" + shortDescription + '\'' +
				", description='" + description + '\'' +
				", price=" + price +
				", archive=" + archive +
				", date=" + date +
				", fileIds=" + fileIds +
				'}';
	}*/
}
