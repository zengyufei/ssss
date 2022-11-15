package com.xunmo.common.base;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class XmEntity extends XmIdEntity {

    @TableField(fill = FieldFill.INSERT)
    private String ylAppid;
    @TableField(fill = FieldFill.INSERT)
    private String tenantId;
    @TableField(fill = FieldFill.INSERT)
    private Integer disabled;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
    @TableField(fill = FieldFill.INSERT)
    private String createUserNickName;
    @TableField(fill = FieldFill.UPDATE)
    private String lastUpdateTime;
    @TableField(fill = FieldFill.UPDATE)
    private String lastUpdateUser;
    @TableField(fill = FieldFill.UPDATE)
    private String lastUpdateUserNickName;
}
