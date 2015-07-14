package de.zedat.fudis.identitytest.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Account implements Serializable
{
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	
	@NotNull
	@Column(name = "uid")
	private String uid;
	
	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "account")
	@JsonManagedReference(value = "accountIdentifiers")
	private List<AccountIdentifier> identifiers;
	
	protected Account() {}

	public Account(String uid)
	{
		super();
		this.uid = uid;
	}

	/**
	 * @return the id
	 */
	public Long getId()
	{
		return id;
	}

	/**
	 * @return the uid
	 */
	public String getUid()
	{
		return uid;
	}

	/**
	 * @return the identifiers
	 */
	public List<AccountIdentifier> getIdentifiers()
	{
		return identifiers;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id)
	{
		this.id = id;
	}

	/**
	 * @param uid the uid to set
	 */
	public void setUid(String uid)
	{
		this.uid = uid;
	}

	/**
	 * @param identifiers the identifiers to set
	 */
	public void setIdentifiers(List<AccountIdentifier> identifiers)
	{
		this.identifiers = identifiers;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (uid == null)
		{
			if (other.uid != null)
				return false;
		}
		else if (!uid.equals(other.uid))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		builder.append("Account [id=");
		builder.append(id);
		builder.append(", uid=");
		builder.append(uid);
		builder.append("]");
		return builder.toString();
	};
	
	
}
