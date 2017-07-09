package model;

/**
 * Created by KinshukBasu on 04-Jul-17.
 */
public class User {

    private String id;
    private String name;
    private String email;

    public User(String id, String name, String email){
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public User(){
        this.id = null;
        this.name = null;
        this.email = null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
