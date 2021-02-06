package org.casey.basic.benchmark;

import com.alibaba.fastjson.JSONObject;
import org.casey.basic.benchmark.entity.User;
import org.openjdk.jmh.annotations.*;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName StringBenchmark
 * @Author Fu Kai
 * @Description 测试三个类型
 * 1. 多线程下 单例和多例的ObjectMapper测试是否阻塞
 * 2. 单线程下 测试ObjectMapper 与 Reader和Writer性能
 * 3. 单线程下 测试JSONObject与ObjectMapper的序列化和反序列化性能
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
@Warmup(iterations = 2)
// 进行1轮测试
@Measurement(iterations = 1)
public class JacksonBenchmark {

    @Param({"1000", "100000"}) // 定义四个参数，之后会分别对这四个参数进行测试
    private int n;
    private User user;


    @Setup(Level.Trial) // 初始化方法，在全部Benchmark运行之前进行
    public void init() {
        User user = new User();
        user.setId(1L);
        user.setName("Fu Kai");
        user.setAge(21);
        user.setBirthday(LocalDateTime.now());
        this.user = user;
    }

    @Benchmark
    public void fastJsonSerializeBenchmark() {
        JSONObject.toJSONString(user);
    }

}
