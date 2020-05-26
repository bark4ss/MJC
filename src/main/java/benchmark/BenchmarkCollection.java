package benchmark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
@State(Scope.Thread)
@Fork(value = 2, jvmArgs = { "-Xms2G", "-Xmx2G" })
public class BenchmarkCollection {

	@Param({ "10000" })
	private static int N;
	private static final String STRING_TO_FIND = String.valueOf(N - N / 4);

	private List<String> linkedList;
	private List<String> arrayList;
	private Set<String> hashSet;
	private Set<String> linkedHashSet;
	private Set<String> treeSet;

	@Setup
	public void setUp() {
		linkedList = new LinkedList<>();
		arrayList = new ArrayList<>();
		hashSet = new HashSet<>();
		linkedHashSet = new LinkedHashSet<>();
		treeSet = new TreeSet<>();
		for (int i = 0; i < N; i++) {
			final String value = String.valueOf(i);
			linkedList.add(value);
			arrayList.add(value);
			hashSet.add(value);
			linkedHashSet.add(value);
			treeSet.add(value);
		}

	}

	@Benchmark
	public void List_LinkedList() {
		linkedList.contains(STRING_TO_FIND);
	}

	@Benchmark
	public void List_ArrayList() {
		arrayList.contains(STRING_TO_FIND);
	}

	@Benchmark
	public void Set_HashSet() {
		hashSet.contains(STRING_TO_FIND);
	}

	@Benchmark
	public void Set_LinkedHashSet() {
		linkedHashSet.contains(STRING_TO_FIND);
	}

	@Benchmark
	public void Set_TreeSet() {
		treeSet.contains(STRING_TO_FIND);
	}
}
