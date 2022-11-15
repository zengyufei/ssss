package com.xunmo.webs.dict.controller;

import com.xunmo.common.base.move.XmSimpleMoveController;
import com.xunmo.utils.AjaxJson;
import com.xunmo.webs.dict.dto.DictDelDTO;
import com.xunmo.webs.dict.dto.DictGetDTO;
import com.xunmo.webs.dict.dto.DictGetPageDTO;
import com.xunmo.webs.dict.dto.DictSaveDTO;
import com.xunmo.webs.dict.dto.DictUpdateDTO;
import com.xunmo.webs.dict.entity.Dict;
import com.xunmo.webs.dict.service.DictService;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Controller;
import org.noear.solon.annotation.Mapping;
import org.noear.solon.validation.annotation.Valid;
import org.noear.solon.validation.annotation.Validated;

@Slf4j
@Valid
@Controller
@Mapping("/dict")
public class DictController extends XmSimpleMoveController<DictService, Dict> implements IDictController{

    @Override
    public AjaxJson get(@Validated DictGetDTO dictGetDTO) throws Exception {
        final Dict dict = service.getById(dictGetDTO.getId());
        return AjaxJson.getSuccessData(dict);
    }

    @Override
    public AjaxJson getTree(String id) throws Exception {
        return AjaxJson.getSuccessData(service.getTree(id));
    }

    @Override
    public AjaxJson list(@Validated DictGetPageDTO dictGetPageDTO) throws Exception {
        service.getList(dictGetPageDTO);
        return AjaxJson.getPageData();
    }

    @Override
    public AjaxJson add(@Validated DictSaveDTO dictSaveDTO) throws Exception {
        final Dict dict = dictSaveDTO.toEntity();
        service.add(dict);
        return AjaxJson.getSuccessData(dict);
    }

    @Override
    public AjaxJson del(@Validated DictDelDTO dictDelDTO) throws Exception {
        service.del(dictDelDTO.getId());
        return AjaxJson.getSuccess();
    }

    @Override
    public AjaxJson update(@Validated DictUpdateDTO dictUpdateDTO) throws Exception {
        service.updateBean(dictUpdateDTO);
        return AjaxJson.getSuccess();
    }
}
