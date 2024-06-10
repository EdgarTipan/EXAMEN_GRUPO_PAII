package controller;

import com.example.galaga.galaga.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import repository.GameRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/game")
public class GameController {

    @Autowired
    private GameRepository gameRepository;

    // Endpoint para guardar un juego
    @PostMapping("/save")
    public ResponseEntity<Game> saveGame(@RequestBody Game game) {
        try {
            Game savedGame = gameRepository.save(game);
            return new ResponseEntity<>(savedGame, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Endpoint para obtener un juego por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable("id") long id) {
        Optional<Game> gameData = gameRepository.findById(id);

        if (gameData.isPresent()) {
            return new ResponseEntity<>(gameData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Endpoint para obtener todos los juegos
    @GetMapping("/all")
    public ResponseEntity<List<Game>> getAllGames() {
        try {
            List<Game> games = new ArrayList<>(gameRepository.findAll());

            if (games.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(games, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}



