import static org.junit.Assert.*;
import org.junit.Test;

public class HelloWorldTest {
	@Test public void TesthelloWorld() {
		HelloWorld hw = new HelloWorld();
		String result = hw.helloWorld();
		assertEquals("Hello World!", result);
	}
}
