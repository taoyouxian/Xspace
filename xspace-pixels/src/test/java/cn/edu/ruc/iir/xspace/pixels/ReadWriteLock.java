package cn.edu.ruc.iir.xspace.pixels;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @version V1.0
 * @Package: cn.edu.ruc.iir.pixels.daemon.zookeeper
 * @ClassName: ReadWriteLock
 * @Description:
 * @author: tao
 * @date: Create in 2018-10-27 09:48
 **/
public class ReadWriteLock {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory
                .newClient("dbiir02:2181", retryPolicy);
        client.start();

        InterProcessReadWriteLock readWriteLock = new InterProcessReadWriteLock(client, "/read-write-lock");

        //读锁
        final InterProcessMutex readLock = readWriteLock.readLock();
        //写锁
        final InterProcessMutex writeLock = readWriteLock.writeLock();

        final InterProcessMutex readLock1 = readWriteLock.readLock();

        final InterProcessMutex readLock2 = readWriteLock.readLock();

        final InterProcessMutex writeLock1 = readWriteLock.writeLock();

        try {
            readLock.acquire();
            System.out.println(Thread.currentThread() + "获取到读锁");

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread() + "writeLock");
                        //在读锁没释放之前不能读取写锁。
                        writeLock.acquire();
                        System.out.println(Thread.currentThread() + "获取到写锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            writeLock.release();
                            System.out.println(Thread.currentThread() + "writeLock 释放锁");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread() + "readLock1");
                        readLock1.acquire();
                        System.out.println(Thread.currentThread() + "readLock1 获取到读锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(8000);
                            readLock1.release();
                            System.out.println(Thread.currentThread() + "readLock1 释放锁");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread() + "readLock2");
                        readLock2.acquire();
                        System.out.println(Thread.currentThread() + "readLock2 获取到读锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            Thread.sleep(6000);
                            readLock2.release();
                            System.out.println(Thread.currentThread() + "readLock2 释放锁");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(Thread.currentThread() + "writeLock1");
                        //在读锁没释放之前不能读取写锁。
                        writeLock1.acquire();
                        System.out.println(Thread.currentThread() + "writeLock1 获取到写锁");
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            writeLock1.release();
                            System.out.println(Thread.currentThread() + "writeLock1 释放锁");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();

            //停顿3000毫秒不释放锁，这时其它线程可以获取读锁，却不能获取写锁。
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //停顿3000毫秒不释放锁，这时其它线程可以获取读锁，却不能获取写锁。
            Thread.sleep(3000);
            System.out.println(Thread.currentThread() + "readLock 释放读锁");
            readLock.release();
        }

        Thread.sleep(10000);
        client.close();
    }

}
