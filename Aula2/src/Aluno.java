import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Aluno {

	private String nome;
	private int numero;
	
	public Aluno(String nome, int numero) {
		this.nome = nome;
		this.numero = numero;
	}

	public String getNome() {
		return nome;
	}

	public int getNumero() {
		return numero;
	}
	
	@Override
	public String toString() {
		return numero + ": " + nome;
	}

	public static ArrayList<Aluno> readAlunos(String path){
		File file = new File(path);
		ArrayList<Aluno> alunos = new ArrayList<>();
		try {
			Scanner sc = new Scanner(new File(path));
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				String[] tokens = line.split(" ", 2);
				int numero = Integer.parseInt(tokens[0]);
				String nome = tokens[1];

				Aluno aluno = new Aluno(nome, numero);
				alunos.add(aluno);
			}
			sc.close();
		}catch (FileNotFoundException e){
			e.printStackTrace();
		}

		return alunos;


	}

	public static void main(String[] args) {
		Aluno a = new Aluno("Aluno 1", 1);
		//erro de diretoria
		ArrayList<Aluno> alunos = Aluno.readAlunos("Estudantes.txt");
		System.out.println(alunos);
	}
	
	
	
	
}
