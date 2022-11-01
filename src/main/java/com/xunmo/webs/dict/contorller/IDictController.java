package com.xunmo.webs.dict.contorller;

import com.xunmo.utils.AjaxJson;
import com.xunmo.webs.dict.dto.*;
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
    public AjaxJson get(@Validated DictGetDTO dictGetDTO) throws Exception;

    @NotNull({"id"})
    @NotBlank({"id"})
    @Post
    @Mapping("/get/{id}/tree")
    public AjaxJson getTree(String id) throws Exception;

    @Post
    @Mapping("/list")
    public AjaxJson list(@Validated DictGetPageDTO dictGetPageDTO) throws Exception;

    //    @NoRepeatSubmit  //重复提交验证
    @Post
    @Mapping("/add")
    public AjaxJson add(@Validated DictSaveDTO dictSaveDTO) throws Exception;

    @Post
    @Mapping("/del")
    public AjaxJson del(@Validated DictDelDTO dictDelDTO) throws Exception;

    //    @NoRepeatSubmit  //重复提交验证
    @Post
    @Mapping("/update")
    public AjaxJson update(@Validated DictUpdateDTO dictUpdateDTO) throws Exception;
}
