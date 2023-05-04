package com.banco.bancoprueba;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(properties = "foo=bar")
class BancoPruebaApplicationTests {

	@Value("${foo}")
	String foo;


}
