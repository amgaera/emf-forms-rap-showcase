package rap;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.ecp.ui.view.ECPRendererException;
import org.eclipse.emf.ecp.ui.view.swt.ECPSWTViewRenderer;
import org.eclipse.emf.ecp.view.spi.rule.model.EnableRule;
import org.eclipse.emf.ecp.view.spi.rule.model.RuleFactory;
import org.eclipse.jface.databinding.swt.SWTObservables;
import org.eclipse.rap.rwt.RWT;
import org.eclipse.rap.rwt.application.AbstractEntryPoint;
import org.eclipse.rap.rwt.service.UISession;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;

import com.eclipsesource.makeithappen.model.task.TaskFactory;
import com.eclipsesource.makeithappen.model.task.User;


public class BasicEntryPoint extends AbstractEntryPoint {
	
	public User user;

    @Override
    protected void createContents(Composite parent) {
//        parent.setLayout(new GridLayout(2, false));
//        Group group = new Group(parent, SWT.NONE);
//        
//        Button checkbox = new Button(group, SWT.CHECK);
//        checkbox.setText("Hello");
//        checkbox.pack();
//        
        Button button = new Button(parent, SWT.PUSH);
        button.setText("World");
        
        user = TaskFactory.eINSTANCE.createUser();
        
        button.addSelectionListener(new SelectionListener() {

			@Override
			public void widgetSelected(SelectionEvent e) {
				System.out.println("widgetSelected");
				System.out.println(user);
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				// TODO Auto-generated method stub
				
			}
        	
        });
//        button.pack();
//        
//        Button button2 = new Button(parent, SWT.PUSH);
//        button2.setText("World2");
//        
//        group.setText("Group Label");
//        group.pack();
        //group.setVisible(true);
    	setupRealm(Display.getCurrent());
    	
        System.out.println(user);
        
        try {
			ECPSWTViewRenderer.INSTANCE.render(parent, user);
		} catch (ECPRendererException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    protected void setupRealm(Display display) {
        UISession uiSession = RWT.getUISession();
        if (uiSession.getAttribute("realm") == null) {
            Realm realm = SWTObservables.getRealm(display);
            RealmSetter.setRealm(realm);
            RWT.getUISession().setAttribute("realm", realm);
        }
    }

}
