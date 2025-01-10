package com.example.demo.services;

import com.example.demo.domain.Post;
import com.example.demo.repositories.PostRepository;
import com.example.demo.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {
    @Autowired
    private PostRepository repository;

    public Post findById(String id){
        Optional<Post> optionalPost = repository.findById(id);
        return optionalPost.orElseThrow(() -> new ObjectNotFoundException("Post Not Found"));
    }

    public List<Post> findByTitle(String text){
        return repository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Instant minDate, Instant maxDate){
        return repository.fullSearch(text, minDate, maxDate);
    }
}
