package com.guiPalma.desafio.core.enums;

public enum TipoLimiteEnum {
	
	CHEQUE_ESPECIAL(1, "Cheque Especial", "CE"),
	CARTAO_CREDITO(2, "Cartão de Credito","CC");
	
	private int cod;
	private String descricao;
	private String sigla;
	
	private TipoLimiteEnum(int cod, String descricao, String sigla) {
		this.cod = cod;
		this.descricao = descricao;
	}
	
	public int getCod() {
		return cod;
	}
	
	public String getDescricao () {
		return descricao;
	}
	
	
	  public void setCod(int cod) {
		this.cod = cod;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public static TipoLimiteEnum fromCodigo(int codigo) {
	        for (TipoLimiteEnum item : TipoLimiteEnum.values()) {
	            if (item.cod == codigo) {
	                return item;
	            }
	        }
	        return null;
	    }
	
	
	public static TipoPessoaEnum toEnum(Integer cod) {
		
		if (cod == null) {
			return null;
		}
		
		for (TipoPessoaEnum x : TipoPessoaEnum.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	


}
