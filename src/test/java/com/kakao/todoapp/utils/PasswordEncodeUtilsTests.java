package com.kakao.todoapp.utils;

import com.kakao.todoapp.utils.crypto.PasswordEncodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.util.StringUtils;

@Slf4j
public class PasswordEncodeUtilsTests {

	@Test
	public void encodePasswordTest() {
		String plain = "1234";
		String encrypted = PasswordEncodeUtils.encodePassword(plain);

		Assert.assertTrue(!StringUtils.isEmpty(encrypted));
		log.info("encrypted : {}", encrypted);
	}
}