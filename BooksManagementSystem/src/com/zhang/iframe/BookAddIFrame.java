package com.zhang.iframe;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.zhang.util.Item;
import com.zhang.dao.Dao;
import com.zhang.model.BookType;
import com.zhang.util.MyDocument;
import com.zhang.util.CreateIcon;

/**
 * 名称：图书信息添加窗体
 * 
 */
public class BookAddIFrame extends JInternalFrame {
	private JTextField publisher;
	private JTextField price;
	private JFormattedTextField pubDate;
	private JTextField translator;
	private JTextField writer;
	private JTextField ISBN;
	private JTextField bookName;
	private JComboBox bookType;
	private JButton buttonadd;
	private JButton buttonclose;
	DefaultComboBoxModel bookTypeModel;

	Map map = new HashMap();

	public BookAddIFrame() {
		super();
		final BorderLayout borderLayout = new BorderLayout();
		getContentPane().setLayout(borderLayout);
		setIconifiable(true); // 设置窗体可最小化
		setClosable(true); // 设置窗体可关闭
		setTitle("图书信息添加"); // 设置窗体标题
		setBounds(100, 100, 550, 300); // 设置窗体位置和大小

		final JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(10, 10, 20, 10));
		final GridLayout gridLayout = new GridLayout(0, 4);
		gridLayout.setVgap(5);
		gridLayout.setHgap(5);
		panel.setLayout(gridLayout);
		getContentPane().add(panel);
		final JLabel label_2 = new JLabel();
		label_2.setText("ISBN：");
		panel.add(label_2);

		ISBN = new JTextField("13位图书编号不能为空！", 13);
		ISBN.setDocument(new MyDocument(13)); // 设置书号文本框最大输入值为13
		ISBN.setColumns(13);
		ISBN.addKeyListener(new ISBNkeyListener());
		ISBN.addFocusListener(new ISBNFocusListener());
		panel.add(ISBN);

		final JLabel label = new JLabel();
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setText("类别：");
		panel.add(label);

		bookType = new JComboBox();
		bookTypeModel = (DefaultComboBoxModel) bookType.getModel();

		// 从数据库中取出图书类别
		List list = Dao.selectBookCategory();
		for (int i = 0; i < list.size(); i++) {
			BookType booktype = (BookType) list.get(i);
			Item item = new Item();
			item.setId((String) booktype.getId());
			item.setName((String) booktype.getTypeName());
			bookTypeModel.addElement(item);
		}
		panel.add(bookType);

		final JLabel label_1 = new JLabel();
		label_1.setText("书名：");
		panel.add(label_1);

		bookName = new JTextField();
		panel.add(bookName);

		final JLabel label_3 = new JLabel();
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setText("作者：");
		panel.add(label_3);

		writer = new JTextField();
		writer.setDocument(new MyDocument(10));
		panel.add(writer);

		final JLabel label_2_1 = new JLabel();
		label_2_1.setText("出版社：");
		panel.add(label_2_1);

		publisher = new JTextField();
		publisher.setDocument(new MyDocument(10));
		panel.add(publisher);

		final JLabel label_4 = new JLabel();
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setText("译者：");
		panel.add(label_4);

		translator = new JTextField();
		translator.setDocument(new MyDocument(10));
		panel.add(translator);

		final JLabel label_1_1 = new JLabel();
		label_1_1.setText("出版日期：");
		panel.add(label_1_1);

		SimpleDateFormat myfmt = new SimpleDateFormat("yyyy-MM-dd");
		pubDate = new JFormattedTextField(myfmt.getDateInstance());
		pubDate.setValue(new java.util.Date());
		panel.add(pubDate);
		final JLabel label_3_1 = new JLabel();
		label_3_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_3_1.setText("单价：");
		panel.add(label_3_1);
		price = new JTextField();
		price.setDocument(new MyDocument(5));
		price.addKeyListener(new NumberListener());
		panel.add(price);

		final JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(panel_1, BorderLayout.SOUTH);
		final FlowLayout flowLayout = new FlowLayout();
		flowLayout.setVgap(2);
		flowLayout.setHgap(30);
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panel_1.setLayout(flowLayout);

		buttonadd = new JButton();
		buttonadd.addActionListener(new addBookActionListener());
		buttonadd.setText("添加");
		panel_1.add(buttonadd);

		buttonclose = new JButton();
		buttonclose.addActionListener(new CloseActionListener());
		buttonclose.setText("退出");
		panel_1.add(buttonclose);

		final JLabel label_5 = new JLabel();
		ImageIcon bookAddIcon = CreateIcon.add("newBookorderImg.jpg");
		label_5.setIcon(bookAddIcon);
		label_5.setPreferredSize(new Dimension(400, 80));
		label_5.setBorder(new LineBorder(SystemColor.activeCaptionBorder, 1, false));
		getContentPane().add(label_5, BorderLayout.NORTH);
		//label_5.setText("新书定购(LOGO图片)");

		setVisible(true); // 显示窗体可关闭－－－必须在添加所有控件之后执行该语句
	}

	class ISBNFocusListener extends FocusAdapter {
		public void focusLost(FocusEvent e) {
			if (!Dao.selectBookInfo(ISBN.getText().trim()).isEmpty()) {
				JOptionPane.showMessageDialog(null, "您输入的ISBN已存在！");
				return;
			}
		}
	}

	class ISBNkeyListener extends KeyAdapter {
		public void keyPressed(final KeyEvent e) {
			if (e.getKeyCode() == 13) {
				buttonadd.doClick();
			}
		}
	}

	class CloseActionListener implements ActionListener { // 添加关闭按钮的事件监听器
		public void actionPerformed(final ActionEvent e) {
			doDefaultCloseAction();
		}
	}

	class addBookActionListener implements ActionListener { // 添加按钮的单击事件监听器
		public void actionPerformed(final ActionEvent e) {
			if (ISBN.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "图书编号不能为空！");
				return;
			}
			if (ISBN.getText().length() != 13) {
				JOptionPane.showMessageDialog(null, "请输入正确的13位图书编号！");
				return;
			}
			if (bookName.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "书名不能为空！");
				return;
			}
			if (writer.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "作者不能为空！");
				return;
			}
			if (pubDate.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "出版日期不能为空！");
				return;
			}
			if (price.getText().length() == 0) {
				JOptionPane.showMessageDialog(null, "单价不能为空！");
				return;
			}
			String ISBNs = ISBN.getText().trim();
			// 分类
			Object selectedItem = bookType.getSelectedItem();
			if (selectedItem == null)
				return;
			Item item = (Item) selectedItem;
			String bookTypes = item.getId();

			String translators = translator.getText().trim();
			String bookNames = bookName.getText().trim();
			String writers = writer.getText().trim();
			String publishers = (String) publisher.getText().trim();
			String pubDates = pubDate.getText().trim();
			String prices = price.getText().trim();
			int i = Dao.Insertbook(ISBNs, bookTypes, bookNames, writers, translators, publishers,
					java.sql.Date.valueOf(pubDates), Double.parseDouble(prices));

			if (i == 1) {
				JOptionPane.showMessageDialog(null, "图书信息添加成功！");
				doDefaultCloseAction();
			}
		}
	}

	class NumberListener extends KeyAdapter {
		public void keyTyped(KeyEvent e) {
			String numStr = "0123456789." + (char) 8;
			if (numStr.indexOf(e.getKeyChar()) < 0) {
				e.consume();
			}
		}
	}
}
