package com.xunmo.webs.dict.controller;

import com.xunmo.utils.AjaxJson;
import com.xunmo.webs.dict.dto.DictDelDTO;
import com.xunmo.webs.dict.dto.DictGetDTO;
import com.xunmo.webs.dict.dto.DictGetPageDTO;
import com.xunmo.webs.dict.dto.DictSaveDTO;
import com.xunmo.webs.dict.dto.DictUpdateDTO;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Valid;
import org.noear.solon.validation.annotation.Validated;

@Valid
@Controller
public interface IDictController {

    @Post
    @Mapping("/get")
    AjaxJson get(@Validated DictGetDTO dictGetDTO) throws Exception;

    @NotNull({"id"})
    @NotBlank({"id"})
    @Post
    @Mapping("/get/{id}/tree")
    AjaxJson getTree(String id) throws Exception;

    @Post
    @Mapping("/list")
    AjaxJson list(@Validated DictGetPageDTO dictGetPageDTO) throws Exception;

    //    @NoRepeatSubmit  //重复提交验证
    @Post
    @Mapping("/add")
    AjaxJson add(@Validated DictSaveDTO dictSaveDTO) throws Exception;

    @Post
    @Mapping("/del")
    AjaxJson del(@Validated DictDelDTO dictDelDTO) throws Exception;

    //    @NoRepeatSubmit  //重复提交验证
    @Post
    @Mapping("/update")
    AjaxJson update(@Validated DictUpdateDTO dictUpdateDTO) throws Exception;
}
