/**
 * Created by KinshukBasu on 04-Jul-17.
 */
import services.UserService;
import controller.UserController;

public class Main {


        public static void main(String[] args) throws Exception {
            new UserController(new UserService());
        }
}

