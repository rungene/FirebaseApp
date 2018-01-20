package net.simplifiedcoding.FirebaseApp;

/**
 * Created by SAM on 9/20/2017.
 */



public class Upload {
    public String avata;
    public String email;


    // Default constructor required for calls to
    // DataSnapshot.getValue(User.class)
    public Upload() {
    }

    public Upload(String avata, String email) {
        this.email = email;
        this.avata= avata;
    }


    public String getAvata() {
        return avata;
    }
    public String getEmail() {
        return email;
    }
}






/*
public class ModelClass {

    public String avanta;
    public String first_name;

    public ModelClass(String avanta, String first_name) {
        this.avanta = avanta;
        this.first_name = first_name;
    }

    public ModelClass() {
    }

    public String getAvanta() {
        return avanta;
    }

    public void setAvanta(String avanta) {
        this.avanta = avanta;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }
}*/
