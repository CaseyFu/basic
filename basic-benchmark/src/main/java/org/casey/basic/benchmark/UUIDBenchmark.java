package org.casey.basic.benchmark;

import cn.hutool.core.util.IdUtil;
import org.openjdk.jmh.annotations.*;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName BenchmarkTest
 * @Author Fu Kai
 * @Description 基准测试
 * @Date 2020/12/5 22:21
 */


@BenchmarkMode(Mode.Throughput)
@Warmup(iterations = 3, time = 1)
@Measurement(iterations = 1, time = 1)
@Threads(4)
@Fork(1)
@State(value = Scope.Benchmark)
@OutputTimeUnit(TimeUnit.MILLISECONDS)
public class UUIDBenchmark {

    // @Param(value = {"10", "50", "100"})
    // private int length;

    @Benchmark
    public String java8UUIDBenchmark() {
        return UUID.randomUUID().toString();
    }

    @Benchmark
    public String hutoolFastUUIDBenchmark(){
        return IdUtil.fastUUID();
    }

    @Benchmark
    public String hutoolFastSimpleUUIDBenchmark(){
        return IdUtil.fastSimpleUUID();
    }

    @Benchmark
    public String hutoolUUIDBenchmark(){
        return cn.hutool.core.lang.UUID.randomUUID().toString();
    }

}
