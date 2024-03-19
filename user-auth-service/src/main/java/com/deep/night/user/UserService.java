package com.deep.night.user;

import com.deep.night.client.BoardClient;
import com.deep.night.config.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    BoardClient BoardClient;

    public Result getBoard(int dnBoardId){
        return BoardClient.getBoardDetail(dnBoardId);
    }
}
