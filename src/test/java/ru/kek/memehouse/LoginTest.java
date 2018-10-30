package ru.kek.memehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * gordeevnm@gmail.com
 * 15.01.18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
	  classes = {DBInit.class, MemeHouseApplication.class})
@TestPropertySource("classpath:application-test.properties")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RequiredArgsConstructor
@AutoConfigureMockMvc
public class LoginTest {
	private final ObjectMapper objectMapper;
	@Resource
	private WebApplicationContext webApplicationContext;
	private final MockMvc mockMvc;

//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.apply(springSecurity())
//				.build();
//	}
	
	public static String authToken;
	
	@Test
	public void test1_loginTest() throws Exception {
		MvcResult mvcResult = mockMvc
			  .perform(
					 post("/api/login")
							.contentType(MediaType.APPLICATION_JSON_UTF8)
							.param("username", TestUserData.testUsername)
							.param("password", TestUserData.testPassword)
			  )
			  .andDo(result -> System.out.println(result.getResponse().getContentAsString()))
			  .andExpect(status().isOk())
			  .andExpect(jsonPath("$").exists())
			  .andExpect(jsonPath("$.authToken").isNotEmpty())
			  .andReturn();
		String content = mvcResult.getResponse().getContentAsString();
		authToken = JsonPath.read(content, "$.authToken");
		System.out.println(authToken);
	}
	
	@Test
	public void test2_getAuth() throws Exception {
		mockMvc.perform(
			  get("/api/authentication")
					 .contentType(MediaType.APPLICATION_JSON_UTF8)
					 .header("X-Auth-Token", authToken))
			  .andDo(print())
			  .andExpect(status().isOk())
			  .andExpect(jsonPath("$").exists());
	}
}
