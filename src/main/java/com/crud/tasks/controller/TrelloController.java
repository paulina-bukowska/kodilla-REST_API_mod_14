package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCardDto;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import com.crud.tasks.trello.client.TrelloClient;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/trello")
public class TrelloController {

    @Autowired
    private TrelloFacade trelloFacade;

    @RequestMapping(method = RequestMethod.GET, value = "/getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTrelloCard")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }



//    //na potrzeby zad. 18
//    @Autowired
//    private TrelloClient trelloClient;
//
//    //zad. 18.2 podpunkt 3.
//    public List<TrelloBoardDto> getTrelloKodillaBoards() {
//        return trelloClient.getTrelloBoards()
//                .stream()
//                .filter(board -> board.getId() != null)
//                .filter(board -> board.getName() != null)
//                .filter(board -> board.getName().contains("Kodilla"))
//                .collect(Collectors.toList());
//    }
//
//    //zad. 18.2 podpunkt 4.
//    public Optional<List<TrelloBoardDto>> getTrelloKodillaBoardsUsingOptional() {
//        List<TrelloBoardDto> boards = trelloClient.getTrelloBoards()
//                .stream()
//                .filter(board -> board.getName().contains("Kodilla"))
//                .collect(Collectors.toList());
//        return Optional.of(boards);
//    }
}
