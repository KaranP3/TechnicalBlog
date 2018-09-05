package blog.services;

import blog.model.Post;

import java.util.List;

public interface PostService {

    Post create(Post post);
    List<Post> findAll();
    List<Post> firstThreePosts();
    Post findbyTitle(String title);
    Post editPost(Post post);
    void deleteById(Post post);
}
