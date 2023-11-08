package com.whiskersandwonders.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "reviews")
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(columnDefinition = "VARCHAR(100)")
    private String body;
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "fosterPet_id", nullable = false)
    private Pet fosterPet;

    public Review() {

    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    @JsonIgnore
    public Pet getPet() {
        return fosterPet;
    }
    public void setPet(Pet fosterPet) {
        this.fosterPet = fosterPet;
    }
    public Pet getFosterPet() {
        return fosterPet;
    }

    public void setFosterPet(Pet fosterPet) {
        this.fosterPet = fosterPet;
    }
}
