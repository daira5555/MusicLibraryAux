package model;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
//algo
@SuppressWarnings("serial")
public class CloseTabButton extends JTabbedPane{
	public CloseTabButton() {
		super();
	}
	
	/* addTab is for including a close button in every tab we want*/
	@Override
	public void addTab(String title, Icon icon, Component component, String tip) {
		super.addTab(title, icon, component, tip);
		int count = this.getTabCount() -1;
		setTabComponentAt(count, new CloseButtonTab(component,title,icon));
	}
	
	/* addTabNoExit is for including a tab without a close button*/
	public void addTabNoExit(String title, Icon icon, Component component, String tip) {
		super.addTab(title, icon, component, tip);
	}
	
	/*Button*/
	
	public class CloseButtonTab extends JPanel{
		private Component tab;
		
		public CloseButtonTab(final Component tab, String title, Icon icon) {
			this.tab = tab;
			setOpaque(false);
			FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3,3);
			setLayout(flowLayout);
			JLabel jLabel = new JLabel(title);
			jLabel.setIcon(icon);
			add(jLabel);
			JButton button = new JButton("X");
			button.setBackground(null);
			button.setBorder(null);
			button.setMargin(new Insets(0, 0, 0, 0));
			button.addMouseListener(new CloseListener(tab));
			add(button);
		}
	}
	
	/*ClickListener*/
	public class CloseListener implements MouseListener{
		private Component tab;
		
		public CloseListener(Component tab) {
			this.tab = tab;
		}
		
		@Override
		public void mouseClicked(MouseEvent e) {
			if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
                JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
                tabbedPane.remove(tab);
	        }
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			if(e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
			}
		}

		@Override
		public void mouseExited(MouseEvent e) {
			if(e.getSource() instanceof JButton) {
				JButton clickedButton = (JButton) e.getSource();
			}
		}

		@Override
		public void mousePressed(MouseEvent e) {}

		@Override
		public void mouseReleased(MouseEvent e) {}
	}
}
