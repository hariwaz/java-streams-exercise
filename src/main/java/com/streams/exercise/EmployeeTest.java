package com.streams.exercise;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.stream.Collectors;

public class EmployeeTest {

	public static void main(String[] args) {
		List<Employee> elist = createListOfEmployees();
		//Calculate the maximum salary of an employee from the list of employees
		OptionalDouble maxSalary = elist.stream().mapToDouble(Employee::getSalary).max();
		System.out.println("Max Salary Emp:"+maxSalary);
		Optional<Employee> maxSalaryEmp = elist.stream().max(Comparator.comparingDouble(Employee::getSalary));
		System.out.println("Max Salary of Emp:"+maxSalaryEmp);
		//Print the employees grouping by department
		Map<String, List<Employee>> empGroupByDeptMap = elist.stream().collect(Collectors.groupingBy(Employee::getDepartment));
		empGroupByDeptMap.entrySet().stream().forEach(e -> System.out.println(e.getKey()+":"+e.getValue()));
		//Print only MALE / FEMALE employees
		List<Employee> malesList = elist.stream().filter(emp -> emp.getGender().compareTo(Sex.MALE) == 0).collect(Collectors.toList());
		System.out.println("Start Of Male Employees:");
		malesList.forEach(System.out::println);
		System.out.println("End Of Male Employees:");
		List<Employee> femalesList = elist.stream().filter(emp -> emp.getGender().compareTo(Sex.FEMALE) == 0).collect(Collectors.toList());
		System.out.println("Start Of Female Employees:");
		femalesList.forEach(System.out::println);
		System.out.println("End Of Female Employees:");
		//Calculate the average age of employees
		Double averageAge = elist.stream().collect(Collectors.averagingInt(Employee::getAge));
		//Double averageAge = elist.stream().mapToInt(Employee::getAge).average().orElse(0.0);
		System.out.println("Average Age Of Employees:"+averageAge);
		//Calculate the average salary
		Double averageSalary = elist.stream().mapToDouble(Employee::getSalary).average().orElse(0.0);
		System.out.println("Average Salary Of Employees:"+averageSalary);
	}

	private static List<Employee> createListOfEmployees() {
		List<Employee> elist = new ArrayList<>();
		for (int i = 1; i <= 5; i++) {
			elist.add(createEmployee(i));
		}
		return elist;
	}

	private static Employee createEmployee(int i) {
		Employee e = new Employee();
		e.setFirstName("A" + i);
		e.setLastName("Z" + i);
		e.setDepartment("Dep" + i);
		e.setBirthday(LocalDate.now());
		e.setSalary((double) i);
		e.setAge(10*i);
		e.setGender(i % 2 == 0 ? Sex.MALE : Sex.FEMALE);
		return e;
	}
}

enum Sex {
	MALE, FEMALE;
}

//The other non-public class inside the same Java file.
class Employee {
	public Employee() {
	}

	private String firstName;
	private String lastName;
	private String department;
	private LocalDate birthday;
	private Double Salary;
	private Sex Gender;
	private int age;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Double getSalary() {
		return Salary;
	}

	public void setSalary(Double salary) {
		Salary = salary;
	}

	public Sex getGender() {
		return Gender;
	}

	public void setGender(Sex gender) {
		Gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Gender, Salary, age, birthday, department, firstName, lastName);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Gender == other.Gender && Objects.equals(Salary, other.Salary) && age == other.age
				&& Objects.equals(birthday, other.birthday) && Objects.equals(department, other.department)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(lastName, other.lastName);
	}

	@Override
	public String toString() {
		return "Employee [firstName=" + firstName + ", lastName=" + lastName + ", department=" + department
				+ ", birthday=" + birthday + ", Salary=" + Salary + ", Gender=" + Gender + ", age=" + age + "]";
	}

}
