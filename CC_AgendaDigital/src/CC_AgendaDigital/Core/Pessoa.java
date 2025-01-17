package CC_AgendaDigital.Core;

import java.util.ArrayList;

import CC_AgendaDigital.DAO.SQLite;

public class Pessoa{

	private int PessoaId;
	private String Nome;
	private int Idade;
	private String DataNascimento;
	private ArrayList<Compromisso> Compromissos;

	public Pessoa(String Nome, String DataNascimento, int Idade) {
		//this.PessoaId = (SQLite.pessoasId(this));
		this.Nome = Nome;
		this.Idade = Idade;
		this.DataNascimento = DataNascimento;
		Compromissos = new ArrayList<Compromisso>();
	}

	public boolean adicionarCompromisso(Compromisso compromisso) {
		boolean addCompBool = false;
		Compromissos.add(compromisso);
		if (Compromissos.contains(compromisso)) {
			addCompBool = true;
		}
		return addCompBool;
	}

	public ArrayList<Compromisso> getCompromisso() {
		return Compromissos;
	}

	public int getQdeCompromissos(){
		return Compromissos.size();
	}
	
	public String getNome() {
		return Nome;
	}

	public void setNome(String Nome) {
		if (Nome != null || Nome != "") {
			this.Nome = Nome;
		}
	}

	public int getIdade() {
		return Idade;
	}
	
	public int getId(){
		return PessoaId;
	}

	public void setIdade(int idade) {
		if (idade > 5) {
			Idade = idade;
		}
	}

	public String getDataNascimento() {
		return DataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		if (dataNascimento != null) {
			DataNascimento = dataNascimento;
		}
	}

	public String toString() {
		PessoaId = SQLite.pessoasId(this);
		return "�" + (PessoaId)+ ".  " + Nome;
	}
}
