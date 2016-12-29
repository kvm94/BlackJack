package Models;

import Beans.Game;
import Beans.User;

public class Test {

	public static void main(String[] args) {
		UserModel userModel = new UserModel();
		User user = userModel.connect("test", "test");
		
		user.setCapital(1000);
		
		GameModel gameModel = new GameModel();
		Game game = gameModel.init(user.getCapital());
	}

}
