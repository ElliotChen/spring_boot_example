package tw.elliot.reactor;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/*
@RunWith(SpringRunner.class)
@SpringBootTest
*/
public class ApplicationTests {

    @Test
    public void contextLoads() {

        Integer[] data = {1,2,3,4,5,6,7,8,9,10};

        List<Integer> ds = new ArrayList<>();
        ds.add(1);
        ds.add(2);
        ds.add(3);
        ds.add(4);
        ds.add(5);
        ds.add(6);
        ds.add(7);
        ds.add(8);
        ds.add(9);
        ds.add(10);
        ds.add(10);
        ds.add(10);
        ds.add(10);
        ds.add(10);
        ds.add(10);
        ds.add(10);
        ds.add(10);
        Long time1 = 0L;
        Long time2 = 0L;
        Long start = System.currentTimeMillis();
        for (int i=0; i< 100; i++) {
            ds.forEach(System.out::println);
        }
        Long end = System.currentTimeMillis();
        time1 = end - start;
        System.out.print(end - start);

        start = System.currentTimeMillis();
        for (int i=0; i< 100; i++) {
            for (int j = 0; j < ds.size(); j++) {
                System.out.println(ds.get(j));
            }
        }
        end = System.currentTimeMillis();
        time2 = end - start;
        System.out.print(end - start);

        System.out.println("Time1 is ["+time1+"] ms, Time2 is ["+time2+"] ms");

    }

}
