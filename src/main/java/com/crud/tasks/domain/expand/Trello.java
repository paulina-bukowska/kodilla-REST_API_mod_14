package com.crud.tasks.domain.expand;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Trello {

    @JsonProperty("board")
    private int board;

    @JsonProperty("card")
    private int card;
}
