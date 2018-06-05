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

public class BfsTest {
	private static final String NOVA_LINHA = System.lineSeparator();
	private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; 
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; 
    private final String path3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph3.txt"; 
    private final String path4 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph4.txt"; 
    private final String path5 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph5.txt"; 
    private final String path6 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph6.txt"; 
    private final String path7 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph7.txt"; 
    private final String path8 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph8.txt"; 

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
        List<String> lines = Arrays.asList("4", "2 3", "3 4");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile3() {
        List<String> lines = Arrays.asList("4", "3 2 -4", "4 3 -4");
        Path file = Paths.get(path3);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile4() {
        List<String> lines = Arrays.asList("4", "1 2 -4", "2 3 -4", "3 4 0.01");
        Path file = Paths.get(path4);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile5() {
        List<String> lines = Arrays.asList("6", "1 2 -4", "2 3 -4", "3 4 0.01", "5 6 1");
        Path file = Paths.get(path5);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile6() {
        List<String> lines = Arrays.asList("5", "1 3 -4", "3 4 -4");
        Path file = Paths.get(path6);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile7() {
        List<String> lines = Arrays.asList("5", "1 3", "2 5", "3 4");
        Path file = Paths.get(path7);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile8() {
        List<String> lines = Arrays.asList("6", "4 5", "5 6", "3 5");
        Path file = Paths.get(path8);
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
    
	/*
	 * Grafo 1 - vertice inicial == "1"
	 */
    @Test
    public void testBFS1() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 0 -" + NOVA_LINHA + "2 - 1 1" +  NOVA_LINHA +"3 - 1 1" + NOVA_LINHA + "4 - 1 1" + NOVA_LINHA + "5 - 2 4" + NOVA_LINHA + "6 - 1 1" + NOVA_LINHA + "7 - 2 6" + NOVA_LINHA + "8 - 2 2" + NOVA_LINHA + "9 - 1 1";
        
       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 2 - vertice inicial == "1"
     */
    @Test
    public void testBFS2() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 0 -";
        
       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     *  Grafo 3 - vertice inicial == "2"
     */
    @Test
    public void testBFS3() {
        try {
            controller.readWeightedGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "2 - 0 -" + NOVA_LINHA + "3 - 1 2" + NOVA_LINHA + "4 - 2 3";

       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "2"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 3 - vertice inicial == "1"
     */
    @Test
    public void testBFS4() {
        try {
            controller.readWeightedGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 0 -";

       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 4 - vertice inicial == "1"
     */
    @Test
    public void testBFS5() {
        try {
            controller.readWeightedGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 0 -" + NOVA_LINHA + "2 - 1 1" + NOVA_LINHA + "3 - 2 2" + NOVA_LINHA + "4 - 3 3";
        
       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 5 - vertice inicial == "5"
     */
    @Test
    public void testBFS6() {
        try {
            controller.readWeightedGraph(path5);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "5 - 0 -" + NOVA_LINHA + "6 - 1 5";
        
       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "5"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 6 - vertice inicial == "1"
     */
    @Test
    public void testBFS7() {
        try {
            controller.readWeightedGraph(path6);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 0 -" + NOVA_LINHA + "3 - 1 1" + NOVA_LINHA + "4 - 2 3";
        
       
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 7 - vertice inicial == "1"
     */
    @Test
    public void testBFS8() {
        try {
            controller.readGraph(path7);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 0 -" + NOVA_LINHA + "3 - 1 1" + NOVA_LINHA + "4 - 2 3";
        
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    /*
     * Grafo 8 - vertice inicial == "5"
     */
    @Test
    public void testBFS9() {
        try {
            controller.readGraph(path8);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "3 - 1 5" + NOVA_LINHA + "4 - 1 5" + NOVA_LINHA + "5 - 0 -" + NOVA_LINHA + "6 - 1 5";
        
        try {
        	Assert.assertEquals(expectedOutput, controller.BFS(controller.getGraph(), "5"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
}
