package sub1;

import org.springframework.beans.factory.annotation.Autowired;

public class SamsungTV {
	
	@Autowired
	private Speaker spk;
	
	public void powerOn() {
		System.out.println("SamsungTV power on...");
	}
	
	public void powerOff() {
		System.out.println("SamsungTV power off...");
	}
	
	public void soundUp() {
		spk.soundUp();
	}
	
	public void soundDown() {
		spk.soundDown();
	}
}