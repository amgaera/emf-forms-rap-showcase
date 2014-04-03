package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import java.lang.reflect.Method;

import org.eclipse.core.databinding.observable.Realm;

public class RealmSetter {

    public static void setRealm(Realm realm) {
        try {
            Class<Realm> clazz = Realm.class;
            Method method = clazz.getDeclaredMethod("setDefault", clazz);
            method.setAccessible(true);
            method.invoke(null, new Object[] { realm });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
