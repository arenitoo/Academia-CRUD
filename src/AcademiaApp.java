import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AcademiaApp {

    private JFrame frame;
    private JTable clienteTable;
    private JTable instrutorTable;
    private DefaultTableModel clienteTableModel;
    private DefaultTableModel instrutorTableModel;
    private ClienteDAO clienteDAO;
    private InstrutorDAO instrutorDAO;

    public AcademiaApp() {
        clienteDAO = new ClienteDAO();
        instrutorDAO = new InstrutorDAO();
        initialize();
    }

    private void initialize() {
        frame = new JFrame("Academia - Gerenciamento de Clientes e Instrutores");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 700);

        JTabbedPane tabbedPane = new JTabbedPane();

        JPanel clientePanel = criarPainelClientes();
        tabbedPane.addTab("Clientes", clientePanel);

        JPanel instrutorPanel = criarPainelInstrutores();
        tabbedPane.addTab("Instrutores", instrutorPanel);

        frame.add(tabbedPane);
        frame.setVisible(true);
    }

    private JPanel criarPainelClientes() {
        JPanel panel = new JPanel(new BorderLayout());

        clienteTableModel = new DefaultTableModel(new String[]{"ID", "Nome", "CPF", "Idade", "Telefone", "Email"}, 0);
        clienteTable = new JTable(clienteTableModel);
        JScrollPane scrollPane = new JScrollPane(clienteTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnAdd = new JButton("Adicionar Cliente");
        JButton btnUpdate = new JButton("Atualizar Cliente");
        JButton btnDelete = new JButton("Excluir Cliente");
        JButton btnList = new JButton("Listar Clientes");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnList);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> adicionarCliente());
        btnUpdate.addActionListener(e -> atualizarCliente());
        btnDelete.addActionListener(e -> excluirCliente());
        btnList.addActionListener(e -> listarClientes());

        return panel;
    }

    private JPanel criarPainelInstrutores() {
        JPanel panel = new JPanel(new BorderLayout());

        instrutorTableModel = new DefaultTableModel(new String[]{"ID", "Nome", "Especialidade", "Telefone", "Email"}, 0);
        instrutorTable = new JTable(instrutorTableModel);
        JScrollPane scrollPane = new JScrollPane(instrutorTable);
        panel.add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());

        JButton btnAdd = new JButton("Adicionar Instrutor");
        JButton btnUpdate = new JButton("Atualizar Instrutor");
        JButton btnDelete = new JButton("Excluir Instrutor");
        JButton btnList = new JButton("Listar Instrutores");

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnList);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        btnAdd.addActionListener(e -> adicionarInstrutor());
        btnUpdate.addActionListener(e -> atualizarInstrutor());
        btnDelete.addActionListener(e -> excluirInstrutor());
        btnList.addActionListener(e -> listarInstrutores());

        return panel;
    }

    private void adicionarCliente() {
        try {
            JTextField nomeField = new JTextField();
            JTextField cpfField = new JTextField();
            JTextField idadeField = new JTextField();
            JTextField telefoneField = new JTextField();
            JTextField emailField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(5, 2));
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("CPF:"));
            panel.add(cpfField);
            panel.add(new JLabel("Idade:"));
            panel.add(idadeField);
            panel.add(new JLabel("Telefone:"));
            panel.add(telefoneField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Adicionar Cliente", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Cliente cliente = new Cliente(
                        0,
                        nomeField.getText(),
                        cpfField.getText(),
                        Integer.parseInt(idadeField.getText()),
                        telefoneField.getText(),
                        emailField.getText()
                );
                clienteDAO.cadastrarCliente(cliente);
                listarClientes();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar cliente: " + e.getMessage());
        }
    }

    private void atualizarCliente() {
        int selectedRow = clienteTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um cliente para atualizar.");
            return;
        }

        int id = (int) clienteTableModel.getValueAt(selectedRow, 0);
        String nome = (String) clienteTableModel.getValueAt(selectedRow, 1);
        String cpf = (String) clienteTableModel.getValueAt(selectedRow, 2);
        int idade = (int) clienteTableModel.getValueAt(selectedRow, 3);
        String telefone = (String) clienteTableModel.getValueAt(selectedRow, 4);
        String email = (String) clienteTableModel.getValueAt(selectedRow, 5);

        JTextField nomeField = new JTextField(nome);
        JTextField cpfField = new JTextField(cpf);
        JTextField idadeField = new JTextField(String.valueOf(idade));
        JTextField telefoneField = new JTextField(telefone);
        JTextField emailField = new JTextField(email);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("CPF:"));
        panel.add(cpfField);
        panel.add(new JLabel("Idade:"));
        panel.add(idadeField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Atualizar cliente", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Cliente cliente = new Cliente(id, nomeField.getText(), cpfField.getText(), Integer.parseInt(idadeField.getText()), telefoneField.getText(), emailField.getText());
            clienteDAO.atualizarCliente(cliente);
            listarClientes();
        }
    }

    private void excluirCliente() {
        int selectedRow = clienteTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um cliente para excluir.");
            return;
        }

        int id = (int) clienteTableModel.getValueAt(selectedRow, 0);
        int result = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja excluir este cliente?", "Excluir Cliente", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            clienteDAO.excluirCliente(id);
            listarClientes();
        }
    }

    private void listarClientes() {
        clienteTableModel.setRowCount(0);
        try {
            List<Cliente> clientes = clienteDAO.listarClientes();
            for (Cliente cliente : clientes) {
                clienteTableModel.addRow(new Object[]{
                        cliente.getIdCliente(),
                        cliente.getNome(),
                        cliente.getCpf(),
                        cliente.getIdade(),
                        cliente.getTelefone(),
                        cliente.getEmail()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar clientes: " + e.getMessage());
        }
    }

    private void adicionarInstrutor() {
        try {
            JTextField nomeField = new JTextField();
            JTextField especialidadeField = new JTextField();
            JTextField telefoneField = new JTextField();
            JTextField emailField = new JTextField();

            JPanel panel = new JPanel(new GridLayout(4, 2));
            panel.add(new JLabel("Nome:"));
            panel.add(nomeField);
            panel.add(new JLabel("Especialidade:"));
            panel.add(especialidadeField);
            panel.add(new JLabel("Telefone:"));
            panel.add(telefoneField);
            panel.add(new JLabel("Email:"));
            panel.add(emailField);

            int result = JOptionPane.showConfirmDialog(frame, panel, "Adicionar Instrutor", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                Instrutor instrutor = new Instrutor(
                        0,
                        nomeField.getText(),
                        especialidadeField.getText(),
                        telefoneField.getText(),
                        emailField.getText()
                );
                instrutorDAO.cadastrarInstrutor(instrutor);
                listarInstrutores();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao adicionar instrutor: " + e.getMessage());
        }
    }

    private void atualizarInstrutor() {
        int selectedRow = instrutorTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um instrutor para atualizar.");
            return;
        }

        int id = (int) instrutorTableModel.getValueAt(selectedRow, 0);
        String nome = (String) instrutorTableModel.getValueAt(selectedRow, 1);
        String especialidade = (String) instrutorTableModel.getValueAt(selectedRow, 2);
        String telefone = (String) instrutorTableModel.getValueAt(selectedRow, 3);
        String email = (String) instrutorTableModel.getValueAt(selectedRow, 4);

        JTextField nomeField = new JTextField(nome);
        JTextField especialidadeField = new JTextField(especialidade);
        JTextField telefoneField = new JTextField(telefone);
        JTextField emailField = new JTextField(email);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        panel.add(new JLabel("Nome:"));
        panel.add(nomeField);
        panel.add(new JLabel("Especialidade:"));
        panel.add(especialidadeField);
        panel.add(new JLabel("Telefone:"));
        panel.add(telefoneField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);

        int result = JOptionPane.showConfirmDialog(frame, panel, "Atualizar Instrutor", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            Instrutor instrutor = new Instrutor(id, nomeField.getText(), especialidadeField.getText(), telefoneField.getText(), emailField.getText());
            instrutorDAO.atualizarInstrutor(instrutor);
            listarInstrutores();
        }
    }

    private void excluirInstrutor() {
        int selectedRow = instrutorTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Selecione um instrutor para excluir.");
            return;
        }

        int id = (int) instrutorTableModel.getValueAt(selectedRow, 0);
        int result = JOptionPane.showConfirmDialog(frame, "Tem certeza que deseja excluir este instrutor?", "Excluir Instrutor", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            instrutorDAO.excluirInstrutor(id);
            listarInstrutores();
        }
    }

    private void listarInstrutores() {
        instrutorTableModel.setRowCount(0);
        try {
            List<Instrutor> instrutores = instrutorDAO.listarInstrutores();
            for (Instrutor instrutor : instrutores) {
                instrutorTableModel.addRow(new Object[]{
                        instrutor.getIdInstrutor(),
                        instrutor.getNome(),
                        instrutor.getEspecialidade(),
                        instrutor.getTelefone(),
                        instrutor.getEmail()
                });
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Erro ao listar instrutores: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(AcademiaApp::new);
    }
}
