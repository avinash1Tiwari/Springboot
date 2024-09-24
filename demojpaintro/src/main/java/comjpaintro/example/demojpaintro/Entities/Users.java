package comjpaintro.example.demojpaintro.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id; // This will be the primary key, and its value will be auto-generated

    private String name;
    private String city;
    private String status;
    @Override
    public String toString() {
        return "Users{id=" + id + ", name='" + name + "', city='" + city + "'}";
    }

}
