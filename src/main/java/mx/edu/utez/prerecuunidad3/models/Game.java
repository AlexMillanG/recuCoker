package mx.edu.utez.prerecuunidad3.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "game")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,length = 45)
    private String name;

    @Column(nullable = false)
    private Integer duration;

    public Game(Long id, String name, Integer duration, List<Category> categories, Author author) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.categories = categories;
        this.author = author;
    }

    public Game() {
    }


    @ManyToMany
    @JoinTable(
            name = "game_has_categories",
            joinColumns = @JoinColumn(
                    name = "game_id",
                    nullable = false
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "category_id",
                    nullable = false
            )
    )

    private List<Category> categories;





    @ManyToOne
    @JoinColumn(name = "author_id", nullable = false)

    private Author author;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }
}
