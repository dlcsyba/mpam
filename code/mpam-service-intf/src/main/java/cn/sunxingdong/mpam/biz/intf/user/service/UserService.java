package cn.sunxingdong.mpam.biz.intf.user.service;

import cn.sunxingdong.mpam.biz.intf.user.model.UserDto;

import java.util.List;

public interface UserService {

    public int addUser(UserDto dto);

    public int modUser(UserDto dto);

    public int delUser(UserDto dto);

    public UserDto getUser(Long userId);

    public List<UserDto> getUserList();
}
