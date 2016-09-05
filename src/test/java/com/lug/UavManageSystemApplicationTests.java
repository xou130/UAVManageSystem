package com.lug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
// @WebAppConfiguration ensure that all your files and beans related to the web app are accessible
public class UavManageSystemApplicationTests {

	@Test
	public void contextLoads() {
	}

}
