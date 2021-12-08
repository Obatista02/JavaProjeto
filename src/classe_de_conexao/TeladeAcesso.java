package classe_de_conexao;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;

public class TeladeAcesso extends JFrame {

	private JPanel contentPane;
	private JTextField tfUsuario_1;
	private JPasswordField pfSenha_1;
	protected JLabel tfUsuario;
	protected JLabel pfSenha;
	private JPasswordField senha;
	private JTextField usuario;
	private JTextField textousuario;
	private JPasswordField senhatexto;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeladeAcesso frame = new TeladeAcesso();
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
	public TeladeAcesso() {
		setResizable(false);
		setTitle("Tela de Acesso");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 519, 397);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel iblUsuario = new JLabel("Usuario");
		iblUsuario.setForeground(Color.RED);
		iblUsuario.setFont(new Font("Arial Black", Font.BOLD, 16));
		iblUsuario.setBounds(37, 67, 113, 47);
		contentPane.add(iblUsuario);

		JLabel iblSenha = new JLabel("Senha");
		iblSenha.setForeground(Color.RED);
		iblSenha.setFont(new Font("Arial Black", Font.BOLD, 16));
		iblSenha.setBounds(37, 140, 84, 47);
		contentPane.add(iblSenha);

		JButton btnNewButton = new JButton("Entrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					Connection con = Conexao.faz_conexao();

					String sql = "select *from dados_senhas where usuario=? and senha=?";

					PreparedStatement stmt = con.prepareStatement(sql);

					stmt.setString(1, textousuario.getText());
					stmt.setString(2, new String(senhatexto.getPassword()));

					ResultSet rs = stmt.executeQuery();

					if (rs.next()) {
						tela_cadastro exibir = new tela_cadastro();
						exibir.setVisible(true);
						setVisible(false);

					} else {
						JOptionPane.showMessageDialog(null, "Usuario ou Senha incorreta");
					}
					stmt.close();
					con.close();

				} catch (SQLException e1) {

					e1.printStackTrace();
				}

			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD, 16));
		btnNewButton.setBounds(131, 213, 171, 39);
		contentPane.add(btnNewButton);

		textousuario = new JTextField();
		textousuario.setBounds(132, 83, 294, 31);
		contentPane.add(textousuario);
		textousuario.setColumns(10);

		senhatexto = new JPasswordField();
		senhatexto.setBounds(131, 140, 295, 39);
		contentPane.add(senhatexto);

	}
}
