package br.com.contratei.controller;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping("create-comment")
    public void createComment(@RequestBody CommentDto comment) {
        service.createComment(comment);
    }
}
