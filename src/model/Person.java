package model;

public class Person implements Comparable<Person>  {
	private int roomNumber;
	private String firstName;
	private String lastName;
	private int startHour;
	private int endHour;

public Person(int roomNumber, String firstName, String lastName, int startHour,int endHour) {
	super();
	this.roomNumber = roomNumber ;
	this.firstName = firstName;
	this.lastName = lastName;
	this.startHour = startHour;
	this.endHour = endHour;
}

public int getRoomNumber() {
	return roomNumber;
}

public void setRoomNumber(int roomNumber) {
	this.roomNumber = roomNumber;
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

public int getStartHour() {
	return startHour;
}

public void setStartHour(int startHour) {
	this.startHour = startHour;
}

public int getEndHour() {
	return endHour;
}

public void setEndHour(int endHour) {
	this.endHour = endHour;
}

@Override
public int compareTo(Person o) {
    return (int) ((this.getEndHour() - this.startHour) - (o.getEndHour()-o.startHour));
	//return (int) ((o.getEndHour()-o.startHour) - (this.getEndHour() - this.startHour));

	}
}
