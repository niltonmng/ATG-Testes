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

public class ConnectedTest {
	private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; 
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; 
    private final String path3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph3.txt"; 
    private final String path4 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph4.txt"; 
    private final String path5 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph5.txt"; 
    private final String path6 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph6.txt";
	
	@Before
    public void prepareGraphFile1() {
        List<String> lines = Arrays.asList("12", "1 2", "1 3", "1 4", "2 3", "2 4", "3 4", "4 5", "1 6", "7 5", "3 6", "6 7", "2 8", "3 8", "8 9", "8 7", "9 1", "6 2");
        Path file = Paths.get(path1);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareGraphFile2() {
        List<String> lines = Arrays.asList("12", "1 2", "1 3", "1 4", "2 3", "2 4", "3 4", "4 5", "1 6", "7 5", "3 6", "6 7", "2 8", "3 8", "8 7", "6 2", "9 9");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile3() {
        List<String> lines = Arrays.asList("7","3 4", "4 5", "5 6", "6 7");
        Path file = Paths.get(path3);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile4() {
        List<String> lines = Arrays.asList("3", "2 3");
        Path file = Paths.get(path4);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile5() {
        List<String> lines = Arrays.asList("2", "1 2");
        Path file = Paths.get(path5);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
    @Before
    public void prepareGraphFile6() {
        List<String> lines = Arrays.asList("7", "");
        Path file = Paths.get(path6);
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
    public void testConnectedGraph1() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }

        try {
        	Assert.assertTrue(controller.connected(controller.getGraph()));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testConnectedGraph2() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	Assert.assertFalse(controller.connected(controller.getGraph()));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     *  7 vertices | Vertices 1 e 2 nao estao conectados com ninguem
     */
    @Test
    public void testConnectedGraph3() {
        try {
            controller.readGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	Assert.assertFalse(controller.connected(controller.getGraph()));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     *  3 vertices | 1 aresta (2->3)
     */
    @Test
    public void testConnectedGraph4() {
        try {
            controller.readGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	Assert.assertFalse(controller.connected(controller.getGraph()));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testConnectedGraph5() {
        try {
            controller.readGraph(path5);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	Assert.assertTrue(controller.connected(controller.getGraph()));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     *  7 vertices | 0 arestas
     */
    @Test
    public void testConnectedGraph6() {
        try {
            controller.readGraph(path6);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	Assert.assertFalse(controller.connected(controller.getGraph()));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
}
