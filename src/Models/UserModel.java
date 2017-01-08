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

	// Attributs utiles pour l'affichage JSP
	private String 				result;
	private Map<String, String> errors = new HashMap<String, String>();

	// Getteur/setteur des erreurs
	public Map<String, String> getErrors() {
		return errors;
	}
	private void setError(String field, String message) {
		errors.put(field, message);
	}

	// Getteur du result 
	public String getResult() {
		return result;
	}

	// Utile pour la DB
	private AbstractDAOFactory adf;
	private UserDAO userDAO;

	// Fonction de connection
	public User connect(String mail, String password){
		
		User user = null;
		
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
				user = checkMailPassword(user);
			} catch (Exception e) {
				setError(mail, e.getMessage());
			}
			
			if (errors.isEmpty()) {
				result = "Succès de la connexion.";
			} else {
				result = "Échec de la connexion.";
			}
		
		return user;
	}

	// Fonction d'enregistrement
	public User regist(String mail, String password, String confirmPassword, String name, String firstName, String birthDate) {
		
		User user = null;

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
				setError(CHAMP_BIRTH_DATE, e.getMessage());
			}

			try {
				checkRegistration(user);
			} catch (Exception e) {
				setError(CHAMP_MAIL, e.getMessage());
			}
			
			// Add user into DB
			if (errors.isEmpty()) {
				result = "Succès de l'inscription.";
			} else {
				result = "Échec de l'inscription.";
			}
		
		return user;
	}

	// Fonction qui vérifie si l'email entré en ai bien un
	private void validMail(String mail) throws Exception {
		if (mail != null) {
			if (!mail.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
				throw new Exception("Merci de saisir une adresse mail valide.");
			}
		} else {
			throw new Exception("Merci de saisir une adresse mail.");
		}
	}

	// Fonction qui vérifie si le mot de passe entré est valide
	private void validPassword(String password) throws Exception {
		if (password != null) {
			if (password.length() < 3) {
				throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir votre mot de passe.");
		}
	}

	// Fonction qui vérifie si le mot de passe de confirmation entré est valide
	private void validPassword(String password, String confirmPassword) throws Exception {
		if (password != null && confirmPassword != null) {
			if (!password.equals(confirmPassword)) {
				throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");

			} else if (password.length() < 3) {
				throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
			}
		} else {
			throw new Exception("Merci de saisir et confirmer votre mot de passe.");
		}
	}

	// Fonction qui vérifie si la date entrée est valide
	private void validBirthDate(String birthDate) throws Exception {
		if (birthDate != null) {
			if (!birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
				throw new Exception("Merci de saisir une date de naissance valide [yyyy-mm-dd].");
			}
		} else {
			throw new Exception("Merci de saisir une date de naissance.");
		}
	}
	
	// Fonction qui vérifie dans la db si on peut ce connecter
	private User checkMailPassword(User user) throws Exception {
		try {
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
		} catch (Exception e) {
			throw e;
		}
		return user;
	}
	
	// Fonction qui vérifie dans la db si l'utilisateur existe déjà si non il l'enregistre dans la DB
	private void checkRegistration(User user) throws Exception {
		try {
			adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
			userDAO = (UserDAO)adf.getUserDAO();
			boolean regist = false;
			regist = userDAO.create(user);
			if (!regist) {
				throw new Exception("Email déjà utilisé.");
			}
		} catch (Exception e) {
			throw e;
		}
	}

	// Update de l'utilisateur en DB (capital)
	public void updateUser(User user) throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		userDAO = (UserDAO)adf.getUserDAO();
		
		userDAO.update(user);
	}
	
}
