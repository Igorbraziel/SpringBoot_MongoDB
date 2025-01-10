package com.example.demo.resources;

import com.example.demo.domain.Post;
import com.example.demo.resources.util.URL;
import com.example.demo.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {
    @Autowired
    private PostService service;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        Post post = service.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titlesearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text){
        String decodedText = URL.decodeParam(text);
        List<Post> posts = service.findByTitle(decodedText);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "fullsearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDateText,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDateText
    ){
        text = URL.decodeParam(text);
        Instant minDate = URL.convertDate(minDateText, Instant.EPOCH);
        Instant maxDate = URL.convertDate(maxDateText, Instant.now().plus(1, ChronoUnit.DAYS));
        List<Post> posts = service.fullSearch(text, minDate, maxDate);
        return ResponseEntity.ok().body(posts);
    }
}
