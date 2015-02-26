package unit;

import models.Post;
import org.junit.Test;
import play.twirl.api.Content;
import views.html.posts.list;

import java.util.ArrayList;
import java.util.List;

import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;
import static play.test.Helpers.contentType;


public class PostViewTest {

    @Test
    public void renderTemplate() {
        List<Post> posts = new ArrayList<>();
        Content html = list.render(posts);
        assertThat(contentType(html)).isEqualTo("text/html");
        assertThat(contentAsString(html)).contains("Your new application is ready.");
    }

}
