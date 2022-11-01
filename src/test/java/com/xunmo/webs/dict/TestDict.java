package com.xunmo.webs.dict;

import com.xunmo.DemoApp;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.noear.snack.ONode;
import org.noear.solon.test.HttpTestBase;
import org.noear.solon.test.HttpUtils;
import org.noear.solon.test.SolonJUnit4ClassRunner;
import org.noear.solon.test.SolonTest;

@Slf4j
@RunWith(SolonJUnit4ClassRunner.class)
@SolonTest(DemoApp.class)
public class TestDict extends HttpTestBase {

    @Test
    public void testMoveMoveMove() throws Exception {
        HttpUtils httpUtils = path("/dict/upMove")
                .bodyJson("{id:'1373296926478733313', parentId: '1373280539488194561'}");
        httpUtils.post();
        httpUtils.post();
        httpUtils = path("/dict/downMove")
                .bodyJson("{id:'1373296926478733313', parentId: '1373280539488194561'}");
        httpUtils.post();
    }

    @Test
    public void list() throws Exception {
        String json = path("/dict/list")
                .bodyJson("{}")
                .post();
        ONode node = ONode.load(json);
        assert node.get("code").getInt() == 200;
    }


    @Test
    public void apiList() throws Exception {
        String json = path("/api_dict/list")
                .bodyJson("{}")
                .post();
        ONode node = ONode.load(json);
        assert node.get("code").getInt() == 200;
    }

    @Test
    public void page() throws Exception {
        String json = path("/dict/list?pageNo=1&pageSize=2")
                .bodyJson("{}")
                .post();
        ONode node = ONode.load(json);
        assert node.get("code").getInt() == 200;
    }

    @Test
    public void upMove() throws Exception {
        String json = path("/dict/upMove")
                .bodyJson("{id:'1373299290652708866'}")
                .post();
        ONode node = ONode.load(json);
        assert node.get("code").getInt() == 200;
    }


    @Test
    public void downMove() throws Exception {
        String json = path("/dict/downMove")
                .bodyJson("{id:'1373296926478733313', parentId: '1373280539488194561'}")
                .post();
        ONode node = ONode.load(json);
        assert node.get("code").getInt() == 200;
    }

}
