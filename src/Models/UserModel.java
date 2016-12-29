package Models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    public String getResult() {
        return result;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public User connect(String mail, String password) {
        
    	User user = new User();

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

        // TODO : On doit ici faire les requêtes dans la DB pour se connecter
        
        if (errors.isEmpty()) {
            result = "Succès de la connexion.";
        } else {
            result = "Échec de la connexion.";
        }

        return user;
    }
    
    public User regist(String mail, String password, String confirmPassword, String name, String firstName, String birthDate) {
    	
    	User user = new User();
    	
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
    	
    	// TODO: On doit ici faire les requêtes dans la DB pour s'inscrire
    	
    	if (errors.isEmpty()) {
            result = "Succès de l'inscription.";
        } else {
            result = "Échec de l'inscription.";
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
                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }
    }
    
    private void validPassword(String password, String confirmPassword) throws Exception {
        if (password != null && confirmPassword != null) {
            if (!password.equals(confirmPassword)) {
                throw new Exception( "Les mots de passe entrés sont différents, merci de les saisir à nouveau." );
            } else if (password.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }
    
    private void validBirthDate(String birthDate) throws Exception {
    	if (birthDate != null) {
    		if (!birthDate.matches("\\d{4}-\\d{2}-\\d{2}")) {
    			throw new Exception("Merci de saisir une date de naissance valide [dd/mm/yyyy.");
    		}
    	} else {
    		throw new Exception("Merci de saisir une date de naissance.");
    	}
    }
    
    private void setError(String field, String message) {
        errors.put(field, message);
    }
}
