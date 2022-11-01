package com.xunmo.webs.department.dto;

import com.xunmo.webs.department.entity.Department;
import com.xunmo.webs.department.entity.DepartmentDO;
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
public class DepartmentUpdateDTO extends DepartmentDO<Department> {

    @NotNull
    @NotBlank
    private String id;
}
