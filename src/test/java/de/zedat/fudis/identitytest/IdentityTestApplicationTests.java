package de.zedat.fudis.identitytest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import de.zedat.fudis.identitytest.domain.Account;
import de.zedat.fudis.identitytest.domain.AccountIdentifier;
import de.zedat.fudis.identitytest.repository.AccountRepository;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = IdentityTestApplication.class)
@WebAppConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class IdentityTestApplicationTests {

	@Autowired
	WebApplicationContext context;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	private MockMvc mockMvc;
	private Account account;
	private AccountIdentifier identifier;
	
	@Before
	public void setUp()
	{
		mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
		account = new Account("testuid");
		identifier = new AccountIdentifier(account, "testvalue");
		account.setIdentifiers(Arrays.asList(identifier));
	}
	
	@After
	public void tearDown()
	{
		accountRepository.deleteAll();
	}
	
	@Test
	public void contextLoads() {}
	
	@Test
	public void thatAccountsCanBeSaved()
	{
		account = accountRepository.save(account);
		Assert.assertNotNull(account.getId());
		Assert.assertTrue(account.getId() > 0);
	}
	
	@Test
	public void thatAccountsHaveIdentifiers()
	{
		account = accountRepository.save(account);
		Assert.assertNotNull(account.getIdentifiers());
		Assert.assertTrue(account.getIdentifiers().size() > 0);
	}
	
	@Test
	public void thatAccountsCanBeSavedViaRest() throws Exception
	{
		mockMvc.perform(post("/accounts")
				.content(objectMapper.writeValueAsString(account)))
				.andExpect(status().isCreated());
	}
	
	@Test
	public void thatAccountsHaveIdentifiersViaRest() throws Exception
	{
		MvcResult result = mockMvc.perform(post("/accounts")
				.content(objectMapper.writeValueAsString(account)))
				.andExpect(status().isCreated()).andReturn();
		
		mockMvc.perform(get(result.getResponse().getRedirectedUrl())
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andDo(print());
	}
}
