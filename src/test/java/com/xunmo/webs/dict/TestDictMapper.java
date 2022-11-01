package com.xunmo.webs.dict;

import com.xunmo.DemoApp;
import com.xunmo.utils.XmMap;
import com.xunmo.webs.dict.entity.Dict;
import com.xunmo.webs.dict.mapper.DictMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.solon.annotation.Inject;
import org.noear.solon.test.HttpTestBase;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

@Slf4j
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(DemoApp.class)
public class TestDictMapper extends HttpTestBase {

    @Inject
    private DictMapper dictMapper;

    @Test
    public void testMoveMoveMove() throws Exception {
        testDel();
    }

    @Test
    public void testDel() throws Exception {
        final XmMap<String, Object> xmMap = new XmMap<>();
        xmMap.put(Dict::getId, 123);
        dictMapper.deleteByMap(xmMap);
    }



}
