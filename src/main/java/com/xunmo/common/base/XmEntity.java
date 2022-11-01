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

    private String ylAppid;
    private String tenantId;
    private Integer disabled;
    @TableField(fill = FieldFill.INSERT)
    private String createTime;
    private String createUser;
    private String createUserNickName;
    @TableField(fill = FieldFill.UPDATE)
    private String lastUpdateTime;
    private String lastUpdateUser;
    private String lastUpdateUserNickName;
}
