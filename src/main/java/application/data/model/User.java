package application.data.model;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "tbl_user")
public class User {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userid")
    @Id
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "realname")
    private String realname;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "updated_date")
    private Date updatedDate;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

    public User() {
    }

    public User(String username, String password, String realname, Date createdDate, Date updatedDate, String email, String phone) {
        this.username = username;
        this.password = password;
        this.realname = realname;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.email = email;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
