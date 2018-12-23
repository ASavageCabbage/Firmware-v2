package test.model;

import model.Battery;
import org.junit.Assert;
import org.junit.Test;
import ui.Main;
import ui.SavingManager;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class SavingManagerTests {

    private File testFile = new File("test.txt");

    @Test
    public void testGetFileName() {
        Assert.assertEquals("test", SavingManager.getFileName(testFile));
    }

    @Test
    public void testGetClassName() {
        Battery b = new Battery();
        Main m = new Main();
        Assert.assertEquals("Battery", SavingManager.getClassName(b));
        Assert.assertEquals("Main", SavingManager.getClassName(m));
    }

    @Test
    public void testReadFile() {
        System.out.println(System.getProperty("user.dir"));
        List<String> contents = SavingManager.readFile(testFile);
        List<String> expected = new LinkedList<>();
        expected.add("hello");
        expected.add("world");
        expected.add("a");
        expected.add("bc");
        expected.add("def");
        Assert.assertEquals(expected, contents);
    }
}
