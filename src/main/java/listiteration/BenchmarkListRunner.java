package listiteration;

import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class BenchmarkListRunner {

	public static void main(String[] args) throws RunnerException {
		final Options options = new OptionsBuilder()
                .include(BenchmarkListCollection.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();

	}

}
