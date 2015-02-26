package controllers;

import models.Post;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.posts.details;
import views.html.posts.list;

import java.util.List;

public class Posts extends Controller {

    private static final Form<Post> postForm = Form.form(Post.class);

    public static Result list() {
        List<Post> posts = Post.findAll();
        return ok(list.render(posts));
    }

    public static Result newPost() {
        return ok(details.render(postForm));
    }

    public static Result details(String ean) {
        final Post product = Post.findByEan(ean);
        if (product == null) {
            return notFound(String.format("Post %s does not exist.", ean));
        }

        Form<Post> filledForm = postForm.fill(product);
        return ok(details.render(filledForm));
    }

    public static Result save() {
        Form<Post> boundForm = postForm.bindFromRequest();
        if (boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(details.render(boundForm));
        }

        Post post = boundForm.get();
        post.save();

        flash("success", "Successfully added new post");
        return redirect(routes.Posts.list());
    }

    public static Result delete(String ean) {
        final Post product = Post.findByEan(ean);
        if(product == null) {
            return notFound(String.format("Post %s does not exists.", ean));
        }
        Post.remove(product);
        return redirect(routes.Posts.list());
    }

}
