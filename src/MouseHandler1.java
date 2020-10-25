
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author azizu
 */
public class MouseHandler1  implements MouseListener{
     GUI ref;

    public MouseHandler1(GUI gui) {
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
        evt.getComponent().setBackground(new java.awt.Color(85, 123, 114));
    }

    @Override
    public void mouseExited(java.awt.event.MouseEvent evt) {
        evt.getComponent().setBackground(new java.awt.Color(244, 244, 244));
    }
}
