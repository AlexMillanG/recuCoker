package mx.edu.utez.prerecuunidad3.controllers.dto;

import jakarta.validation.constraints.*;
import mx.edu.utez.prerecuunidad3.models.Author;
import mx.edu.utez.prerecuunidad3.models.Category;

import java.util.List;

public class GameDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 255, message = "El nombre no puede superar los 255 caracteres")
    private String name;

    @NotNull(message = "La duración es obligatoria")
    @Min(value = 1, message = "La duración debe ser al menos 1 minuto")
    @Max(value = 1000, message = "La duración no puede superar los 1000 minutos")
    private Integer duration;

    @NotNull(message = "El autor es obligatorio")
    private Author author;

    @NotNull(message = "La categoría es obligatoria")
    private List<Category> categories;

    public GameDto(Long id, String name, Integer duration, Author author, List<Category> categories) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.author = author;
        this.categories = categories;
    }

    public GameDto() {
    }

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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
