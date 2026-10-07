import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.sql.*;

/** Tela de Relatório Financeiro: consome a VIEW vw_resumo_por_plano. */
public class Relatorios extends JFrame {

    private DefaultTableModel modelo;
    private JLabel lblTotais;

    public Relatorios() {
        setTitle("Relatório Financeiro por Plano");
        setSize(720, 380);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel(
            new String[]{"Plano", "Matrículas", "Ativas", "Canceladas", "Receita (R$)"}, 0) {
            public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable tabela = new JTable(modelo);
        tabela.setRowHeight(24);

        JButton btnAtualizar = new JButton("🔄 Atualizar");
        btnAtualizar.addActionListener(e -> carregar());
        lblTotais = new JLabel(" ");
        lblTotais.setFont(new Font("Arial", Font.BOLD, 13));

        JPanel topo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topo.add(btnAtualizar);
        JPanel rodape = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rodape.add(lblTotais);

        setLayout(new BorderLayout(0, 6));
        add(topo, BorderLayout.NORTH);
        add(new JScrollPane(tabela), BorderLayout.CENTER);
        add(rodape, BorderLayout.SOUTH);
        carregar();
    }

    private void carregar() {
        modelo.setRowCount(0);
        double receita = 0;
        int total = 0;
        String sql = "SELECT plano, total_matriculas, ativas, canceladas, receita_total "
                   + "FROM vw_resumo_por_plano ORDER BY receita_total DESC";   // VIEW do banco
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                modelo.addRow(new Object[]{
                    rs.getString("plano"), rs.getInt("total_matriculas"),
                    rs.getInt("ativas"), rs.getInt("canceladas"),
                    String.format("%.2f", rs.getDouble("receita_total"))});
                receita += rs.getDouble("receita_total");
                total   += rs.getInt("total_matriculas");
            }
            lblTotais.setText(String.format("Total: %d matrículas  |  Receita: R$ %.2f", total, receita));
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar relatório: " + ex.getMessage());
        }
    }
}
