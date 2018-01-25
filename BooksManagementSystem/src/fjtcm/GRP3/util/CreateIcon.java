package fjtcm.GRP3.util;

import java.net.URL;
import javax.swing.ImageIcon;
import fjtcm.GRP3.iframe.MainFrame;
//添加图片类，在窗体上添加JPanel，然后在JPanel上添加JLabel，
//将图片初始化为ImageIcon，使用JLabel.setIcon实现在窗体上添加图片的功能。
public class CreateIcon {
	public static ImageIcon add(String ImageName) {
		URL IconUrl = MainFrame.class.getResource("/" + ImageName);
		ImageIcon icon = new ImageIcon(IconUrl);
		return icon;
	}
}

