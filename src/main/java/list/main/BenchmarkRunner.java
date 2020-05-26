package list.main;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import list.benchmark.SearchBenchmark;

public class BenchmarkRunner {

	public static void main(String[] args) throws RunnerException {
		final Options options = new OptionsBuilder()
                .include(SearchBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();

	}

}
