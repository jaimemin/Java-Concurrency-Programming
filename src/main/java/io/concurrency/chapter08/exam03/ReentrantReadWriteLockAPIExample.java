package io.concurrency.chapter08.exam03;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentrantReadWriteLockAPIExample {
	private static ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

	public int getQueueLength() {
		return readWriteLock.getQueueLength();
	}

	public int getReadHoldCount() {
		ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();

		return readWriteLock.getReadHoldCount();
	}

	public int getReadLockCount() {
		return readWriteLock.getReadLockCount();
	}

	public int getWriteHoldCount() {
		ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

		return readWriteLock.getWriteHoldCount();
	}

	public boolean hasQueuedThread(Thread thread) {
		return readWriteLock.hasQueuedThread(thread);
	}

	public boolean hasQueuedThreads() {
		return readWriteLock.hasQueuedThreads();
	}

	public boolean isFair() {
		return readWriteLock.isFair();
	}

	public boolean isWriteLocked() {
		return readWriteLock.isWriteLocked();
	}

	public boolean isWriteLockedByCurrentThread() {
		ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

		return readWriteLock.isWriteLockedByCurrentThread();
	}

	public static void main(String[] args) {
		ReentrantReadWriteLockAPIExample example = new ReentrantReadWriteLockAPIExample();

		Thread thread1 = new Thread(() -> {
			readWriteLock.readLock().lock();

			try {
				System.out.println("Has Queued Thread: " + example.hasQueuedThread(Thread.currentThread()));
				Thread.sleep(1000); // 스레드 2가 Lock을 보유한 상태에서 대기
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				readWriteLock.readLock().unlock();
			}
		});

		Thread thread2 = new Thread(() -> {
			readWriteLock.writeLock().lock();
			try {
				System.out.println("Has Queued Thread: " + example.hasQueuedThread(Thread.currentThread()));
				Thread.sleep(1000); // 스레드 2가 Lock을 보유한 상태에서 대기
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				readWriteLock.writeLock().unlock();
			}
		});

		thread1.start();
		thread2.start();

		try {
			Thread.sleep(500); // 메인 스레드 대기
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Queue Length: " + example.getQueueLength());
		System.out.println("Read Hold Count: " + example.getReadHoldCount());
		System.out.println("Read Lock Count: " + example.getReadLockCount());
		System.out.println("Write Hold Count: " + example.getWriteHoldCount());
		System.out.println("Has Queued Threads: " + example.hasQueuedThreads());
		System.out.println("Is Fair: " + example.isFair());
		System.out.println("Is Write Locked: " + example.isWriteLocked());
		System.out.println("Is Write Locked By Current Thread: " + example.isWriteLockedByCurrentThread());
	}
}
