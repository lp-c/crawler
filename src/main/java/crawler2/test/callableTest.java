package crawler2.test;

import java.util.ArrayList;
import java.util.concurrent.*;

public class callableTest implements Callable {
    private int id;

    public callableTest(int id){
        this.id=id;
    }

    @Override
    public String call() throws Exception {
        System.out.println("call()方法被调用 "+Thread.currentThread().getName());
        return "call()方法被调用 返回的结果是 "+id+" "+Thread.currentThread().getName();
    }

    public static void main(String[] args){
        ExecutorService pool = Executors.newCachedThreadPool();
        ArrayList<Future<String>> resultList = new ArrayList<>();
        for(int i=0;i<10;i++){
            Future fu = pool.submit(new callableTest(i));
            //System.out.println(fu);
            resultList.add(fu);
        }
        for(Future<String> future:resultList){
            try{
                while(!future.isDone()){
                    System.out.println(future.get());
                }
            }catch (InterruptedException e){
                e.printStackTrace();
            }catch (ExecutionException e){
                e.printStackTrace();
            }finally {
                //启动一次顺序关闭 执行以前提交的任务 但不执行新任务
                pool.shutdown();
            }
        }
    }
}
