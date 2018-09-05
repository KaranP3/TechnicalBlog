package blog.services;

import blog.common.PostsManager;
import blog.model.Post;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class PostServiceImp implements PostService {

    private List<Post> posts = new ArrayList<Post>();

    private PostsManager postsManager;

    public PostServiceImp() {
        postsManager= new PostsManager();
    }


    @Override
    public Post create(Post post) {
        postsManager.writeToFile(post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return postsManager.readAllPosts();
    }

    @Override
    public List<Post> firstThreePosts() {
        return posts.subList(0, 3);
    }

    @Override
    public Post findbyTitle(String title) {
        return postsManager.getPost(title);
    }

    @Override
    public Post editPost(Post post) {
        return null;
    }

    @Override
    public void deleteById(Post post) {
        postsManager.deletePost(post.getTitle());
    }
}
