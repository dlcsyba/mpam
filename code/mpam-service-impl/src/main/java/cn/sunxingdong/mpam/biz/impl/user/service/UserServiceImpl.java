package cn.sunxingdong.mpam.biz.impl.user.service;

import cn.sunxingdong.mpam.biz.intf.user.dao.IUserDao;
import cn.sunxingdong.mpam.biz.intf.user.model.UserDto;
import cn.sunxingdong.mpam.biz.intf.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/2 14:01
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    IUserDao userDao;

    @Override
    public int addUser(UserDto dto) {
        userDao.insertUser(dto);
        return 0;
    }

    @Override
    public int modUser(UserDto dto) {
        return 0;
    }

    @Override
    public int delUser(UserDto dto) {
        return 0;
    }

    @Override
    public UserDto getUser(Long userId) {
        return null;
    }

    @Override
    public List<UserDto> getUserList() {
        return null;
    }
}
