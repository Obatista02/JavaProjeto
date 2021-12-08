package classe_de_conexao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class tela_cadastro extends JFrame {

	private JPanel contentPane;
	private JTextField tfID;
	private JTextField tfUSUARIO;
	private JTextField tgSENHA;
	private JButton btnNewButton;
	private JTextField tfbusca;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tela_cadastro frame = new tela_cadastro();
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
	public tela_cadastro() {
		setResizable(false);
		setTitle("CADASTRO");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 589, 437);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel.setBounds(26, 49, 49, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("USUARIO");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 103, 83, 37);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("SENHA");
		lblNewLabel_2.setFont(new Font("Arial", Font.BOLD, 14));
		lblNewLabel_2.setBounds(26, 175, 49, 22);
		contentPane.add(lblNewLabel_2);
		
		tfID = new JTextField();
		tfID.setEditable(false);
		tfID.setBounds(107, 49, 135, 28);
		contentPane.add(tfID);
		tfID.setColumns(10);
		
		tfUSUARIO = new JTextField();
		tfUSUARIO.setBounds(107, 108, 135, 29);
		contentPane.add(tfUSUARIO);
		tfUSUARIO.setColumns(10);
		
		tgSENHA = new JTextField();
		tgSENHA.setBounds(107, 169, 135, 28);
		contentPane.add(tgSENHA);
		tgSENHA.setColumns(10);
		
		btnNewButton = new JButton("SALVAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Conexao.faz_conexao();
					String sql = "insert into db_dados(usuario, senha)values(?, ?) ";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1,tfUSUARIO.getText());
					stmt.setString(2, tgSENHA.getText());
					
					stmt.execute();
					
					stmt.close();
					con.close();
					
					
					JOptionPane.showMessageDialog(null, "Iuuuuuuuuuuuupiiiiiiiii, Cadastrado");
					
					
				} catch (SQLException e1) {
					
					e1.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(63, 265, 144, 37);
		contentPane.add(btnNewButton);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "ABRIR DADOS", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(10, 323, 480, 76);
		contentPane.add(panel);
		panel.setLayout(null);
		
		tfbusca = new JTextField();
		tfbusca.setBounds(179, 27, 147, 38);
		panel.add(tfbusca);
		tfbusca.setColumns(10);
		
		JButton btnAbrir = new JButton("ABRIR");
		btnAbrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(tfbusca.getText().equals("")) {
					JOptionPane.showConfirmDialog(null, "Informe um id");
				}else {
					
				}
				
				try {
					Connection con = Conexao.faz_conexao();
					
					String sql = "select *from dados_senhas where id like ?";
					
					PreparedStatement stmt = con.prepareStatement(sql);
					
					stmt.setString(1,"%"+tfbusca.getText());
					
					ResultSet rs =stmt.executeQuery();
					
					while(rs.next()) {
						tfID.setText(rs.getString("id"));
						tfUSUARIO.setText(rs.getString("usuario"));
						tgSENHA.setText(rs.getNString("senha"));
					}
					
					rs.close();
					con.close();
					
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnAbrir.setBounds(10, 28, 144, 37);
		panel.add(btnAbrir);
		
		btnNewButton_1 = new JButton("EXCLUIR");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Connection con = Conexao.faz_conexao();
				String sql = "delete from dados_senhas where id=?";
				
				PreparedStatement stmt = con.prepareStatement(sql);
				
				stmt.setString(1, tfID.getText());
				
				stmt.execute();
				
				stmt.close();
				con.close();
				
				JOptionPane.showMessageDialog(null, "Excluido");
					
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
		});
		btnNewButton_1.setBounds(239, 272, 93, 30);
		contentPane.add(btnNewButton_1);
		
		
	}
}
