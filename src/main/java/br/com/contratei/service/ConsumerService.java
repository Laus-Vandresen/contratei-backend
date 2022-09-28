package br.com.contratei.service;

import br.com.contratei.dto.CommentDto;
import br.com.contratei.dto.ConsumerUserDto;
import br.com.contratei.dto.CoreConsumerDto;

public interface ConsumerService {

    void createComment(CommentDto comment);

    ConsumerUserDto changeConsumerUser(int consumerId, CoreConsumerDto coreConsumer);
}
