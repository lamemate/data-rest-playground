package de.zedat.fudis.identitytest.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import de.zedat.fudis.identitytest.domain.AccountIdentifier;

@RepositoryRestResource(collectionResourceRel = "accountidentifiers", path = "accountidentifiers", exported = false)
public interface AccountIdentifierRepository extends PagingAndSortingRepository<AccountIdentifier, Long>
{
	@RestResource(exported = false)
	@Override
	public Iterable<AccountIdentifier> findAll();
	
	@RestResource(exported = false)
	@Override
	public Iterable<AccountIdentifier> findAll(Iterable<Long> ids);
	
	@RestResource(exported = false)
	@Override
	public Page<AccountIdentifier> findAll(Pageable pageable);
	
	@RestResource(exported = false)
	@Override
	public Iterable<AccountIdentifier> findAll(Sort sort);
	
	@RestResource(exported = false)
	@Override
	public void delete(AccountIdentifier entity);
	
	@RestResource(exported = false)
	@Override
	public void delete(Iterable<? extends AccountIdentifier> entities);
	
	@RestResource(exported = false)
	@Override
	public void delete(Long id);

	@RestResource(exported = false)
	@Override
	public void deleteAll();
}
