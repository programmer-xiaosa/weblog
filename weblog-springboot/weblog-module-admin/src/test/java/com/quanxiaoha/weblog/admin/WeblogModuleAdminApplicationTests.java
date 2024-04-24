package com.quanxiaoha.weblog.admin;

import com.baomidou.mybatisplus.autoconfigure.MybatisPlusAutoConfiguration;
import com.quanxiaoha.weblog.common.domain.dos.UserDO;
import com.quanxiaoha.weblog.common.domain.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.time.LocalDateTime;

@SpringBootTest(classes = WeblogModuleAdminApplicationTests.Application.class)
class WeblogModuleAdminApplicationTests {
	// @Import({
	// 		DataSourceAutoConfiguration.class, // Spring DB 自动配置类
	// 		DataSourceTransactionManagerAutoConfiguration.class, // Spring 事务自动配置类
	// 		MybatisPlusAutoConfiguration.class, // MyBatis 的自动配置类
	// })
	public static class Application {
	}

}
