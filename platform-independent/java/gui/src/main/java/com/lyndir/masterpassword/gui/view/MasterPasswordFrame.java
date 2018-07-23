package com.lyndir.masterpassword.gui.view;

import com.lyndir.lhunath.opal.system.logging.Logger;
import com.lyndir.masterpassword.gui.Res;
import com.lyndir.masterpassword.gui.util.Components;
import com.lyndir.masterpassword.model.MPUser;
import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import javax.annotation.Nullable;
import javax.swing.*;


/**
 * @author lhunath, 2018-07-14
 */
@SuppressWarnings("serial")
public class MasterPasswordFrame extends JFrame implements FilesPanel.Listener, ComponentListener {

    private static final Logger logger = Logger.get( MasterPasswordFrame.class );

    @SuppressWarnings("FieldCanBeLocal")
    private final Components.GradientPanel root;
    private final FilesPanel               filesPanel = new FilesPanel();
    private final UserPanel                userPanel  = new UserPanel();

    @SuppressWarnings("MagicNumber")
    public MasterPasswordFrame() {
        super( "Master Password" );

        setDefaultCloseOperation( DISPOSE_ON_CLOSE );
        setContentPane( root = Components.gradientPanel( Res.colors().frameBg(), new FlowLayout() ) );
        root.setLayout( new BoxLayout( root, BoxLayout.PAGE_AXIS ) );
        root.setBorder( Components.marginBorder() );

        root.add( filesPanel );
        root.add( new JSeparator( SwingConstants.HORIZONTAL ) );
        root.add( Components.strut() );
        root.add( Components.borderPanel( BorderFactory.createRaisedBevelBorder(), Res.colors().controlBg(), userPanel ) );

        filesPanel.addListener( this );
        filesPanel.reload();

        addComponentListener(this  );
        setPreferredSize( new Dimension( 640, 480 ) );
        pack();

        setLocationByPlatform( true );
        setLocationRelativeTo( null );
    }

    @Override
    public void onUserSelected(@Nullable final MPUser<?> selectedUser) {
        userPanel.setUser( selectedUser );
    }

    @Override
    public void componentResized(final ComponentEvent e) {
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
    }

    @Override
    public void componentShown(final ComponentEvent e) {
        userPanel.transferFocus();
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
    }
}
