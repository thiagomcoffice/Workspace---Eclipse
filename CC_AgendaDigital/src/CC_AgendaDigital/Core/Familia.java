package CC_AgendaDigital.Core;

import java.util.ArrayList;

public class Familia {
	private String NomeDaFamilia;
	private ArrayList<Pessoa> ListaDePessoas;
	private int quantidadeDePessoas;

	public Familia() {
		NomeDaFamilia = "";
		ListaDePessoas = new ArrayList<Pessoa>();
		quantidadeDePessoas = 0;
	}

	public Familia(String NameOfFamily) {
		this.NomeDaFamilia = NameOfFamily;
		ListaDePessoas = new ArrayList<Pessoa>();
		quantidadeDePessoas = 0;
	}

	public boolean registrarPessoa(Pessoa pessoa) {
		ListaDePessoas.add(pessoa);
		if (ListaDePessoas.contains(pessoa)) {
			quantidadeDePessoas++;
			return true;
		}
		return false;
	}
	
	public int getQuantidadeDePessoas(){
		return quantidadeDePessoas;
	}

	public ArrayList<Pessoa> retornarListaDePessoas() {
		return ListaDePessoas;
	}

	public String getNomeDaFamilia() {
		return NomeDaFamilia;
	}

	public void setNomeDaFamilia(String NomeDaFamilia) {
		if (NomeDaFamilia != null) {
			this.NomeDaFamilia = NomeDaFamilia;
		}
	}

	public boolean isEmpty() {
		if(quantidadeDePessoas == 0){
			return true;
		}
		return false;
	}

}