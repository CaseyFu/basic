package org.casey.basic.benchmark;


import org.junit.Test;

// @SpringBootTest
class MemoBenchmarkApplicationTests {

	@Test
	void stringTest() {
		String string = "string ";
		String plainString = "";
		for (int i = 0; i < 2; i++) {
			plainString += string;
		}
		System.out.println(plainString);
	}

	@Test
	void stringBuilderTest() {
		String string = "string ";
		StringBuilder stringBuilder= new StringBuilder();
		for (int i = 0; i < 2; i++) {
			stringBuilder.append(string);
		}
		System.out.println(stringBuilder);
	}

}
