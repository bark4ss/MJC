package listiteration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Measurement(iterations = 200)
@Warmup(iterations = 5)
public class BenchmarkListCollection {

	private static final int N = 1_000_000;

	@Setup(Level.Trial)
	public void doSetup() {
		for (int i = 0; i < N; i++) {
			testData.add(Integer.valueOf(i));
		}
	}

	@TearDown(Level.Trial)
	public void doTearDown() {
		testData = new ArrayList<>(N);
	}

	private static List<Integer> testData = new ArrayList<>(N);

	@Benchmark
	public List<Integer> iteratorWhile() {

		List<Integer> result = new ArrayList<>(testData.size());

		Iterator<Integer> it = testData.iterator();
		while (it.hasNext()) {
			Integer i = it.next();
			result.add((int) Math.sqrt(i));
		}

		return result;
	}

	@Benchmark
	public List<Integer> forLoop() {

		List<Integer> result = new ArrayList<>(testData.size());

		for (int j = 0; j < testData.size(); j++) {

			result.add((int) Math.sqrt(testData.get(j)));

		}

		return result;
	}

	@Benchmark
	public List<Integer> forEach() {

		List<Integer> result = new ArrayList<>(testData.size());

		for (Integer item : testData) {

			result.add((int) Math.sqrt(item));

		}

		return result;

	}

	@Benchmark
	public List<Integer> forEachLambda() {

		List<Integer> result = new ArrayList<>(testData.size());

		testData.forEach(i -> result.add((int) Math.sqrt(i)));

		return result;

	}

	@Benchmark
	public List<Integer> streamSingleThread() {

		List<Integer> result = new ArrayList<>(testData.size());

		testData.stream().forEach(i -> result.add((int) Math.sqrt(i)));

		return result;

	}

	@Benchmark
	public List<Integer> streamMultiThread() {

		List<Integer> result = new ArrayList<>(testData.size());

		testData.stream().parallel().forEach(i -> result.add((int) Math.sqrt(i)));

		return result;

	}
}
