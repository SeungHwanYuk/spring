package dw.gameshop.service;

import dw.gameshop.exception.ResourceNotFoundException;
import dw.gameshop.model.Game;
import dw.gameshop.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.font.GlyphMetrics;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameService {
    GameRepository gameRepository;

    @Autowired
    public GameService(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public List<Game> getAllGames() {
        return gameRepository.findAll();
    }

    public Game getGameById(long id) {
        Optional<Game> games = gameRepository.findById(id);
        if (games.isPresent()) {
            return games.get();
        } else {
            throw new ResourceNotFoundException("Game", "ID", id);
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
            throw new ResourceNotFoundException("Game", "ID", id);
        }
    }

    // 제일 비싼 게임의 정보
    public Game findTopPriceGame() {
        List<Game> games = gameRepository.findAll();
        // 람다식이 아닌 일반 자바코드 사용 예
//        if (games.size() <=0 ) {
//            throw new ResourceNotFoundException("Max Price"," "," ");
//        }
//        Game max = games.get(0);
//        for (int i = 0; i < games.size() - 1; i++) {
//            if (max.getPrice() < games.get(i + 1).getPrice()) {
//                max = games.get(i+1);
//            }
//        }
//        return max;

        // 람다식 사용예
        return games.stream().sorted(Comparator.comparingInt(Game::getPrice).reversed())
                .findFirst()
                // 람다식 예외처리
                .orElseThrow(() -> new ResourceNotFoundException("Max Price", " ", " "));
    }


    // 제일 비싼 게임 Top 3

    public List<Game> findTopPriceGameTop3() {
        List<Game> games = gameRepository.findAll();

//        // 람다식 sort
//        games.sort(Comparator.comparingInt((Game game) -> game.getPrice()).reversed());
//        List<Game> newGames = new ArrayList<>();
//        newGames.add(games.get(0));
//        newGames.add(games.get(1));
//        newGames.add(games.get(2));
//        return newGames;
//    }

        // 람다식 사용 예
//        return games.stream()
//                .sorted(Comparator.comparingInt(Game::getPrice).reversed())
//                .limit(3)
//                .collect(Collectors.toList());
//    }

        // JPQL 사용 예
        return gameRepository.findTopPriceGameTop3()
                .stream()
                .limit(3)
                .collect(Collectors.toList());
    }
}
