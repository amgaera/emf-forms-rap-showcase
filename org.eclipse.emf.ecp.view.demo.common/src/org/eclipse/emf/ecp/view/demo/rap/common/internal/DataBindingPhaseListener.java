package org.eclipse.emf.ecp.view.demo.rap.common.internal;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.lifecycle.PhaseEvent;
import org.eclipse.rap.rwt.lifecycle.PhaseId;
import org.eclipse.rap.rwt.lifecycle.PhaseListener;

@SuppressWarnings({ "serial", "deprecation" })
public class DataBindingPhaseListener implements PhaseListener {

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.PROCESS_ACTION;
	}

	@Override
	public void beforePhase(PhaseEvent event) {
		final Realm realm = (Realm) RWT.getUISession().getAttribute("realm"); //$NON-NLS-1$
		RealmSetter.setRealm(realm);
	}

	@Override
	public void afterPhase(PhaseEvent event) {
		RealmSetter.setRealm(null);
	}
}