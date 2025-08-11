package mx.edu.utez.prerecuunidad3.controllers.dto;

public class DeleteDto {

    private Long id;

    public DeleteDto(Long id) {
        this.id = id;
    }

    public DeleteDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
