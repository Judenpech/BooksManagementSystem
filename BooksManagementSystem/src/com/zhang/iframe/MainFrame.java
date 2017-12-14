package com.zhang.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.WindowConstants;
import javax.swing.border.BevelBorder;

import com.zhang.iframe.LoginIFrame;
import com.zhang.util.CreateIcon;
import com.zhang.util.MenuActions;

/**
 * 主窗体
 * 
 */
public class MainFrame extends JFrame {
	private static final JDesktopPane DESKTOP_PANE = new JDesktopPane();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			new LoginIFrame();// 登录窗口
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void addIFame(JInternalFrame iframe) { // 添加子窗体的方法
		DESKTOP_PANE.add(iframe);
	}

	public MainFrame() {
		super();
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setLocationByPlatform(true);
		setSize(900, 700);
		setTitle("图书信息管理系统");
		JMenuBar menuBar = createMenu(); // 调用创建菜单栏的方法
		setJMenuBar(menuBar);
		JToolBar toolBar = createToolBar(); // 调用创建工具栏的方法
		getContentPane().add(toolBar, BorderLayout.NORTH);
		final JLabel label = new JLabel();
		label.setBounds(0, 0, 0, 0);
		label.setIcon(null); // 窗体背景

		DESKTOP_PANE.addComponentListener(new ComponentAdapter() {
			public void componentResized(final ComponentEvent e) {
				Dimension size = e.getComponent().getSize();
				label.setSize(e.getComponent().getSize());
				label.setText("<html><img width=" + size.width + " height=" + size.height + " src='"
						+ this.getClass().getResource("/backImg.jpg") + "'></html>");
			}
		});
		DESKTOP_PANE.add(label, new Integer(Integer.MIN_VALUE));
		getContentPane().add(DESKTOP_PANE);
	}

	/**
	 * 创建工具栏
	 * 
	 * @return JToolBar
	 */
	private JToolBar createToolBar() { // 创建工具栏的方法
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		toolBar.setBorder(new BevelBorder(BevelBorder.RAISED));
		JButton bookAddButton = new JButton(MenuActions.BOOK_ADD);
		// ImageIcon icon=CreatecdIcon.add("bookAdd.bmp");//创建图标方法
		ImageIcon icon = new ImageIcon(MainFrame.class.getResource("/bookAddtb.jpg"));// 添加菜单栏图标
		bookAddButton.setIcon(icon);
		bookAddButton.setHideActionText(true);
		// bookAddButton.setToolTipText("fjdkjfk");//图片上提示字
		toolBar.add(bookAddButton);
		// toolBar.add(MenuActions.BOOK_MODIFY);
		// 在工具栏中添加图书修改与删除图标
		JButton bookModiAndDelButton = new JButton(MenuActions.BOOK_MODIFY);
		ImageIcon bookmodiicon = CreateIcon.add("bookModiAndDeltb.jpg");// 创建图标方法
		bookModiAndDelButton.setIcon(bookmodiicon);
		bookModiAndDelButton.setHideActionText(true);
		toolBar.add(bookModiAndDelButton);

		JButton bookTypeAddButton = new JButton(MenuActions.BOOKTYPE_ADD);
		ImageIcon bookTypeAddicon = CreateIcon.add("bookTypeAddtb.jpg");// 创建图标方法
		bookTypeAddButton.setIcon(bookTypeAddicon);
		bookTypeAddButton.setHideActionText(true);
		toolBar.add(bookTypeAddButton);

		JButton readerAddButton = new JButton(MenuActions.READER_ADD);
		ImageIcon readerAddicon = CreateIcon.add("readerAddtb.jpg");// 创建图标方法
		readerAddButton.setIcon(readerAddicon);
		readerAddButton.setHideActionText(true);
		toolBar.add(readerAddButton);

		JButton readerModiAndDelButton = new JButton(MenuActions.READER_MODIFY);
		ImageIcon readerModiAndDelicon = CreateIcon.add("readerModiAndDeltb.jpg");// 创建图标方法
		readerModiAndDelButton.setIcon(readerModiAndDelicon);
		readerModiAndDelButton.setHideActionText(true);
		toolBar.add(readerModiAndDelButton);

		JButton ExitButton = new JButton(MenuActions.EXIT);
		ImageIcon Exiticon = CreateIcon.add("exittb.jpg");// 创建图标方法
		ExitButton.setIcon(Exiticon);
		ExitButton.setHideActionText(true);
		toolBar.add(ExitButton);
		return toolBar;
	}

	/**
	 * 创建菜单栏
	 */
	private JMenuBar createMenu() { // 创建菜单栏的方法
		JMenuBar menuBar = new JMenuBar();

		JMenu baseMenu = new JMenu();// 初始化基础数据维护菜单
		baseMenu.setIcon(CreateIcon.add("jcsjcd.jpg"));
		{
			JMenu readerManagerMItem = new JMenu("读者信息管理");
			readerManagerMItem.add(MenuActions.READER_ADD);
			readerManagerMItem.add(MenuActions.READER_MODIFY);

			JMenu bookTypeManageMItem = new JMenu("图书类别管理");
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_ADD);
			bookTypeManageMItem.add(MenuActions.BOOKTYPE_MODIFY);

			JMenu menu = new JMenu("图书信息管理");
			menu.add(MenuActions.BOOK_ADD);
			menu.add(MenuActions.BOOK_MODIFY);

			baseMenu.add(readerManagerMItem);
			baseMenu.add(bookTypeManageMItem);
			baseMenu.add(menu);
			baseMenu.addSeparator();
			baseMenu.add(MenuActions.EXIT);
		}
		JMenu sysManageMenu = new JMenu(); // 系统维护
		sysManageMenu.setIcon(CreateIcon.add("jcwhcd.jpg"));
		JMenu userManageMItem = new JMenu("用户管理"); // 用户管理
		userManageMItem.add(MenuActions.USER_ADD);
		userManageMItem.add(MenuActions.USER_MODIFY);
		sysManageMenu.add(MenuActions.MODIFY_PASSWORD);
		sysManageMenu.add(userManageMItem);

		menuBar.add(baseMenu); // 添加基础数据维护菜单到菜单栏
		menuBar.add(sysManageMenu); // 添加系统维护菜单到菜单栏
		return menuBar;
	}
}
