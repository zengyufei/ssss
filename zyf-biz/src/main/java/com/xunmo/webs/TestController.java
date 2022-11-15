package com.xunmo.webs;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xunmo.common.base.XmSimpleController;
import com.xunmo.webs.dict.entity.Dict;
import com.xunmo.webs.dict.mapper.DictMapper;
import com.xunmo.webs.dict.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.solon.annotation.Db;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Inject;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.validation.annotation.Valid;

import java.util.List;

@Slf4j
@Valid
@Controller
@Mapping("/test")
public class TestController extends XmSimpleController {
    @Db
    private DictMapper dictMapper;

    @Mapping("test1")
    public List<Dict> test1() throws Exception {
        return dictMapper.selectList(Wrappers.emptyWrapper());
    }

    @Inject
    private DictService dictService;

    @Mapping("test2")
    public List<Dict> test2() throws Exception {
        return dictService.list(Wrappers.emptyWrapper());
    }

}
