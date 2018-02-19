package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloClient trelloClient;

    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    public Optional<TrelloBoardDto> getTrelloBoards() {
        return Optional.ofNullable(trelloClient.getTrelloBoards())
                .filter(board -> board.contains("Kodilla"))
                .ifPresent(????);
    }

//    public List<TrelloBoardDto> getTrelloBoards() {
//        return trelloClient.getTrelloBoards()
//                .stream()
//                .filter(board -> board.getId() != null)
//                .filter(board -> board.getName() != null)
//                .filter(board -> board.getName().contains("Kodilla"))
//                .collect(Collectors.toList());
//    }
}
