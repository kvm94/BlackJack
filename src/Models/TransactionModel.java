package Models;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import Beans.Transaction;
import DAO.AbstractDAOFactory;
import DAO.TransactionDAO;
import DAO.UserDAO;

public class TransactionModel {

	private static final String CHAMP_BUY_AMOUNT = "buyAmount";
	private static final String CHAMP_SELL_AMOUNT = "sellAmount";

	private String result;
	private Map<String, String> errors = new HashMap<String, String>();
	
	private AbstractDAOFactory adf;
	private TransactionDAO transactionDAO;

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

	public Map<String, String> getErrors() {
		return errors;
	}

	public String getResult() {
		return result;
	}

	public Transaction init() {
		Transaction transaction = new Transaction();
		transaction.setDate(LocalDate.now());
		return transaction;
	}

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

	private void setError(String field, String message) {
		errors.put(field, message);
	}

	private void validAmount(int amount) throws Exception {
		if (amount <= 0) {
			throw new Exception("Merci de saisir un montant valide.");
		}
	}

	private void validAmount(int amount, double amountMax) throws Exception {
		if (amount > amountMax) {
			throw new Exception("Vous n'avez pas assez de capital pour vendre autaunt de jetons.");
		} else if (amount <= 0) {
			throw new Exception("Merci de saisir un montant valide.");
		}
	}

	private void validAmount(String amount) throws Exception {
		if (amount != null) {
			if (!amount.matches("\\d+")) {
				throw new Exception("Merci de saisir un montant valide.");
			}
		} else {
			throw new Exception("Merci de saisir un montant.");
		}
	}
	
	public void createTransaction(Transaction trans) throws Exception{
		adf = AbstractDAOFactory.getFactory(AbstractDAOFactory.DAO_FACTORY);
		transactionDAO = (TransactionDAO)adf.getTransactionDAO();
		
		transactionDAO.create(trans);
	}
	
}
