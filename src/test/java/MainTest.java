import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class MainTest {

    @Test
    public void testSingleRoute() throws Exception {
        String[][] input = {{"MTY", "GDL"}};
        ArrayList<String[]> inputSourceArray = new ArrayList<>(Arrays.asList(input));
        ArrayList<String> expectedOutput = new ArrayList<>(Arrays.asList("MTY", "GDL"));

        ArrayList<String> actualOutput = Main.orderRoute(inputSourceArray);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testMultipleRoutes() throws Exception {
        String[][] input = {{"AICM", "GDL"}, {"MTY", "SJO"}, {"NLU", "MTY"}, {"SJO", "AICM"}};
        ArrayList<String[]> inputSourceArray = new ArrayList<>(Arrays.asList(input));
        ArrayList<String> expectedOutput = new ArrayList<>(Arrays.asList("NLU", "MTY", "SJO", "AICM", "GDL"));

        ArrayList<String> actualOutput = Main.orderRoute(inputSourceArray);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test
    public void testNoRoutes() throws Exception {
        String[][] input = {};
        ArrayList<String[]> inputSourceArray = new ArrayList<>(Arrays.asList(input));
        ArrayList<String> expectedOutput = new ArrayList<>();

        ArrayList<String> actualOutput = Main.orderRoute(inputSourceArray);
        assertEquals(expectedOutput, actualOutput);
    }

    @Test(expected = Exception.class)
    public void testWrongInputRoute() throws Exception {
        String[][] input = {{"AICM", "GDL"}, {"MTY", "SJO"}};
        ArrayList<String[]> inputSourceArray = new ArrayList<>(Arrays.asList(input));
        ArrayList<String> expectedOutput = new ArrayList<>(Arrays.asList("MTY", "SJO", "AICM", "GDL"));

        Main.orderRoute(inputSourceArray);
    }
}
