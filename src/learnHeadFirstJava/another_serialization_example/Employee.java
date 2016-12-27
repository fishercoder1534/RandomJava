package learnHeadFirstJava.another_serialization_example;

import java.io.Serializable;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

//Any one of the following three annotations: @Service, @Repository and @Component will work fine here since I'm doing a component-scan.
//@Component
//@Repository
@Service
@RequiredArgsConstructor
public class Employee implements Serializable {
	private String address;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public int getSSN() {
		return SSN;
	}

	public void setSSN(int SSN) {
		this.SSN = SSN;
	}

	private String name;
	private int age;
	private double salary;
	private transient int SSN;// any field that cannot be serialized needs to be
								// marked as transient, for demo purpose, we
								// just set SSN as a non-serializable field, it
								// doesn't mean anything, it's just that we
								// don't want to serialize it.

	public void mailCheck() {
		System.out.println("Mailing a check to " + name + " at " + address);
	}

	@Override
	public String toString() {//this toString() method doesn't contain SSN field either.
		return "Employee [address=" + address + ", name=" + name + ", age="
				+ age + ", salary=" + salary + "]";
	}

}
