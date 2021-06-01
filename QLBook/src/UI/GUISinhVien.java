package UI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileWriter;
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

import Entity.SinhVien;
import Service.SinhVienService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

public class GUISinhVien extends JFrame implements MouseListener, ActionListener{

	private JPanel contentPane;
	private JTextField txtmaso;
	private JTextField txtngaysinh;
	private DefaultTableModel datamodel; 
	private JPanel panel;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton btnThem,btnSua,btnXoaRong,btnTim;
	private SinhVienService sinhvienService= SinhVienService.GetInstance();
	private SinhVien mSinhVien = new SinhVien();
	private JTextField txtgioitinh;
	private JTextField txtemail;
	private JTextField txthoten;
	private JTextField txttim;
	private JLabel lblNewLabel_2;
	private JButton btnXuatGetAll;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUISinhVien frame = new GUISinhVien();
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
	public GUISinhVien() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 836, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblTitle = new JLabel("Quản Lý Sinh Viên");
		lblTitle.setBounds(243, 21, 327, 40);
		lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitle.setFont(new Font("Times New Roman", Font.BOLD, 32));
		contentPane.add(lblTitle);
		
		JLabel lblNewLabel = new JLabel("Mã số :");
		lblNewLabel.setBounds(31, 83, 78, 23);
		contentPane.add(lblNewLabel);
		
		txtmaso = new JTextField();
		txtmaso.setBounds(148, 84, 223, 23);
		contentPane.add(txtmaso);
		txtmaso.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Ngày sinh :");
		lblNewLabel_1.setBounds(31, 146, 78, 23);
		contentPane.add(lblNewLabel_1);
		
		txtngaysinh = new JTextField();
		txtngaysinh.setColumns(10);
		txtngaysinh.setBounds(148, 147, 223, 23);
		contentPane.add(txtngaysinh);
		
		String[]headers = {"Mã số","Họ tên","Ngày sinh","Giới tính","Email"};
		datamodel = new DefaultTableModel(headers,0);
		contentPane.add(scrollPane= new JScrollPane(table = new JTable(datamodel)));
		scrollPane.setBounds(28, 211, 700, 118);
		table.setFont(new Font("Tahoma", Font.PLAIN, 12));
		scrollPane.setBackground(SystemColor.scrollbar);
		
