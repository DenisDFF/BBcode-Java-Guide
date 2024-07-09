package com.example.DF;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.perm.kefir.bbcode.TextProcessor;

import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private PostJpa postJpa;
    private TextProcessor textProcessor;

    public Controller(PostJpa postJpa, TextProcessor textProcessor) {
        this.postJpa = postJpa;
        this.textProcessor = textProcessor;
    }

    @RequestMapping("/")
    public String test(Model model) {
        List<Post> posts = postJpa.findAll();
        model.addAttribute("posts", posts);
        return "index";
    }

    @RequestMapping("/createPost")
    public String createPost (Model model) {
        model.addAttribute("post", new Post());
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam("name") String name,
                         @RequestParam("description") String description,
                         Model model) {

        String parsDescription = textProcessor.process(description);

        Post post = new Post();
        post.setName(name);
        post.setDescription(parsDescription);

        postJpa.save(post);
        return "redirect:/";
    }
}
