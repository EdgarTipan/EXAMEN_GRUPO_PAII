package model;

import com.example.galaga.galaga.entities.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import repository.GameRepository;

@Service
public class GameStateService {

    @Autowired
    private GameRepository gameStateRepository;

    @Transactional
    public void saveGameState(Game gameState) {
        gameStateRepository.save(gameState);
    }

    @Transactional
    public Game loadGameState(Long id) {
        return gameStateRepository.findById(id).orElse(null);
    }
}


