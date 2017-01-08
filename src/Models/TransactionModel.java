package Models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Beans.Transaction;
import DAO.AbstractDAOFactory;
import DAO.TransactionDAO;

public class TransactionModel {

	private static final String CHAMP_BUY_AMOUNT = "buyAmount";
	private static final String CHAMP_SELL_AMOUNT = "sellAmount";

	// Attributs utiles pour l'affichage JSP
	private String result;
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
	private TransactionDAO transactionDAO;

	// Initialisation d'une transaction
	public Transaction init() {
		Transaction transaction = new Transaction();
		transaction.setDate(LocalDate.now());
		return transaction;
	}
	
	// Fonction d'achat
	public int buyToken(String amount) {

		int amountInt = 0;

		try {
			validAmount(amount);
		} catch (Exception e) {
			setError(CHAMP_BUY_AMOUNT, e.getMessage());
		}
		try {
			amountInt = Integer.parseInt(amount);
		} catch (Exception e) {

		}
		try {
			validAmount(amountInt);
		} catch (Exception e) {
			setError(CHAMP_BUY_AMOUNT, e.getMessage());
		}

		if (errors.isEmpty()) {
			result = "Succès de l'achat.";
		} else {
			result = "Échec de l'achat.";
		}
		return amountInt;
	}

	// Fonction de vente
	public int sellToken(String amount, double capital) {
		int amountInt = 0;

		try {
			validAmount(amount);
		} catch (Exception e) {
			setError(CHAMP_SELL_AMOUNT, e.getMessage());
		}
		try {
			amountInt = Integer.parseInt(amount);
		} catch (Exception e) {

		}
		try {
			validAmount(amountInt, capital);
		} catch (Exception e) {
			setError(CHAMP_SELL_AMOUNT, e.getMessage());
		}

		if (errors.isEmpty()) {
			result = "Succès de la vente.";
		} else {
			result = "Échec de la vente.";
		}

		return -amountInt;
	}

	// Fonction qui vérifie si le montant entré est valide (si c'est bien un int)
	private void validAmount(String amount) throws Exception {
		if (amount != null) {
			if (!amount.matches("\\d+")) {
				throw new Exception("Merci de saisir un montant valide.");
			}
		} else {
			throw new Exception("Merci de saisir un montant.");
		}
	}
	
	// Fonction qui vérifie si le montant entré est valide dans le cas d'un achat (pas de limite)
	private void validAmount(int amount) throws Exception {
		if (amount <= 0) {
			throw new Exception("Merci de saisir un montant valide.");
		}
	}

	// Fonction qui vérifie si le montant entré est valide dans le cas d'une vent (limite du capital de l'utilisateur)
	private void validAmount(int amount, double amountMax) throws Exception {
		if (amount > amountMax) {
			throw new Exception("Vous n'avez pas assez de capital pour vendre autaunt de jetons.");
		} else if (amount <= 0) {
			throw new Exception("Merci de saisir un montant valide.");
		}
	}

	// Enregistrement d'une transaction
	public void createTransaction(Transaction trans) throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		transactionDAO = (TransactionDAO)adf.getTransactionDAO();
		
		transactionDAO.create(trans);
	}

}
