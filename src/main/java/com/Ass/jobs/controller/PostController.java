package com.Ass.jobs.controller;

import com.Ass.jobs.repository.PostRepository;
import com.Ass.jobs.model.Post;
import com.Ass.jobs.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    SearchRepository srepo;

    @RequestMapping(value = "/")
   public String redirect(HttpServletResponse response) throws IOException {
         return ("welcome");
    }

    @GetMapping("/posts")
    public List<Post> getAllPosts(){
        return repo.findAll();
    }

    @GetMapping("/posts/{text}")
    public List<Post> search(@PathVariable String text)
    {
        return srepo.findByText(text);
    }

    @PostMapping("/post")
    public Post addPost(@RequestBody Post post)
    {
       return repo.save(post);
    }
}
