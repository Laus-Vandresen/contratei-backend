package br.com.contratei.controller;

import br.com.contratei.dto.*;
import br.com.contratei.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PutMapping("/{consumerId}")
    public ConsumerUserDto changeConsumerUser(@PathVariable  int consumerId, @RequestBody CoreConsumerDto coreConsumer) {
        return service.changeConsumerUser(consumerId, coreConsumer);
    }

    @GetMapping("find-by-id")
    public ConsumerUserDto findById(@RequestParam int consumerId) {
        return service.findById(consumerId);
    }
}
