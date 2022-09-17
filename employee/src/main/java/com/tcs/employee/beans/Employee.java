package com.tcs.employee.beans;


import javax.persistence.*;


@Entity
@Table(name = "employees")

public class Employee {
	
	    @Id
	    @Column(name="id")
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    @Column(name = "first_Name")
	    private String firstName;

	    @Column(name = "last_Name")
	    private String lastName;

	    @Column(name = "email")
	    private String email;
	    
	    public Employee() {
			super();
			
		}
		
		public Employee( String firstname, String lastname, String email) {
			super();
			this.firstName = firstname;
			this.lastName= lastname;
			this.email = email;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

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

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		

}
