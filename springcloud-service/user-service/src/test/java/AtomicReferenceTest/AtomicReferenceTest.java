package AtomicReferenceTest;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class AtomicReferenceTest {
    public final static AtomicReference<Statistic> atomicStatistic = new AtomicReference<Statistic>();

    static Statistic statistic = new Statistic("", 0);

    public static void main(String[] args) {

        //用来参考，AtomicInteger是怎么实现自增的
        AtomicInteger a = new AtomicInteger();
        a.incrementAndGet();

        //初始值为Statistic对象
        atomicStatistic.set(statistic);

        for (int i = 0; i < 10; i++) {
            new Thread() {
                public void run() {
                    try {
                        //为了 使得控制台打印的 更改student1的线程 能显示出不一样 每个线程随机停顿 多执行几次能看出效果
                        Thread.sleep(Math.abs((int) Math.random() * 100));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Statistic expect = null;
                    Statistic update = new Statistic();

                    do {
                        System.out.println(Thread.currentThread().getId() + "正在交换");
                        expect = atomicStatistic.get();
                        update.setParticipants(expect.getParticipants() + "," + Thread.currentThread().getId());
                        update.setMoney(expect.getMoney() + 1);
                    } while (!atomicStatistic.compareAndSet(expect, update));
                    expect = atomicStatistic.get();
                    System.out.println(Thread.currentThread().getId() + "交换结束，当前" + expect.toString());
                }
            }.start();
        }
    }
}

/**
 * 统计类，假设这是个募捐统计类
 */
@Data
class Statistic {

    /**
     * 募捐者的名字集合，用逗号隔开
     */
    private String participants;

    /**
     * 募捐钱的总数
     */
    private Integer money;

    public Statistic() {

    }

    public Statistic(String participants, Integer money) {
        this.participants = participants;
        this.money = money;
    }
}
