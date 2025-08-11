package mx.edu.utez.prerecuunidad3.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import mx.edu.utez.prerecuunidad3.controllers.dto.DeleteDto;
import mx.edu.utez.prerecuunidad3.controllers.dto.GameDto;
import mx.edu.utez.prerecuunidad3.services.GameService;
import mx.edu.utez.prerecuunidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/games")
public class GameController {

    @Autowired
    private GameService service;

    @Operation(summary = "Obtener todos los juegos", description = "Devuelve una lista de todos los juegos registrados en el sistema.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista obtenida correctamente",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Juegos obtenidos correctamente\", \"error\": false, \"status\": \"OK\", \"data\": [] }"))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Error al obtener juegos\", \"error\": true, \"status\": \"INTERNAL_SERVER_ERROR\" }")))
    })
    @GetMapping
    public ResponseEntity<APIResponse> findAll() {
        APIResponse response = service.findAll();
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(summary = "Registrar un nuevo juego", description = "Agrega un nuevo juego con su autor y categoría.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Juego guardado correctamente",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Juego guardado\", \"error\": false, \"status\": \"OK\" }"))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Error al guardar juego\", \"error\": true, \"status\": \"INTERNAL_SERVER_ERROR\" }")))
    })
    @PostMapping
    public ResponseEntity<APIResponse> save(@RequestBody GameDto gameDto) {
        APIResponse response = service.save(gameDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(summary = "Actualizar un juego", description = "Modifica los datos de un juego existente.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Juego actualizado correctamente",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Juego actualizado\", \"error\": false, \"status\": \"OK\" }"))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Error al actualizar juego\", \"error\": true, \"status\": \"INTERNAL_SERVER_ERROR\" }")))
    })
    @PutMapping
    public ResponseEntity<APIResponse> update(@RequestBody GameDto gameDto) {
        APIResponse response = service.update(gameDto);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(summary = "Obtener un juego por ID", description = "Devuelve la información de un juego específico.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Juego encontrado",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Juego encontrado\", \"error\": false, \"status\": \"OK\", \"data\": { \"id\": 1, \"name\": \"Ajedrez\" } }"))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Error al buscar juego\", \"error\": true, \"status\": \"INTERNAL_SERVER_ERROR\" }")))
    })
    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> findOne(@PathVariable Long id) {
        APIResponse response = service.findOne(id);
        return new ResponseEntity<>(response, response.getStatus());
    }

    @Operation(summary = "Eliminar un juego", description = "Borra un juego de la base de datos.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Juego eliminado correctamente",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Juego eliminado\", \"error\": false, \"status\": \"OK\" }"))),
            @ApiResponse(responseCode = "500", description = "Error interno del servidor",
                    content = @Content(examples = @ExampleObject(value = "{ \"message\": \"Error al eliminar juego\", \"error\": true, \"status\": \"INTERNAL_SERVER_ERROR\" }")))
    })
    @DeleteMapping
    public ResponseEntity<APIResponse> delete(@RequestBody DeleteDto dto) {
        APIResponse response = service.delete(dto.getId());
        return new ResponseEntity<>(response, response.getStatus());
    }
}
