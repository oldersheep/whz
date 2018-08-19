package top.deramertn9527.center.service.simpleschedule;


public abstract class AbstractWorker {

    /**
     * work 任务方法
     */
    public abstract void work();

    /**
     * Scheduled 调用方法
     */
    public abstract void start();
}