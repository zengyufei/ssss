package com.xunmo.webs.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class UserGetDTO {

    @NotNull
    @NotBlank
    private String id;

}
