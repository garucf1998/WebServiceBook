package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import Entity.Book;
import Service.BookService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class UIBook extends JFrame implements MouseListener, ActionListener{

	private JPanel contentPane;
	private JTextField txtID;
	private JTextField txtName;
	private DefaultTableModel datamodel; 
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnThem,btnXoa,btnSua,btnXoaRong;
	private BookService bookService= BookService.GetInstance();
	private Book mBook = new Book();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIBook frame = new UIBook();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public UIBook() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 479);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Quản Lý Sách");
		lblTitle.setBounds(243, 21, 327, 40);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
		contentPane.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("ID :");
		lblNewLabel.setBounds(31, 100, 78, 23);
		contentPane.add(lblNewLabel);
		
		txtID = new JTextField();
		txtID.setBounds(148, 101, 223, 23);
		contentPane.add(txtID);
		txtID.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Name :");
		lblNewLabel_1.setBounds(31, 146, 78, 23);
		contentPane.add(lblNewLabel_1);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(148, 147, 223, 23);
		contentPane.add(txtName);
		
		String[]headers = {"ID","Name"};
		datamodel = new DefaultTableModel(headers,0);
		contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
		scrollPane.setBounds(28, 211, 700, 118);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBackground(SystemColor.scrollbar);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBorder(new TitledBorder(null, "Danh sách nhân viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 180, 750, 161);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(10, 366, 136, 40);
		contentPane.add(btnThem);
		
		btnXoa = new JButton("Xóa");
		btnXoa.setBounds(180, 366, 136, 40);
		contentPane.add(btnXoa);
		
		btnSua = new JButton("Sửa");
		btnSua.setBounds(359, 366, 136, 40);
		contentPane.add(btnSua);
		
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
		
		btnSua.setEnabled(false);
		btnXoa.setEnabled(false);
		
		removeTable();
		updateTableData();
		txtID.setEnabled(false);
		
		btnXoaRong = new JButton("Xoá Rỗng");
		btnXoaRong.setBounds(525, 366, 136, 40);
		contentPane.add(btnXoaRong);
		btnXoaRong.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		int row = table.getSelectedRow();
		int ketquaPOST=0;
		int ketquaPUT=0;
		int ketquaDELETE=0;
		
		if(o==btnThem) {
			Book b = new Book();
			b.setName(txtName.getText());
			try {
				ketquaPOST=bookService.POSTRequest(b);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketquaPOST==200)
			{
				removeTable();
				updateTableData();
				JOptionPane.showMessageDialog(table,"Bạn vừa thêm mới thông tin 1 sách !","Chú ý",JOptionPane.CLOSED_OPTION);
				
			}
			else {
				JOptionPane.showMessageDialog(table,"Bạn vừa thêm mới thất bại thông tin 1 sách !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
		}else if(o==btnXoa) {
			
			try {
				ketquaDELETE=bookService.DeleteBook(Long.parseLong(table.getValueAt(row, 0).toString()));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketquaDELETE==200) {
				removeTable();
				updateTableData();
				JOptionPane.showMessageDialog(table,"Bạn vừa xóa thông tin 1 sách !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
			else
			{
				JOptionPane.showMessageDialog(table,"Bạn vừa xóa thất bại thông tin 1 sách !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
		}else if(o==btnSua) {
			try {
				mBook=bookService.GetOneBook(Long.parseLong(table.getValueAt(row, 0).toString()));
			} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			mBook.setName(txtName.getText());
			try {
				ketquaPUT=bookService.PUTRequest(mBook);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketquaPUT==200) {
				removeTable();
				updateTableData();
				JOptionPane.showMessageDialog(table,"Bạn vừa cập nhật thông tin 1 sách !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
			else
			{
				JOptionPane.showMessageDialog(table,"Bạn vừa Cập nhật thất bại thông tin 1 sách !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
		}else if(o==btnXoaRong) {
			txtID.setText("");
			txtName.setText("");
			btnSua.setEnabled(false);
			btnXoa.setEnabled(false);
			mBook=new Book();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row =table.getSelectedRow();
		btnXoa.setEnabled(true);
		btnSua.setEnabled(true);
		
		
			txtID.setText(table.getValueAt(row, 0).toString());
			txtName.setText(table.getValueAt(row, 1).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	public void updateTableData() 
	{
		ArrayList<Book>list=new ArrayList<>();
		try {
			list.addAll(bookService.GetAllBook());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.get(0)!=null)
		{
			for (Book b : list) {
				
				
				String[] rowdata = {String.valueOf(b.getId()).toString(),b.getName()};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
}
