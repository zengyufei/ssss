package com.xunmo.webs.dict.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xunmo.common.base.move.XmSimpleMoveService;
import com.xunmo.common.base.move.XmSimpleMoveServiceImpl;
import com.xunmo.utils.XmUtil;
import com.xunmo.webs.dict.dto.DictGetPageDTO;
import com.xunmo.webs.dict.dto.DictUpdateDTO;
import com.xunmo.webs.dict.entity.Dict;
import com.xunmo.webs.dict.mapper.DictMapper;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;

import java.util.List;

/**
 * Service: t_xm -- 项目
 *
 * @author zengyufei
 */
@Service
public class DictService extends XmSimpleMoveServiceImpl<DictMapper, Dict> implements XmSimpleMoveService<Dict> {

    /**
     * 获取树
     *
     * @param id id
     * @return {@link List}<{@link Tree}<{@link String}>>
     */
    public List<Tree<String>> getTree(String id) {
        final List<Dict> dicts = this.baseMapper.selectList(Wrappers.<Dict>lambdaQuery().eq(Dict::getParentId, id));
        // 配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 ，即返回列表里对象的字段名
        treeNodeConfig.setIdKey(XmUtil.getField(Dict::getId));
        treeNodeConfig.setParentIdKey(XmUtil.getField(Dict::getParentId));
        treeNodeConfig.setWeightKey(XmUtil.getField(Dict::getSort));
        treeNodeConfig.setNameKey(XmUtil.getField(Dict::getDicDescription));
        treeNodeConfig.setChildrenKey("children");

        return TreeUtil.build(dicts, "-1", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentId());
            tree.setWeight(treeNode.getSort());
            tree.setName(treeNode.getDicDescription());
            // 扩展属性 ...
//            tree.putExtra("sectionId", treeNode.getSectionId());
        });
    }

    /**
     * 获取列表
     *
     * @param dictGetPageDTO dict获取页面dto
     * @return {@link List}<{@link Dict}>
     */
    public List<Dict> getList(DictGetPageDTO dictGetPageDTO) {
        final Dict dict = dictGetPageDTO.toEntity();
        return startPage(() -> this.baseMapper.selectList(Wrappers.lambdaQuery(dict)));
    }

    /**
     * 添加
     *
     * @param dict dict
     * @return {@link Dict}
     */
    @Tran
    public Dict add(Dict dict) throws Exception{
        final String parentId = StrUtil.blankToDefault(dict.getParentId(), "-1");
        dict.setParentId(parentId);
        int max = this.baseMapper.getMax(dict);
        dict.setSort(max + 1);
        if (StrUtil.equals("-1", parentId)) {
            dict.setDicLevel(1);
        } else {
            final Dict parentDict = this.checkAndGet(parentId);
            Integer parentLevel = parentDict.getDicLevel();
            dict.setDicLevel(++parentLevel);
        }
        this.baseMapper.insert(dict);
        return dict;
    }

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    @Tran
    public boolean del(String id) {
        final List<Dict> dicts = this.baseMapper.selectList(Wrappers.<Dict>lambdaQuery().eq(Dict::getParentId, id));
        if (CollUtil.isNotEmpty(dicts)) {
            this.baseMapper.deleteBatchIds(XmUtil.mapToList(dicts, Dict::getId));
        }
        this.baseMapper.deleteById(id);
        return true;
    }

    /**
     * 更新
     *
     * @param dictUpdateDTO dict更新dto
     * @return boolean
     */
    @Tran
    public boolean updateBean(DictUpdateDTO dictUpdateDTO) throws Exception {
        final Dict inputDict = toBean(dictUpdateDTO, Dict.class);
        final String id = inputDict.getId();
        final String inputParentId = StrUtil.blankToDefault(inputDict.getParentId(), "-1");

        final Dict oldDict = checkAndGet(id);
        if (!StrUtil.equalsIgnoreCase(oldDict.getParentId(), inputParentId)) {
            int max = this.baseMapper.getMax(oldDict);
            inputDict.setSort(max + 1);
            inputDict.setParentId(inputParentId);

            if (StrUtil.equals("-1", inputParentId)) {
                inputDict.setDicLevel(1);
            } else {
                final Dict parentDict = this.checkAndGet(inputParentId);
                Integer parentLevel = parentDict.getDicLevel();
                inputDict.setDicLevel(++parentLevel);
            }
        }
        this.baseMapper.updateNotNullById(inputDict);
        return true;
    }


}
