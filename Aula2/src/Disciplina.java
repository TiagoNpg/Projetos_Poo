import java.util.ArrayList;

public class Disciplina {
	
	private String nome;
	private String sigla;
	private int capacidade;
	
	private ArrayList<Inscricao> inscricoes = new ArrayList<>();
	
	public Disciplina(String nome, String sigla, int capacidade) {
		this.nome = nome;
		this.sigla = sigla;
		this.capacidade = capacidade;
	}
	
	public void inscrever(Aluno aluno) {
		
		if (inscricoes.size() < capacidade) {
			Inscricao insc = new Inscricao(aluno);
			inscricoes.add(insc);	
		}
		else
			System.err.println("O aluno " + aluno + " já não tem espaço em " + sigla + "...");
	}


	@Override
	public String toString() {
		
		String aux = nome + " (" + sigla + ") - cap: " + capacidade + "\n";
		
		for (Inscricao insc : inscricoes)
			aux += insc + "\n";
		
		return aux;
	}

	//2.3
	private void createDisciplina(int capacidade, String nomeDisciplina, ArrayList<Inscricao> inscricoes) {

	}

	private void Aluno (ArrayList<Inscricao> inscricoes) {
		for (Inscricao insc : inscricoes) {
			if(insc.getAluno().getNumero()
		}
	}



	private String createTag (String nomeDisciplina){
		String sigla = "";
		String [] tokens = nomeDisciplina.split(" ");
        for (String token : tokens) {
            sigla += token.charAt(0);
        }
		return sigla;

	}
	
	
	
}
