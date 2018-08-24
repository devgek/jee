package com.gek.jee.cmis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Repository;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

import com.gek.jee.scope.ApplicationScopedBean;

@ApplicationScopedBean
public class OpenCmisSessionHolder {
	private Session session;
	
	public synchronized Session getSession() {
		if (this.session == null) {
			// default factory implementation of client runtime
			SessionFactory f = SessionFactoryImpl.newInstance();
			Map<String, String> parameter = new HashMap<String, String>();

			// user credentials
			parameter.put(SessionParameter.USER, "admin");
			parameter.put(SessionParameter.PASSWORD, "admin");

			// connection settings
			parameter.put(SessionParameter.ATOMPUB_URL,	"http://localhost:8080/alfresco/api/-default-/public/cmis/versions/1.1/atom");
			parameter.put(SessionParameter.BINDING_TYPE, BindingType.ATOMPUB.value());

			// session locale
			parameter.put(SessionParameter.LOCALE_ISO3166_COUNTRY, "");
			parameter.put(SessionParameter.LOCALE_ISO639_LANGUAGE, "de");

			// create session
			parameter.put(SessionParameter.REPOSITORY_ID, "-default-");
			this.session = f.createSession(parameter);
		}
		
		return this.session;
	}
}
