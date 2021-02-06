package org.casey.basic.benchmark;

import org.openjdk.jmh.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName StringBenchmark
 * @Author Fu Kai
 * @Description todo
 * @Date 2021/1/4 10:52
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
@Warmup(iterations = 1)
// 进行1轮测试
@Measurement(iterations = 1)
public class StringBenchmark {

    @Param({"10", "20"}) // 定义四个参数，之后会分别对这四个参数进行测试
    private int n;

    private String string;


    @Setup(Level.Trial) // 初始化方法，在全部Benchmark运行之前进行
    public void init() {
        this.string = "string";
    }

    @Benchmark
    public void stringBuilderBenchmark() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < n; i++) {
            stringBuilder.append(string);
        }
    }

    @Benchmark
    public void stringBenchmark() {
        String plainString = "";
        for (int i = 0; i < n; i++) {
            plainString += string;
        }
    }


}