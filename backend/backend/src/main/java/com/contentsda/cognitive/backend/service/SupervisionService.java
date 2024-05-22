package com.contentsda.cognitive.backend.service;

import com.contentsda.cognitive.backend.entity.Supervision;
import com.contentsda.cognitive.backend.repository.SupervisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;

@Service
public class SupervisionService implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(SupervisionService.class);

    @Autowired
    private SupervisionRepository supervisionRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        logger.info("Attempting to load user by username: {}", loginId); // 추가된 로그
        Supervision supervision = supervisionRepository.findByLoginId(loginId);
        if (supervision == null) {
            logger.warn("User not found: {}", loginId); // 추가된 로그
            throw new UsernameNotFoundException("Supervision not found");
        }
        logger.info("User found: {}", loginId); // 추가된 로그
        return new org.springframework.security.core.userdetails.User(supervision.getLoginId(), supervision.getLoginPw(), new ArrayList<>());
    }
}
