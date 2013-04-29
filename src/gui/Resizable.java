package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JMenu;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import componenttree.ComponentItem;
import componenttree.ContainerItem;

//Online Source: http://zetcode.com/tutorials/javaswingtutorial/resizablecomponent/

@SuppressWarnings("serial")
public class Resizable extends JComponent
{
	private int offsetForBounds = 10;
	private int minCompSize = 30;
	
	//These Items wrap this resizable.
	private ComponentItem compitem;
	private ContainerItem contitem;
	
	//this resizable wraps this component.
	private Component component;

	
	public Resizable(Component comp, Dimension s)
	{
		this(comp, new ResizableBorder(10));
	
	}
	
	public Resizable(Component comp, Dimension s, int size)
	{
		this(comp, new ResizableBorder(size));

	}

	public Resizable(Component comp, ResizableBorder border)
	{
		setLayout(new BorderLayout());
		add(comp);
		addMouseListener(resizeListener);
		addMouseMotionListener(resizeListener);
		setBorder(border);
		component = comp;
	}
	
	public void changeBorder(int size){
		ResizableBorder bord = new ResizableBorder(size);
		setBorder(bord);
	}
	
	public void setContainerItem(ContainerItem item){
		contitem = item;
	}
	public void setComponentItem(ComponentItem item){
		compitem = item;
	}
	
	public void changeSize(Dimension xy){
	}
	
	public boolean wrapsJPanel()
	{
		return (getComp().getClass().getSimpleName().equals("JPanel"));
	}

	//I know this is weird. One should always be null when the other is not.
	public ComponentItem getItem(){
		return contitem != null ? contitem : compitem;
	}
	public ComponentItem getCompItem()
	{
		return getItem();
	}
	
	public Component getComp(){
		return component;
	}
	
	public ContainerItem getContItem(){
		return contitem;
	}

	@SuppressWarnings("deprecation")
	public String getTextForGen()
	{
		if (component instanceof JButton)
		{
			return ((JButton) component).getText();
		}
		if (component instanceof JTextField)
		{
			return ((JTextField) component).getText();
		}
		if (component instanceof JTextArea)
		{
			return ((JTextArea) component).getText();
		}
		if (component instanceof JPasswordField)
		{
			return ((JPasswordField) component).getText();
		}
		if(component instanceof JMenu){
			return ((JMenu) component).getText();
		}
		return "";
	}

	private void resize()
	{
		if (getParent() != null)
		{
			((JComponent) getParent()).revalidate();
		}
	}

	MouseInputListener resizeListener = new MouseInputAdapter()
	{
		public void mouseMoved(MouseEvent me)
		{
			if (hasFocus())
			{
				ResizableBorder border = (ResizableBorder) getBorder();
				setCursor(Cursor.getPredefinedCursor(border.getCursor(me)));
			}
		}

		public void mouseExited(MouseEvent mouseEvent)
		{
			setCursor(Cursor.getDefaultCursor());
		}

		private int cursor;
		private Point startPos = null;

		public void mousePressed(MouseEvent me)
		{
			ResizableBorder border = (ResizableBorder) getBorder();
			cursor = border.getCursor(me);
			startPos = me.getPoint();
			requestFocus();
			repaint();
			validate();
		}

		private boolean boundsCheckin(Rectangle bounds, double dx, double dy)
		{
			return !(bounds.x + bounds.width + dx > getParent().getWidth() || bounds.x + dx < 0 || bounds.y + bounds.height + dy + offsetForBounds >= getParent().getHeight() || bounds.y + dy < 0);
		}

		public void mouseDragged(MouseEvent me)
		{
			if (startPos != null)
			{

				int x = getX();
				int y = getY();
				int w = getWidth();
				int h = getHeight();

				int dx = me.getX() - startPos.x;
				int dy = me.getY() - startPos.y;

				int sbound = y + h + dy + offsetForBounds;
				int nbound = y + dy;
				int wbound = x + dx;
				int ebound = x + w + dx + 2;
				switch (cursor)
				{
				case Cursor.N_RESIZE_CURSOR:
					if (!(h - dy < minCompSize))
					{
						if (!(nbound < 0))
						{
							setBounds(x, y + dy, w, h - dy);
							resize();
						}
					}
					break;

				case Cursor.S_RESIZE_CURSOR:
					if (!(h + dy < minCompSize))
					{
						if (!(sbound > getParent().getHeight()))
						{
							setBounds(x, y, w, h + dy);
							startPos = me.getPoint();
							resize();
						}
					}
					break;

				case Cursor.W_RESIZE_CURSOR:
					if (!(w - dx < minCompSize))
					{
						if (!(wbound < 0))
							setBounds(x + dx, y, w - dx, h);
						resize();
					}
					break;

				case Cursor.E_RESIZE_CURSOR:
					if (!(w + dx < minCompSize))
					{
						if (!(ebound > getParent().getWidth()))
						{
							setBounds(x, y, w + dx, h);
							startPos = me.getPoint();
							resize();
						}
					}
					break;

				case Cursor.NW_RESIZE_CURSOR:
					if (!(w - dx < minCompSize) && !(h - dy < minCompSize))
					{
						if (!(nbound < 0 || wbound < 0))
						{
							setBounds(x + dx, y + dy, w - dx, h - dy);
							resize();
						}
					}
					break;

				case Cursor.NE_RESIZE_CURSOR:
					if (!(w + dx < minCompSize) && !(h - dy < minCompSize))
					{
						if (!(nbound < 0 || ebound > getParent().getWidth()))
						{
							setBounds(x, y + dy, w + dx, h - dy);
							startPos = new Point(me.getX(), startPos.y);
							resize();
						}
					}
					break;

				case Cursor.SW_RESIZE_CURSOR:
					if (!(w - dx < minCompSize) && !(h + dy < minCompSize))
					{
						if (!(wbound < 0 || sbound > getParent().getHeight()))
						{
							setBounds(x + dx, y, w - dx, h + dy);
							startPos = new Point(startPos.x, me.getY());
							resize();
						}
					}
					break;

				case Cursor.SE_RESIZE_CURSOR:
					if (!(w + dx < minCompSize) && !(h + dy < minCompSize))
					{
						if (!(ebound > getParent().getWidth() || sbound > getParent().getHeight()))
							setBounds(x, y, w + dx, h + dy);
						startPos = me.getPoint();
						resize();
					}
					break;

				case Cursor.MOVE_CURSOR:
					Rectangle bounds = getBounds();
					if (boundsCheckin(bounds, dx, dy))
					{
						bounds.translate(dx, dy);
					}
					setBounds(bounds);
					resize();
				}
				setCursor(Cursor.getPredefinedCursor(cursor));
			}
		}

		public void mouseReleased(MouseEvent mouseEvent)
		{
			startPos = null;
		}
	};

	public void getText()
	{
		this.getTextForGen();
	}
}