package MVC.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "expiration", schema = "registro")
public class ExpirationEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "date_expiration", nullable = false, length = -1)
    private String dateExpiration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public ExpirationEntity(int id, String dateExpiration) {
        this.id = id;
        this.dateExpiration = dateExpiration;
    }

    public ExpirationEntity(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public ExpirationEntity() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExpirationEntity)) return false;
        ExpirationEntity that = (ExpirationEntity) o;
        return getId() == that.getId() && getDateExpiration().equals(that.getDateExpiration());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getDateExpiration());
    }


}
