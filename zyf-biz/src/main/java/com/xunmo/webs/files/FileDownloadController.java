package com.xunmo.webs.files;

import com.xunmo.common.base.XmSimpleController;
import com.xunmo.event.LogEvent;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Get;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.annotation.Param;
import org.noear.solon.core.event.EventBus;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.DownloadedFile;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;
import org.noear.solon.validation.annotation.Valid;


/**
 * 接口开发，与控制器开发差不多的; 但进入网关的接口，要用 @Component 注解
 *
 */
//这个注解一定要加类上（或者基类上）
@Valid
@Mapping("api")
@Controller
public class FileDownloadController extends XmSimpleController {

    //文件下载, 只接受 urlencode 的中文名或普通字符名
    @NotNull({"fileName"})  //非NULL验证
    @NotBlank({"fileName"})  //非Blank验证
    @Get
    @Mapping("/download/{fileName}")
    public DownloadedFile download(String fileName, @Param String parentDir) {
        return getDownloadedFile(fileName, parentDir);
    }

    //文件下载， 接受 post 请求，接受 urlencode 的中文名或普通字符名
    @NotNull({"fileName"})  //非NULL验证
    @NotBlank({"fileName"})  //非Blank验证
    @Mapping("/download")
    public DownloadedFile downloadPost(String fileName) {
        return getDownloadedFile(fileName, null);
    }

    private DownloadedFile getDownloadedFile(String fileName, String parentDir) {
        final Context ctx = Context.current();
        final String ip = ctx.ip();
        // TODO 下载文件逻辑
        final byte[] fileContent = {};
        ctx.headerSet("Content-Length", fileContent.length+"");
        //使用 InputStream 实例化
        String contentType = "application/octet-stream";
        if (fileName.endsWith(".doc")) {
            contentType = "application/msword";
        } else if (fileName.endsWith(".pdf")) {
            contentType = "application/pdf";
        } else if (fileName.endsWith(".xls")) {
            contentType = "application/octet-stream;charset=ISO8859-1";
        }
        EventBus.push(new LogEvent(ip+" 进行下载, 下载文件["+fileName+"]"));
        return new DownloadedFile(contentType, fileContent, fileName);
    }

}
