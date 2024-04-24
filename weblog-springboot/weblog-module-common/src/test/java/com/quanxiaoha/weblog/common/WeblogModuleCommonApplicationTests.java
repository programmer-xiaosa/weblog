package com.quanxiaoha.weblog.common;

import com.quanxiaoha.weblog.common.domain.dos.UserDO;
import com.quanxiaoha.weblog.common.domain.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = WeblogModuleCommonApplicationTests.Application.class)
@Slf4j
class WeblogModuleCommonApplicationTests {

    public static class Application {
    }

    @Test
    public void test() {
    }

}
