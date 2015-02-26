package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import play.data.validation.Constraints;

public class Post {

    private static Map<String, Post> posts;

    static {
        posts = new TreeMap<>();
        posts.put("1111111111111", new Post("1111111111111", "Paperclips 1", "Paperclips description 1"));
        posts.put("2222222222222", new Post("2222222222222", "Paperclips 2", "Paperclips description "));
        posts.put("3333333333333", new Post("3333333333333", "Paperclips 3", "Paperclips description 3"));
        posts.put("4444444444444", new Post("4444444444444", "Paperclips 4", "Paperclips description 4"));
        posts.put("5555555555555", new Post("5555555555555", "Paperclips 5", "Paperclips description 5"));
    }

    @Constraints.Required
    public String ean;

    @Constraints.Required
    public String title;

    public String text;

    public Post() {
    }

    public Post(String ean, String title, String text) {
        this.ean = ean;
        this.title = title;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Post{" + "ean='" + ean + '\'' + ", title='" + title + '\'' + ", text='" + text + '\'' + '}';
    }

    public static List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    public static Post findByEan(String ean) {
        return posts.get(ean);
    }

    public static List<Post> findByName(String term) {
        final List<Post> results = new ArrayList<>();
        for (Post candidate : findAll()) {
            if (candidate.title.toLowerCase().contains(term.toLowerCase())) {
                results.add(candidate);
            }
        }

        return results;
    }

    public static boolean remove(Post product) {

        return posts.remove(product.ean) != null;
    }

    public void save() {
        posts.remove(this.ean);
        posts.put(this.ean, this);
    }
}
