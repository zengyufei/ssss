package com.xunmo.webs.files;

import com.xunmo.common.base.XmSimpleController;
import com.xunmo.event.LogEvent;
import com.xunmo.utils.AjaxJson;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Post;
import org.noear.solon.core.event.EventBus;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.UploadedFile;
import org.noear.solon.validation.annotation.Valid;

@Slf4j
@Valid
@Mapping("/files")
@Controller
public class FileUploadController extends XmSimpleController {

    //文件上传
    @Post
    @Mapping("/upload")
    public AjaxJson upload(Context ctx, UploadedFile file, String parentDir) {
        final String ip = ctx.ip();
        final String fileName = file.name;
        EventBus.push(new LogEvent(ip+" 进行上传, 上传文件["+fileName+"], 文件大小:" + file.contentSize));
        // TODO 上传文件逻辑
        EventBus.push(new LogEvent(ip+" 上传文件["+fileName+"] 成功!"));
        return AjaxJson.getSuccess();
    }


}
