package thread;


import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class ThreadTest {


    public static void main(String[] args) {


        //1.传递 Runnable 对象，通过 start 启动。
        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();

        //2.复写 Thread#run方法
        class MyThread extends Thread {
            @Override
            public void run() {

            }
        }
        new MyThread().start();

        FutureTask task = new FutureTask(new MyCallable());
        new Thread(task).start();


//        Executors.newCachedThreadPool();//线程可复用的线程池
//        Executors.newFixedThreadPool();//固定数量的线程池
//        Executors.newScheduledThreadPool();//可指定定时任务的线程池
//        Executors.newSingleThreadExecutor();//线程数量为1的线程池



    }

   static class MyCallable implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return null;
        }
    }



}
