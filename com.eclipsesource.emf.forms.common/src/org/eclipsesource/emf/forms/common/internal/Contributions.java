/*******************************************************************************
 * Copyright (c) 2011, 2013 EclipseSource and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    EclipseSource - initial API and implementation
 ******************************************************************************/
package org.eclipsesource.emf.forms.common.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.forms.main.IExampleContribution;
import org.eclipse.emf.forms.main.IExamplePage;
import org.eclipsesource.emf.forms.common.ButtonExamplePage;


class Contributions {

  private final List<IExampleContribution> contributions;

  Contributions() {
    contributions = new ArrayList<IExampleContribution>();
    collectContributions();
  }

  List<IExampleContribution> getContibutions() {
    return Collections.unmodifiableList( contributions );
  }

  private void collectContributions() {
    addContribution( "button", "Buttons", ButtonExamplePage.class );
  }

  private void addContribution( final String id,
                                final String title,
                                final Class<? extends IExamplePage> clazz )
  {
    IExampleContribution contribution = new IExampleContribution() {

      public String getId() {
        return id;
      }

      public String getTitle() {
        return title;
      }

      public IExamplePage createPage() {
        try {
          return clazz.newInstance();
        } catch( Exception exception ) {
          throw new RuntimeException( "Failed to instatiate class " + clazz.getName(), exception );
        }
      }
    };
    contributions.add( contribution );
  }

}
