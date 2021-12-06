package com.crema.creamaspring.controllers;
import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.services.ChatService;
import com.crema.creamaspring.services.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") //CORS fix, koppla ihop med react
@RestController
@RequestMapping("/api")
public class QuoteController {

    final QuoteService quoteService;
    final ChatService chatService;

    @Autowired
    public QuoteController(QuoteService quoteService, ChatService chatService) {
        this.quoteService = quoteService;
        this.chatService = chatService;
    }

    @GetMapping("/quotes")
    public ResponseEntity<List<Quote>> allQuotes() {
        List<Quote> quotes = quoteService.getAll();
        if (quotes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(quotes, HttpStatus.OK);
    }

    @GetMapping("/quotes/find")
    public ResponseEntity<Quote>findQuotes(@RequestParam String inputMessage) {
          Quote quote = chatService.getChatResponse(inputMessage);

        return new ResponseEntity<>(quote, HttpStatus.OK);
    }


}
