package com.xunmo.webs.user.controller;

import com.xunmo.common.base.XmController;
import com.xunmo.utils.AjaxJson;
import com.xunmo.webs.user.dto.UserDelDTO;
import com.xunmo.webs.user.dto.UserGetDTO;
import com.xunmo.webs.user.dto.UserGetPageDTO;
import com.xunmo.webs.user.dto.UserSaveDTO;
import com.xunmo.webs.user.dto.UserUpdateDTO;
import com.xunmo.webs.user.entity.User;
import com.xunmo.webs.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.validation.annotation.Valid;
import org.noear.solon.validation.annotation.Validated;

@Slf4j
@Valid
@Controller
@Mapping("/user")
public class UserController extends XmController<UserService> implements IUserController {

    @Override
    public AjaxJson get(@Validated UserGetDTO userGetDTO) throws Exception {
        final User user = service.getById(userGetDTO.getId());
        return AjaxJson.getSuccessData(user);
    }

    @Override
    public AjaxJson list(@Validated UserGetPageDTO userGetPageDTO) throws Exception {
        service.getList(userGetPageDTO);
        return AjaxJson.getPageData();
    }

    @Override
    public AjaxJson add(@Validated UserSaveDTO userSaveDTO) throws Exception {
        final User user = userSaveDTO.toEntity();
        service.add(user);
        return AjaxJson.getSuccessData(user);
    }

    @Override
    public AjaxJson del(@Validated UserDelDTO userDelDTO) throws Exception {
        service.del(userDelDTO.getId());
        return AjaxJson.getSuccess();
    }

    @Override
    public AjaxJson update(@Validated UserUpdateDTO userUpdateDTO) throws Exception {
        service.updateBean(userUpdateDTO);
        return AjaxJson.getSuccess();
    }
}
