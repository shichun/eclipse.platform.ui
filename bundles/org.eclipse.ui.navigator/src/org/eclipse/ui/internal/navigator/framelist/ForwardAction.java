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
package org.eclipse.ui.internal.navigator.framelist;

import org.eclipse.osgi.util.NLS;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;

/**
 * Generic "Forward" action which goes forward one frame.
 * @since 3.4
 */
public class ForwardAction extends FrameAction {

	private static final String ID = "org.eclipse.ui.framelist.forward"; //$NON-NLS-1$

	/**
     * Constructs a new action for the specified frame list.
     *
     * @param frameList the frame list
     */
    public ForwardAction(FrameList frameList) {
        super(frameList);
        setId(ID);
        setText(FrameListMessages.Forward_text);
        ISharedImages images = PlatformUI.getWorkbench().getSharedImages();
        setImageDescriptor(images
                .getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD));
        setDisabledImageDescriptor(images
                .getImageDescriptor(ISharedImages.IMG_TOOL_FORWARD_DISABLED));
        PlatformUI.getWorkbench().getHelpSystem().setHelp(this,
				IFrameListHelpContextIds.FORWARD_ACTION);
        update();
    }

    private Frame getNextFrame() {
        FrameList list = getFrameList();
        return list.getFrame(list.getCurrentIndex() + 1);
    }

    private String getToolTipText(Frame nextFrame) {
        if (nextFrame != null) {
            String text = nextFrame.getToolTipText();
            if (text != null && text.length() > 0) {
                return NLS.bind(FrameListMessages.Forward_toolTipOneArg, text);
            }
        }
        return FrameListMessages.Forward_toolTip;
    }

    /**
     * Calls <code>forward()</code> on the frame list.
     */
    @Override
	public void run() {
        getFrameList().forward();
    }

    /**
     * Updates this action's enabled state and tool tip text.
     * This action is enabled only when there is a next frame in the frame list.
     * The tool tip text is "Forward to " plus the tool tip text for the next
     * frame.
     */
    @Override
	public void update() {
        super.update();
        Frame nextFrame = getNextFrame();
        setEnabled(nextFrame != null);
        setToolTipText(getToolTipText(nextFrame));
    }

}
