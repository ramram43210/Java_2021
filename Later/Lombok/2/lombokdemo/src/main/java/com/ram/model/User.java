package com.ram.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class User
{
	private @NonNull String name;
	private int age;
	private @NonNull String address;

	/**
	 * No Getters and Setters, hashCode, Equal, even though we can
	 * refer it from client code. This is how we are taking help from
	 * Lombok to get rid of boiler plate code.
	 */
	public static void main(String[] args)
	{
		User user1 = new User();
		user1.setName("Peter");
		user1.setAge(45);
		user1.setAddress("15th Cross,Bangalore");

		System.out.println("user1 = "+user1);
		
		User user2 = new UserBuilder().name("John").
				age(25).address("19th Cross,Delhi").build();
		System.out.println("user2 = "+user2);
	}
}
