package pkg1;

/**
 * Person class to create ticket objects.
 */
public class Person {
    private String name;
    private String surname;
    private String email;

    /**
     *Person constructor to create ticket objects.
     */
    public Person(String name,String surname,String email){
        this.name=name;
        this.surname=surname;
        this.email=email;
    }

    /**
     *Method to set the name of the person.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     *Method to set the surname of the person.
     */
    public void setSurname(String surname){
        this.surname = surname;
    }

    /**
     *Method to set the email of the person.
     */
    public void setEmail(String email){
        this.email = email;
    }

    /**
     *Method to get the name of the person.
     */
    public String getName(){
        return name;
    }

    /**
     *Method to get the surname of the person.
     */
    public String getSurname(){
        return surname;
    }

    /**
     *Method to get the email of the person.
     */
    public String getEmail(){
        return email;
    }

    /**
     *Method to get the person.
     */
    void getPerson(){
        System.out.println("Name: "+name);
        System.out.println("Surname: "+surname);
        System.out.println("Email: "+email);
    }
}
