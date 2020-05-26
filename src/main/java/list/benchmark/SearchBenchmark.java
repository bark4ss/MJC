package list.benchmark;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
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
import org.openjdk.jmh.infra.Blackhole;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" }, warmups = 2)
@Warmup(iterations = 5)
@Measurement(iterations = 10)
public class SearchBenchmark {

	@Param({ "10000" })
	private int N;

	private List<String> linkedList;
	private List<String> arrayList;
	private List<String> copyOnWriteArrayList;

	private String stringToFind = "8888";

	@Setup
	public void setupCollections() {
		linkedList = new LinkedList<>();
		arrayList = new ArrayList<>();
		copyOnWriteArrayList = new CopyOnWriteArrayList<>();
		for (int i = 0; i < N; i++) {
			final String value = String.valueOf(i);
			linkedList.add(value);
			arrayList.add(value);
			copyOnWriteArrayList.add(value);
		}

		stringToFind = String.valueOf(new Random().nextInt(N));
	}

	@Benchmark
	public void testSearchInLinkedList(Blackhole blackhole) {
		blackhole.consume(linkedList.contains(stringToFind));
	}

	@Benchmark
	public void testSearchInArrayList(Blackhole blackhole) {
		blackhole.consume(arrayList.contains(stringToFind));
	}

	@Benchmark
	public void testSearchInCopyOnWriteArrayList(Blackhole blackhole) {
		blackhole.consume(copyOnWriteArrayList.contains(stringToFind));
	}
}
