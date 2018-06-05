import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ShortestPathTest {
	private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; 
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; 
    private final String path3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph3.txt"; 
    private final String path4 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph4.txt"; 
    private final String path5 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph5.txt"; 

    @Before
    public void prepareGraphFile1() {
        List<String> lines = Arrays.asList("6", "1 2 1", "2 3 2", "3 5 3", "5 6 8", "5 4 -10", "4 2 2");
        Path file = Paths.get(path1);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile2() {
        List<String> lines = Arrays.asList("4", "1 2 2", "1 3 3", "3 2 -3", "2 4 5");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile3() {
        List<String> lines = Arrays.asList("4", "1 2 2", "1 3 100", "3 4 1", "2 4 100");
        Path file = Paths.get(path3);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile4() {
        List<String> lines = Arrays.asList("4", "1 2 3", "1 3 5", "3 4 1", "2 4 1");
        Path file = Paths.get(path4);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile5() {
        List<String> lines = Arrays.asList("4", "1 2 -3", "1 3 5", "3 4 1", "2 4 1");
        Path file = Paths.get(path5);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void createController() {
        controller = new Controller();
    }
    
    @Test(timeout=1000)
    public void testNegativeCycle1() {
        try {
            controller.readWeightedGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 2 4 5 6";
      
        try {
        	Assert.assertEquals(expectedOutput, controller.shortestPath("1", "6"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test(timeout=1000)
    public void testNegativeEdge1() {
        try {
            controller.readWeightedGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 3 2 4";
        
        try {
        	Assert.assertEquals(expectedOutput, controller.shortestPath("1", "4"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testPositiveEdge1() {
        try {
            controller.readWeightedGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 3 4";
        
        try {
        	Assert.assertEquals(expectedOutput, controller.shortestPath("1", "4"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testPositiveEdge2() {
        try {
            controller.readWeightedGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 2 4";
        
        try {
        	Assert.assertEquals(expectedOutput, controller.shortestPath("1", "4"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test(timeout=1000)
    public void testNegativeEdge2() {
        try {
            controller.readWeightedGraph(path5);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 2 4";
        
        try {
        	Assert.assertEquals(expectedOutput, controller.shortestPath("1", "4"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
}
