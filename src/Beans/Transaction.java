
package Beans;

import java.time.LocalDate;

public class Transaction {

	private int id;
	private LocalDate date;
	private int amount;
	private User user;

	public int getAmount() {
		return amount;
	}

	public LocalDate getDate() {
		return date;
	}

	public int getId() {
		return id;
	}

	public User getUser() {
		return user;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
