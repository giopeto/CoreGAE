package gsg.gae.core.ps.domain;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Load;
import gsg.gae.core.items.domain.Item;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class PS {

	@Id
	public Long id;
	@Index public Long userId;
	public int quantity;
	@Index public Date date;
	@Load public List<Item> items = new ArrayList<>();
	public String status;

	public PS() {}

	public PS(Long id, Long userId, int quantity, Date date, List<Item> items, String status) {
		this.id = id;
		this.userId = userId;
		this.quantity = quantity;
		this.date = new Date();
		this.items = items;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
