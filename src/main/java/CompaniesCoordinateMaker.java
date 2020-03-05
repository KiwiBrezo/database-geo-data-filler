import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CompaniesCoordinateMaker {
    private static List<Coordinate> arrayOfCoordinates;

    public static void createCoordinates(Double leftLat, Double leftLong, Double rightLat, Double rightLong) {
        arrayOfCoordinates = new ArrayList<Coordinate>();

        for (int i = 0; i < 2500; i++) {
            Double randLat = leftLat + new Random().nextDouble() * (rightLat - leftLat);
            Double randLong = leftLong + new Random().nextDouble() * (rightLong - leftLong);

            arrayOfCoordinates.add(new Coordinate(randLat, randLong));
        }
    }

    public static List<Coordinate> getCoordinatesArray() {
        return arrayOfCoordinates;
    }
}
