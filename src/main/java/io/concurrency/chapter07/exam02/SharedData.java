package io.concurrency.chapter07.exam02;

public class SharedData {

	private Object lock = new Object();

	private boolean conditionVariable = false;

	public void consume() throws InterruptedException {
		/**
		 * wait()은 반드시 synchronized 블록 안에서 실행
		 * 그렇지 않을 경우 IllegalMonitorStateException 발생
		 */
		synchronized (lock) {
			wait();
		}

		/**
		 * 상태 변수 값이 참과 거짓에 따라 wait()을 실행할 것인지 아닌지 조건 명시
		 * wait() 호출하기 전 조건 확인하고 notify() 혹은 notifyAll()로 인해 깨어난 쓰레드도 다음 명령어 수행 전 조건 확인 필요
		 * 조건을 확인하는 구문은 반드시 while 구문이여야 하는 이유
		 * 1. 알 수 없는 이유로 쓸데ㅡ가 깨어나는 현상인 spurious wakeups가 간혹 일어나는데 이런 쓰레드가 활동을 게시하면 심각한 상황 발생 가능
		 * 2. 대기에서 깨어난 쓰레드가 락 획득 후 wait() 구문 이후 명령을 진행하기 전 조건을 확인해보니 다시 wait()을 실행해야하는 상황일 수 있음
		 */
		while (!conditionVariable) {
			wait();
		}

		// 락 해제하기 전 상태 값을 초기화해서 새롭게 진입하는 쓰레드는 다른 쓰레드에 의해 notify() 할 때까지는 대기 상태로 가도록
		conditionVariable = false;
	}

	public void produce() throws InterruptedException {
		/**
		 * notify(), notifyAll()은 반드시 synchronized 블록 안에서 실행
		 * 그렇지 않을 경우 IllegalMonitorStateException 발생
		 */
		synchronized (lock) {
			conditionVariable = true;
			notifyAll();
		}
	}
}
