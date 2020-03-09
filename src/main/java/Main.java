import java.sql.*;
import java.util.List;

public class Main {
    private static final String URL = "jdbc:postgresql://localhost/companies";
    private static final String USER = "breznar-test";
    private static final String PASSWORD = "BrkataSinica";

    private static void clearDB() throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement statement = conn.createStatement();
            statement.execute("TRUNCATE TABLE companies");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }
    }

    private static void insertCoordinateIntoDB(List<Coordinate> cords, String name) throws SQLException {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            Integer counter = 1;
            for (Coordinate cord : cords) {
                Integer type = (Math.random() <= 0.5) ? 1 : 2;
                String point = "POINT("+ cord.getLongitude() + " " + cord.getLatitude() +")";
                String sqlQuery = "INSERT INTO companies (company_name, company_address, type_id, finance_office_id, tax_number, activity_code, geom) VALUES (?, ?, ?, 1, '0000000', '00.000', st_geomfromtext(?, 3857))";

                PreparedStatement preparedStatement = conn.prepareStatement(sqlQuery);
                preparedStatement.setString(1,name + " " + counter);
                preparedStatement.setString(2,name + " " + counter);
                preparedStatement.setInt(3, type);
                preparedStatement.setString(4, point);

                preparedStatement.execute();

                counter++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            conn.close();
        }
    }

    public static void main(String[] args) {
        final Double LEFT_LOVER_CORNER_LAT = 45.42142412;
        final Double LEFT_LOVER_CORNER_LONG = 13.37546956;
        final Double RIGHT_UPPER_CORNER_LAT = 46.87668152;
        final Double RIGHT_UPPER_CORNER_LONG = 16.59677015;

        BorderMaker.createBorders(LEFT_LOVER_CORNER_LAT, LEFT_LOVER_CORNER_LONG, RIGHT_UPPER_CORNER_LAT, RIGHT_UPPER_CORNER_LONG);
        CompaniesCoordinateMaker.createCoordinates(LEFT_LOVER_CORNER_LAT, LEFT_LOVER_CORNER_LONG, RIGHT_UPPER_CORNER_LAT, RIGHT_UPPER_CORNER_LONG);

        List<Coordinate> arrayOfBorders = BorderMaker.getBorderArray();
        List<Coordinate> arrayOfCoordinates = CompaniesCoordinateMaker.getCoordinatesArray();

        System.out.println("Started importing coordinates!");
        try {
            clearDB();
            //insertCoordinateIntoDB(arrayOfBorders, "Border");
            insertCoordinateIntoDB(arrayOfCoordinates, "Company");
            System.out.println("Finished importing coordinates!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
