package com.xunmo.webs.user.controller;

import com.xunmo.utils.AjaxJson;
import com.xunmo.webs.user.dto.UserDelDTO;
import com.xunmo.webs.user.dto.UserGetDTO;
import com.xunmo.webs.user.dto.UserGetPageDTO;
import com.xunmo.webs.user.dto.UserSaveDTO;
import com.xunmo.webs.user.dto.UserUpdateDTO;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.validation.annotation.Valid;
import org.noear.solon.validation.annotation.Validated;

@Valid
@Controller
public interface IUserController {

    @Post
    @Mapping("/get")
    AjaxJson get(@Validated UserGetDTO userGetDTO) throws Exception;

    @Post
    @Mapping("/list")
    AjaxJson list(@Validated UserGetPageDTO userGetPageDTO) throws Exception;

    //    @NoRepeatSubmit  //重复提交验证
    @Post
    @Mapping("/add")
    AjaxJson add(@Validated UserSaveDTO userSaveDTO) throws Exception;

    @Post
    @Mapping("/del")
    AjaxJson del(@Validated UserDelDTO userDelDTO) throws Exception;

    //    @NoRepeatSubmit  //重复提交验证
    @Post
    @Mapping("/update")
    AjaxJson update(@Validated UserUpdateDTO userUpdateDTO) throws Exception;

}
