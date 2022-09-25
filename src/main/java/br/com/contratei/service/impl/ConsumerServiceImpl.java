package br.com.contratei.service.impl;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.service.CommentService;
import br.com.contratei.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private CommentService commentService;

    @Override
    public void createComment(CommentDto comment) {
        commentService.createComment(comment);
    }
}
