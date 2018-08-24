package com.gek.jee.persistence.util;

import java.io.Serializable;
import java.util.Properties;

import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.dialect.Dialect;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.PersistentIdentifierGenerator;
import org.hibernate.id.SequenceGenerator;
import org.hibernate.type.Type;

import com.gek.jee.persistence.entity.AbstractPersistable;

/**
 * Id generation based on the table name of the entity AND if id is already set the current id will be used for insert
 * (no call via sequence).
 */
public class HibernateIdGenerator extends SequenceGenerator
{
	/**
	 * If the parameters do not contain a {@link SequenceGenerator#SEQUENCE} name, we assign one based on the table
	 * name.
	 */
	@Override
	public void configure(Type type, Properties params, Dialect dialect) throws MappingException
	{
		if (params.getProperty(SEQUENCE) == null || params.getProperty(SEQUENCE).length() == 0)
		{
			String tableName = params.getProperty(PersistentIdentifierGenerator.TABLE);
			if (tableName != null)
			{
				String seqName = tableName + "_seq";
				params.setProperty(SEQUENCE, seqName);
			}
		}
		super.configure(type, params, dialect);
	}

	/**
	 * If an object has manually set an id don't generate a new one via the sequence.
	 */
	@Override
	public Serializable generate(SessionImplementor session, Object obj) throws HibernateException
	{
		if (obj == null)
			throw new HibernateException(new IllegalArgumentException("obj is null."));

		if ((((AbstractPersistable<?>) obj).getId()) == null)
		{
			return super.generate(session, obj);
		}
		else
		{
			return ((AbstractPersistable<?>) obj).getId();
		}
	}
}
