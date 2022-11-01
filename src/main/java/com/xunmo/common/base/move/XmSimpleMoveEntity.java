package com.xunmo.common.base.move;

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

    private String ylAppid;
    private String tenantId;
    private Integer disabled;
    private Date createTime;
    private String createUser;
    private String createUserNickName;
    private Date lastUpdateTime;
    private String lastUpdateUser;
    private String lastUpdateUserNickName;
}
