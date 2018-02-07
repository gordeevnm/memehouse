package ru.kek.memehouse;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import ru.kek.memehouse.dto.RegistrationDto;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * gordeevnm@gmail.com
 * 14.01.18
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
		classes = {DBInit.class, MemeHouseApplication.class})
@TestPropertySource("classpath:application-test.properties")
@AutoConfigureMockMvc
public class RegistrationTest {
	@Autowired
	private ObjectMapper objectMapper;
	@Resource
	private WebApplicationContext webApplicationContext;
	@Autowired
	private MockMvc mockMvc;
	
//	@Before
//	public void setUp() throws Exception {
//		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
//				.apply(springSecurity())
//				.build();
//	}
	
	private static final RegistrationDto testRegDto = RegistrationDto.builder()
			.email(TestUserData.testEmail)
			.username(TestUserData.testUsername)
			.password(TestUserData.testPassword)
			.build();
	
	@Test
	public void registrationTest() throws Exception {
		MvcResult mvcResult = mockMvc
				.perform(
						post("/api/registration")
								.contentType(MediaType.APPLICATION_JSON_UTF8)
								.content(objectMapper.writeValueAsString(testRegDto))
				)
				.andDo(result -> System.out.println(result.getResponse().getContentAsString()))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$").exists())
//				.andExpect(jsonPath("$.authToken").isNotEmpty())
				.andReturn();
	}
}