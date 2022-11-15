package com.xunmo.common.base.move;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.xunmo.common.base.XmIdEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class XmSimpleMoveEntity extends XmIdEntity {

    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private String ylAppid;
    @TableField(fill = FieldFill.INSERT)
    private String tenantId;
    @TableField(fill = FieldFill.INSERT)
    private Integer disabled;
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;
    @TableField(fill = FieldFill.INSERT)
    private String createUser;
    @TableField(fill = FieldFill.INSERT)
    private String createUserNickName;
    @TableField(fill = FieldFill.UPDATE)
    private Date lastUpdateTime;
    @TableField(fill = FieldFill.UPDATE)
    private String lastUpdateUser;
    @TableField(fill = FieldFill.UPDATE)
    private String lastUpdateUserNickName;
}
