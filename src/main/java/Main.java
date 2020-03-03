import java.util.List;

public class Main {
    public static void main(String[] args) {
        final Double LEFT_LOVER_CORNER_LAT = 45.42142412;
        final Double LEFT_LOVER_CORNER_LONG = 13.37546956;
        final Double RIGHT_UPPER_CORNER_LAT = 46.87668152;
        final Double RIGHT_UPPER_CORNER_LONG = 16.59677015;

        BorderMaker.createBorders(LEFT_LOVER_CORNER_LAT, LEFT_LOVER_CORNER_LONG, RIGHT_UPPER_CORNER_LAT, RIGHT_UPPER_CORNER_LONG);

        List< List<Double> > arrayOfBorders = BorderMaker.getBorderArray();

        System.out.println("It works...");
    }
}
