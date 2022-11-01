package com.xunmo.common.base.relate;

import com.xunmo.annotations.SortUni;
import com.xunmo.common.base.move.XmSimpleMoveEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class XmRelateCodeEntity extends XmSimpleMoveEntity {

    private String code;
    @SortUni
    private String parentCode;
}
