package com.iwonabendig.blackjack.controller;

import com.iwonabendig.blackjack.mapper.GameMapper;
import com.iwonabendig.blackjack.model.dto.GameStateDTO;
import com.iwonabendig.blackjack.model.enums.GameStatus;
import com.iwonabendig.blackjack.service.GameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.iwonabendig.blackjack.model.GameState;

@RestController
@RequestMapping("/api/game")
public class GameController {
    private final GameService gameService;
    private final GameMapper gameMapper;

    public GameController(GameService gameService, GameMapper gameMapper) {
        this.gameService = gameService;
        this.gameMapper = gameMapper;
    }

    @PostMapping("/start")
    public ResponseEntity<GameStateDTO> start() {
        GameState state = gameService.deal();
        boolean hideDealerCard = state.getGameStatus() == GameStatus.PLAYING;
        return ResponseEntity.ok(gameMapper.toDTO(state, hideDealerCard));
    }

    @PostMapping("/hit")
    public ResponseEntity<GameStateDTO> hit() {
        GameState state = gameService.hit();
        boolean hideDealerCard = state.getGameStatus() == GameStatus.PLAYING;
        return ResponseEntity.ok(gameMapper.toDTO(state, hideDealerCard));
    }

    @PostMapping("/stand")
    public ResponseEntity<GameStateDTO> stand() {
        GameState state = gameService.stand();
        return ResponseEntity.ok(gameMapper.toDTO(state, false));
    }
}
