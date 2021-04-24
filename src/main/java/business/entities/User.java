package business.entities;

public class User {

    private int id; // just used to demo retrieval of autogen keys in UserMapper
    private String email;
    private String password; // Should be hashed and secured
    private String role;
    private double saldo;

    public User(String email, String password, String role, Double saldo) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.saldo = saldo;
    }

    public User() {
    }

    public User( int id, String email, String password, String role,double saldo){
            this.id = id;
            this.email = email;
            this.password = password;
            this.role = role;
            this.saldo = saldo;

        }

        public double getSaldo () {
            return saldo;
        }

        public void setSaldo ( double saldo){
            this.saldo = saldo;
        }

        public String getEmail ()
        {
            return email;
        }

        public void setEmail (String email)
        {
            this.email = email;
        }

        public String getPassword ()
        {
            return password;
        }

        public void setPassword (String password)
        {
            this.password = password;
        }

        public String getRole ()
        {
            return role;
        }

        public void setRole (String role)
        {
            this.role = role;
        }

        public int getId ()
        {
            return id;
        }

        public void setId ( int id)
        {
            this.id = id;
        }

    }

