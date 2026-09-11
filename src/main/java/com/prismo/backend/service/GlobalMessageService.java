package com.prismo.backend.service;

import com.prismo.backend.model.GlobalMessage;
import com.prismo.backend.model.User;
import com.prismo.backend.repository.GlobalMessageRepository;
import com.prismo.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GlobalMessageService {

    @Autowired
    private GlobalMessageRepository repository;

    @Autowired
    private UserRepository userRepository;

    public List<GlobalMessage> getMessagesByProject(Long projectId) {
        return repository.findAllByProjectIdOrderByCreatedAtAsc(projectId);
    }

    public GlobalMessage sendMessage(Long senderId, GlobalMessage message) {
        User sender = userRepository.findById(senderId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        message.setSender(sender);
        return repository.save(message);
    }
}
