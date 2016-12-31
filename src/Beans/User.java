package Beans;

import java.time.LocalDate;
import java.util.List;

public class User {

	private int id;
	private String mail;
	private String password;
	private String name;
	private String firstName;
	private LocalDate birthDate;
	private double capital;
	private List<Transaction> transactions;
	private List<Game> games;

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public double getCapital() {
		return capital;
	}

	public String getFirstName() {
		return firstName;
	}

	public List<Game> getGames() {
		return games;
	}

	public int getId() {
		return id;
	}

	public String getMail() {
		return mail;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public void setCapital(double capital) {
		this.capital = capital;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
