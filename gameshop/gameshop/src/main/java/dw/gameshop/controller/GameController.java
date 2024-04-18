package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GameController {
GameService gameService;
    @Autowired
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @GetMapping("/api/games/products")
    public List<Game> getAllGames() {return gameService.getAllGames();};

    @GetMapping("/api/games/products/{id}")
    public Game getGameById(@PathVariable long id) {return gameService.getGameById(id);}

    @PutMapping("/api/games/products/{id}")
    public Game updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return gameService.updateGameById(id , game);
    }
}