		panel = new JPanel();
		panel.setBackground(SystemColor.inactiveCaptionBorder);
		panel.setBorder(new TitledBorder(null, "Danh sách Sinh viên", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 180, 750, 161);
		contentPane.add(panel);
		panel.setLayout(null);
		
		btnThem = new JButton("Thêm");
		btnThem.setBounds(10, 441, 136, 40);
		contentPane.add(btnThem);
		
		btnSua = new JButton("Sửa");
		btnSua.setBounds(228, 441, 136, 40);
		contentPane.add(btnSua);
		
		btnThem.addActionListener(this);
		btnSua.addActionListener(this);
		table.addMouseListener(this);
		
		btnSua.setEnabled(false);
		
		removeTable();
		updateTableData();
		
		btnXoaRong = new JButton("Xoá Rỗng");
		btnXoaRong.setBounds(463, 441, 136, 40);
		contentPane.add(btnXoaRong);
		
		JLabel lblDesc = new JLabel("Giới tính :");
		lblDesc.setBounds(406, 99, 78, 23);
		contentPane.add(lblDesc);
		
		txtgioitinh = new JTextField();
		txtgioitinh.setColumns(10);
		txtgioitinh.setBounds(523, 100, 223, 23);
		contentPane.add(txtgioitinh);
		
		JLabel lblNewLabel_1_1 = new JLabel("Email :");
		lblNewLabel_1_1.setBounds(406, 145, 78, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(523, 146, 223, 23);
		contentPane.add(txtemail);
		
		txthoten = new JTextField();
		txthoten.setColumns(10);
		txthoten.setBounds(148, 118, 223, 23);
		contentPane.add(txthoten);
		
		JLabel lblNewLabel_1_2 = new JLabel("Họ tên : ");
		lblNewLabel_1_2.setBounds(31, 117, 78, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txttim = new JTextField();
		txttim.setColumns(10);
		txttim.setBounds(206, 368, 253, 31);
		contentPane.add(txttim);
		
		lblNewLabel_2 = new JLabel("Mã số sinh viên cần tìm :");
		lblNewLabel_2.setBounds(28, 368, 143, 31);
		contentPane.add(lblNewLabel_2);
		
		btnTim = new JButton("Tìm");
		btnTim.setBounds(488, 363, 136, 40);
		contentPane.add(btnTim);
		
		btnXuatGetAll = new JButton("Xuất File");
		btnXuatGetAll.setBounds(660, 363, 136, 40);
		contentPane.add(btnXuatGetAll);
		
		btnXoaRong.addActionListener(this);
		btnXuatGetAll.addActionListener(this);
		btnTim.addActionListener(this);
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
			SinhVien b = new SinhVien((txtmaso.getText()),txthoten.getText(),txtngaysinh.getText(),txtgioitinh.getText(),txtemail.getText());
			
			try {
				ketquaPOST=sinhvienService.POSTRequest(b);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketquaPOST==200)
			{
				removeTable();
				updateTableData();
				JOptionPane.showMessageDialog(table,"Bạn vừa thêm mới thông tin 1 sinh viên !","Chú ý",JOptionPane.CLOSED_OPTION);
				
			}
			else {
				JOptionPane.showMessageDialog(table,"Bạn vừa thêm mới thất bại thông tin 1 sinh viên !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
		}else if(o==btnSua) {
			try {
				mSinhVien=sinhvienService.GetOneSinhVien(Integer.parseInt(table.getValueAt(row, 0).toString()));
			} catch (NumberFormatException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			mSinhVien.setMaso((txtmaso.getText()));
			mSinhVien.setHoten(txthoten.getText());
			mSinhVien.setNgaysinh(txtngaysinh.getText());
			mSinhVien.setGioitinh(txtgioitinh.getText());
			mSinhVien.setEmail(txtemail.getText());
			try {
				ketquaPUT=sinhvienService.PUTRequest(mSinhVien);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(ketquaPUT==200) {
				removeTable();
				updateTableData();
				JOptionPane.showMessageDialog(table,"Bạn vừa cập nhật thông tin 1 sinh viên !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
			else
			{
				JOptionPane.showMessageDialog(table,"Bạn vừa Cập nhật thất bại thông tin 1 sinh viên !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
		}else if(o==btnXoaRong) {
			txtmaso.setText("");
			txtngaysinh.setText("");
			txthoten.setText("");
			txtgioitinh.setText("");
			txtemail.setText("");
			
			btnSua.setEnabled(false);
			mSinhVien=new SinhVien();
		}else if(o==btnTim) {
			SinhVien sv = new SinhVien();
			try {
				sv=sinhvienService.GetOneSinhVien(Integer.parseInt(txttim.getText()));
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			if(sv!=null) {
				txtmaso.setText(String.valueOf(sv.getMaso()));
				txthoten.setText(sv.getHoten());
				txtngaysinh.setText(sv.getNgaysinh());
				txtgioitinh.setText(sv.getGioitinh());
				txtemail.setText(sv.getEmail());
				JOptionPane.showMessageDialog(table,"Đã tìm thấy sinh viên !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
			else
				JOptionPane.showMessageDialog(table,"Không tìm thấy sinh viên !","Chú ý",JOptionPane.CLOSED_OPTION);
		}else if(o==btnXuatGetAll) {
			ArrayList<SinhVien>list=new ArrayList<>();
			try {
				list.addAll(sinhvienService.GetAllSinhVien());
			} catch (IOException e2) {
				// TODO Auto-generated catch block
				e2.printStackTrace();
			}
			if(list.size()!=0) {
				try {
		            FileWriter fw = new FileWriter("C:\\Users\\Vien\\Documents\\Ktra\\hotensv.txt");
		            for (SinhVien sv : list) {
		            	fw.write(sv.toString()+"\n");
		            }
		            
		            fw.close();
		        } catch (Exception e1) {
		            System.out.println(e1);
		        }
				JOptionPane.showMessageDialog(table,"Xuất thành công !","Chú ý",JOptionPane.CLOSED_OPTION);
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row =table.getSelectedRow();
		btnSua.setEnabled(true);
		
		
			txtmaso.setText(table.getValueAt(row, 0).toString());
			txthoten.setText(table.getValueAt(row, 1).toString());
			txtngaysinh.setText(table.getValueAt(row, 2).toString());
			txtgioitinh.setText(table.getValueAt(row, 3).toString());
			txtemail.setText(table.getValueAt(row, 4).toString());
			
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
		ArrayList<SinhVien>list=new ArrayList<>();
		try {
			list.addAll(sinhvienService.GetAllSinhVien());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(list.size()!=0)
		{
			for (SinhVien b : list) {
				
				
				String[] rowdata = {String.valueOf(b.getMaso()),b.getHoten(),b.getNgaysinh(),b.getGioitinh(),b.getEmail()};
				datamodel.addRow(rowdata);
			}
		}
	}
	public void removeTable() {
		DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
	}
}
