package com.xunmo.webs.user.entity;

import com.xunmo.common.base.XmDO;
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
public abstract class UserDO<T> extends XmDO<T> {

    /**
    * 头像
    */
    private String avatar;

    /**
    * 昵称
    */
    private String nickname;

    /**
    * 姓名
    */
    private String fullname;

    /**
    * 性别：0-女；1-男；2-未知；
    */
    private Long sex;

    /**
    * 出生日期
    */
    private Date birthday;

    /**
    * 电话
    */
    private String phone;

    /**
    * 邮箱
    */
    private String email;

    /**
    * 错误登录次数
    */
    private Long errorTimes;

    /**
    * 登录时间
    */
    private Date loginTime;

    /**
    * 登录IP
    */
    private String loginIp;

    /**
    * 退出时间
    */
    private Date logoutTime;

    /**
    * 注册时间
    */
    private Date registerTime;

    /**
    * 状态: normal-正常; forbidden-禁用;
    */
    private String status;

    /**
    * 是否为租户主账号: 0-否; 1-是;
    */
    private Integer isTenantAccount;


}
