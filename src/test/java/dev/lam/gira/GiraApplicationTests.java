package dev.lam.gira;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@SpringBootTest
class GiraApplicationTests {

	@Test
	void contextLoads() {
		assertThat(1 + 1).isEqualTo(2);
	}

}
