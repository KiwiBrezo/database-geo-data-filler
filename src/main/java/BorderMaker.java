import java.util.ArrayList;
import java.util.List;

public class BorderMaker {

    private static final Double STEP = 0.001;

    private static List<Coordinate> bordersArray;

    public static void createBorders(Double leftLat, Double leftLong, Double rightLat, Double rightLong) {
        bordersArray = new ArrayList<Coordinate>();

        for (double i = leftLat; i < rightLat; i += STEP) {
            bordersArray.add(new Coordinate(i, leftLong));
            bordersArray.add(new Coordinate(i, rightLong));
        }

        for (double i = leftLong; i < rightLong; i += STEP) {
            bordersArray.add(new Coordinate(leftLat, i));
            bordersArray.add(new Coordinate(rightLat, i));
        }
    }

    public static List<Coordinate> getBorderArray() {
        return bordersArray;
    }
}
