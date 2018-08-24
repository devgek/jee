package com.gek.jee.persistence.entity;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

@MappedSuperclass
public abstract class AbstractPersistable<PK extends Serializable> implements Persistable<PK>
{
	private static final long serialVersionUID = 620038493534380395L;

	public final static String ID_DB_FIELD = "id";

	@Id
	@GeneratedValue(generator = "HibernateIdGenerator")
	@GenericGenerator(name = "HibernateIdGenerator", strategy = "com.gek.jee.persistence.util.HibernateIdGenerator")
	private PK id;

	public PK getId()
	{
		return id;
	}

	public void setId(PK id)
	{
		this.id = id;
	}

	public boolean isNew()
	{
		return null == getId();
	}

	@Override
	public String toString()
	{
		return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
	}

	@Override
	public boolean equals(Object obj)
	{
		if (null == obj)
		{
			return false;
		}
		if (this == obj)
		{
			return true;
		}
		if (!getClass().equals(obj.getClass()))
		{
			return false;
		}
		AbstractPersistable<?> that = (AbstractPersistable<?>) obj;
		return null == this.getId() ? false : this.getId().equals(that.getId());
	}

	@Override
	public int hashCode()
	{
		int hashCode = 17;
		hashCode += null == getId() ? 0 : getId().hashCode() * 31;
		return hashCode;
	}
}