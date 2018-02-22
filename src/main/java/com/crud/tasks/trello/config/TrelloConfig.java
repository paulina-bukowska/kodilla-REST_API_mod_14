package com.crud.tasks.trello.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class TrelloConfig {
    @Value("${trello.api.endpoint.prod}")
    private String trelloEndpoint;

    @Value("${trello.app.key}")
    private String trelloKey;

    @Value("${trello.app.token}")
    private String trelloToken;

    @Value("${trello.app.username}")
    private String trelloUsername;
}
