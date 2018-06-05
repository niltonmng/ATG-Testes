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

public class GraphRepresentationTest {
	private static final String NOVA_LINHA = System.lineSeparator();
	private Controller controller;
    private final String path1 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph1.txt"; 
    private final String path2 = System.getProperty("java.io.tmpdir") + System.getProperty("file.separator") + "graph2.txt";
    
    @Before
    public void prepareGraphFile1() {
        List<String> lines = Arrays.asList("4", "1 2 0.3", "2 3 0.1", "3 4 0.2");
        Path file = Paths.get(path1);
        try {
            Files.write(file, lines, Charset.forName("UTF-8"));
        } catch (IOException e) {
            Assert.fail();
        }
    }
	
	@Before
    public void prepareGraphFile2() {
        List<String> lines = Arrays.asList("4", "1 2", "3 2", "4 3");
        Path file = Paths.get(path2);
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
    public void testAM1() {
        try {
            controller.readWeightedGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = " 1 2 3 4" + NOVA_LINHA
				+  "1 0 0.3 0 0" + NOVA_LINHA
				+  "2 0.3 0 0.1 0" + NOVA_LINHA
				+  "3 0 0.1 0 0.2" + NOVA_LINHA
				+  "4 0 0 0.2 0";
       
        try {
        	Assert.assertEquals(expectedOutput, controller.graphRepresentation(controller.getGraph(), "AM"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
	
	@Test
    public void testAL1() {
        try {
            controller.readWeightedGraph(path1);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 2(0.3)" + NOVA_LINHA
        					+ 	"2 - 1(0.3) 3(0.1)" + NOVA_LINHA
        					+	"3 - 2(0.1) 4(0.2)" + NOVA_LINHA
        					+   "4 - 3(0.2)";
       
        try {
        	Assert.assertEquals(expectedOutput, controller.graphRepresentation(controller.getGraph(), "AL"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
	
	@Test
    public void testAM2() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = " 1 2 3 4" + NOVA_LINHA
				+  "1 0 1 0 0" + NOVA_LINHA
				+  "2 1 0 1 0" + NOVA_LINHA
				+  "3 0 1 0 1" + NOVA_LINHA
				+  "4 0 0 1 0";
       
        try {
        	Assert.assertEquals(expectedOutput, controller.graphRepresentation(controller.getGraph(), "AM"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
	
	@Test
    public void testAL2() {
        try {
            controller.readGraph(path2);
        } catch (Exception e) {
            Assert.fail();
        }
        
        String expectedOutput = "1 - 2" + NOVA_LINHA
        					+ 	"2 - 1 3" + NOVA_LINHA
        					+	"3 - 2 4" + NOVA_LINHA
        					+   "4 - 3";
       
        try {
        	Assert.assertEquals(expectedOutput, controller.graphRepresentation(controller.getGraph(), "AL"));
        } catch (Exception e) {
        	Assert.fail();
        }
    }
	
}
