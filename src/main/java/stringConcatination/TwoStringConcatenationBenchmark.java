package stringConcatination;

import java.util.Arrays;
import java.util.StringJoiner;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.google.common.base.Joiner;

@Warmup(iterations = 3, time = 2, timeUnit = TimeUnit.SECONDS)
@Measurement(iterations = 10, time = 2, timeUnit = TimeUnit.SECONDS)
@Fork(1)
@State(Scope.Thread)
public class TwoStringConcatenationBenchmark {
    private String s0;
    private String s1;
    
    @Setup
    public void setupTest(){
        s0 = UUID.randomUUID().toString();
        s1 = UUID.randomUUID().toString();
    }

    @Benchmark
    public void testStringFormat(Blackhole bh) {
        String combined = String.format("%s%s", s0, s1);
        bh.consume(combined);
    }

    @Benchmark
    public void testPlus(Blackhole bh) {
        String combined = s0 + s1;
        bh.consume(combined);
    }

    @Benchmark
    public void testStringBuilder(Blackhole bh) {
        StringBuilder sb = new StringBuilder()
                .append(s0)
                .append(s1);
        bh.consume(sb.toString());
    }

    @Benchmark
    public void testStringBuffer(Blackhole bh) {
        StringBuffer sb = new StringBuffer()
                .append(s0)
                .append(s1);
        bh.consume(sb.toString());
    }

    @Benchmark
    public void testStringJoin(Blackhole bh) {
        String combined = String.join("", s0, s1);
        bh.consume(combined);
    }
    
    @Benchmark
    public void testStringJoiner(Blackhole bh) {
        StringJoiner combined = new StringJoiner("", s0, s1);
        bh.consume(combined.toString());
    }

    @Benchmark
    public void testStringConcat(Blackhole bh) {
        String combined = s0.concat(s1);
        bh.consume(combined);
    }

    @Benchmark
    public void testStringUtilsJoin(Blackhole bh) {
        String combined = StringUtils.join(s0, s1);
        bh.consume(combined);
    }

    @Benchmark
    public void testGuavaJoiner(Blackhole bh) {
        String combined = Joiner.on("").join(s0, s1);
        bh.consume(combined);
    }

    @Benchmark
    public void testStreamJoining(Blackhole bh) {
        String combined = Arrays.asList(s0, s1)
                .stream()
                .collect(Collectors.joining());
        bh.consume(combined);
    }
    
    public static void main(String[] args) throws RunnerException {
		final Options options = new OptionsBuilder()
                .include(TwoStringConcatenationBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();

	}
}
