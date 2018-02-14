package cn.sunxingdong.mpam.biz.intf.user.dao;

import cn.sunxingdong.mpam.biz.intf.user.model.UserDto;

/**
 * Author: sun.xingdong
 * CreateDate: 2018/2/13 11:17
 */
public interface IUserDao {
    /**
     *
     * @return UserDto
     */
    public UserDto selectUserById();

    /**
     *
     * @param dto
     * @return
     */
    public int insertUser(UserDto dto);


}
