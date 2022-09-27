package br.com.contratei.controller;

import br.com.contratei.dto.*;
import br.com.contratei.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumer")
public class ConsumerController {

    @Autowired
    private ConsumerService service;

    @PostMapping("create-comment")
    public void createComment(@RequestBody CommentDto comment) {
        service.createComment(comment);
    }

    @PutMapping
    public ConsumerUserDto changeProviderUser(@PathVariable  int consumerId, @RequestBody CoreConsumerDto coreConsumer) {
        return service.changeProviderUser(consumerId, coreConsumer);
    }
}
