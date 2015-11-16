
package MyButtons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class PokerButtons {
    protected int xPos;
    protected int yPos;
    protected int width;
    protected int height;
    protected Color color;
    protected String label;
    
    public PokerButtons()
    {
        color = Color.black;
        
    }
    public void drawButton(Graphics2D g,int _xpos,int _ypos,int _width,int _height,Color _color,String _lable)
	{
            xPos= _xpos;
            yPos = _ypos;
            width=_width;
            height=_height;
            color = _color;
            label = _lable;
            g.setColor(color);
            g.fillRoundRect(xPos,yPos,width,height,5,5);
            g.setColor(Color.white);
            int fontSize = 24;
            if(width>=400&&height>=100)
            {
                fontSize=48;
                g.setColor(new Color(40,59,64));
            }
            
            
            Font text = new Font("Futura",Font.BOLD,fontSize);           
            g.setFont(text);
            FontMetrics fontMetrics = g.getFontMetrics(text);
            int x=(int) (xPos+((width-fontMetrics.stringWidth(label))/2));
            int y = (int) (yPos+(height-fontMetrics.getAscent()))+(int) (fontMetrics.getAscent()/2);
            g.drawString(label,( x),  (y));
            
        }
    public void drawTextButton(Graphics2D g,int _xpos,int _ypos,int _width,int _height,Color _color,String _lable)
	{
            xPos= _xpos;
            yPos = _ypos;
            width=_width;
            height=_height;
            color = _color;
            label = _lable;
            g.setColor(Color.white);
            g.fillRoundRect(xPos,yPos,width,height,5,5);
            g.setColor(new Color(40,59,64));
            int fontSize = 24;
            if(width>=400&&height>=100)
            {
                fontSize=48;
                g.setColor(new Color(40,59,64));
            }
            
            
            Font text = new Font("Futura",Font.BOLD,fontSize);           
            g.setFont(text);
            FontMetrics fontMetrics = g.getFontMetrics(text);
            int x=(int) (xPos+((width-fontMetrics.stringWidth(label))/2));
            int y = (int) (yPos+(height-fontMetrics.getAscent()))+(int) (fontMetrics.getAscent()/2);
            g.drawString(label,( x),  (y));
            
        }
    public boolean buttonClicked(int mouseX,int mouseY)
    {
        
            if(mouseX>xPos&&mouseX<xPos+width&&mouseY>yPos&&mouseY<yPos+height)
            {
                return true;
            }
        return false;
    }
    
    
}
