package cn.sunxingdong.mpam.biz.impl.user.dao.jdbc;

import cn.sunxingdong.mpam.biz.intf.user.dao.IUserDao;
import cn.sunxingdong.mpam.biz.intf.user.model.UserDto;
import org.springframework.stereotype.Repository;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 13:25
 */
@Repository
public class UserDao implements IUserDao {
    @Override
    public UserDto selectUserById() {
        return null;
    }

    @Override
    public int insertUser(UserDto dto) {
        return 0;
    }
}
