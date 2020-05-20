//package com.codeup.springblogapp;
//
//import com.codeup.springblogapp.models.Post;
//import com.codeup.springblogapp.models.User;
//import com.codeup.springblogapp.repositories.PostRepository;
//import com.codeup.springblogapp.repositories.UserRepository;
//import com.example.springblogapp.SpringBlogAppApplication;
//import com.example.springblogapp.model.Post;
//import com.example.springblogapp.model.User;
//import com.example.springblogapp.repositories.PostRepository;
//import com.example.springblogapp.repositories.UserRepository;
//import org.aspectj.lang.annotation.Before;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.HttpStatus;
//import org.springframework.mock.web.MockHttpSession;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.test.web.servlet.MockMvc;
//
//import javax.servlet.http.HttpSession;
//
//import static org.hamcrest.Matchers.containsString;
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = SpringBlogAppApplication.class)
//@AutoConfigureMockMvc
//public class PostsIntegrationTests {
//
//    private User testUser;
//    private HttpSession httpSession;
//
//    @Autowired
//    private MockMvc mvc;
//
//    @Autowired
//    UserRepository userDao;
//
//    @Autowired
//    PostRepository postsDao;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Before
//    public void setup() throws Exception {
//
//        testUser = userDao.findByUsername("testUser");
//
//        // Creates the test user if not exists
//        if(testUser == null){
//            User newUser = new User();
//            newUser.setUsername("testUser");
//            newUser.setPassword(passwordEncoder.encode("pass"));
//            newUser.setEmail("testUser@codeup.com");
//            testUser = userDao.save(newUser);
//        }
//
//        // Throws a Post request to /login and expect a redirection to the Ads index page after being logged in
//        httpSession = this.mvc.perform(post("/login").with(csrf())
//                .param("username", "testUser")
//                .param("password", "pass"))
//                .andExpect(status().is(HttpStatus.FOUND.value()))
//                .andExpect(redirectedUrl("/posts"))
//                .andReturn()
//                .getRequest()
//                .getSession();
//    }
//
//    @Test
//    public void testCreateAd() throws Exception {
//        // Makes a Post request to /ads/create and expect a redirection to the Ad
//        this.mvc.perform(
//                post("/posts/create").with(csrf())
//                        .session((MockHttpSession) httpSession)
//                        // Add all the required parameters to your request like this
//                        .param("title", "test")
//                        .param("body", "this is a test body"))
//                .andExpect(status().is3xxRedirection());
//    }
//
//    @Test
//    public void testShowAd() throws Exception {
//
//        Post existingPost = postsDao.findAll().get(0);
//
//        // Makes a Get request to /post/show/{id} and expect a redirection to the Post show page
//        this.mvc.perform(get("/post/show/" + existingPost.getId()))
//                .andExpect(status().isOk())
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString(existingPost.getBody())));
//    }
//
//    @Test
//    public void testAdsIndex() throws Exception {
//        Post existingPost = postsDao.findAll().get(0);
//
//        // Makes a Get request to /posts and verifies that we get some of the static text of the posts/index.html template and at least the title from the first Post is present in the template.
//        this.mvc.perform(get("/posts"))
//                .andExpect(status().isOk())
//                // Test the static content of the page
//                .andExpect(content().string(containsString("View All Posts")))
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString(existingPost.getTitle())));
//    }
//
//    @Test
//    public void testEditAd() throws Exception {
//        // Gets the first Post for tests purposes
//        Post existingPost = postsDao.findAll().get(0);
//
//        // Makes a Post request to /post/update/{id} and expect a redirection to the Post show page
//        this.mvc.perform(
//                post("/post/update/" + existingPost.getId()).with(csrf())
//                        .session((MockHttpSession) httpSession)
//                        .param("title", "edited title")
//                        .param("body", "edited body"))
//                .andExpect(status().is3xxRedirection());
//
//        // Makes a GET request to /post/show/{id} and expect a redirection to the Post show page
//        this.mvc.perform(get("/post/show/" + existingPost.getId()))
//                .andExpect(status().isOk())
//                // Test the dynamic content of the page
//                .andExpect(content().string(containsString("edited title")))
//                .andExpect(content().string(containsString("edited body")));
//    }
//
//    @Test
//    public void testDeleteAd() throws Exception {
//        // Creates a test Post to be deleted
//        this.mvc.perform(
//                post("/posts/create").with(csrf())
//                        .session((MockHttpSession) httpSession)
//                        .param("title", "post to be deleted")
//                        .param("body", "won't last long"))
//                .andExpect(status().is3xxRedirection());
//
//        // Get the recent Ad that matches the title
//        Post existingPost = postsDao.findByTitle("post to be deleted");
//
//        // Makes a Post request to /post/delete/{id} and expect a redirection to the Posts index
//        this.mvc.perform(
//                post("/post/delete/" + existingPost.getId()).with(csrf())
//                        .session((MockHttpSession) httpSession)
//                        .param("id", String.valueOf(existingPost.getId())))
//                .andExpect(status().is3xxRedirection());
//    }
//}
