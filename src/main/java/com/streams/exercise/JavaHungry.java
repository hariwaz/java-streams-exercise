package com.streams.exercise;

import java.util.Map;
import java.util.stream.Collectors;

public class JavaHungry {

	public static void main(String[] args) {
		String input = "Java Hungry Blog Alive is Awesome";
		//First convert to Character object and then to lowercase
		int result = input.chars()
				                .mapToObj(Character::toLowerCase)
				                .collect(Collectors.groupingBy(c -> c, Collectors.counting()))
				                .entrySet()
				                .stream()
				                .filter(entry -> entry.getValue() == 1)
				                .map(Map.Entry::getKey)
				                .findFirst()
				                .orElse(null);
		System.out.println("First non-Repeatable Character:"+(char)result);

	}

}
