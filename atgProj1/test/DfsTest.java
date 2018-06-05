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

public class DfsTest {
	private static final String NOVA_LINHA = System.lineSeparator();
	private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; 
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt"; 
    private final String path3 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph3.txt"; 
    private final String path4 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph4.txt"; 
    private final String path5 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph5.txt"; 

    @Before
    public void prepareGraphFile1() {
        List<String> lines = Arrays.asList("9", "1 2", "1 3", "1 4", "2 3", "2 4", "3 4", "4 5", "1 6", "7 5", "3 6", "6 7", "2 8", "3 8", "8 9", "8 7", "9 1", "6 2");
        Path file = Paths.get(path1);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }

    @Before
    public void prepareGraphFile2() {
        List<String> lines = Arrays.asList("9", "1 2", "1 3", "1 4", "2 3", "2 4", "3 4", "4 5", "1 6", "7 5", "3 6", "6 7", "2 8", "3 8", "8 7", "6 2", "9 9");
        Path file = Paths.get(path2);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
    
	@Before
    public void prepareGraphFile3() {
        List<String> lines = Arrays.asList("3", "3 2 -4", "4 3 -4", "4 2 -1");
        Path file = Paths.get(path3);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile4() {
        List<String> lines = Arrays.asList("5", "1 2 -4", "1 5 -3", "2 3 -4", "2 4 -0.5", "2 5 -0.1", "3 4 -1", "3 5 0.01");
        Path file = Paths.get(path4);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile5() {
        List<String> lines = Arrays.asList("13", "1 2", "1 3", "1 4", "2 3", "2 4", "3 4", "4 5", "1 6", "7 5", "3 6", "6 7", "2 8", "3 8", "8 9", "8 7", "9 1", "6 2", "10 3", "10 8", "10 5", "11 8", "11 2", "12 5", "12 6", "13 9");
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
    
    @Test
    public void testDFSGraph1n1() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"1 - 0 -" + NOVA_LINHA + 
        	"2 - 1 1" + NOVA_LINHA + 
        	"3 - 2 2" + NOVA_LINHA +
        	"4 - 3 3" + NOVA_LINHA + 
        	"5 - 4 4" + NOVA_LINHA + 
        	"6 - 6 7" + NOVA_LINHA +
        	"7 - 5 5" + NOVA_LINHA +
        	"8 - 6 7" + NOVA_LINHA +
        	"9 - 7 8";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "1"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph1n2() {
        try {
            controller.readGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"1 - 1 2" + NOVA_LINHA + 
			"2 - 0 -" + NOVA_LINHA + 
			"3 - 2 1" + NOVA_LINHA +
			"4 - 3 3" + NOVA_LINHA + 
			"5 - 4 4" + NOVA_LINHA + 
			"6 - 6 7" + NOVA_LINHA +
			"7 - 5 5" + NOVA_LINHA +
			"8 - 6 7" + NOVA_LINHA +
			"9 - 7 8";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "2"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph2n1() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"1 - 1 3" + NOVA_LINHA + 
        	"2 - 2 1" + NOVA_LINHA + 
        	"3 - 0 -" + NOVA_LINHA +
        	"4 - 3 2" + NOVA_LINHA + 
        	"5 - 4 4" + NOVA_LINHA + 
        	"6 - 6 7" + NOVA_LINHA +
        	"7 - 5 5" + NOVA_LINHA +
        	"8 - 6 7";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "3"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph2n2() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"9 - 0 -";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "9"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph3n1() {
        try {
            controller.readGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"2 - 1 3" + NOVA_LINHA + 
        	"3 - 0 -" + NOVA_LINHA + 
        	"4 - 2 2";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "3"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph3n2() {
        try {
            controller.readGraph(path3);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
            "2 - 1 4" + NOVA_LINHA + 
            "3 - 2 2" + NOVA_LINHA + 
            "4 - 0 -";

        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "4"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph4n1() {
        try {
            controller.readGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"1 - 2 2" + NOVA_LINHA + 
        	"2 - 1 4" + NOVA_LINHA + 
        	"3 - 4 5" + NOVA_LINHA +
        	"4 - 0 -" + NOVA_LINHA + 
        	"5 - 3 1";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "4"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
    
    @Test
    public void testDFSGraph4n2() {
        try {
            controller.readGraph(path4);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
        	"1 - 1 2" + NOVA_LINHA + 
        	"2 - 0 -" + NOVA_LINHA + 
        	"3 - 3 5" + NOVA_LINHA +
        	"4 - 4 3" + NOVA_LINHA + 
        	"5 - 2 1";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "2"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }    
    
    @Test
    public void testDFSGraph5n1() {
        try {
            controller.readGraph(path5);
        } catch (Exception e) {
            Assert.fail();
        }
        
        try {
        	String DFSesperada = "" +
            "1 - 2 9" + NOVA_LINHA + 
        	"2 - 3 1" + NOVA_LINHA + 
        	"3 - 4 2" + NOVA_LINHA +
        	"4 - 5 3" + NOVA_LINHA + 
        	"5 - 6 4" + NOVA_LINHA + 
        	"6 - 8 7" + NOVA_LINHA +
        	"7 - 7 5" + NOVA_LINHA +
        	"8 - 8 7" + NOVA_LINHA +
        	"9 - 1 13" + NOVA_LINHA +
        	"10 - 10 8" + NOVA_LINHA +
        	"11 - 10 8" + NOVA_LINHA +
        	"12 - 9 6" + NOVA_LINHA +
        	"13 - 0 -";
        	
        	Assert.assertEquals(DFSesperada, controller.DFS(controller.getGraph(), "13"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
}