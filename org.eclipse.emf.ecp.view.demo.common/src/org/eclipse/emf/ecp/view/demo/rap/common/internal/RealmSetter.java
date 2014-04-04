package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import java.lang.reflect.Method;

import org.eclipse.core.databinding.observable.Realm;

public class RealmSetter {

	public static void setRealm(Realm realm) {
		try {
			final Class<Realm> clazz = Realm.class;
			final Method method = clazz.getDeclaredMethod("setDefault", clazz); //$NON-NLS-1$
			method.setAccessible(true);
			method.invoke(null, new Object[] { realm });
		} catch (final Exception exception) {
			exception.printStackTrace();
		}
	}

}
