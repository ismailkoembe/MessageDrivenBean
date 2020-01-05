package de.ikoembe.slotMachine;


public class User {
    
    private int id;
    private String firstName;
    private String lastName;
    private int credit;
   
    
    public User(int ID, String FirstName, String LastName, int Credit)
    {
        this.id = ID;
        this.firstName = FirstName;
        this.lastName = LastName;
        this.credit = Credit;
     
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }
    

}
