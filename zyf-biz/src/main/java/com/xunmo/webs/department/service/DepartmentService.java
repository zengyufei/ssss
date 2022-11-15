package com.xunmo.webs.department.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNodeConfig;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.xunmo.common.base.move.XmSimpleMoveService;
import com.xunmo.common.base.move.XmSimpleMoveServiceImpl;
import com.xunmo.utils.XmUtil;
import com.xunmo.webs.department.dto.DepartmentGetPageDTO;
import com.xunmo.webs.department.dto.DepartmentUpdateDTO;
import com.xunmo.webs.department.entity.Department;
import com.xunmo.webs.department.entity.DepartmentRelate;
import com.xunmo.webs.department.mapper.DepartmentMapper;
import org.noear.solon.annotation.Inject;
import org.noear.solon.aspect.annotation.Service;
import org.noear.solon.data.annotation.Tran;

import java.util.List;
import java.util.stream.Collectors;


/**
 * 部门
 */
@Service
public class DepartmentService extends XmSimpleMoveServiceImpl<DepartmentMapper, Department> implements XmSimpleMoveService<Department> {

    @Inject
    private DepartmentRelateService departmentRelateService;

    /**
     * 获取树
     *
     * @param department department
     * @return {@link List}<{@link Tree}<{@link String}>>
     */
    public List<Tree<String>> getTree(Department department) {
        final String id = department.getId();
        final String code = department.getCode();
        final String parentCode = department.getParentCode();

        List<Department> departments;
        if (StrUtil.isNotBlank(id)) {
            departments = this.baseMapper.getAllChildAndSelfById(id);
        } else if (StrUtil.isNotBlank(code)) {
            departments = this.baseMapper.getAllChildAndSelfByCode(code);
        } else {
            departments = this.baseMapper.getAllChildAndSelfByCode(parentCode);
        }

        // 配置
        TreeNodeConfig treeNodeConfig = new TreeNodeConfig();
        // 自定义属性名 ，即返回列表里对象的字段名
        treeNodeConfig.setIdKey(XmUtil.getField(Department::getId));
        treeNodeConfig.setParentIdKey(XmUtil.getField(Department::getParentCode));
        treeNodeConfig.setWeightKey(XmUtil.getField(Department::getSort));
        treeNodeConfig.setNameKey(XmUtil.getField(Department::getName));
        treeNodeConfig.setChildrenKey("children");

        return TreeUtil.build(departments, "-1", treeNodeConfig, (treeNode, tree) -> {
            tree.setId(treeNode.getId());
            tree.setParentId(treeNode.getParentCode());
            tree.setWeight(treeNode.getSort());
            tree.setName(treeNode.getName());
            // 扩展属性 ...
//            tree.putExtra("sectionId", treeNode.getSectionId());
            tree.putExtra("shortName",treeNode.getShortName());
            tree.putExtra("name",treeNode.getName());
            tree.putExtra("code",treeNode.getCode());
            tree.putExtra("type",treeNode.getType());
            tree.putExtra("description",treeNode.getDescription());
            tree.putExtra("isHide",treeNode.getIsHide());
            tree.putExtra("isShow",treeNode.getIsShow());
            tree.putExtra("level",treeNode.getLevel());
            tree.putExtra("parentCode",treeNode.getParentCode());
            tree.putExtra("sort",treeNode.getSort());
            tree.putExtra("ylAppid",treeNode.getYlAppid());
            tree.putExtra("tenantId",treeNode.getTenantId());
            tree.putExtra("disabled",treeNode.getDisabled());
            tree.putExtra("createTime",treeNode.getCreateTime());
            tree.putExtra("createUser",treeNode.getCreateUser());
            tree.putExtra("createUserNickName",treeNode.getCreateUser());
            tree.putExtra("lastUpdateTime",treeNode.getLastUpdateTime());
            tree.putExtra("lastUpdateUser",treeNode.getLastUpdateUser());
            tree.putExtra("lastUpdateUserNickName",treeNode.getLastUpdateUserNickName());
        });
    }

    /**
     * 获取列表
     *
     * @param departmentGetPageDTO department获取页面dto
     * @return {@link List}<{@link Department}>
     */
    public List<Department> getList(DepartmentGetPageDTO departmentGetPageDTO) {
        final Department department = departmentGetPageDTO.toEntity();
        return startPage(() -> this.baseMapper.selectList(Wrappers.lambdaQuery(department)));
    }

    /**
     * 添加
     *
     * @param department department
     * @return {@link Department}
     */
    @Tran
    public Department add(Department department) throws Exception {
        final String rootName = "root";
        final String parentCode = StrUtil.blankToDefault(department.getParentCode(), rootName);
        department.setParentCode(parentCode);
        int max = this.baseMapper.getMax(department);
        department.setSort(max + 1);
        if (StrUtil.equals(rootName, parentCode)) {
            department.setLevel(1);
        } else {
            final Department parentDepartment = baseMapper.checkAndGetByCode(parentCode, () -> "上级部门不存在!");
            Integer parentLevel = parentDepartment.getLevel();
            department.setLevel(++parentLevel);
        }
        this.baseMapper.insert(department);
        this.departmentRelateService.insertDeptRelation(department);
        return department;
    }

    /**
     * 删除
     *
     * @param id id
     * @return boolean
     */
    @Tran
    public boolean del(String id) {
        final Department department = this.baseMapper.checkAndGet(id);
        final String code = department.getCode();
        // 删除下级部门
        List<String> codeList = this.departmentRelateService
                .list(Wrappers.<DepartmentRelate>query().lambda().eq(DepartmentRelate::getParentCode, code)).stream()
                .map(DepartmentRelate::getChildCode).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(codeList)) {
            this.baseMapper.removeByCodes(codeList);
        }
        // 删除部门级联关系
        this.departmentRelateService.deleteAllDeptRealtion(code);
        // 最后删除自己
        this.baseMapper.deleteById(id);
        return true;
    }

    /**
     * 更新
     *
     * @param departmentUpdateDTO department更新dto
     * @return boolean
     */
    @Tran
    public boolean updateBean(DepartmentUpdateDTO departmentUpdateDTO) throws Exception {
        final Department inputDepartment = toBean(departmentUpdateDTO, Department.class);
        final String id = inputDepartment.getId();
        final String inputParentCode = StrUtil.blankToDefault(inputDepartment.getParentCode(), "root");

        final Department oldDepartment = checkAndGet(id);
        if (!StrUtil.equalsIgnoreCase(oldDepartment.getParentCode(), inputParentCode)) {
            int max = this.baseMapper.getMax(oldDepartment);
            inputDepartment.setSort(max + 1);
            inputDepartment.setParentCode(inputParentCode);

            if (StrUtil.equals("root", inputParentCode)) {
                inputDepartment.setLevel(1);
            } else {
                final Department parentDepartment = this.baseMapper.checkAndGetByCode(inputParentCode);
                Integer parentLevel = parentDepartment.getLevel();
                inputDepartment.setLevel(++parentLevel);
            }
        }
        // 更新部门状态
        this.baseMapper.updateNotNullById(inputDepartment);
        // 更新部门关系
        DepartmentRelate relation = new DepartmentRelate();
        relation.setParentCode(inputDepartment.getParentCode());
        relation.setChildCode(inputDepartment.getCode());
        this.departmentRelateService.updateDeptRealtion(relation);
        return true;
    }


}
