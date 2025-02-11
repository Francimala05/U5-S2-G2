package com.example.SpringData.controller;

import com.example.SpringData.entities.BlogPost;
import com.example.SpringData.repositories.BlogPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostRepository blogPostRepository;

    // Creare un nuovo blog post
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost createBlogPost(@RequestBody BlogPost blogPost) {
        return blogPostRepository.save(blogPost);
    }

    // Ottenere tutti i blog post
    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepository.findAll();
    }

    // Ottenere un blog post per ID
    @GetMapping("/{id}")
    public Optional<BlogPost> getBlogPostById(@PathVariable Long id) {
        return blogPostRepository.findById(id);
    }

    // Aggiornare un blog post
    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable Long id, @RequestBody BlogPost blogPost) {
        if (blogPostRepository.existsById(id)) {
            blogPost.setId(id);
            return blogPostRepository.save(blogPost);
        }
        return null;
    }
    // Cancellare un blog post
    @DeleteMapping("/{id}")
    public void deleteBlogPost(@PathVariable Long id) {
        blogPostRepository.deleteById(id);
    }

}
