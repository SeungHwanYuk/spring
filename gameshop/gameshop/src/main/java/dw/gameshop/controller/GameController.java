package dw.gameshop.controller;

import dw.gameshop.model.Game;
import dw.gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Game>> getAllGames() {
        return new ResponseEntity<>(gameService.getAllGames(), HttpStatus.OK);};

    @GetMapping("/api/games/products/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable long id) {
        return new ResponseEntity<>(gameService.getGameById(id),HttpStatus.OK);}

    @PutMapping("/api/games/products/{id}")
    public ResponseEntity<Game> updateGameById(@PathVariable long id,
                               @RequestBody Game game) {
        return new ResponseEntity<>(gameService.updateGameById(id , game),HttpStatus.OK);
    }

    @GetMapping("/api/games/top_price")
    public ResponseEntity<Game> findTopPriceGame() {
        return new ResponseEntity<>(gameService.findTopPriceGame(),HttpStatus.OK);
    }

    @GetMapping("api/games/top3price")
    public ResponseEntity<List<Game>> findTopPriceGameTop3() {
        return new ResponseEntity<>(gameService.findTopPriceGameTop3(),HttpStatus.OK);
    }
}
