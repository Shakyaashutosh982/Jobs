package com.Ass.jobs.repository;

import com.Ass.jobs.model.Post;
import com.mongodb.ConnectionString;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class SearchRepositoryImplementation  implements SearchRepository{

    @Autowired
    MongoClient client;

    @Autowired
    MongoConverter mongoConverter;


    @Override
    public List<Post> findByText(String text) {

        final List<Post> posts = new ArrayList<>();

        MongoDatabase database = client.getDatabase("joblisting");
        MongoCollection<Document> collection = database.getCollection("jobpost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text",
                                new Document("query", text)
                                        .append("path", Arrays.asList("profile", "desc")))),
                new Document("$sort",
                        new Document("exp", 1L)),
                new Document("$limit", 1L)));

      result.forEach(doc ->posts.add(mongoConverter.read(Post.class,doc)));
        return posts;
    }
}
