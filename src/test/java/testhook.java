public class testhook {

    public static void main(String[] args) {
        System.out.println("MainThread 启动");

        final Thread mainThread = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("接收到关闭信号");

            // 给主线程发送中断信号
            mainThread.interrupt();

            try {
                // 等待主线程正常执行完成
                mainThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("优雅关闭完成");
        }));

        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            // 中断响应
            System.out.println("主线程被中断，处理中断逻辑");
        }

        System.out.println("Main Thread执行完毕");
    }
}
