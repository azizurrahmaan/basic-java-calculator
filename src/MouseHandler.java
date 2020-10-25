
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author azizu
 */
public class MouseHandler implements MouseListener {

    GUI ref;

    public MouseHandler(GUI gui) {
        ref = gui;
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(java.awt.event.MouseEvent evt) {
        evt.getComponent().setBackground(new java.awt.Color(219, 218, 218));
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        evt.getComponent().setBackground(new java.awt.Color(238, 238, 238));
    }

}
