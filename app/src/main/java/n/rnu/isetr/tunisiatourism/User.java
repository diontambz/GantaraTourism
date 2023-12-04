package n.rnu.isetr.tunisiatourism;

public class User {
    public String fullName,Email;

    public User(){

    }

    public User(String fullName){
        this.fullName=fullName;
    }

    public User(String fullName,String email){
        this.fullName=fullName;
        this.Email=email;

    }
}
