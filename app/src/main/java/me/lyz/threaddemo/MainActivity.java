package me.lyz.threaddemo;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import me.lyz.threaddemo.asynctask.MyAsyncTask;
import me.lyz.threaddemo.asynctask.MyAsyncTask2;
import me.lyz.threaddemo.service.MyIntentService;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private WindowManager.LayoutParams mLayoutParams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        findViewById(R.id.text_hello).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(intent);
            }
        });
        init();

        Button floatButton = new Button(this);
        floatButton.setText("hello");
        mLayoutParams = new WindowManager.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                0, 0, PixelFormat.TRANSPARENT);
        mLayoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
        mLayoutParams.type = WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;

        mLayoutParams.x = 100;
        mLayoutParams.y = 300;
        getWindowManager().addView(floatButton, mLayoutParams);
    }

    private void init() {
    }

    /**
     * AsyncTask使用
     */
    private void useAsyncTask() {
        //所有AsyncTask默认都是在同一个线程池执行的
        new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MyAsyncTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        new MyAsyncTask2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MyAsyncTask2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MyAsyncTask2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new MyAsyncTask2().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }


    /**
     * ThreadPoolExecutor使用
     */
    private void useThreadPoolExecutor() {
        Runnable task = new Runnable() {
            @Override
            public void run() {
                Log.d(TAG, "run: " + System.currentTimeMillis());
            }
        };

        ExecutorService singleThreadExecutor = Executors.newSingleThreadExecutor();
        singleThreadExecutor.execute(task);
        singleThreadExecutor.shutdown();

        ExecutorService cacheThreadPool = Executors.newCachedThreadPool();
        cacheThreadPool.execute(task);
        cacheThreadPool.shutdown();

        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(5);
        fixedThreadPool.execute(task);
        fixedThreadPool.shutdown();

        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        scheduledThreadPool.schedule(task, 1, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleAtFixedRate(task, 1, 10, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(task, 1, 10, TimeUnit.SECONDS);
        scheduledThreadPool.shutdown();
    }


    /**
     * IntentService使用
     */
    private Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Intent intent1 = new Intent(MainActivity.this, MyIntentService.class);
            intent1.putExtra("msg", "intent4");
            startService(intent1);
            return true;
        }
    });

    private void useIntentService() {
        Intent intent1 = new Intent(this, MyIntentService.class);
        intent1.putExtra("msg", "intent1");
        startService(intent1);

        Intent intent2 = new Intent(this, MyIntentService.class);
        intent2.putExtra("msg", "intent2");
        startService(intent2);

        Intent intent3 = new Intent(this, MyIntentService.class);
        intent3.putExtra("msg", "intent3");
        startService(intent3);


        mHandler.sendMessageDelayed(new Message(), 5000);
    }


    /**
     * HandlerThread使用
     * 初始化工作线程Handler前要先启动HandlerThread
     * 在onDestroy中或不再使用HandlerThread时，
     * 调用{@link HandlerThread#quit()}或{@link HandlerThread#quitSafely()}来结束线程
     */
    private HandlerThread mHandlerThread;

    /**
     * 工作线程Handler
     */
    private Handler mWorkerHandler;

    /**
     * UI线程Handler
     */
    private Handler mMainHandler;


    private void useHandlerThread() {

        mHandlerThread = new HandlerThread("Worker") {
            @Override
            public void run() {
                super.run();
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                mWorkerHandler.sendEmptyMessage(0);
            }
        };
        mHandlerThread.start();

        mMainHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });
        mWorkerHandler = new Handler(mHandlerThread.getLooper(), new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                return false;
            }
        });

        mHandlerThread.quit();
    }
}
