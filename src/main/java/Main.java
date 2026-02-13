import dao.*;
import model.*;

import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int entidade = 0;

        do {
            System.out.println("\n--- SISTEMA ACADÊMICO ---");
            System.out.println("|1 - PESSOA (Genérico)");
            System.out.println("|2 - PROFESSOR");
            System.out.println("|3 - ALUNO");
            System.out.println("|4 - NOTA");
            System.out.println("|5 - DISCIPLINA");
            System.out.println("|0 - SAIR");
            System.out.print("Escolha uma opção: ");

            try {
                String input = sc.nextLine();
                if (input.isEmpty()) continue;
                entidade = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Opção inválida.");
                continue;
            }

            if (entidade == 0) break;

            if (entidade >= 1 && entidade <= 5) {
                System.out.println("\n--- OPERAÇÃO ---");
                System.out.println("1 - CADASTRAR (Create)");
                System.out.println("2 - LISTAR (Read)");
                System.out.println("3 - ATUALIZAR (Update)");
                System.out.println("4 - DELETAR (Delete)");
                System.out.println("0 - VOLTAR");
                System.out.print("Escolha: ");

                int op = 0;
                try {
                    String input = sc.nextLine();
                    if (!input.isEmpty()) op = Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    op = -1;
                }

                if (op == 0) continue;

                try {
                    switch (entidade) {
                        case 1: processarPessoa(op, sc); break;
                        case 2: processarProfessor(op, sc); break;
                        case 3: processarAluno(op, sc); break;
                        case 4: processarNota(op, sc); break;
                        case 5: processarDisciplina(op, sc); break;
                    }
                } catch (Exception e) {
                    System.err.println("Erro ao processar operação: " + e.getMessage());
                }
            } else {
                System.out.println("Opção inválida!");
            }

        } while (entidade != 0);

        System.out.println("Sistema encerrado.");
        sc.close();
    }

    private static void processarPessoa(int op, Scanner sc) throws Exception {
        PessoaDAO dao = new PessoaDAO();
        switch (op) {
            case 1:
                System.out.println("--- NOVA PESSOA ---");
                System.out.print("Nome: "); String nome = sc.nextLine();
                System.out.print("CPF: "); String cpf = sc.nextLine();
                System.out.print("Email: "); String email = sc.nextLine();
                System.out.print("Telefone: "); String tel = sc.nextLine();
                System.out.print("Nascimento (YYYY-MM-DD): ");
                LocalDate data = LocalDate.parse(sc.nextLine());

                //O banco que vai gerar o id!
                Pessoa p = new Pessoa(0, nome, cpf, email, tel, data);
                dao.save(p);
                break;

            case 2:
                System.out.println("--- LISTA DE PESSOAS ---");
                for (Pessoa item : dao.read()) {
                    System.out.println(item);
                }
                break;

            case 3:
                System.out.println("--- ATUALIZAR PESSOA ---");
                System.out.print("Informe o ID da Pessoa que deseja alterar: ");
                int idBusca = Integer.parseInt(sc.nextLine());

                System.out.print("Novo Nome: "); String nN = sc.nextLine();
                System.out.print("Novo CPF: "); String nC = sc.nextLine();
                System.out.print("Novo Email: "); String nE = sc.nextLine();
                System.out.print("Novo Telefone: "); String nT = sc.nextLine();
                System.out.print("Nova Data (YYYY-MM-DD): ");
                LocalDate nD = LocalDate.parse(sc.nextLine());

                dao.update(new Pessoa(idBusca, nN, nC, nE, nT, nD));
                System.out.println("Dados atualizados com sucesso!");
                break;

            case 4:
                System.out.print("Informe o ID para deletar: ");
                dao.delete(Integer.parseInt(sc.nextLine()));
                System.out.println("Pessoa deletada!");
                break;
        }
    }

    private static void processarAluno(int op, Scanner sc) throws Exception {
        AlunoDAO alunoDao = new AlunoDAO();
        PessoaDAO pessoaDao = new PessoaDAO();

        switch (op) {
            case 1:
                System.out.println("--- NOVO ALUNO ---");
                System.out.print("Nome: "); String n = sc.nextLine();
                System.out.print("CPF: "); String c = sc.nextLine();
                System.out.print("Email: "); String e = sc.nextLine();
                System.out.print("Telefone: "); String t = sc.nextLine();
                System.out.print("Nascimento (YYYY-MM-DD): "); LocalDate d = LocalDate.parse(sc.nextLine());
                System.out.print("Matrícula: "); String mat = sc.nextLine();
                System.out.print("Data Matrícula (YYYY-MM-DD): "); LocalDate dMat = LocalDate.parse(sc.nextLine());
                System.out.print("Status: "); String st = sc.nextLine();
                System.out.print("Nome Responsável: "); String res = sc.nextLine();
                System.out.print("CPF Responsável: "); String cr = sc.nextLine();
                System.out.print("Tel Responsável: "); String tr = sc.nextLine();

                Aluno novoAluno = new Aluno(0, n, c, e, t, d, mat, dMat, st, res, cr, tr);
                pessoaDao.save(novoAluno);
                alunoDao.save(novoAluno);
                System.out.println("Aluno cadastrado! ID: " + novoAluno.getId());
                break;

            case 2:
                System.out.println("--- LISTA DE ALUNOS ---");
                alunoDao.read().forEach(System.out::println);
                break;

            case 3:
                System.out.println("--- ATUALIZAR ALUNO ---");
                System.out.print("Informe o ID do Aluno: ");
                int idUp = Integer.parseInt(sc.nextLine());

                System.out.println("Atualizando dados acadêmicos...");
                System.out.print("Nova Matrícula: "); String nm = sc.nextLine();
                System.out.print("Nova Data Matrícula: "); LocalDate ndm = LocalDate.parse(sc.nextLine());
                System.out.print("Novo Status: "); String nst = sc.nextLine();
                System.out.print("Novo Responsável: "); String nr = sc.nextLine();
                System.out.print("CPF Resp: "); String ncr = sc.nextLine();
                System.out.print("Tel Resp: "); String ntr = sc.nextLine();

                Aluno aUp = new Aluno(idUp, null, null, null, null, null, nm, ndm, nst, nr, ncr, ntr);
                alunoDao.update(aUp);

                System.out.print("Deseja atualizar também nome/cpf/email? (S/N): ");
                if (sc.nextLine().equalsIgnoreCase("S")) {
                    System.out.print("Nome: "); String nn = sc.nextLine();
                    System.out.print("CPF: "); String nc = sc.nextLine();
                    System.out.print("Email: "); String ne = sc.nextLine();
                    System.out.print("Telefone: "); String nt = sc.nextLine();
                    System.out.print("Nascimento: "); LocalDate nd = LocalDate.parse(sc.nextLine());
                    pessoaDao.update(new Pessoa(idUp, nn, nc, ne, nt, nd));
                }
                System.out.println("Aluno atualizado!");
                break;

            case 4:
                System.out.print("ID para deletar: ");
                int idDel = Integer.parseInt(sc.nextLine());
                alunoDao.delete(idDel);
                pessoaDao.delete(idDel);
                System.out.println("Registro removido.");
                break;
        }
    }

    private static void processarProfessor(int op, Scanner sc) throws Exception {
        ProfessorDAO profDao = new ProfessorDAO();
        PessoaDAO pessoaDao = new PessoaDAO();

        switch (op) {
            case 1:
                System.out.println("--- NOVO PROFESSOR ---");
                System.out.print("Nome: "); String n = sc.nextLine();
                System.out.print("CPF: "); String c = sc.nextLine();
                System.out.print("Email: "); String e = sc.nextLine();
                System.out.print("Telefone: "); String t = sc.nextLine();
                System.out.print("Nascimento (YYYY-MM-DD): "); LocalDate d = LocalDate.parse(sc.nextLine());
                System.out.print("Formação: "); String form = sc.nextLine();
                System.out.print("Salário: "); double sal = Double.parseDouble(sc.nextLine());

                Professor novoProf = new Professor(0, n, c, e, t, d, form, sal);
                pessoaDao.save(novoProf);
                profDao.save(novoProf);
                break;

            case 2:
                System.out.println("--- LISTA DE PROFESSORES ---");
                profDao.read().forEach(System.out::println);
                break;

            case 3:
                System.out.println("--- ATUALIZAR PROFESSOR ---");
                System.out.print("ID do Professor: ");
                int idUp = Integer.parseInt(sc.nextLine());

                System.out.print("Nova Formação: "); String nf = sc.nextLine();
                System.out.print("Novo Salário: "); double ns = Double.parseDouble(sc.nextLine());

                Professor pUp = new Professor(idUp, null, null, null, null, null, nf, ns);
                profDao.update(pUp);

                System.out.print("Atualizar dados pessoais? (S/N): ");
                if (sc.nextLine().equalsIgnoreCase("S")) {
                    System.out.print("Nome: "); String pn = sc.nextLine();
                    System.out.print("CPF: "); String pc = sc.nextLine();
                    System.out.print("Email: "); String pe = sc.nextLine();
                    System.out.print("Telefone: "); String pt = sc.nextLine();
                    System.out.print("Nascimento: "); LocalDate pd = LocalDate.parse(sc.nextLine());
                    pessoaDao.update(new Pessoa(idUp, pn, pc, pe, pt, pd));
                }
                break;

            case 4:
                System.out.print("ID para deletar: ");
                int idDel = Integer.parseInt(sc.nextLine());
                profDao.delete(idDel);
                pessoaDao.delete(idDel);
                break;
        }
    }

    private static void processarNota(int op, Scanner sc) throws Exception {
        NotaDAO dao = new NotaDAO();
        switch (op) {
            case 1:
                System.out.println("--- LANÇAR NOTA ---");
                System.out.print("ID do Aluno: "); int idA = Integer.parseInt(sc.nextLine());
                System.out.print("ID da Disciplina: "); int idD = Integer.parseInt(sc.nextLine());

                Aluno al = new AlunoDAO().findById(idA);
                Disciplina ds = new DisciplinaDAO().findById(idD);

                if (al != null && ds != null) {
                    System.out.print("Valor da Nota: "); double v = Double.parseDouble(sc.nextLine());
                    System.out.print("Bimestre (1-4): "); int b = Integer.parseInt(sc.nextLine());
                    //O banco que vai gerar o id!
                    dao.save(new Nota(0, b, al, ds, v));
                    System.out.println("Nota lançada!");
                } else {
                    System.out.println("Erro: Aluno ou Disciplina não encontrados.");
                }
                break;

            case 2:
                System.out.println("--- BOLETIM ---");
                dao.read().forEach(System.out::println);
                break;

            case 3:
                System.out.println("--- ALTERAR NOTA/BIMESTRE ---");
                System.out.print("Informe o ID da Nota (o aluno e disciplina não serão alterados): ");
                int idN = Integer.parseInt(sc.nextLine());

                System.out.print("Novo Valor da Nota: ");
                double nv = Double.parseDouble(sc.nextLine());

                System.out.print("Corrigir Bimestre: ");
                int nb = Integer.parseInt(sc.nextLine());

                dao.update(new Nota(idN, nb, null, null, nv));
                System.out.println("Nota atualizada com sucesso!");
                break;

            case 4:
                System.out.print("ID da Nota para deletar: ");
                dao.delete(Integer.parseInt(sc.nextLine()));
                System.out.println("Nota removida.");
                break;
        }
    }

    private static void processarDisciplina(int op, Scanner sc) throws Exception {
        DisciplinaDAO dao = new DisciplinaDAO();
        switch (op) {
            case 1:
                System.out.println("--- NOVA DISCIPLINA ---");
                System.out.print("Nome: "); String nome = sc.nextLine();
                System.out.print("Carga Horária: "); int ch = Integer.parseInt(sc.nextLine());
                System.out.print("Ementa: "); String em = sc.nextLine();
                //O banco que vai gerar o id!
                dao.save(new Disciplina(0, nome, em, ch));
                break;

            case 2:
                System.out.println("--- QUADRO DE DISCIPLINAS ---");
                dao.read().forEach(System.out::println);
                break;

            case 3:
                System.out.println("--- ATUALIZAR DISCIPLINA ---");
                System.out.print("ID da Disciplina para alterar: ");
                int idD = Integer.parseInt(sc.nextLine());

                System.out.print("Novo Nome: "); String nNome = sc.nextLine();
                System.out.print("Nova Carga Horária: "); int nCh = Integer.parseInt(sc.nextLine());
                System.out.print("Nova Ementa: "); String nEm = sc.nextLine();

                dao.update(new Disciplina(idD, nNome, nEm, nCh));
                System.out.println("Disciplina atualizada!");
                break;

            case 4:
                System.out.print("ID para deletar: ");
                dao.delete(Integer.parseInt(sc.nextLine()));
                System.out.println("Disciplina removida.");
                break;
        }
    }
}