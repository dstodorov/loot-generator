package com.dst.lootgenerator.dailymotivation.controller;

import com.dst.lootgenerator.dailymotivation.models.Quote;
import com.dst.lootgenerator.dailymotivation.service.QuoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/quote")
@RequiredArgsConstructor
public class QuoteController {

    private final QuoteService quoteService;

    @GetMapping
    public ResponseEntity<Quote> getQuote() {
        return ResponseEntity.ok(quoteService.getRandomQuote());
    }

    @PostMapping
    public ResponseEntity<Quote> addQuote(@RequestBody Quote quote) {

        Quote newQuote = this.quoteService.add(quote);

        return ResponseEntity.ok(newQuote);
    }
}
