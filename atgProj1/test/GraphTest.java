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

public class GraphTest {
	private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; 
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; 
    private final String path3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph3.txt"; 

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
        List<String> lines = Arrays.asList("5", "1 2");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile3() {
        List<String> lines = Arrays.asList("5", "1 2", "3 2", "4 5");
        Path file = Paths.get(path3);
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
    
    @Test
    public void testGraph1() {
        try {
            controller.readWeightedGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        Integer expectedEdgeN = 6;
        Double expectedMeanEdge = 2.0;
        Integer expectedVertexN = 6;
        Assert.assertTrue(expectedEdgeN == controller.getEdgeNumber(controller.getGraph()));
    	Assert.assertTrue(expectedMeanEdge == controller.getMeanEdge(controller.getGraph()));
    	Assert.assertTrue(expectedVertexN == controller.getVertexNumber(controller.getGraph()));

    }
    
    @Test
    public void testGraph2() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        Integer expectedEdgeN = 1;
        Double expectedMeanEdge =  2.0/5.0;
        Integer expectedVertexN = 5;

        Assert.assertTrue(expectedEdgeN == controller.getEdgeNumber(controller.getGraph()));
        Assert.assertTrue(expectedVertexN == controller.getVertexNumber(controller.getGraph()));
    	Assert.assertTrue(expectedMeanEdge == controller.getMeanEdge(controller.getGraph()));

        
    }
    
    @Test
    public void testGraph3() {
        try {
            controller.readGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        Integer expectedEdgeN = 3;
        Double expectedMeanEdge =  6.0/5.0;
        Integer expectedVertexN = 5;

        
        Assert.assertTrue(expectedVertexN == controller.getVertexNumber(controller.getGraph()));
    	Assert.assertTrue(expectedEdgeN == controller.getEdgeNumber(controller.getGraph()));
    	Assert.assertTrue(expectedMeanEdge == controller.getMeanEdge(controller.getGraph()));

    }
}
