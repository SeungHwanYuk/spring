package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GameService {
    GameRepository gameRepository;
    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {return gameRepository.findAll();}

    public Game getGameById(long id) {
        Optional<Game> games = gameRepository.findById(id);
        if (games.isPresent()) {
            return games.get();
        }else {
            throw new ResourceNotFoundException("Game","ID",id);
        }
    }
    public Game updateGameById(long id, Game game) {
        Optional<Game> game1 = gameRepository.findById(id);
        if (game1.isPresent()) {
            game1.get().setTitle(game.getTitle());
            game1.get().setGenre(game.getGenre());
            game1.get().setPrice(game.getPrice());
            game1.get().setImage(game.getImage());
            game1.get().setText(game.getText());
            gameRepository.save(game1.get());
            return game1.get();
        } else {
            throw new ResourceNotFoundException("Game","ID",id);
        }
    }
}
