import controller.RentalController;
import model.RentalModel;
import view.RentalView;

public class Main {
    public static void main(String[] args) {
        RentalModel model = new RentalModel();
        RentalView view = new RentalView();
        new RentalController(model, view);
    }
}
