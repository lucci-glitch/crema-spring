package com.crema.creamaspring.controllers;

import com.crema.creamaspring.models.Quote;
import com.crema.creamaspring.services.ChatService;
import com.crema.creamaspring.services.QuoteService;
import lombok.extern.log4j.Log4j2;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000") //CORS fix, koppla ihop med react
@Log4j2
@RestController
@RequestMapping("/api")
public class ChatController {

    final QuoteService quoteService;
    final ChatService chatService;

    @Autowired
    public ChatController(QuoteService quoteService, ChatService chatService) {
        this.quoteService = quoteService;
        this.chatService = chatService;
    }

    @GetMapping("/chat")
    public ResponseEntity<String> allQuotes(@RequestParam String response) {
        return new ResponseEntity<>(chatService.chatQuestions(response), HttpStatus.OK);
    }








    // Skall ha in en category

//    @GetMapping("/quotes/{category}/find")
//    public ResponseEntity<Quote>findQuotes(@PathVariable("category") String category, @RequestParam String inputMessage) {
//        Quote quote = chatService.getChatResponse(category, inputMessage);
//
//        return new ResponseEntity<>(quote, HttpStatus.OK);
//    }
}
