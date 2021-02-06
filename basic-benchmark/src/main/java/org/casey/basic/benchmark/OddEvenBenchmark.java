package org.casey.basic.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName OddEvenBenchmark
 * @Author Fu Kai
 * @Description 奇数基准测试
 * @Date 2021/1/26 16:17
 */
// 吞吐量单位ops/ms(尽量与ms搭配) 1ms运行多少次
@BenchmarkMode(Mode.Throughput)

// 平均时间 ns/ops(尽量与ns搭配) 运行一次所需时间ns
// @BenchmarkMode(Mode.AverageTime)

// 结果所使用的时间单位
@OutputTimeUnit(TimeUnit.MILLISECONDS)
// 每个测试线程分配一个实例
@State(Scope.Thread)
// Fork进行的数目, fork为2就是测试两遍
@Fork(1)
// 先预热1轮
@Warmup(iterations = 2)
// 进行1轮测试
@Measurement(iterations = 1)
public class OddEvenBenchmark {


    // @Benchmark
    // public void benchmark1() {
    //     int i = 2/2;
    // }
    //
    // @Benchmark
    // public void benchmark2() {
    //     int i = 2 << 1;
    // }

    @Benchmark
    public void benchmark1() {
        int j = 1;
        boolean i = (j & 1) == 1;
    }

    @Benchmark
    public void benchmark2() {
        int j = 1;
        boolean i = (j % 1 == 0);
    }

}
