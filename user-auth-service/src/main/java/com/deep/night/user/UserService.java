package com.deep.night.user;

import com.deep.night.config.response.Result;
import com.deep.night.repository.UserRepository;
import com.deep.night.user.dto.UserDto;
import com.deep.night.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Result<UserDto.Res> getUserDetail(int dnUserId){
        return new Result<>(new UserDto.Res(userRepository.findById(dnUserId)));
    }
}
