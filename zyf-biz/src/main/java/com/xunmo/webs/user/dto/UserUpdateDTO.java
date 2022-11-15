package com.xunmo.webs.user.dto;

import com.xunmo.webs.user.entity.User;
import com.xunmo.webs.user.entity.UserDO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;

@Data
@NoArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserUpdateDTO extends UserDO<User> {

    @NotNull
    @NotBlank
    private String id;

}
