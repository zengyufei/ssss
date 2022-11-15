package com.xunmo.common.base;

import com.baomidou.mybatisplus.annotation.TableId;
import com.xunmo.common.validationGroups.MoveValid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.noear.solon.validation.annotation.NotBlank;
import org.noear.solon.validation.annotation.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class XmIdEntity {
    @TableId
    @NotBlank(value = "id 不能为空", groups = {MoveValid.class})
    @NotNull(value = "id 不能为null", groups = {MoveValid.class})
    private String id;

}
