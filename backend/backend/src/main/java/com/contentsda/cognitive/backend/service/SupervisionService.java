package com.contentsda.cognitive.backend.service;

import com.contentsda.cognitive.backend.entity.Supervision;
import com.contentsda.cognitive.backend.repository.SupervisionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class SupervisionService implements UserDetailsService {
    @Autowired
    private SupervisionRepository supervisionRepository;

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException{
        Supervision supervision = supervisionRepository.findByLoginId(loginId);
        if (supervision == null){
            throw new UsernameNotFoundException("Supervision not found");
        }
        return new org.springframework.security.core.userdetails.User(supervision.getLoginId(), supervision.getLoginPw(), new ArrayList<>());
    }
}
