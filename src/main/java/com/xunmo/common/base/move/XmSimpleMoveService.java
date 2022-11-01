package com.xunmo.common.base.move;

import com.xunmo.common.base.XmService;

public interface XmSimpleMoveService<T> extends XmService<T> {

    boolean upTop(final String id) throws Exception;

    boolean upMove(final String id) throws Exception;

    boolean downMove(final String id) throws Exception;

    boolean changeSort(final String preId, final String nextId) throws Exception;
}
