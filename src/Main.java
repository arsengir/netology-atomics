import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.LongAdder;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("Генерация 3 массивов целых положительных чисел");
        List<Integer> reportShop1 = new ArrayList<>();
        List<Integer> reportShop2 = new ArrayList<>();
        List<Integer> reportShop3 = new ArrayList<>();
        addList(reportShop1, 5);
        addList(reportShop2, 8);
        addList(reportShop3, 3);
        System.out.println(reportShop1);
        System.out.println(reportShop2);
        System.out.println(reportShop3);

        System.out.println("Создание трех потоков, которые суммируют выручку");
        LongAdder stat = new LongAdder();
        Thread shop1 = new Thread(() -> reportShop1.forEach(stat::add));
        Thread shop2 = new Thread(() -> reportShop2.forEach(stat::add));
        Thread shop3 = new Thread(() -> reportShop3.forEach(stat::add));

        shop1.start();
        shop2.start();
        shop3.start();

        shop3.join();
        shop2.join();
        shop1.join();

        System.out.println("Общий итог: " + stat.sum());

    }

    private static void addList(List<Integer> list, int size) {
        final int FROM = 1;
        final int TO = 10000;
        for (int i = 0; i < size; i++) {
            list.add(FROM + (int) (Math.random() * TO));
        }
    }


}
