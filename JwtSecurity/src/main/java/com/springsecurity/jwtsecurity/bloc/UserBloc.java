package com.springsecurity.jwtsecurity.bloc;

import com.springsecurity.jwtsecurity.dto.response.user.UserRes;
import com.springsecurity.jwtsecurity.mapper.UserMapper;
import com.springsecurity.jwtsecurity.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
@Transactional
public class UserBloc {

    private UserService userService;

    public List<UserRes> fetchAllUser() {
        var userList = userService.fetchAllUser();
        return UserMapper.MAPPER.toUserResList(userList);
    }

    public String getCurrentUsername() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;

        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        return username;
    }
}
