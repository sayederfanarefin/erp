package com.sweetitech.sweeterp.service.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;
import com.sweetitech.sweeterp.model.dto.UserDto;

import com.sweetitech.sweeterp.exception.UserAlreadyExistException;
import com.sweetitech.sweeterp.model.user.User;

public interface IUserService {

    User registerNewUserAccount(UserDto accountDto) throws UserAlreadyExistException;


    void saveRegisteredUser(User user);

    User findUserByEmail(String email);

    User getUserByID(long id);


    boolean checkIfValidOldPassword(User user, String password);

    public UserDto findUserProfileDtoByEmail(final String email);
    public Page<User> findAllUser(int page) ;
  
    List<UserDto> search(String searchString);
}
