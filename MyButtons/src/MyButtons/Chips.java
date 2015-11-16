
package MyButtons;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;

public class Chips extends PokerButtons{
    private int value;
    private static int numChips=0;
    public Image chipPic;
    
    public Chips(int _value)
    {
        super();
        value=_value;
    }
    public Chips(String _label)
    {
        super();
        label=_label;
    }
    public Chips(String _label,int _value)
    {
        super();
        label=_label;
        value= _value;
    }
    public void drawButton(Graphics2D g,int _xpos,int _ypos,int _width, Color _color)
	{
            xPos= _xpos;
            yPos = _ypos;
            width=_width;
            color = _color;
            
            if(label==null)
                label = "$"+value;
            
            
            g.setColor(color);
            if(chipPic==null)
            g.fillOval(xPos,yPos,_width,_width);
            else
            g.drawImage(chipPic, xPos, yPos, width, width, null);
            
            g.setColor(Color.white);
            
            int fontSize=20;
            Font text = new Font("Futura",Font.BOLD,fontSize);           
            g.setFont(text);
            FontMetrics fontMetrics = g.getFontMetrics(text);
            int x=(int) (xPos+((width-fontMetrics.stringWidth(label))/2));
            int y = (int) (yPos+width/2+5);
            g.drawString(label,( x),  (y));
            
            
        }
    public boolean buttonClicked(int mouseX,int mouseY)
    {
        int centerX=xPos+width/2;
        int centery=yPos+width/2;
        int radius = width/2;
        int distance = (int) (Math.sqrt(Math.abs(mouseX-centerX)*Math.abs(mouseX-centerX)+Math.abs(mouseY-centery)*Math.abs(mouseY-centery)));
        
            if(distance<radius-2)//offset because the images draw slightly smaller
            {
                return true;
            }
        return false;
    }
    public int getValue()
    {
        return value;
    }
    public String getLabel()
    {
        return label;
    }
}
