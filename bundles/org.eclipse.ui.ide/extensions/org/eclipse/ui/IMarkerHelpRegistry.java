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
package org.eclipse.ui;

import org.eclipse.core.resources.IMarker;

/**
 * Registry of F1 help contexts and resolutions for markers.
 * <p>
 * The information contained in the registry is read from the
 * org.eclipse.ui.markerhelp and org.eclipse.ui.markerresolution
 * extension points.
 * </p>
 *
 * @since 2.0
 */
public interface IMarkerHelpRegistry {
    /**
     * Returns a help context id for the given marker or
     * <code>null</code> if no help has been registered
     * for the marker.
     *
     * @param marker the marker for which to obtain help
     * @return the help context id
     * @since 2.0
     */
    public String getHelp(IMarker marker);

    /**
     * Returns <code>false</code> if there are no resolutions for
     * the given marker. Returns <code>true</code> if their may
     * be resolutions. In most cases a <code>true</code> value
     * means there are resolutions but due to plugin loading
     * issues getResolutions may sometimes return an empty array
     * after this method returns <code>true</code>.
     *
     * @param marker the marker for which to determine if there
     * are resolutions
     * @return <code>true</code> if there may be resolutions
     * @since 2.0
     */
    public boolean hasResolutions(IMarker marker);

    /**
     * Returns an array of resolutions for the given marker.
     * The returned array will be empty if there are no resolutions
     * for the marker.
     *
     * @param marker the marker for which to obtain resolutions
     * @return an array with the marker resolutions
     * @since 2.0
     */
    public IMarkerResolution[] getResolutions(IMarker marker);
}

