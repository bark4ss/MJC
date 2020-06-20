package listiteration;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Benchmark)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
@Measurement(iterations = 10)
@Warmup(iterations = 5)
public class BenchmarkListCollection {

	@Param({ "500000", "1000000", "2000000" })
	private int size;

	private List<String> testData = new ArrayList<>();

	@Setup
	public void doSetup() {
		populateList(size);
	}

	private List<String> populateList(int size) {
		List<String> list = new ArrayList<>();
		for (int ctr = 0; ctr < size; ++ctr) {
			list.add("zzz");
		}
		return list;
	}

	@Benchmark
	public long iteratorWhile() {

		long count = 0;

		Iterator<String> it = testData.iterator();
		while (it.hasNext()) {
			String i = it.next();
			if (i.length() == 3) {
				++count;
			}

		}

		return count;
	}

	@Benchmark
	public long forLoop() {

		long count = 0;

		for (int i = 0; i < testData.size(); i++) {

			if (testData.get(i).length() == 3) {
				++count;
			}

		}

		return count;
	}

	@Benchmark
	public long forEach() {

		long count = 0;

		for (String item : testData) {

			if (item.length() == 3) {
				++count;
			}

		}

		return count;

	}

	@Benchmark
	public long forEachLambda() {

		IntHolder intHolder = new IntHolder();

		testData.forEach(i -> {
			if (i.length() == 3)
				++intHolder.value;
		});

		return intHolder.value;

	}

	@Benchmark
	public long streamSingleThread() {
		return testData.stream().filter(i->i.length()==3).count();
	}

	@Benchmark
	public long streamMultiThread() {

		return testData.stream().parallel().filter(i->i.length()==3).count();

	}

	public static class IntHolder {
		public long value = 0;
	}
}
