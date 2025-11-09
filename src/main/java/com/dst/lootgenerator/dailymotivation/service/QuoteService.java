package com.dst.lootgenerator.dailymotivation.service;

import com.dst.lootgenerator.dailymotivation.models.Quote;
import com.dst.lootgenerator.dailymotivation.repository.QuoteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public List<Quote> findAll() {
        return quoteRepository.findAll();
    }

    public Quote getRandomQuote() {
        return quoteRepository.findAll().get((int) (Math.random() * quoteRepository.findAll().size()));
    }

    public Quote add(Quote quote) {
        return this.quoteRepository.save(quote);
    }
}
