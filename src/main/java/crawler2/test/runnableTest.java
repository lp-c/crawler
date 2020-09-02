package crawler2.test;

import lombok.SneakyThrows;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class runnableTest implements Runnable{

    private int id;

    public runnableTest(int id){
        this.id=id;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName());
        System.out.println("hello "+id);
    }

    public static void main(String[] args) {
        ExecutorService pool = Executors.newCachedThreadPool();
        for(int i=0;i<10;i++){
            pool.execute(new runnableTest(i));
        }
    }
}
