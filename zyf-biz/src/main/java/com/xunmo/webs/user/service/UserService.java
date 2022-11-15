package com.xunmo.webs.user.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xunmo.common.base.XmService;
import com.xunmo.common.base.XmServiceImpl;
import com.xunmo.webs.user.dto.UserGetPageDTO;
import com.xunmo.webs.user.dto.UserUpdateDTO;
import com.xunmo.webs.user.entity.User;
import com.xunmo.webs.user.mapper.UserMapper;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;

import java.util.List;

/**
* Service: t_user -- 用户基本信息表
* @author zengyufei
*/
@Service
public class UserService extends XmServiceImpl<UserMapper, User> implements XmService<User> {

    /**
    * 获取列表
    *
    * @param userGetPageDTO user获取页面dto
    * @return {@link List}<{@link User}>
    */
    public List<User> getList(UserGetPageDTO userGetPageDTO) {
        final User user = userGetPageDTO.toEntity();
        return startPage(() -> this.baseMapper.selectList(Wrappers.lambdaQuery(user)));
    }

    /**
    * 添加
    *
    * @param user user
    * @return {@link User}
    */
    @Tran
    public User add(User user) throws Exception {
        this.baseMapper.insert(user);
        return user;
    }

    /**
    * 删除
    *
    * @param id id
    * @return boolean
    */
    @Tran
    public boolean del(String id) {
        this.baseMapper.deleteById(id);
        return true;
    }

    /**
    * 更新
    *
    * @param userUpdateDTO user更新dto
    * @return boolean
    */
    @Tran
    public boolean updateBean(UserUpdateDTO userUpdateDTO) throws Exception {
        checkAndGet(userUpdateDTO.getId());
        final User inputUser = toBean(userUpdateDTO, User.class);
        this.baseMapper.updateNotNullById(inputUser);
        return true;
    }


}
