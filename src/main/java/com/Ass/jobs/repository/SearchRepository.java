package com.Ass.jobs.repository;

import com.Ass.jobs.model.Post;

import java.util.List;


public interface SearchRepository {

    List<Post> findByText (String text);
}
