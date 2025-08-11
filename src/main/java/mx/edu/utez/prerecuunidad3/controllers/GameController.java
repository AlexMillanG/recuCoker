package mx.edu.utez.prerecuunidad3.controllers;

import io.swagger.v3.oas.models.responses.ApiResponse;
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

    @GetMapping
    public ResponseEntity<APIResponse> findAll(){
        APIResponse response = service.findAll();
        return new ResponseEntity<>(response,response.getStatus());
    }

    @PostMapping
    public ResponseEntity<APIResponse> save (@RequestBody GameDto gameDto){
        APIResponse response = service.save(gameDto);

        return new ResponseEntity<>(response,response.getStatus());
    }

    @PutMapping
    ResponseEntity<APIResponse> update (@RequestBody GameDto gameDto){
        APIResponse response = service.update(gameDto);

        return new ResponseEntity<>(response, response.getStatus());
    }

    @GetMapping("/{id}")

    ResponseEntity<APIResponse> findOne (@PathVariable Long id) {
        APIResponse response = service.findOne(id);

        return new ResponseEntity<>(response,response.getStatus());
    }

    @DeleteMapping
    ResponseEntity<APIResponse> delete (@RequestBody DeleteDto dto){
        APIResponse response = service.delete(dto.getId());
        return  new ResponseEntity<>(response,response.getStatus());
    }

}
