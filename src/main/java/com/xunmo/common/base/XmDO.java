package com.xunmo.common.base;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.solon.toolkit.GenericTypeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public abstract class XmDO<T> {

    public T toEntity() {
        Class<T> clazz = (Class<T>) GenericTypeUtil.getSuperClassGenericType(this.getClass(), XmDO.class, 0);
        return BeanUtil.copyProperties(this, clazz);
    }
}
