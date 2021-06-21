import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Login extends JPanel {

	//
	JPanel p1, p2, p3;

	//

	JLabel lbLogo, lbLogo2, lbBotao, lbLO;
	JTextField txUsu;
	JPasswordField pfSenha;
	JButton btEntrar;
	ImageIcon imgLogo, imgLogo2, imgBotao;

	static ImageIcon imgLO;

	String usuario = "ceap";
	String senha = "pedreira";

	//

	JLabel label1, label2, label3, label4, label5;
	JButton btGravar, btExcluir, btAlterar, btNovo, btLocalizar, btSair, btProx;
	JTextField tfSala, tfMaterial, tfNome;
	JFormattedTextField tfData, tfHoras;
	MaskFormatter msData, msHoras;
	SistemaDAO s;

	//
	JScrollPane scrollTable;
	JTable table;
	BD bd;
	PreparedStatement st;
	ResultSet rs;
	JButton btVoltar;
	JLabel lbImgFundo;
	ImageIcon imgTab;
	JButton btAtu;

	public Login() {
		InicializarComponentes();
		DefinirEventos();
	}

	public void InicializarComponentes() {
		setLayout(null);

		p1 = new JPanel();
		p1.setLayout(null);
		p1.setBounds(0, 0, 800, 550);
		p1.setBackground(Color.white);

		imgLogo = new ImageIcon("C:\\imagens- java\\Fundo-Cadastro.jpeg");
		lbLogo = new JLabel(imgLogo);

		imgLO = new ImageIcon("C:\\imagens- java\\logo_frame_ceap.png");
		lbLO = new JLabel(imgLO);
		
		btEntrar = new JButton("Entrar");
		btEntrar.setFont(new Font("Times New Roman", Font.BOLD, 30));
		btEntrar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btEntrar.setForeground(Color.black);
		btEntrar.setContentAreaFilled(false);
		btEntrar.setBorder(null);

		txUsu = new JTextField();
		pfSenha = new JPasswordField();

		lbLogo.setBounds(0, 0, 770, 420);
		txUsu.setBounds(252, 200, 210, 25);
		pfSenha.setBounds(252, 273, 210, 25);
		btEntrar.setBounds(300, 320, 150, 40);

		p1.add(txUsu);
		p1.add(pfSenha);
		p1.add(btEntrar);
		p1.add(lbLogo);

		//

		p2 = new JPanel(null);
		p2.setLayout(null);
		p2.setBounds(0, 0, 800, 550);
		p2.setBackground(Color.white);
		try {
			msData = new MaskFormatter("##/##");
			msHoras = new MaskFormatter("##:##");

			tfData = new JFormattedTextField(msData);
			tfHoras = new JFormattedTextField(msHoras);
			tfSala = new JTextField(15);
			tfMaterial = new JTextField(10);
			tfNome = new JTextField();
			btGravar = new JButton("Gravar");
			btGravar.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btGravar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btGravar.setForeground(Color.white);
			btGravar.setContentAreaFilled(false);
			btGravar.setBorder(null);
			btAlterar = new JButton("Alterar");
			btAlterar.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btAlterar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btAlterar.setForeground(Color.white);
			btAlterar.setContentAreaFilled(false);
			btAlterar.setBorder(null);
			btNovo = new JButton("Novo");
			btNovo.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btNovo.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btNovo.setForeground(Color.white);
			btNovo.setContentAreaFilled(false);
			btNovo.setBorder(null);
			btLocalizar = new JButton("Localizar");
			btLocalizar.setFont(new Font("Times New Roman", Font.BOLD, 20));
			btLocalizar.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btLocalizar.setForeground(Color.white);
			btLocalizar.setContentAreaFilled(false);
			btLocalizar.setBorder(null);
			btProx = new JButton("Tabela");

			btProx.setFont(new Font("Arial", Font.BOLD, 20));
			btProx.setCursor(new Cursor(Cursor.HAND_CURSOR));
			btProx.setForeground(new Color(16, 78, 139));
			btProx.setContentAreaFilled(false);
			btProx.setBorder(null);

		} catch (ParseException erro) {
			JOptionPane.showMessageDialog(null, "Aqui ta o erro");
		}
		imgLogo2 = new ImageIcon("C:\\imagens- java\\Fundo-Sistema-CEAP.jpeg");
		lbLogo2 = new JLabel(imgLogo2);
		lbLogo2.setBounds(0, 0, 770, 420);
		tfData.setBounds(95, 175, 202, 20);
		tfHoras.setBounds(95, 105, 205, 20);
		tfSala.setBounds(490, 176, 205, 20);
		tfMaterial.setBounds(492, 244, 205, 20);
		tfNome.setBounds(490, 106, 205, 20);
		btGravar.setBounds(440, 315, 120, 25);
		btNovo.setBounds(200, 315, 120, 25);
		btAlterar.setBounds(30, 315, 120, 25);
		btLocalizar.setBounds(610, 315, 120, 25);
		btProx.setBounds(310, 360, 150, 30);

		p2.add(tfData);
		p2.add(tfHoras);
		p2.add(tfSala);
		p2.add(tfMaterial);
		p2.add(tfNome);
		p2.add(btAlterar);
		p2.add(btLocalizar);
		p2.add(btGravar);
		p2.add(btNovo);
		p2.add(btProx);
		p2.add(lbLogo2);

		setBotoes(true, true, false, false); 
		s = new SistemaDAO(); 
		if (!s.bd.getConnection()) {
			JOptionPane.showMessageDialog(null, "Falha na conexão!");
			System.exit(0);
		}

		//
		p3 = new JPanel(null);
		p3.setLayout(null);
		p3.setBounds(0, 0, 800, 550);
		p3.setBackground(Color.white);

		imgTab = new ImageIcon("C:\\imagens- java\\Fundo-tabela.jpeg");
		lbImgFundo = new JLabel(imgTab);
		lbImgFundo.setBounds(0, 0, 770, 420);

		btVoltar = new JButton("Voltar");
		btVoltar.setBounds(65, 340, 120, 30);
		btVoltar.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btVoltar.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btVoltar.setForeground(Color.black);
		btVoltar.setContentAreaFilled(false);
		btVoltar.setBorder(null);

		btExcluir = new JButton("Excluir");
		btExcluir.setBounds(550, 340, 120, 25);
		btExcluir.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btExcluir.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btExcluir.setForeground(Color.black);
		btExcluir.setContentAreaFilled(false);
		btExcluir.setBorder(null);
		
		btAtu = new JButton("Atualizar");
		btAtu.setBounds(310, 340, 120, 25);
		btAtu.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btAtu.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btAtu.setForeground(Color.black);
		btAtu.setContentAreaFilled(false);
		btAtu.setBorder(null);
		
		scrollTable = new JScrollPane();
		scrollTable.setBounds(20, 30, 725, 280);
		bd = new BD();

		
		p3.add(scrollTable);
		p3.add(btVoltar);
		p3.add(btExcluir);
		p3.add(btAtu);
		p3.add(lbImgFundo);
		
		
		adicionarTabela();

		p1.setVisible(true);
		p2.setVisible(false);
		p3.setVisible(false);

		add(p2);
		add(p1);
		add(p3);
	}

	public void limparCampos() {
		tfData.setText("");
		tfHoras.setText("");
		tfSala.setText("");
		tfMaterial.setText("");
		tfNome.setText("");
		setBotoes(true, true, false, false);
	}

	public void adicionarTabela() {
		
		try {
			if (!bd.getConnection()) {
				JOptionPane.showMessageDialog(null, "Falha na conex�o!");
				System.exit(0);
			}
			st = bd.c.prepareStatement(s.atualizarTabela());
			rs = st.executeQuery();
			DefaultTableModel tableModel = new DefaultTableModel(
					new String[] { "Data", "Horas", "Professor", "Sala", "Material" }, 0) {
			};

			table = new JTable(tableModel);
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();

			while (rs.next()) {
				try {
					String[] dados = new String[5];
					for (int i = 1; i <= 5; i++) {
						dados[i - 1] = rs.getString(i);

					}
					dtm.addRow(dados);
					System.out.println();
				} catch (SQLException erro) {

				}
				scrollTable.setViewportView(table);
			}
			rs.close();
			st.close();
			bd.close();
		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Comando Inválido" + erro.toString());
		}
		table.setFont(new Font("Times New Roman", Font.BOLD, 18));
		table.setForeground(new Color(16, 78, 139));
		table.setBackground(Color.white);
		DefaultTableCellRenderer Centro = new DefaultTableCellRenderer();
		Centro.setHorizontalAlignment(SwingConstants.CENTER);
		table.setRowHeight(40);
		table.getColumnModel().getColumn(0).setPreferredWidth(125);
		table.getColumnModel().getColumn(0).setResizable(true);
		table.getColumnModel().getColumn(0).setCellRenderer(Centro);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setCellRenderer(Centro);
		table.getColumnModel().getColumn(2).setPreferredWidth(170);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setCellRenderer(Centro);
		table.getColumnModel().getColumn(3).setPreferredWidth(125);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(3).setCellRenderer(Centro);
		table.getColumnModel().getColumn(4).setPreferredWidth(160);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(4).setCellRenderer(Centro);
		table.getTableHeader().setReorderingAllowed(false);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		

	}
	public void atualizarCampos() {
		s.Final.setProf(tfNome.getText());
		if (s.localizar()) {
			tfData.setText(s.Final.getData());
			tfHoras.setText(s.Final.getHoras());
			tfSala.setText(s.Final.getSala());
			tfMaterial.setText(s.Final.getMaterial());
			tfNome.setText(s.Final.getProf());

			setBotoes(true, true, false, true);

		} else {
			JOptionPane.showMessageDialog(null, "Professor não encontrado");
			limparCampos();
		}
	}

	public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar, boolean bAlterar) {
		btNovo.setEnabled(bNovo);
		btLocalizar.setEnabled(bLocalizar);
		btGravar.setEnabled(bGravar);
		btAlterar.setEnabled(bAlterar);
	}
	

	public void DefinirEventos() {
		btEntrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (!txUsu.getText().equals(usuario)) {
					JOptionPane.showMessageDialog(null, "Seu usuario  está incorreto");
					return;
				}
				if (!pfSenha.getText().equals(senha)) {
					JOptionPane.showMessageDialog(null, "Sua senha está incorreta");
					return;
				}

				p1.setVisible(false);
				p2.setVisible(true);
				p3.setVisible(false);
			}
		});

		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				setBotoes(false, false, true, false);

			}
		});

		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfData.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor preencha a data!");
					tfData.requestFocus();
					return;
				}
				if (tfHoras.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor preencha as horas!");
					tfHoras.requestFocus();
					return;
				}
				if (tfSala.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor preencha a sala!");
					tfSala.requestFocus();
					return;
				}
				if (tfNome.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Por favor preencha o nome do professor!");
					tfMaterial.requestFocus();
					return;
				}

				s.Final.setData(tfData.getText());
				s.Final.setHoras(tfHoras.getText());
				s.Final.setSala(tfSala.getText());
				s.Final.setMaterial(tfMaterial.getText());
				s.Final.setProf(tfNome.getText());

				JOptionPane.showMessageDialog(null, s.atualizar(SistemaDAO.INCLUSAO));
				s.atualizarTabela();
				limparCampos();
			}
		});

		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				s.Final.setData(tfData.getText());
				s.Final.setHoras(tfHoras.getText());
				s.Final.setSala(tfSala.getText());
				s.Final.setMaterial(tfMaterial.getText());
				JOptionPane.showMessageDialog(null, s.atualizar(SistemaDAO.ALTERACAO));
				limparCampos();
			}
		});
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String nomeProf = (String) table.getValueAt(table.getSelectedRow(), 2);

				s.Final.setProf(nomeProf);
				s.localizar();

				int n = JOptionPane.showConfirmDialog(null, nomeProf, "Excluir o professor?", JOptionPane.YES_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, s.atualizar(SistemaDAO.EXCLUSAO));
					int[] linhas = table.getSelectedRows();
					DefaultTableModel dtm = (DefaultTableModel) table.getModel();
					for (int i = (linhas.length - 1); i >= 0; --i) {
						dtm.removeRow(linhas[i]);

					}
				}

			}
		});

		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos();
			}
		});
		btProx.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.setVisible(false);
				p2.setVisible(false);
				p3.setVisible(true);

			}
		});

		btVoltar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p1.setVisible(false);
				p2.setVisible(true);
				p3.setVisible(false);

			}
		});
		btAtu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				s.atualizarTabela();
				adicionarTabela();
				
			}});
	}

	public static void main(String args[]) {
		JFrame frame = new JFrame("Secretária CEAP");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(new Login());
		frame.setIconImage(imgLO.getImage());
		frame.setBounds(200, 100, 785, 460);
		frame.setVisible(true);
		frame.setResizable(false);
	}
}
