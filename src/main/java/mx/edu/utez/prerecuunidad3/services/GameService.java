package mx.edu.utez.prerecuunidad3.services;

import mx.edu.utez.prerecuunidad3.controllers.dto.GameDto;
import mx.edu.utez.prerecuunidad3.models.*;
import mx.edu.utez.prerecuunidad3.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class GameService {

    @Autowired
    private GameRepository repository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public APIResponse findAll(){
        return new APIResponse("to bien", repository.findAll(),false, HttpStatus.OK);
    }

    public APIResponse save(GameDto dto){

        Optional<Author> foundAuthor = authorRepository.findById(dto.getAuthor().getId());

        if(foundAuthor.isEmpty())
            return new APIResponse("autor no encontrado", true, HttpStatus.BAD_REQUEST);


        List<Category> categories = new ArrayList<>();

        for (Category category : dto.getCategories()){
            Optional<Category> foundCategory = categoryRepository.findById(category.getId());
            if (foundCategory.isEmpty())
                return new APIResponse("categoria no encontrada", true, HttpStatus.BAD_REQUEST);
            categories.add(category);
        }



        Game game = new Game();



        game.setName(dto.getName());
        game.setDuration(dto.getDuration());
        game.setAuthor(foundAuthor.get());

        if (categories.isEmpty())
            return new APIResponse("agrega al menos una categoria", true, HttpStatus.BAD_REQUEST);
        game.setCategories(categories);


        return new APIResponse("to bien", repository.save(game),false,HttpStatus.CREATED);
    }

    public APIResponse update(GameDto dto) {
        Optional<Game> foundGame = repository.findById(dto.getId());
        if (foundGame.isEmpty())
            return new APIResponse("juego no encontrado", true, HttpStatus.NOT_FOUND);

        Optional<Author> foundAuthor = authorRepository.findById(dto.getAuthor().getId());
        if (foundAuthor.isEmpty())
            return new APIResponse("autor no encontrado", true, HttpStatus.NOT_FOUND);

        List<Category> categories = new ArrayList<>();
        for (Category category : dto.getCategories()) {
            Optional<Category> foundCategory = categoryRepository.findById(category.getId());
            if (foundCategory.isEmpty())
                return new APIResponse("categoria no encontrada", true, HttpStatus.BAD_REQUEST);
            categories.add(foundCategory.get());
        }
        Game game = new Game();
        game.setId(dto.getId());

        game.setName(dto.getName());
        game.setDuration(dto.getDuration());
        game.setAuthor(foundAuthor.get());
        if (categories.isEmpty())
            return new APIResponse("agrega al menos una categoria", true, HttpStatus.BAD_REQUEST);
        game.setCategories(categories);

        return new APIResponse("actualizado correctamente", repository.save(game), false, HttpStatus.OK);
    }


    public APIResponse findOne (Long id){

        Optional<Game> foundGame = repository.findById(id);

        if (foundGame.isEmpty())
            return new APIResponse("Juego no encontrado", true, HttpStatus.NOT_FOUND);
        return new APIResponse("bien", foundGame.get(),false,HttpStatus.OK);

    }

    public APIResponse delete(Long id){
        Optional<Game> foundGame = repository.findById(id);

        if (foundGame.isEmpty())
            return new APIResponse("Juego no encontrado", true, HttpStatus.NOT_FOUND);


        repository.deleteById(id);
        return new APIResponse("eliminado correctamente" ,false, HttpStatus.OK);
    }

}
