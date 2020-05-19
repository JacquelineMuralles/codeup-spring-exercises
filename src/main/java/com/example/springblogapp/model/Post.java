package com.example.springblogapp.model;

import javax.persistence.*;

@Entity
@Table(name="posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//auto-increments
    private long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String body;

    // Set up our relationship between the posts and users
    // many posts can belong to one user = @ManyToOne

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Post() {}


    public Post(String title, String body, User user, long id) {
        this.title = title;
        this.body = body;
        this.user = user;
        this.id = id;
    }

    public Post(String title, String body, User user) {
        this.title = title;
        this.body = body;
        this.user = user;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
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
    public void setUser(User user){
        this.user = user;
    }
}
