package utils;

import controllers.AppController;
import services.AppService;
import views.AppView;

public class AppStarter {
    public static void startApp() {
        AppService service = new AppService();
        AppView view = new AppView();
        AppController controller = new AppController(view, service);
        controller.runApp();
    }
}
