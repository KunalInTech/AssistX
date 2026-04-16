package com.assistx.controller;

import com.assistx.model.Query;
import com.assistx.model.User;
import com.assistx.repository.UserRepository;
import com.assistx.service.AIService;
import com.assistx.service.QueryService;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/query")
@CrossOrigin
public class QueryController {

    private final AIService aiService;
    private final QueryService queryService;
    private final UserRepository userRepository;

    public QueryController(AIService aiService,
                           QueryService queryService,
                           UserRepository userRepository) {
        this.aiService = aiService;
        this.queryService = queryService;
        this.userRepository = userRepository;
    }

    // AI TEST (optional)
    @PostMapping("/classify")
    public Map<String, Object> classify(@RequestBody Map<String, String> body) {
        return aiService.classifyQuery(body.get("text"));
    }

    // CREATE QUERY
    @PostMapping("/create")
    public Query createQuery(@RequestBody Query query, Authentication auth) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        return queryService.createQuery(query, user);
    }

    // GET USER QUERIES
    @GetMapping("/my")
    public List<Query> getMyQueries(Authentication auth) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        return queryService.getUserQueries(user);
    }

    // WITHDRAW QUERY
    @PostMapping("/withdraw/{id}")
    public Query withdraw(@PathVariable Long id, Authentication auth) {

        if (auth == null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Please login first"
            );
        }

        String email = auth.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "User not found"
                ));

        return queryService.withdrawQuery(id, user);
    }
}