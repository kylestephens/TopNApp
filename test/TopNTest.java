import com.kyle.test.TopN;
import junit.framework.TestCase;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TopNTest extends TestCase {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    @Before
    public void setupStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Test
    public void getTopNTest() throws Exception {
        TopN topN = new TopN();
        topN.getTopN("res/testInput.txt", 4);

        assertEquals("10, 10, 10, 9.", outContent.toString());
    }

    @After
    public void cleanupStreams() {
        System.setOut(null);
    }
}
