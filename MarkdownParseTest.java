import static org.junit.Assert.*;
import org.junit.*;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;

public class MarkdownParseTest {
    //class header for the test
    @Test //declares the next method is a test
    public void addition() {
        assertEquals(2, 1 + 1); //checks to see if the two are the same
    }

    @Test
    public void testGetLinks() throws IOException{
        Path fileName = Path.of("test-file2.md");
        String content = Files.readString(fileName);
        ArrayList<String> links = new ArrayList<>();
        links.add("https://something.com");
        links.add("some-page.html"); 
        assertEquals(links, MarkdownParse.getLinks(content));
    }
}