import Interfaces.*;
import Models.PlaneDb;
import Models.User;
import Services.*;
import Windows.WindowsManager;
import org.json.JSONException;

public class DriverClass {

    private static PlaneDb planeDb;
    private static IFileService fileService;
    private static ITicketService ticketService;
    private static IRouteService routeService;
    private static IAuthenticationService authService;
    private static ILocationService locationService;
    private static User currentUser = new User();

    public static void main(String[] args) throws JSONException {
        fileService = new FileService();
        planeDb = PlaneDb.getPlainDb(fileService);
        ticketService = new TicketService(planeDb);
        locationService = new LocationService(planeDb);
        routeService = new RouteService(planeDb, locationService);
        authService = new AuthenticationService(planeDb);
        WindowsManager windowsManager = WindowsManager.getInstance(currentUser, ticketService, routeService, locationService, authService);
        windowsManager.openIntroductionWindow();
    }
}
