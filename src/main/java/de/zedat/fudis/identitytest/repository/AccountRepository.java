package de.zedat.fudis.identitytest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import de.zedat.fudis.identitytest.domain.Account;

@RepositoryRestResource(collectionResourceRel = "accounts", path = "accounts")
public interface AccountRepository extends PagingAndSortingRepository<Account, Long>
{
	@RestResource(exported = false)
	@Override
	public Iterable<Account> findAll();
	
	@RestResource(exported = false)
	@Override
	public Iterable<Account> findAll(Iterable<Long> ids);
	
	@RestResource(exported = false)
	@Override
	public Page<Account> findAll(Pageable pageable);
	
	@RestResource(exported = false)
	@Override
	public Iterable<Account> findAll(Sort sort);
	
	@RestResource(exported = false)
	@Override
	public void delete(Account entity);
	
	@RestResource(exported = false)
	@Override
	public void delete(Iterable<? extends Account> entities);
	
	@RestResource(exported = false)
	@Override
	public void delete(Long id);
	
	@RestResource(exported = false)
	@Override
	public void deleteAll();
}
