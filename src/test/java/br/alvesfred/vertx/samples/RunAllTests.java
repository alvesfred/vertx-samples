package br.alvesfred.vertx.samples;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectPackages;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectPackages("br.alvesfred.vertx.samples")
public class RunAllTests {

	@TestFactory
	public Stream<DynamicTest> translateDynamicTestsFromStream() {
		Map<Integer, String> in = new HashMap<>();
		in.put(0, "a");
		in.put(1, "b");
		in.put(2, "c");

		List<String> outputList = Arrays.asList("a", "b", "c");

		return in.keySet().stream().map(key -> DynamicTest.dynamicTest("Test Key: " + key, () -> {
			String value = in.get(key);
			assertEquals(outputList.get(key), value);
		}));
	}
}
