/*******************************************************************************
 * Copyright (c) 2000, 2015 IBM Corporation and others.
 *
 * This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License 2.0
 * which accompanies this distribution, and is available at
 * https://www.eclipse.org/legal/epl-2.0/
 *
 * SPDX-License-Identifier: EPL-2.0
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jface.viewers;

import java.util.EventObject;

import org.eclipse.core.runtime.Assert;

/**
 * Event object describing an open which may be generated from a
 * selection or default selection event. The source of these
 * events is a viewer.
 *
 * @see IOpenListener
 */
public class OpenEvent extends EventObject {

    /**
     * Generated serial version UID for this class.
     * @since 3.1
     */
    private static final long serialVersionUID = 3761972652973176117L;

    /**
     * The selection.
     */
    protected ISelection selection;

    /**
     * Creates a new event for the given source and selection.
     *
     * @param source the viewer
     * @param selection the selection
     */
    public OpenEvent(Viewer source, ISelection selection) {
        super(source);
        Assert.isNotNull(selection);
        this.selection = selection;
    }

    /**
     * Returns the selection.
     *
     * @return the selection
     */
    public ISelection getSelection() {
        return selection;
    }

    /**
     * Returns the viewer that is the source of this event.
     *
     * @return the originating viewer
     */
    public Viewer getViewer() {
        return (Viewer) getSource();
    }
}
