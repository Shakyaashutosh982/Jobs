package com.Ass.jobs.repository;

import com.Ass.jobs.model.Post;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostRepository extends MongoRepository<Post, String>
{
}
