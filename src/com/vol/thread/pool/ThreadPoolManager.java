/**
 * 
 */
package com.vol.thread.pool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.vol.thread.task.ThreadTask;

/**
 * @author miseryyc
 *
 */
public class ThreadPoolManager {
	
	private static ThreadPoolManager threadPoolManager;
	// 线程池维护线程的最少数量
    private final static int CORE_POOL_SIZE = 4;
    // 线程池维护线程的最大数量
    private final static int MAX_POOL_SIZE = 10;
    // 线程池维护线程所允许的空闲时间
    private final static int KEEP_ALIVE_TIME = 0;
    // 线程池所使用的缓冲队列大小
    private final static int WORK_QUEUE_SIZE = 10;
    // 消息缓冲队列
    Queue<ThreadTask> taskQueue = new LinkedList<ThreadTask>();
    
	public static ThreadPoolManager getInstance() {
		if(threadPoolManager == null) {
			threadPoolManager = new ThreadPoolManager();
		}
		return threadPoolManager;
	}
	
	final Runnable accessBufferThread = new Runnable()
    {
        public void run()
        {
            // 查看是否有待定请求，如果有，则创建一个新的AccessDBThread，并添加到线程池中
            if( hasMoreAcquire() )
            {
            	ThreadTask task = (ThreadTask) taskQueue.poll();
                threadPool.execute(task);
            }
        }
    };

    final RejectedExecutionHandler handler = new RejectedExecutionHandler()
    {
        public void rejectedExecution( Runnable r, ThreadPoolExecutor executor )
        {
            taskQueue.offer((ThreadTask)r);
        }
    };
    
    // 管理数据库访问的线程池
    final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
            new ArrayBlockingQueue( WORK_QUEUE_SIZE ), this.handler );
    
    // 调度线程池
    final ScheduledExecutorService scheduler = Executors
            .newScheduledThreadPool( 1 );
    
    final ScheduledFuture taskHandler = scheduler.scheduleAtFixedRate(
            accessBufferThread, 0, 1, TimeUnit.SECONDS );
    
    private ThreadPoolManager(){}
    
    private boolean hasMoreAcquire()
    {
        return !taskQueue.isEmpty();
    }
    
    public void addTask(ThreadTask task)
    {
        threadPool.execute(task);
    } 	
}
