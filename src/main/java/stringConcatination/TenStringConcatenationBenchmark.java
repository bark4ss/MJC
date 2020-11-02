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
public class TenStringConcatenationBenchmark {
    private String s0;
    private String s1;
    private String s2;
    private String s3;
    private String s4;
    private String s5;
    private String s6;
    private String s7;
    private String s8;
    private String s9;
    
    @Setup
    public void setupTest(){
        s0 = UUID.randomUUID().toString();
        s1 = UUID.randomUUID().toString();
        s2 = UUID.randomUUID().toString();
        s3 = UUID.randomUUID().toString();
        s4 = UUID.randomUUID().toString();
        s5 = UUID.randomUUID().toString();
        s6 = UUID.randomUUID().toString();
        s7 = UUID.randomUUID().toString();
        s8 = UUID.randomUUID().toString();
        s9 = UUID.randomUUID().toString();
    }

    @Benchmark
    public void testStringFormat(Blackhole bh) {
        String combined = String.format("%s%s%s%s%s%s%s%s%s%s", s0, s1, s2, s3, s4, s5, s6, s7, s8, s9);
        bh.consume(combined);
    }

    @Benchmark
    public void testPlus(Blackhole bh) {
        String combined = s0
                + s1 
                + s2 
                + s3 
                + s4 
                + s5 
                + s6 
                + s7 
                + s8 
                + s9;
        bh.consume(combined);
    }

    @Benchmark
    public void testStringBuilder(Blackhole bh) {
        StringBuilder sb = new StringBuilder()
                .append(s0)
                .append(s1)
                .append(s2)
                .append(s3)
                .append(s4)
                .append(s5)
                .append(s6)
                .append(s7)
                .append(s9);
        bh.consume(sb.toString());
    }

    @Benchmark
    public void testStringBuffer(Blackhole bh) {
        StringBuffer sb = new StringBuffer()
                .append(s0)
                .append(s1)
                .append(s2)
                .append(s3)
                .append(s4)
                .append(s5)
                .append(s6)
                .append(s7)
                .append(s9);
        bh.consume(sb.toString());
    }

    @Benchmark
    public void testStringJoin(Blackhole bh) {
        String combined = String.join("", s0, s1, s2, s3, s4, s5, s6, s7, s8, s9);
        bh.consume(combined);
    }
    
    @Benchmark
    public void testStringJoiner(Blackhole bh) {
        StringJoiner combined = new StringJoiner("")
        		.add(s0)
        		.add(s1)
        		.add(s2)
        		.add(s3)
        		.add(s4)
        		.add(s5)
        		.add(s6)
        		.add(s7)
        		.add(s8)
        		.add(s9);
        bh.consume(combined.toString());
    }

    @Benchmark
    public void testStringConcat(Blackhole bh) {
        String combined = s0.concat(s1)
                .concat(s2)
                .concat(s3)
                .concat(s4)
                .concat(s5)
                .concat(s6)
                .concat(s7)
                .concat(s9);
        bh.consume(combined);
    }

    @Benchmark
    public void testStringUtilsJoin(Blackhole bh) {
        String combined = StringUtils.join(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9);
        bh.consume(combined);
    }

    @Benchmark
    public void testGuavaJoiner(Blackhole bh) {
        String combined = Joiner.on("").join(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9);
        bh.consume(combined);
    }

    @Benchmark
    public void testStreamJoining(Blackhole bh) {
        String combined = Arrays.asList(s0, s1, s2, s3, s4, s5, s6, s7, s8, s9)
                .stream()
                .collect(Collectors.joining());
        bh.consume(combined);
    }
    
    public static void main(String[] args) throws RunnerException {
		final Options options = new OptionsBuilder()
                .include(TenStringConcatenationBenchmark.class.getSimpleName())
                .forks(1)
                .build();

        new Runner(options).run();

	}
}
