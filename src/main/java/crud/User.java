package crud;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by 1 on 11.03.2017.
 */
@Entity
@Table(name = "user", schema = "test", catalog = "")
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private boolean isAdmin;
    private Timestamp createdDate;

    public User() {
        this.name = "";
        this.age = 0;
        this.isAdmin = false;
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
        this.isAdmin = false;
        this.createdDate = new Timestamp(System.currentTimeMillis());
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", length = 25)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "age")
    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Basic
    @Column(name = "isAdmin")
    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Basic
    @Column(name = "createdDate")
    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User that = (User) o;

        if (id != that.id) return false;
        if (isAdmin != that.isAdmin) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (age != null ? !age.equals(that.age) : that.age != null) return false;
        if (createdDate != null ? !createdDate.equals(that.createdDate) : that.createdDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (age != null ? age.hashCode() : 0);
        result = 31 * result + (createdDate != null ? createdDate.hashCode() : 0);
        result = 31 * result + (isAdmin ? 1 : 0);
        return result;
    }

}
