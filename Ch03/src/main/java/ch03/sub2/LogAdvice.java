package ch03.sub2;

import org.springframework.stereotype.Component;

@Component
public class LogAdvice {

	public void beforeLog() {
		System.out.println("----------------------------");
		System.out.println("Cross-cutting - beforeLog...");
	}
	
	public void afterLog() {
		System.out.println("Cross-cutting - afterLog...");
		System.out.println("----------------------------");
	}
}
