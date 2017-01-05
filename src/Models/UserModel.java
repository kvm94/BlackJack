package Models;


import DAO.AbstractDAOFactory;
import DAO.UserDAO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import Beans.User;

public class UserModel {

	private static final String CHAMP_MAIL 				= "mail";
	private static final String CHAMP_PASSWORD 			= "password";
	private static final String CHAMP_CONFIRM_PASSWORD 	= "confirmPassword";
	private static final String CHAMP_BIRTH_DATE 		= "birthDate";

	private String 				result;
	private Map<String, String> errors = new HashMap<String, String>();

	private AbstractDAOFactory adf;
	private UserDAO userDAO;

	public User connect(String mail, String password){
		
		User user = null;
		
		try {
			user = new User();

			try {
				validMail(mail);
			} catch (Exception e) {
				setError(CHAMP_MAIL, e.getMessage());
			}
			user.setMail(mail);

			try {
				validPassword(password);
			} catch (Exception e) {
				setError(CHAMP_PASSWORD, e.getMessage());
			}
			user.setPassword(password);

			try {
				checkMailPassword(user);
			} catch (Exception e) {
				setError(mail, e.getMessage());
			}
			user = null;
			
			if (errors.isEmpty()) {
				result = "Succ�s de la connexion.";
			} else {
				result = "�chec de la connexion.";
			}

		} catch (Exception e1) {
			result = e1.getMessage();
			setError( null, result );
		}
		
		return user;
	}

	public User regist(String mail, String password, String confirmPassword, String name, String firstName, String birthDate) {
		
		User user = null;

		try {
			user = new User();

			try {
				validMail(mail);
			} catch (Exception e) {
				setError(CHAMP_MAIL, e.getMessage());
			}
			user.setMail(mail);

			try {
				validPassword(password, confirmPassword);
			} catch (Exception e) {
				setError(CHAMP_PASSWORD, e.getMessage());
				setError(CHAMP_CONFIRM_PASSWORD, null);
			}
			user.setPassword(password);

			user.setName(name);
			user.setFirstName(firstName);

			try {
				validBirthDate(birthDate);
			} catch (Exception e) {
				setError(CHAMP_BIRTH_DATE, e.getMessage());
			}
			try {
				user.setBirthDate(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("yyyy-MM-dd")));
			} catch (Exception e) {

			}

			try {
				checkRegistration(user);
			} catch (Exception e) {
				setError(CHAMP_MAIL, e.getMessage());
			}
			
			// Add user into DB
			if (errors.isEmpty()) {
				result = "Succ�s de l'inscription.";
			} else {
				result = "�chec de l'inscription.";
			}

		} catch (Exception e1) {
			result = e1.getMessage();
			setError( null, result );
		}
		
		return user;
	}

	private void validMail(String mail) throws Exception {
		if (mail != null) {
			if (!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	private void validPassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caract�res.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	private void validPassword(String password, String confirmPassword) throws Exception {
		if (password != null && confirmPassword != null) {
			if (!password.equals(confirmPassword)) {
				throw new Exception("Les mots de passe entr�s sont diff�rents, merci de les saisir � nouveau.");

			} else if (password.length() < 3) {
				throw new Exception("Les mots de passe doivent contenir au moins 3 caract�res.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	private void validBirthDate(String birthDate) throws Exception {
		if (birthDate != null) {
			if (!birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new Exception("Merci de saisir une date de naissance valide [yyyy-mm-dd].");
			}
		} else {
			throw new Exception("Merci de saisir une date de naissance.");
		}
	}
	
	private void checkMailPassword(User user) throws Exception {
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		userDAO = (UserDAO)adf.getUserDAO();
		ArrayList<User> temp = new ArrayList<User>();
		//Get info from DB
		temp = userDAO.find((Object) user);
		if(temp.size() > 0){
			user = temp.get(0);
		}
		else{
			throw new Exception("Email ou mot de passe incorrecte.");
		}
	}
	
	private void checkRegistration(User user) throws Exception {
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		userDAO = (UserDAO)adf.getUserDAO();
		if (!userDAO.create(user)) {
			throw new Exception("Email d�j� utilis�.");
		}
	}

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	private void setError(String field, String message) {
		errors.put(field, message);
	}
	
	public void updateUser(User user) throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		userDAO = (UserDAO)adf.getUserDAO();
		
		userDAO.update(user);
	}
	
}
