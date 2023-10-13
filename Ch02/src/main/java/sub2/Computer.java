package sub2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/*
 * 생성자와 Setter의 차이?.. 
 *   생성자는 객체를 초기화하고 필수 속성을 설정하는 데 사용되며, 
 *   Setter는 객체의 속성 값을 변경하거나 설정하기 위해 사용
 */

@Component("com")
public class Computer {

	private CPU cpu;
	private RAM ram;
	
	// DI - Field Inject
	@Autowired
	private HDD hdd;
	
	// DI - Constructor Inject
	@Autowired
	public Computer(CPU cpu) {
		this.cpu = cpu;
	}
	
	// ID - Setter Inject
	@Autowired
	public void setRam(RAM ram) {
		this.ram = ram;
	}
	
	public void show() {
		cpu.show();
		ram.show();
		hdd.show();
	}
}