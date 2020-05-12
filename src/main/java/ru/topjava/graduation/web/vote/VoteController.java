package ru.topjava.graduation.web.vote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.topjava.graduation.model.User;
import ru.topjava.graduation.model.Vote;
import ru.topjava.graduation.service.VoteService;
import ru.topjava.graduation.web.Controller;

import java.net.URI;

import static ru.topjava.graduation.web.Controller.JSON_TYPE;
import static ru.topjava.graduation.web.Controller.VOTES_URL;

@RestController
@RequestMapping(value = VOTES_URL, produces = JSON_TYPE)
public class VoteController implements Controller {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private VoteService voteService;

    @GetMapping
    public Integer getVotesCount(@PathVariable Integer restaurantId) {
        log.info("get count of the votes for restaurant {}", restaurantId);
        return voteService.getVotesCount(restaurantId);
    }

    @PostMapping(consumes = JSON_TYPE)
    public ResponseEntity<Vote> create(@AuthenticationPrincipal User user,
                                       @PathVariable Integer restaurantId) {
        Vote created = voteService.create(user, restaurantId);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(VOTES_URL + "/{id}")
                .buildAndExpand(restaurantId, created.getId()).toUri();
        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}