package com.dst.lootgenerator.dailymotivation.controller;

import com.dst.lootgenerator.dailymotivation.models.Quote;
import com.dst.lootgenerator.dailymotivation.service.QuoteService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("/")
public class DailyMotivationController {
    private final QuoteService quoteService;

    public DailyMotivationController(QuoteService quoteService) {
        this.quoteService = quoteService;
    }

    @GetMapping(value = "/daily-motivation")
    public String dailyMotivation(Model model) throws IOException {
        Quote randomQuote = quoteService.getRandomQuote();
        String quote = randomQuote.getQuote();
        String author = randomQuote.getAuthor();

        model.addAttribute("quote", quote);
        model.addAttribute("author", author);

        return "daily-motivation";
    }
}
